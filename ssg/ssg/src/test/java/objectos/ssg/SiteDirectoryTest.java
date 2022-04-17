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

import br.com.objectos.http.media.ImageType;
import br.com.objectos.http.media.TextType;
import java.net.URL;
import org.testng.annotations.Test;

public class SiteDirectoryTest extends AbstractSiteTest {

  private final Css0 css0 = new Css0();

  private final Page0 page0 = new Page0();

  private final Page1 page1 = new Page1();

  @Test
  public void addDirectory() {
    class Docs extends SiteDirectory {
      @Override
      protected final void configure() {
        addPage("index.html", page1);
      }
    }

    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addPage("index.html", page0);

        addDirectory("docs", new Docs());
      }
    });

    assertEquals(dsl.templatePath(page0), "/index.html");
    assertEquals(dsl.templatePath(page1), "/docs/index.html");
  }

  @Test
  public void addPage() {
    class Docs extends SiteDirectory {
      @Override
      protected final void configure() {
        addPage("index.html", page1);
      }
    }

    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addPage("index.html", page0);

        addDirectory("docs", new Docs());
      }
    });

    assertEquals(dsl.templatePath(page0), "/index.html");
    assertEquals(dsl.templatePath(page1), "/docs/index.html");
  }

  @Test
  public void addResource() {
    class Sub extends SiteDirectory {
      @Override
      protected final void configure() {
        addResource("foo.jpg", "5x2.jpg");
        addResource("foo.txt", "5x2.jpg", TextType.PLAIN);
      }
    }

    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addResource("5x2.jpg");

        addDirectory("sub", new Sub());
      }
    });

    URL r;
    r = getClass().getResource("5x2.jpg");

    assertEquals(dsl.resource("/5x2.jpg"), r);
    assertEquals(dsl.mediaType("/5x2.jpg"), ImageType.JPEG);

    assertEquals(dsl.resource("/sub/foo.jpg"), r);
    assertEquals(dsl.mediaType("/sub/foo.jpg"), ImageType.JPEG);

    assertEquals(dsl.resource("/sub/foo.txt"), r);
    assertEquals(dsl.mediaType("/sub/foo.txt"), TextType.PLAIN);
  }

  @Test
  public void addStyleSheet() {
    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addStyleSheet("foo.css", css0);
      }
    });

    assertEquals(dsl.styleSheetPath(css0), "/foo.css");
  }

  @Test
  public void store() {
    class Frag extends SiteFragment {
      @Override
      protected final void definition() {
        p("frag");
      }
    }

    var page = new SitePage() {
      @Override
      protected final void definition() {
        var frag = getObject(Frag.class);

        div(f(frag));
      }
    };

    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addObject(new Frag());

        addPage("index.html", page);
      }
    });

    assertEquals(dsl.html(page), "<div><p>frag</p></div>");
  }

}