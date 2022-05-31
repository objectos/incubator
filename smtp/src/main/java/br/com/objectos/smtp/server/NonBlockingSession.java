/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.objectos.smtp.server;

import br.com.objectos.smtp.command.CommandVisitor;
import br.com.objectos.smtp.mail.ClientName;
import br.com.objectos.smtp.mail.ForwardPath;
import br.com.objectos.smtp.mail.ReversePath;
import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.ByteChannel;
import objectos.lang.Check;

// @NotThreadSafe
final class NonBlockingSession implements Session {

  private Closeable client;

  private boolean keepExecuting;
  private final SessionLogger logger;
  private final RequestFacade requestFacade;

  private final ResponseFacade responseFacade;

  private SessionState state;
  private final TransactionFacade transactionFacade;
  private final TransactionHandler transactionHandler = new TransactionHandler();

  public NonBlockingSession(SessionLogger logger,
                            RequestFacade requestFacade,
                            ResponseFacade responseFacade,
                            TransactionFacade transactionFacade) {
    this.logger = Check.notNull(logger, "logger == null");
    this.requestFacade = Check.notNull(requestFacade, "requestFacade == null");
    this.responseFacade = Check.notNull(responseFacade, "responseFacade == null");
    this.transactionFacade = Check.notNull(transactionFacade, "transactionFacade == null");
  }

  @Override
  public final void execute() {
    try {
      keepExecuting = true;

      while (keepExecuting) {
        state = state.execute(this);
      }
    } catch (Exception uncaught) {
      logger.logSessionException(uncaught, "Unexpected error");

      executeEndSession();
    }
  }

  @Override
  public final boolean isActive() {
    return state.isActive();
  }

  @Override
  public final void startSession(ByteChannel client) {
    this.client = client;
    state = SessionState.START;

    requestFacade.startSession(client);
    responseFacade.startSession(client);
    transactionHandler.startSession();
  }

  private SessionState executeEndSession() {
    try {
      client.close();
    } catch (IOException e) {
      logger.logSessionException(e, "Failed to close connection");
    }

    return executeNonBlocking(SessionState.END);
  }

  private SessionState executeNonBlocking(SessionState caller) {
    keepExecuting = false;

    return caller;
  }

  private SessionState executeOnException(IOException e, String message) {
    logger.logSessionException(e, message);

    return SessionState.END_SESSION;
  }

  private SessionState executeOpeningMessage() {
    try {
      responseFacade.executeOpeningMessage();

      return SessionState.OPENING_MESSAGE_EXECUTED;
    } catch (IOException e) {
      return executeOnException(e, "Failed to write response.");
    }
  }

  private SessionState executePersistData() {
    try {
      transactionHandler.writeData(requestFacade);

      return SessionState.PROCESS_DATA;
    } catch (IOException e) {
      return executeOnException(e, "Failed to persist data.");
    }
  }

  private SessionState executeProcessCommand() {
    requestFacade.consume(transactionHandler);

    return transactionHandler.processCommandResult;
  }

  private SessionState executeProcessData() {
    try {
      ProcessingResult result;
      result = transactionHandler.processData();

      switch (result) {
        case COMPLETE:
          responseFacade.executeProcessDataComplete();

          transactionHandler.commit();

          return SessionState.PROCESS_DATA_EXECUTED;
        default:
          throw new UnsupportedOperationException("Implement me " + result);
      }
    } catch (IOException e) {
      return executeOnException(e, "Failed to process data.");
    }
  }

  private SessionState executeReadCommand(SessionState caller) {
    try {
      boolean somethingWasRead;
      somethingWasRead = requestFacade.readCommand();

      if (somethingWasRead) {
        return SessionState.PROCESS_COMMAND;
      }

      return executeNonBlocking(caller);
    } catch (IOException e) {
      return executeOnException(e, "Failed to read command.");
    }
  }

  private SessionState executeReadCommandOrWrite(SessionState caller) {
    boolean somethingWasRead;

    try {
      somethingWasRead = requestFacade.readCommand();
    } catch (IOException e) {
      return executeOnException(e, "Failed to read command.");
    }

    if (somethingWasRead) {
      return SessionState.PROCESS_COMMAND;
    }

    boolean somethingWasWritten;

    try {
      somethingWasWritten = responseFacade.write();
    } catch (IOException e) {
      return executeOnException(e, "Failed to write response.");
    }

    if (somethingWasWritten) {
      return executeReadCommand(caller);
    }

    return executeNonBlocking(caller);
  }

  private SessionState executeReadData(SessionState caller) {
    try {
      boolean somethingWasRead;
      somethingWasRead = requestFacade.readData();

      if (somethingWasRead) {
        return SessionState.PERSIST_DATA;
      }

      return executeNonBlocking(caller);
    } catch (IOException e) {
      return executeOnException(e, "Failed to read data.");
    }
  }

  private SessionState executeWrite(SessionState caller, SessionState nextState) {
    try {
      boolean somethingWasWritten;
      somethingWasWritten = responseFacade.write();

      if (somethingWasWritten) {
        return nextState;
      }

      return executeNonBlocking(caller);
    } catch (IOException e) {
      return executeOnException(e, "Failed to write response.");
    }
  }

  private enum SessionState {

    COMMAND_DATA {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeWrite(this, READ_DATA);
      }
    },

    COMMAND_EHLO {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeWrite(this, COMMAND_EHLO_EXECUTED);
      }
    },

    COMMAND_EHLO_EXECUTED {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeReadCommand(this);
      }
    },

    COMMAND_ENVELOPE {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeReadCommandOrWrite(this);
      }
    },

    COMMAND_QUIT {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeWrite(this, END_SESSION);
      }
    },

    END {
      @Override
      final boolean isActive() {
        return false;
      }
    },

    END_SESSION {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeEndSession();
      }
    },

    OPENING_MESSAGE {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeOpeningMessage();
      }
    },

    OPENING_MESSAGE_EXECUTED {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeReadCommand(this);
      }
    },

    PERSIST_DATA {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executePersistData();
      }
    },

    PROCESS_COMMAND {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeProcessCommand();
      }
    },

    PROCESS_DATA {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeProcessData();
      }
    },

    PROCESS_DATA_EXECUTED {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeWrite(this, COMMAND_EHLO_EXECUTED);
      }
    },

    READ_DATA {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return session.executeReadData(this);
      }
    },

    START {
      @Override
      final SessionState execute(NonBlockingSession session) {
        return SessionState.OPENING_MESSAGE;
      }
    };

    SessionState execute(NonBlockingSession session) {
      throw new UnsupportedOperationException(name());
    }

    boolean isActive() {
      return true;
    }

  }

  private class TransactionHandler implements CommandVisitor {

    ClientName clientName;

    SessionState processCommandResult;

    TransactionState state;

    Transaction transaction;

    @Override
    public final void visitDATA() {
      state = state.visitDATA(this);
    }

    @Override
    public final void visitEHLO(ClientName clientName) {
      state = state.visitEHLO(this, clientName);
    }

    @Override
    public final void visitMAIL(ReversePath reversePath) {
      state = state.visitMAIL(this, reversePath);
    }

    @Override
    public final void visitQUIT() {
      state = state.visitQUIT(this);
    }

    @Override
    public final void visitRCPT(ForwardPath forwardPath) {
      state = state.visitRCPT(this, forwardPath);
    }

    final void commit() {
      transaction.commit();

      transaction = null;

      state = TransactionState.IDLE;
    }

    final ProcessingResult processData() throws IOException {
      return transaction.processData();
    }

    final void startSession() {
      state = TransactionState.IDLE;
    }

    final void writeData(RequestFacade requestFacade) throws IOException {
      requestFacade.writeData(transaction);
    }

    private TransactionState executeDATA() {
      responseFacade.visitDATA();

      processCommandResult = SessionState.COMMAND_DATA;

      return TransactionState.DATA;
    }

    private TransactionState executeEHLO(ClientName clientName) {
      this.clientName = clientName;

      responseFacade.visitEHLO(clientName);

      processCommandResult = SessionState.COMMAND_EHLO;

      return TransactionState.IDLE;
    }

    private TransactionState executeException(IOException e, String message) {
      transaction.rollback();

      logger.logSessionException(e, message);

      processCommandResult = SessionState.END_SESSION;

      return TransactionState.IDLE;
    }

    private TransactionState executeMAIL(ReversePath reversePath) {
      try {
        startTransactionIfNecessary();
      } catch (IOException e) {
        return executeException(e, "Failed to start transaction");
      }

      transaction.setClientName(clientName);

      transaction.setReversePath(reversePath);

      responseFacade.visitMAIL(reversePath);

      processCommandResult = SessionState.COMMAND_ENVELOPE;

      return TransactionState.MAIL;
    }

    private TransactionState executeQUIT() {
      responseFacade.visitQUIT();

      processCommandResult = SessionState.COMMAND_QUIT;

      return TransactionState.IDLE;
    }

    private TransactionState executeRCPT(ForwardPath forwardPath, TransactionState nextState) {
      try {
        transaction.addForwardPath(forwardPath);
      } catch (IOException e) {
        return executeException(e, "Failed to process forward path");
      }

      responseFacade.visitRCPT(forwardPath);

      processCommandResult = SessionState.COMMAND_ENVELOPE;

      return nextState;
    }

    private void startTransactionIfNecessary() throws IOException {
      if (transaction == null) {
        transaction = transactionFacade.startTransaction();
      }
    }

  }

  private enum TransactionState {

    DATA,

    IDLE,

    MAIL,

    MAIL_RCPT;

    final TransactionState visitDATA(TransactionHandler trx) {
      switch (this) {
        case MAIL_RCPT:
          return trx.executeDATA();
        default:
          throw new UnsupportedOperationException(name());
      }
    }

    final TransactionState visitEHLO(TransactionHandler trx, ClientName clientName) {
      switch (this) {
        case IDLE:
          return trx.executeEHLO(clientName);
        default:
          throw new UnsupportedOperationException(name());
      }
    }

    final TransactionState visitMAIL(TransactionHandler trx, ReversePath reversePath) {
      switch (this) {
        case IDLE:
          return trx.executeMAIL(reversePath);
        default:
          throw new UnsupportedOperationException(name());
      }
    }

    final TransactionState visitQUIT(TransactionHandler trx) {
      switch (this) {
        case IDLE:
          return trx.executeQUIT();
        default:
          throw new UnsupportedOperationException(name());
      }
    }

    final TransactionState visitRCPT(TransactionHandler trx, ForwardPath forwardPath) {
      switch (this) {
        case MAIL:
          return trx.executeRCPT(forwardPath, MAIL_RCPT);
        default:
          throw new UnsupportedOperationException(name());
      }
    }

  }

}
