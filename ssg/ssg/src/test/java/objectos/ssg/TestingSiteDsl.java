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

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.Template;
import br.com.objectos.http.media.MediaType;
import java.util.IdentityHashMap;
import java.util.Map;

class TestingSiteDsl extends AbstractSiteDsl {

  private final Map<Template, String> templatePaths = new IdentityHashMap<>();

  @Override
  public void addResource(String fullPath, InputStreamSource resource) {}

  @Override
  public void addResource(String fullPath, InputStreamSource resource, MediaType mediaType) {}

  @Override
  public void addStyleSheet(String fullPath, StyleSheet styleSheet) {}

  @Override
  public final void addTemplate(String fullPath, Template template) {
    templatePaths.put(template, fullPath);
  }

  public final void clear() {
    templatePaths.clear();
  }

  @Override
  public final String getBaseHref() {
    return "";
  }

  public final String path(SitePage page) {
    return templatePaths.get(page);
  }

}