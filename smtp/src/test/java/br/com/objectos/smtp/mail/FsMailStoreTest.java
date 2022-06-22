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
package br.com.objectos.smtp.mail;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.smtp.ConfigurationException;
import br.com.objectos.smtp.MailBox;
import br.com.objectos.smtp.MailObject;
import br.com.objectos.smtp.MailObject.Contents;
import br.com.objectos.smtp.TestCase;
import br.com.objectos.smtp.server.ProcessingResult;
import br.com.objectos.smtp.server.Transaction;
import java.io.IOException;
import java.nio.ByteBuffer;
import objectos.util.GrowableList;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FsMailStoreTest {

  private GrowableList<Directory> directories;

  @BeforeClass
  public void _setUp() {
    directories = new GrowableList<>();
  }

  @AfterClass(alwaysRun = true)
  public void _tearDown() {
    for (Directory d : directories) {
      deleteContents(d);
      delete(d);
    }
  }

  @Test
  public void testCase00() throws IOException, ConfigurationException {
    TestCase tc;
    tc = TestCase.TC00;

    Directory root;
    root = createTempDir();

    assertTrue(root.isEmpty());

    FsMailStore store;
    store = FsMailStore.create(root);

    MailBox mailBox;
    mailBox = store.getMailBox("tc00-receiver");

    assertEquals(mailBox.size(), 0);

    Transaction trx;
    trx = store.startTransaction();

    ReversePath from;
    from = new ReversePath("tc00-sender@example.com");

    trx.setReversePath(from);

    ForwardPath to;
    to = new ForwardPath("tc00-receiver@example.com");

    trx.addForwardPath(to);

    byte[] bytes;
    bytes = tc.getBodyBytes(Charsets.ASCII);

    ByteBuffer byteBuffer;
    byteBuffer = ByteBuffer.wrap(bytes);

    trx.writeData(byteBuffer);

    ProcessingResult result;
    result = trx.processData();

    assertEquals(result, ProcessingResult.COMPLETE);

    trx.commit();

    assertFalse(root.isEmpty());

    assertEquals(mailBox.size(), 1);

    MailObject obj0;
    obj0 = mailBox.get(0);

    Contents c0;
    c0 = obj0.getContents();

    assertEquals(c0.readAllBytes(), bytes);
  }

  private Directory createTempDir() {
    try {
      Directory tempDir;
      tempDir = TmpDir.create();

      directories.add(tempDir);

      return tempDir;
    } catch (IOException e) {
      Assert.fail("IOException", e);
      return null;
    }
  }

  private void delete(Directory directory) {
    try {
      directory.delete();
    } catch (IOException e) {
    }
  }

  private void deleteContents(Directory directory) {
    try {
      directory.deleteContents();
    } catch (IOException e) {
    }
  }

}
