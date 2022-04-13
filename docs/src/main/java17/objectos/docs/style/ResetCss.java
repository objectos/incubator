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

import br.com.objectos.css.sheet.AbstractStyleSheet;

public final class ResetCss extends AbstractStyleSheet {

  @Override
  protected final void definition() {
    style(
      any(),

      boxSizing(borderBox),
      margin(zero()),
      padding(zero())
    );

    style(
      body,

      margin(zero(), auto),
      minHeight(pct(100))
    );

    style(
      code, or(), pre,

      fontFamily(
        fontFamily(keyword("ui-monospace")),
        fontFamily(l("Liberation Mono")),
        fontFamily(l("Cascadia Mono")),
        fontFamily(l("Segoe UI Mono")),
        fontFamily(keyword("Menlo")),
        fontFamily(keyword("Monaco")),
        fontFamily(keyword("Consolas")),
        fontFamily(monospace)
      )
    );

    style(
      h1, or(),
      h2, or(),
      h3, or(),
      h4, or(),
      h5, or(),
      h6,

      fontSize(inherit),
      fontWeight(inherit)
    );

    style(
      html,

      color(black),
      fontFamily(
        fontFamily(keyword("-apple-system")),
        fontFamily(keyword("BlinkMacSystemFont")),
        fontFamily(l("Segoe UI")),
        fontFamily(l("Liberation Sans")),
        fontFamily(sansSerif)
      ),
      fontSize(px(16)),
      lineHeight(1.5),
      minHeight(pct(100))
    );

    style(
      table,

      width(pct(100))
    );

    style(
      ul, or(), ol,

      listStyleType(none)
    );
  }

}