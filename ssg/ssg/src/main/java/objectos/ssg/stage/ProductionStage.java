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

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Write;
import br.com.objectos.core.object.Checks;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.http.path.Location;
import java.io.IOException;
import java.io.UncheckedIOException;
import objectos.ssg.SitePage;
import objectos.ssg.SiteStyleSheet;
import objectos.ssg.Stage;

public class ProductionStage extends Stage {

  private final String baseHref;

  private final Directory target;

  public ProductionStage(String baseHref, Directory target) {
    this.baseHref = Checks.checkNotNull(baseHref, "baseHref == null");

    this.target = Checks.checkNotNull(target, "target == null");
  }

  @Override
  public final String getBaseHref() {
    return baseHref;
  }

  @Override
  public final void renderSitePage(String fullPath, SitePage page) {
    write(fullPath, page.printMinified());
  }

  @Override
  public final void renderSiteResource(SiteResource resource) {
    try {
      byte[] data;
      data = Read.byteArray(resource);

      String fullPath = resource.path();

      Location location;
      location = Location.parse(fullPath);

      RegularFile dest;
      dest = location.getOrCreateRegularFile(target);

      dest.truncate();

      Write.byteArray(dest, data);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public final void renderSiteStyleSheet(String fullPath, SiteStyleSheet sheet) {
    write(fullPath, sheet.printMinified());
  }

  private void write(String fullPath, String text) {
    try {
      Location location;
      location = Location.parse(fullPath);

      RegularFile file;
      file = location.getOrCreateRegularFile(target);

      file.truncate();

      Write.string(file, Charsets.utf8(), text);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
