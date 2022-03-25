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

import br.com.objectos.core.io.Read;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.smtp.MailObject;
import java.io.IOException;

final class FsMailObject implements MailObject {

  private Contents contents;
  private final RegularFile dataFile;

  @SuppressWarnings("unused")
  private final RegularFile metaFile;

  FsMailObject(RegularFile metaFile, RegularFile dataFile) {
    this.metaFile = metaFile;
    this.dataFile = dataFile;
  }

  @Override
  public final Contents getContents() {
    if (contents == null) {
      contents = new ThisContents(dataFile);
    }

    return contents;
  }

  @Override
  public final Envelope getEnvelope() {
    throw new UnsupportedOperationException("Implement me");
  }

  private class ThisContents implements Contents {

    private final RegularFile file;

    ThisContents(RegularFile file) {
      this.file = file;
    }

    @Override
    public final byte[] readAllBytes() throws IOException {
      return Read.byteArray(file);
    }

  }

}
