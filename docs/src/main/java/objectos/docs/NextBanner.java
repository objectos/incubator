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

import br.com.objectos.html.tmpl.AbstractFragment;
import objectos.docs.style.NextBannerCss;

final class NextBanner extends AbstractFragment {

  private final DocsInjector injector;

  NextBanner(DocsInjector injector) { this.injector = injector; }

  public final boolean shouldRender() {
    var href = injector.$href();

    return href.contains("/next/");
  }

  @Override
  protected final void definition() {
    div(
      NextBannerCss._ID,

      p("""
        You are reading the documentation for the unreleased development version of Objectos.

        Please note that this is a work in progress. It might be incomplete and may
        change without notice.""")
    );
  }

}