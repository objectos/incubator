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
import objectos.ssg.SiteStyleSheet;

public final class NextBannerCss extends SiteStyleSheet {

  public static final SiteStyleSheet INSTANCE = new NextBannerCss();

  public static final IdSelector _ID = Css.randomHash(3);

  @Override
  protected final void definition() {
    style(
      _ID,

      backgroundColor(Colors.GRAPE0),
      borderRadius(Spacing.V02),
      fontSize(FontSize.SM),
      marginBottom(Spacing.V02),
      padding(Spacing.V03)
    );
  }

}