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

import br.com.objectos.css.sheet.AbstractStyleSheet;
import objectos.docs.style.BreadcrumbCss;
import objectos.docs.style.ContainerCss;
import objectos.docs.style.JavaCss;
import objectos.docs.style.NextBannerCss;
import objectos.docs.style.PageSwitcherCss;
import objectos.docs.style.ResetCss;
import objectos.docs.style.SyntaxCss;
import objectos.docs.style.XmlCss;

final class ArticleCss extends AbstractStyleSheet {
  @Override
  protected final void definition() {
    install(new ResetCss());

    install(new objectos.docs.style.ArticleCss());

    install(new BreadcrumbCss());

    install(new ContainerCss());

    install(new PageSwitcherCss());

    install(new SyntaxCss());

    install(new JavaCss());

    install(new XmlCss());

    install(NextBannerCss.INSTANCE);
  }
}