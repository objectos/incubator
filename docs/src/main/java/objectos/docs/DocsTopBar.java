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

import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.sizing.Height;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.PaddingY;
import br.com.objectos.html.tmpl.AbstractFragment;

final class DocsTopBar extends AbstractFragment implements Docs.TopBar {

  @Override
  public final AbstractFragment toFragment() { return this; }

  @Override
  protected final void definition() {
    header(
      BorderColor.slate200,
      BorderBottom.v1,
      Flex.none,
      Height.v16,
      PaddingY.v02,

      nav(
        MarginX.auto,
        MaxWidth.screenX2l,

        raw("&nbsp;")
      )
    );
  }

}