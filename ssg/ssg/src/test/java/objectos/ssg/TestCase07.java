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

import br.com.objectos.html.spi.type.UlValue;
import java.util.List;
import objectos.util.GrowableList;

/**
 * - SiteRegistry
 */
final class TestCase07 extends Site {

  @Override
  protected final void configure() {
    addPage("toc.html", new SitePage() {
      @Override
      protected final void definition() {
        Toc toc;
        toc = getObject(Toc.class);

        html(
          f(toc)
        );
      }
    });

    addObject(new Toc());

    addPage("page1.html", new EmptyPage() {});
    addPage("page2.html", new EmptyPage() {});

    addDirectory("l1", new SiteDirectory() {
      @Override
      protected final void configure() {
        addPage("page1.html", new EmptyPage() {});
        addPage("page2.html", new EmptyPage() {});

        addDirectory("l2", new SiteDirectory() {
          @Override
          protected final void configure() {
            addPage("page1.html", new EmptyPage() {});
          }
        });

        addPage("page3.html", new EmptyPage() {});
      }
    });

    addPage("page3.html", new EmptyPage() {});
  }

  private static class Toc extends SiteFragment implements SiteVisitor {
    private final List<String> items = new GrowableList<>();

    private int level;

    @Override
    public final void postVisitSiteDirectory(SiteDirectory directory) {
      level--;
    }

    @Override
    public final void visitSiteDirectory(SiteDirectory directory) {
      level++;
    }

    @Override
    public final void visitSitePage(SitePage page) {
      items.add(level + ":" + page.path());
    }

    @Override
    protected final void definition() {
      int size;
      size = items.size();

      UlValue[] li;
      li = new UlValue[size];

      for (int i = 0; i < size; i++) {
        String item;
        item = items.get(i);

        li[i] = li(item);
      }

      ul(li);
    }
  }

}