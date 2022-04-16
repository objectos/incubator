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
package objectos.ssg;

import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.Template;
import br.com.objectos.http.media.MediaType;
import java.net.URL;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import objectos.ssg.stage.SiteResource;

class TestingSiteDsl extends AbstractSiteDsl {

  private final Map<String, MediaType> mediaTypes = new HashMap<>();

  private final Map<String, URL> resources = new HashMap<>();

  private final Map<StyleSheet, String> sheetPaths = new IdentityHashMap<>();

  private final Map<Template, String> templatePaths = new IdentityHashMap<>();

  public final void clear() {
    mediaTypes.clear();

    resources.clear();

    sheetPaths.clear();

    templatePaths.clear();
  }

  @Override
  public final String getBaseHref() {
    return "";
  }

  public final MediaType mediaType(String path) {
    return mediaTypes.get(path);
  }

  @Override
  public void renderSitePage(String fullPath, SitePage page) {
    templatePaths.put(page, fullPath);
  }

  @Override
  public final void renderSiteResource(SiteResource resource) {
    String fullPath;
    fullPath = resource.href();

    resources.put(fullPath, resource.url());

    MediaType mediaType;
    mediaType = resource.mediaType();

    mediaTypes.put(fullPath, mediaType);
  }

  @Override
  public final void renderSiteStyleSheet(String fullPath, SiteStyleSheet sheet) {
    sheetPaths.put(sheet, fullPath);
  }

  public final URL resource(String path) {
    return resources.get(path);
  }

  public final String styleSheetPath(SiteStyleSheet sheet) {
    return sheetPaths.get(sheet);
  }

  public final String templatePath(SitePage page) {
    return templatePaths.get(page);
  }

}