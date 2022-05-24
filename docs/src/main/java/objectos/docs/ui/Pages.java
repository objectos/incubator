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
package objectos.docs.ui;

import br.com.objectos.core.list.ImmutableList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;
import objectos.ssg.SiteDirectory;
import objectos.ssg.SiteFragment;
import objectos.ssg.SitePage;
import objectos.ssg.SiteVisitor;

public final class Pages implements SiteVisitor {

  private final Map<DocsPage, DocsPage> nextPages = new IdentityHashMap<>();

  private final Map<DocsPage, DocsPage> previousPages = new IdentityHashMap<>();

  private final Map<DocsPage, ImmutableList<DocsPage>> trails = new IdentityHashMap<>();

  private final Deque<DocsPage> trailBuilder = new ArrayDeque<>();

  private int directoryDepth;

  final SiteFragment prevNext = new PageSwitcher();

  private DocsPage current;

  public Pages() {}

  public final void clear() {
    current = null;
  }

  @Override
  public final void postVisitSiteDirectory(SiteDirectory directory) {
    directoryDepth--;

    trailBuilder.removeLast();
  }

  public final ImmutableList<DocsPage> trail(DocsPage page) {
    return trails.get(page);
  }

  @Override
  public final void visitSiteDirectory(SiteDirectory directory) {
    directoryDepth++;
  }

  @Override
  public final void visitSitePage(SitePage page) {
    if (page instanceof IgnoreMe) {
      return;
    }

    if (page instanceof DocsPage d) {
      visitDocsPage(d);
    }
  }

  final DocsPage backPage(DocsPage page) {
    return previousPages.get(page);
  }

  final DocsPage nextPage(DocsPage page) {
    return nextPages.get(page);
  }

  private void visitDocsPage(DocsPage next) {
    if (current != null) {
      previousPages.put(next, current);

      nextPages.put(current, next);
    }

    current = next;

    if (trailBuilder.size() < directoryDepth) {
      String path;
      path = next.path();

      if (!path.endsWith("index.html")) {
        return;
      }
    }

    trailBuilder.add(next);

    ImmutableList<DocsPage> trail;
    trail = ImmutableList.copyOf(trailBuilder);

    trails.put(next, trail);

    if (trailBuilder.size() > directoryDepth) {
      trailBuilder.removeLast();
    }
  }

  public interface IgnoreMe {}

}