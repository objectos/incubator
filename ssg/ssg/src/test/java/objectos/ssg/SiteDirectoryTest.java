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

public class SiteDirectoryTest {

  private final TestingSiteDsl dsl = new TestingSiteDsl();

  private String href;

  @Test
  public void hrefBuilder() {
    abstract class Subject extends SiteDirectory {
      final void indexHtml() {
        StringBuilder b;
        b = hrefBuilder();

        b.append("index.html");

        href = b.toString();
      }
    }

    SiteDirectory subject;
    subject = new Subject() {
      @Override
      protected final void configure() {
        setName("docs");

        indexHtml();
      }
    };

    subject.acceptSiteDsl(dsl);

    assertEquals(href, "/docs/index.html");

    final SiteDirectory docs;
    docs = new Subject() {
      @Override
      protected final void configure() {
        indexHtml();
      }
    };

    subject = new Subject() {
      @Override
      protected final void configure() {
        addDirectory("docs", docs);
      }
    };

    subject.acceptSiteDsl(dsl);

    assertEquals(href, "/docs/index.html");
  }

}