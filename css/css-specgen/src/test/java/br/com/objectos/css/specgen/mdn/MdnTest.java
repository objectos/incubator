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

import br.com.objectos.css.specgen.spec.Property;
import br.com.objectos.css.specgen.spec.Spec;
import java.io.IOException;
import objectos.util.UnmodifiableList;
import org.testng.annotations.Test;

public class MdnTest {

  @Test
  public void load() throws IOException {
    Spec spec = Mdn.load();
    UnmodifiableList<Property> properties = spec.properties();
    assertEquals(properties.size(), 361);
  }

}
