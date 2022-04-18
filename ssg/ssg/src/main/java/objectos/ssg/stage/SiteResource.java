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
package objectos.ssg.stage;

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.media.MediaTypes;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import objectos.ssg.SiteArtifact;

public record SiteResource(String href, URL url, MediaType mediaType)
    implements
    InputStreamSource,
    SiteArtifact {

  public SiteResource {
    if (mediaType == null) {
      mediaType = MediaTypes.ofFileName(href);
    }
  }

  @Override
  public final InputStream openInputStream() throws IOException {
    return url.openStream();
  }

  @Override
  public final void generate(SiteArtifact.Generator generator) {
    generator.generateResource(this);
  }

}