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

import br.com.objectos.smtp.command.CommandFacade;
import br.com.objectos.smtp.command.CommandVisitor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import objectos.lang.Checks;

public class RequestFacade {

  private final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(8 * 1024);

  private ReadableByteChannel client;

  private final CommandFacade commandFacade;

  public RequestFacade(CommandFacade commandFacade) {
    this.commandFacade = Checks.checkNotNull(commandFacade, "commandFacade == null");
  }

  public final void consume(CommandVisitor visitor) {
    commandFacade.consume(visitor);
  }

  public final boolean readCommand() throws IOException {
    return commandFacade.readCommand(client);
  }

  public final boolean readData() throws IOException {
    int count;
    count = client.read(byteBuffer);

    return count > 0;
  }

  public final void startSession(ReadableByteChannel client) {
    this.client = client;

    commandFacade.startSession();
  }

  public final void writeData(Transaction transaction) throws IOException {
    byteBuffer.flip();

    transaction.writeData(byteBuffer);

    byteBuffer.compact();
  }

}
