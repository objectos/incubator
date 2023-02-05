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
package br.com.objectos.office.internal;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.office.OfficeServer;
import br.com.objectos.office.ServerStartException;
import br.com.objectos.office.writer.Format;
import br.com.objectos.office.writer.WriterScript;
import java.io.IOException;

public class SingleProcessServer implements OfficeServer {

  private final NativeProcess process;

  private SingleProcessServer(NativeProcess process) {
    this.process = process;
  }

  public static OfficeServer create() throws IOException {
    NativeProcess process;
    process = NativeProcess.create();

    return new SingleProcessServer(process);
  }

  @Override
  public final RegularFile
      createWriterDocument(WriterScript script, Format format) throws IOException {
    synchronized (process) {
      Desktop desktop;
      desktop = process.getDesktop();

      WriterDocument document;
      document = desktop.createWriterDocument();

      try {
        script.acceptWriterDsl(document);

        Directory workingDirectory;
        workingDirectory = getWorkingDirectory();

        return document.saveTo(workingDirectory, format);
      } finally {
        document.close();
      }
    }
  }

  @Override
  public final RegularFile editWriterTemplate(
      RegularFile template, WriterScript script, Format format) throws IOException {
    synchronized (process) {
      Desktop desktop;
      desktop = process.getDesktop();

      Document document;
      document = desktop.loadDocument(template);

      try {
        document.executeWriterScript(script);

        Directory workingDirectory;
        workingDirectory = getWorkingDirectory();

        return document.saveTo(workingDirectory, format);
      } finally {
        document.close();
      }
    }
  }

  @Override
  public final String extractText(RegularFile file) throws IOException {
    synchronized (process) {
      Desktop desktop;
      desktop = process.getDesktop();

      Document document;
      document = desktop.loadDocument(file);

      try {
        return document.extractText();
      } finally {
        document.close();
      }
    }
  }

  @Override
  public final void start() throws ServerStartException {
    process.start();
  }

  @Override
  public final void stop() {
    process.stop();
  }

  private Directory getWorkingDirectory() {
    return process.getWorkingDirectory();
  }

}
