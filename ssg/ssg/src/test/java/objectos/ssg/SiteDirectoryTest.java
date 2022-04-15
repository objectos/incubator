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

import br.com.objectos.core.io.Resource;
import br.com.objectos.http.media.TextType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SiteDirectoryTest {

  private final TestingSiteDsl dsl = new TestingSiteDsl();

  private final Page0 page0 = new Page0();

  private final Page1 page1 = new Page1();

  @BeforeMethod
  public void _beforeMethod() {
    dsl.clear();
  }

  @Test
  public void addPage() {
    class Root extends SiteDirectory {
      @Override
      protected final void configure() {
        addPage("index.html", page0);
      }
    }

    class Docs extends SiteDirectory {
      @Override
      protected final void configure() {
        addPage("index.html", page1);
      }
    }

    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addDirectory(new Root());
        addDirectory("docs", new Docs());
      }
    });

    assertEquals(dsl.path(page0), "/index.html");
    assertEquals(dsl.path(page1), "/docs/index.html");
  }

  @Test
  public void addResource() {
    class Root extends SiteDirectory {
      @Override
      protected final void configure() {
        addResource("5x2.jpg");
        addResource("sub/foo.jpg", r("5x2.jpg"));
        addResource("sub/foo.txt", r("5x2.jpg"), TextType.PLAIN);
      }

      private Resource r(String resourceName) {
        return Resource.getResource(getClass(), resourceName);
      }
    }

    run(new AbstractSite() {
      @Override
      protected final void configure() {
        addDirectory(new Root());
      }
    });

    Resource r;
    r = Resource.getResource(getClass(), "5x2.jpg");

    assertEquals(dsl.resource("/5x2.jpg"), r);
    assertEquals(dsl.mediaType("/5x2.jpg"), null);

    assertEquals(dsl.resource("/sub/foo.jpg"), r);
    assertEquals(dsl.mediaType("/sub/foo.jpg"), null);

    assertEquals(dsl.resource("/sub/foo.txt"), r);
    assertEquals(dsl.mediaType("/sub/foo.txt"), TextType.PLAIN);
  }

  private void run(AbstractSite site) {
    site.acceptSiteDsl(dsl);

    dsl.render();
  }

}