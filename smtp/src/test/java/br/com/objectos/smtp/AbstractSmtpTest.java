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
package br.com.objectos.smtp;

import static br.com.objectos.smtp.Smtp.listenSmtp;
import static br.com.objectos.smtp.Smtp.logger;
import static br.com.objectos.smtp.Smtp.loopback;
import static br.com.objectos.smtp.Smtp.server;

import br.com.objectos.comuns.net.SocketChannelFactory;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import java.io.Closeable;
import java.io.IOException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import objectos.lang.NoOpNoteSink;
import objectos.lang.NoteSink;
import objectos.util.UnmodifiableList;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractSmtpTest {

  static Client client;

  static SocketChannelFactory loopback25;
  static Server server;
  private static MailStore store;

  private Directory directory;

  @BeforeSuite
  public void _setUp() throws ConfigurationException, IOException {
    directory = TmpDir.create();

    store = Smtp.newFileSystemMailStore(directory);

    NoteSink noopLogger;
    noopLogger = NoOpNoteSink.getInstance();

    loopback25 = loopback(4025);

    client = Smtp.client(
      logger(noopLogger)
    );

    server = server(
      logger(noopLogger),
      listenSmtp(loopback25),
      store
    );
  }

  @AfterSuite(alwaysRun = true)
  public void _tearDown() {
    if (directory != null) {
      try {
        directory.deleteContents();
      } catch (IOException e) {
      }

      try {
        directory.delete();
      } catch (IOException e) {
      }
    }

    client.close();

    server.close();
  }

  final void close(Closeable c) {
    if (c != null) {
      try {
        c.close();
      } catch (IOException e) {
      }
    }
  }

  final MailBox getMailBox(String address) throws IOException {
    int index;
    index = address.indexOf('@');

    String user;
    user = address.substring(0, index);

    return store.getMailBox(user);
  }

  final MimeMessage getSmtpMimeMessage() {
    return new MimeMessage((Session) null);
  }

  final String lines(String... lines) {
    UnmodifiableList<String> list;
    list = UnmodifiableList.copyOf(lines);

    return list.join("\r\n");
  }

}