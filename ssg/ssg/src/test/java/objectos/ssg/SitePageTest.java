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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class SitePageTest extends AbstractSiteTest {

  @Test
  public void thisHref() {
    var root = new SitePage() {
      @Override
      protected final void definition() {
        div(thisHref());
      }
    };

    var docs = new SitePage() {
      @Override
      protected final void definition() {
        div(thisHref());
      }
    };

    class Docs extends SiteDirectory {
      @Override
      protected final void configure() {
        addPage("foo.html", docs);
      }
    }

    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addPage("index.html", root);
        addDirectory("docs", new Docs());
      }
    });

    assertEquals(dsl.html(root), "<div>/index.html</div>");
    assertEquals(dsl.html(docs), "<div>/docs/foo.html</div>");
  }

}