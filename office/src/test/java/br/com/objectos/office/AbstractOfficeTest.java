/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.office;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.office.internal.NativeProcess;
import br.com.objectos.office.writer.WriterService;
import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractOfficeTest {

  private static OfficeServer server;

  @BeforeSuite
  public static void startServer() throws IOException, ServerStartException {
    server = Office.newSingleProcessServer();
    server.start();
  }

  @AfterSuite(alwaysRun = true)
  public static void stopServer() {
    if (server != null) {
      server.stop();
    }
  }

  protected final Directory createTempDir() throws IOException {
    return TmpDir.create();
  }

  protected final void destroy(NativeProcess process) {
    if (process == null) {
      return;
    }

    process.stop();
  }

  protected final WriterService getWriterService() {
    return server;
  }

}