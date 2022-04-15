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
package br.com.objectos.be.site;

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.Template;
import br.com.objectos.http.media.MediaType;

final class TestingSiteDsl extends AbstractSiteDsl {

  @Override
  public void addResource(String fullPath, InputStreamSource resource) {}

  @Override
  public void addResource(String fullPath, InputStreamSource resource, MediaType mediaType) {}

  @Override
  public void addStyleSheet(String fullPath, StyleSheet styleSheet) {}

  @Override
  public void addTemplate(String fullPath, Template template) {}

  @Override
  public final String getBaseHref() {
    return "";
  }

}