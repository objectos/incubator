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
import br.com.objectos.css.framework.Framework;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

final class DocsCss extends AbstractStyleSheet {

  static final ClassSelector XL_WIDTH_70 = Css.randomDot(3);

  private final Framework framework = new Framework();

  @Override
  protected final void definition() {
    install(framework);

    media(
      screen, minWidth(px(1280)),

      style(
        XL_WIDTH_70,

        width(rem(17.5))
      )
    );
  }

}