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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import br.com.objectos.core.list.ImmutableList;
import objectos.docs.DocsSiteTest;
import objectos.docs.next.Next;
import objectos.docs.next.logging.LoggingDir;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PagesTest extends DocsSiteTest {

  private Pages pages;

  @BeforeClass
  public void _beforeClass() {
    pages = getObject(Pages.class);
  }

  @Test
  public void backPage() {
    DocsPage index;
    index = getObject(Next.INDEX);

    DocsPage toc;
    toc = pages.backPage(index);

    assertNull(toc);
  }

  @Test
  public void trail() {
    DocsPage events;
    events = getObject(LoggingDir.EVENTS);

    ImmutableList<DocsPage> trail;
    trail = pages.trail(events);

    assertEquals(trail.size(), 4);
  }

}