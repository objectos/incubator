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

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.specgen.AbstractCssSpecgenHttpServerTest;
import br.com.objectos.css.specgen.spec.Property;
import br.com.objectos.css.specgen.spec.Spec;
import objectos.util.ImmutableList;
import org.testng.annotations.Test;

public class MdnCrawlerTest extends AbstractCssSpecgenHttpServerTest {

  @Test
  public void crawl() {
    Spec spec = new MdnCrawler(httpTesting.httpLocalhost("/html/MDN")).crawl();

    ImmutableList<Property> props = spec.properties();
    assertEquals(props.size(), 2);

    Property p0 = props.get(0);
    assertEquals(p0.name(), "background");

    Property p1 = props.get(1);
    assertEquals(p1.name(), "bottom");
  }

}
