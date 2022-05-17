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
package br.com.objectos.smtp.command;

import br.com.objectos.smtp.mail.ClientName;
import br.com.objectos.smtp.mail.ForwardPath;
import br.com.objectos.smtp.mail.ReversePath;
import br.com.objectos.smtp.mail.ReversePathException;
import br.com.objectos.smtp.mail.ReversePathFacade;
import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import objectos.lang.Checks;

public class CommandFacade {

  private int[] codes;

  private int codesIndex;
  private final CommandInterpreter commandInterpreter = new ThisInterpreter();

  private final CommandParser commandParser;
  private CommandVisitor commandVisitor;

  private final ReversePathFacade reversePathFacade;

  public CommandFacade(CommandParser commandParser, ReversePathFacade reversePathFacade) {
    this.commandParser = Checks.checkNotNull(commandParser, "commandParser == null");
    this.reversePathFacade = Checks.checkNotNull(reversePathFacade, "reversePathFacade == null");
  }

  public final void consume(CommandVisitor visitor) {
    consume0();
    consume1(visitor);
  }

  public final boolean hasCommand() {
    return commandParser.hasNext();
  }

  public final boolean readCommand(ReadableByteChannel client) throws IOException {
    return commandParser.readCommand(client);
  }

  public final void startSession() {
    commandParser.startSession();
  }

  final int[] consume0() {
    codesIndex = 0;
    return codes = commandParser.consume();
  }

  final void consume1(CommandVisitor visitor) {
    commandVisitor = Checks.checkNotNull(visitor, "visitor == null");

    while (hasCode()) {
      int commandByteCode;
      commandByteCode = nextCode();

      if (commandByteCode != ByteCodes.COMMAND) {
        throw new UnsupportedOperationException("Implement me");
      }

      int commandOrdinal;
      commandOrdinal = nextCode();

      Command command;
      command = Command.getByCode(commandOrdinal);

      command.execute(commandInterpreter);
    }
  }

  private boolean hasCode() {
    return codesIndex < codes.length;
  }

  private int nextCode() {
    return codes[codesIndex++];
  }

  private class ThisInterpreter implements CommandInterpreter {

    @Override
    public final ForwardPath getForwardPath(String argument) {
      return new ForwardPath(argument);
    }

    @Override
    public final ReversePath getReversePath(String argument) {
      try {
        return reversePathFacade.getReversePath(argument);
      } catch (ReversePathException e) {
        throw new UnsupportedOperationException("Implement me");
      }
    }

    @Override
    public final String getString() {
      int start;
      start = nextCode();

      int length;
      length = nextCode();

      return commandParser.getString(start, length);
    }

    @Override
    public final boolean hasCode() {
      return CommandFacade.this.hasCode();
    }

    @Override
    public final int nextCode() {
      return CommandFacade.this.nextCode();
    }

    @Override
    public final void visitDATA() {
      commandVisitor.visitDATA();
    }

    @Override
    public final void visitEHLO(ClientName clientName) {
      commandVisitor.visitEHLO(clientName);
    }

    @Override
    public final void visitMAIL(ReversePath reversePath) {
      commandVisitor.visitMAIL(reversePath);
    }

    @Override
    public final void visitQUIT() {
      commandVisitor.visitQUIT();
    }

    @Override
    public final void visitRCPT(ForwardPath forwardPath) {
      commandVisitor.visitRCPT(forwardPath);
    }

  }

}
