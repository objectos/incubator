/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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

import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderTop;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.sizing.Height;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.PaddingY;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.html.tmpl.AbstractFragment;

final class DocsBottomBar extends AbstractFragment implements Docs.BottomBar {

  @Override
  public final AbstractFragment toFragment() { return this; }

  @Override
  protected final void definition() {
    footer(
      BorderColor.slate200,
      BorderTop.v1,
      Flex.none,
      Height.v16,
      PaddingY.v02,

      nav(
        MarginX.auto,
        MaxWidth.screenX2l,

        p(
          FontSize.small,
          PaddingY.v32,
          TextAlign.center,

          t("© 2010-2022 Objectos Software LTDA. All rights reserved.")
        )
      )
    );
  }

}