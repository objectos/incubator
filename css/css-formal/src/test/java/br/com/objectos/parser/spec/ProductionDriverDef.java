/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.parser.spec;

import br.com.objectos.formal.testing.IsComplex;

abstract class ProductionDriverDef extends AbstractParserSpecTest {

  final ProductionDriverDef givenProduction(Production production) {
    givenProductionImpl(production);
    return this;
  }

  final ProductionDriverDef whenProduce(Object... values) {
    whenProduceImpl(values);
    return this;
  }

  final ProductionDriverDef thenProduced(IsComplex expected) {
    thenProducedImpl(expected);
    return this;
  }

  final ProductionDriverDef thenToString(String expected) {
    thenToStringImpl(expected);
    return this;
  }

  ProductionDriverDef it() {
    return this;
  }

  abstract void givenProductionImpl(Production production);

  abstract void whenProduceImpl(Object... values);

  abstract void thenProducedImpl(IsComplex expected);

  abstract void thenToStringImpl(String expected);

}