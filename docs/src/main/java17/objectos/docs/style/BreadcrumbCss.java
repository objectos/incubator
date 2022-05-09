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
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

public final class BreadcrumbCss extends AbstractStyleSheet {

  public static final IdSelector ID = Css.randomHash(3);

  @Override
  protected final void definition() {
    style(
      ID,

      padding(Spacing.V06, zero())
    );

    style(
      ID, gt(), ol,

      alignItems(center),
      display(flex),
      fontSize(FontSize.XS)
    );

    style(
      ID, gt(), ol, sp(), li,

      display(flex)
    );

    style(
      ID, gt(), ol, sp(), a,

      display(block)
    );

    style(
      ID, gt(), ol, sp(), svg,

      display(block)
    );
  }

}