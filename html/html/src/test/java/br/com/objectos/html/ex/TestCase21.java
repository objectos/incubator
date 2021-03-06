/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.ex;

import br.com.objectos.html.spi.type.AnyElementValue;
import br.com.objectos.html.tmpl.AbstractTemplate;

public class TestCase21 extends AbstractTemplate {

  private final AnyElementValue first = new TestClassSelector("first");

  private final AnyElementValue second = new TestClassSelector("second");

  @Override
  protected final void definition() {
    div(first, second);
  }

}
