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
 * - directory: add directory
 */
final class TestCase05 extends Site {

  @Override
  protected final void configure() {
    addDirectory("foo", new FooDir());
  }

  private static class BarDir extends SiteDirectory {
    @Override
    protected final void configure() {
      addPage("index.html", new Index());
    }
  }

  private static class FooDir extends SiteDirectory {
    @Override
    protected final void configure() {
      addDirectory("bar", new BarDir());
    }
  }

  private static class Index extends SitePage {
    @Override
    protected final void definition() {
      html(
        body("TestCase05:index")
      );
    }
  }

}