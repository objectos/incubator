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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.smtp.mail.ClientName;
import br.com.objectos.smtp.mail.ForwardPath;
import br.com.objectos.smtp.mail.ReversePath;
import org.testng.annotations.Test;

public class CommandFacadeTest extends AbstractCommandFacadeTest {

  @Test
  public void testCase00() {
    ascii.startSession();

    asciiRead(line("ehlo 127.0.0.1"));

    testAscii(
        new int[] {
            ByteCodes.COMMAND,
            Command.EHLO.getCode(),
            ByteCodes.STRING, 5, 9,
            ByteCodes.EOL
        },
        new TestingCommandVisitor() {
          ClientName client;

          @Override
          public final void visitEHLO(ClientName client) {
            this.client = client;
          }

          @Override
          final void testImpl() {
            assertEquals(client, ClientName.of("127.0.0.1"));
          }
        }
    );

    asciiRead(line("MAIL FROM:<tc00-sender@example.com>"));

    testAscii(
        new int[] {
            ByteCodes.COMMAND,
            Command.MAIL.getCode(),
            ByteCodes.PATH, 27, 23,
            ByteCodes.EOL
        },
        new TestingCommandVisitor() {
          ReversePath path;

          @Override
          public final void visitMAIL(ReversePath reversePath) {
            path = reversePath;
          }

          @Override
          final void testImpl() {
            assertEquals(path, new ReversePath("tc00-sender@example.com"));
          }
        }
    );

    asciiRead(line("RCPT TO:<tc00-receiver@example.com>"));

    testAscii(
        new int[] {
            ByteCodes.COMMAND,
            Command.RCPT.getCode(),
            ByteCodes.PATH, 62, 25,
            ByteCodes.EOL
        },
        new TestingCommandVisitor() {
          ForwardPath path;

          @Override
          public final void visitRCPT(ForwardPath forwardPath) {
            path = forwardPath;
          }

          @Override
          final void testImpl() {
            assertEquals(path, new ForwardPath("tc00-receiver@example.com"));
          }
        }
    );

    asciiRead(line("DATA"));

    testAscii(
        new int[] {
            ByteCodes.COMMAND,
            Command.DATA.getCode(),
            ByteCodes.EOL
        },
        new TestingCommandVisitor() {
          boolean data;

          @Override
          public final void visitDATA() {
            data = true;
          }

          @Override
          final void testImpl() {
            assertTrue(data);
          }
        }
    );

    asciiRead(line("QUIT"));

    testAscii(
        new int[] {
            ByteCodes.COMMAND,
            Command.QUIT.getCode(),
            ByteCodes.EOL
        },
        new TestingCommandVisitor() {
          boolean quit;

          @Override
          public final void visitQUIT() {
            quit = true;
          }

          @Override
          final void testImpl() {
            assertTrue(quit);
          }
        }
    );
  }

}