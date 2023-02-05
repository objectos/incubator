/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
 * - addFragment
 */
final class TestCase06 extends Site {

  @Override
  protected final void configure() {
    addObject(new Top());

    addPage("index.html", new Index());
  }

  private static class Index extends SitePage {
    @Override
    protected final void definition() {
      html(
        body(
          f(getObject(Top.class))
        )
      );
    }
  }

  private static class Top extends SiteFragment {
    @Override
    protected final void definition() {
      header(
        a(href(Index.class), t("Objectos"))
      );
    }
  }

}