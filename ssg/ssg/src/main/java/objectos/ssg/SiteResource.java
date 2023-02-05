/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
package objectos.ssg;

import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.media.MediaTypes;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

record SiteResource(String path, URL url, MediaType mediaType)
    implements
    SitePath,
    SiteWriteable {

  public SiteResource {
    if (mediaType == null) {
      mediaType = MediaTypes.ofFileName(path);
    }
  }

  @Override
  public final void writeTo(SiteWriter writer) throws IOException {
    try (InputStream in = url.openStream()) {
      byte[] bytes;
      bytes = in.readAllBytes();

      writer.writeBytes(path, mediaType, bytes);
    }
  }

}