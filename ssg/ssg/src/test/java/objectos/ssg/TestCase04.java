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

import br.com.objectos.http.media.TextType;

/**
 * - directory: add resource
 */
final class TestCase04 extends Site {

  @Override
  protected final void configure() {
    addDirectory("sub", new SubDir());
  }

  private static class Index extends SitePage {
    @Override
    protected final void definition() {
      SubDir subDir;
      subDir = getObject(SubDir.class);

      html(
        a(href(subDir.ico), t("ico")),
        a(href(subDir.jpg), t("jpg")),
        a(href(subDir.txt), t("txt"))
      );
    }
  }

  private static class SubDir extends SiteDirectory {
    SitePath ico;
    SitePath jpg;
    SitePath txt;

    @Override
    public final void postSiteGeneration() {
      super.postSiteGeneration();

      ico = jpg = txt = null;
    }

    @Override
    protected final void configure() {
      addPage("index.html", new Index());

      ico = addResource("5x2.jpg");
      jpg = addResource("foo.jpg", "5x2.jpg");
      txt = addResource("foo.txt", "5x2.jpg", TextType.PLAIN);
    }
  }

}