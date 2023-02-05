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
 * [#35] allow installing a site at a location
 */
final class TestCase08 extends Site {

  @Override
  protected final void configure() {
    addSite("docs", new Docs());
  }

  private static class Docs extends Site {
    @Override
    protected void configure() {
      addPage("index.html", new Index());
    }
  }

  private static class Index extends SitePage {
    @Override
    protected final void definition() {
      html(
        body("TestCase08:index")
      );
    }
  }

}