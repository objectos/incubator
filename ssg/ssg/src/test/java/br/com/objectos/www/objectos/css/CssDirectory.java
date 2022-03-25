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
package br.com.objectos.www.objectos.css;

import br.com.objectos.be.site.SiteDirectory;
import br.com.objectos.be.site.SiteStyleSheet;

public final class CssDirectory extends SiteDirectory {

  public static final Class<? extends SiteStyleSheet> STYLES = Styles.class;

  @Override
  protected void configure() {
    putInstance(new Container());

    addStyleSheet("styles.css", new Styles());
  }

}