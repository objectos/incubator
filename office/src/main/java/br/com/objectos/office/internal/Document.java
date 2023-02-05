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
import br.com.objectos.office.writer.Format;
import br.com.objectos.office.writer.WriterScript;
import com.sun.star.lang.IllegalArgumentException;
import java.io.IOException;

abstract class Document {

  protected Document() {}

  public abstract void close();

  public abstract String extractText();

  void executeWriterScript(WriterScript script) {
    throw new IllegalArgumentException("not a WriterDocument");
  }

  RegularFile saveTo(Directory directory, Format format) throws IOException {
    throw new IllegalArgumentException("not a WriterDocument");
  }

}