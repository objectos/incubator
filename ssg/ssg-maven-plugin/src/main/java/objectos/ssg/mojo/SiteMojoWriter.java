/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg.mojo;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Write;
import br.com.objectos.core.object.Checks;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.path.Location;
import java.io.IOException;
import objectos.ssg.SiteWriter;

final class SiteMojoWriter implements SiteWriter {

  private final Directory target;

  SiteMojoWriter(Directory target) {
    this.target = Checks.checkNotNull(target, "target == null");
  }

  public final void writeBytes(
      String path, MediaType mediaType, byte[] contents) throws IOException {
    Location location;
    location = Location.parse(path);

    RegularFile file;
    file = location.getOrCreateRegularFile(target);

    file.truncate();

    Write.byteArray(file, contents);
  }

  public final void writeString(
      String path, MediaType mediaType, String contents) throws IOException {
    byte[] utf8;
    utf8 = contents.getBytes(Charsets.utf8());

    writeBytes(path, mediaType, utf8);
  }

}
