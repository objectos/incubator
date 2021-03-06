/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package objectos.asciidoc;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.jsoup.Jsoup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AsciidoctorTest extends AsciiDocTest {

  private Asciidoctor asciidoctor;

  private Options options;

  @BeforeClass
  @Override
  public void _beforeClass() {
    asciidoctor = Asciidoctor.Factory.create();

    options = Options.builder()
        .headerFooter(true)
        .build();
  }

  @Test(enabled = false)
  public void _enableCodeMinings() {
  }

  @Override
  final void test(
      String source, int[] expected0, int[] expected1, int[][] expected2, String expectedHtml) {
    var html = asciidoctor.convert(source, options);

    var document = Jsoup.parse(html);

    var body = document.body();

    body.removeAttr("class");

    var footer = body.getElementById("footer");

    footer.remove();

    var result = body.toString();

    testHtml(result, expectedHtml);
  }

}