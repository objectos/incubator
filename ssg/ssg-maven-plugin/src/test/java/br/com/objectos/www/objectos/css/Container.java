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
package br.com.objectos.www.objectos.css;

import objectos.css.Css;
import objectos.css.select.ClassSelector;
import objectos.ssg.SiteStyleSheet;

final class Container extends SiteStyleSheet {

  static final ClassSelector CONTAINER = Css.dot("container");

  @Override
  protected final void definition() {
    style(
      CONTAINER,

      width(pct(100))
    );
  }

}