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

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.http.media.TextType;
import java.io.IOException;
import org.testng.annotations.Test;

public class SiteTest {

  @Test
  public void testCase00() throws IOException {
    TestableWriter w;
    w = gen(new TestCase00());

    testPathList(
      w,

      "/index.html"
    );

    w.testString(
      "/index.html",

      TextType.HTML,

      """
      <html>\
      <body>TestCase00:index</body>\
      </html>"""
    );
  }

  protected void testPathList(TestableWriter w, String... paths) {
    assertEquals(w.pathList(), ImmutableList.copyOf(paths));
  }

  private TestableWriter gen(Site site) throws IOException {
    TestableWriter w;
    w = new TestableWriter();

    site.generate(w);

    return w;
  }

}