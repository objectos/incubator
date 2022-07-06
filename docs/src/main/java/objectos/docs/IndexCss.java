/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs;

import br.com.objectos.css.Css;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import objectos.docs.style.Colors;
import objectos.docs.style.FontSize;
import objectos.docs.style.Spacing;

final class IndexCss extends AbstractStyleSheet {

  static final IdSelector HD = Css.randomHash(3);

  static final IdSelector HDV = Css.randomHash(3);

  @Override
  protected final void definition() {
    install(new ArticleCss());

    style(
      HDV,

      backgroundColor(Colors.GRAY0),
      display(flex),
      fontSize(FontSize.SM),
      justifyContent(spaceBetween),
      padding(Spacing.V03)
    );
  }

}