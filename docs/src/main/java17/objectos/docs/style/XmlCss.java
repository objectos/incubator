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
package objectos.docs.style;

import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

public final class XmlCss extends AbstractStyleSheet {

  public static final ClassSelector _SYMBOL = Css.randomDot(3);

  public static final ClassSelector _TAG_NAME = Css.randomDot(3);

  public static final ClassSelector _TEXT = Css.randomDot(3);

  @Override
  protected final void definition() {
    style(
      _SYMBOL, or(), _TAG_NAME,

      color(Colors.INDIGO7)
    );
  }

}