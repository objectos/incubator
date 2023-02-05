/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.smtp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.smtp.MailObject.Contents;
import br.com.objectos.smtp.mail.Charsets;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmtpTest extends AbstractSmtpTest {

  @Test
  public void testCase00() {
    TestCase tc = TestCase.TC00;

    ClientSession session;

    MailBox box;
    box = null;

    try {
      session = client.connect(loopback25);
    } catch (IOException e) {
      Assert.fail("IOException", e);
      return;
    }

    try {
      sendAndReceiveLoop(session);

      assertTrue(session.hasResponseCode(220));

      session.bufferLine("EHLO 127.0.0.1");

      sendAndReceiveLoop(session);

      assertTrue(session.hasResponseCode(250));

      session.bufferLine("MAIL FROM:<tc00-sender@example.com>");

      sendAndReceiveLoop(session);

      assertTrue(session.hasResponseCode(250));

      session.bufferLine("RCPT TO:<tc00-receiver@example.com>");

      sendAndReceiveLoop(session);

      assertTrue(session.hasResponseCode(250));

      session.bufferLine("DATA");

      sendAndReceiveLoop(session);

      assertTrue(session.hasResponseCode(354));

      session.buffer(tc.getBody());

      sendAndReceiveLoop(session);

      assertTrue(session.hasResponseCode(250));

      session.bufferLine("QUIT");

      sendAndReceiveLoop(session);

      assertTrue(session.hasResponseCode(221));

      box = getMailBox("tc00-receiver@example.com");

      assertEquals(box.size(), 1);

      MailObject m0;
      m0 = box.get(0);

      Contents c0;
      c0 = m0.getContents();

      assertEquals(c0.readAllBytes(), tc.getBodyBytes(Charsets.ASCII));
    } catch (IOException e) {
      Assert.fail("IOException", e);
      return;
    } finally {
      close(session);
      close(box);
    }
  }

  private void sendAndReceiveLoop(ClientSession session) {
    client.executeOne(); // send
    server.executeOne(); // process
    client.executeOne(); // receive
  }

  // 01 sender e-mail incorrect syntax
  // 02 rcpt to e-mail incorrect syntax
  // 03 rcpt unknown
  // 04 attachment size greater than maximum allowed

}
