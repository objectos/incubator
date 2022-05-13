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
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

public class PageSwitcherCss extends AbstractStyleSheet {

  public static final IdSelector _NAV = Css.randomHash(3);

  public static final IdSelector _NEXT = Css.randomHash(3);

  public static final IdSelector _PREV = Css.randomHash(3);

  public static final ClassSelector _TEXT = Css.randomDot(3);

  @Override
  protected final void definition() {
    style(
      _NAV,

      borderTop(Spacing.PX, solid, Colors.GRAY3),
      display(flex),
      marginTop(Spacing.V20),
      padding(Spacing.V02, zero(), Spacing.V04)
    );

    style(
      _NAV, sp(), a,

      alignItems(center),
      color(Colors.BLUE8),
      display(flex)
    );

    style(
      _NAV, sp(), a, HOVER, sp(), span,

      textDecoration(underline)
    );

    style(
      _NAV, gt(), div,

      flexGrow(1)
    );

    style(
      _NAV, sp(), svg,

      display(block)
    );

    style(
      _NEXT,

      textAlign(right)
    );

    style(
      _NEXT, sp(), svg,

      marginLeft(Spacing.V02)
    );

    style(
      _PREV,

      textAlign(left)
    );

    style(
      _PREV, sp(), svg,

      marginRight(Spacing.V02)
    );

    style(
      _TEXT,

      flexGrow(1),
      fontWeight(500)
    );

    style(
      _TEXT, gt(), div,

      fontSize(FontSize.SM)
    );

    style(
      _TEXT, gt(), span,

      display(none)
    );

    media(
      screen, minWidth(Breakpoint.MD),

      style(
        _NAV,

        padding(Spacing.V10, zero(), Spacing.V20)
      ),

      style(
        _NAV, sp(), svg,

        marginTop(Spacing.V05)
      ),

      style(
        _TEXT, gt(), span,

        display(inline)
      )
    );
  }

}