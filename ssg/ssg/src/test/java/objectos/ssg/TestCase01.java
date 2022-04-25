/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg;

/**
 * - single add page.
 * - single add stylesheet.
 * - reference stylesheet from page
 */
final class TestCase01 extends Site {

  public Index index;

  @Override
  protected final void configure() {
    index = addPage("index.html", new Index());

    addStyleSheet("styles.css", new Styles());
  }

  private static class Index extends SitePage {
    @Override
    protected final void definition() {
      html(
        head(
          link(Styles.class)
        )
      );
    }
  }

  private static class Styles extends SiteStyleSheet {
    @Override
    protected final void definition() {
      style(
        body,

        fontFamily("MyCustomFont")
      );
    }
  }

}