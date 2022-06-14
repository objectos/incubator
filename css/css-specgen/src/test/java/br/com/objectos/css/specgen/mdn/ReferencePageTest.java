/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.specgen.mdn;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import java.io.IOException;
import objectos.util.MutableSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

public class ReferencePageTest {

  @Test(description = ""
      + "verify that only properties urls are found."
      + "It should ignore: "
      + "(1) at-rules, "
      + "(2) functions, "
      + "(3) pseudo classes/elements, "
      + "(4) <types>"
      + "(5) length units")
  public void properties() throws IOException {
    Resource resource = Resource.getResource("html/MDN/Reference");

    String html = Read.string(resource, Charsets.utf8());

    Document document = Jsoup.parse(html);
    ReferencePage page = BaseUrlFake.LOCALHOST.getReferencePage(document);

    MutableSet<String> props;
    props = new MutableSet<>();

    Iterable<PropertyAnchor> crawl = page.crawl();

    for (PropertyAnchor anchor : crawl) {
      String property = anchor.property();

      props.add(property);
    }

    assertTrue(props.contains("clear"));
    assertFalse(props.contains(":in-range"));
    assertFalse(props.contains("saturate()"));
    assertFalse(props.contains("px"));
  }

}
