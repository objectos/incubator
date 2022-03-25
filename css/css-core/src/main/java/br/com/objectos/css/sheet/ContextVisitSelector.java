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
package br.com.objectos.css.sheet;

import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.select.SimpleSelector;
import br.com.objectos.css.select.UniversalSelector;

abstract class ContextVisitSelector<E extends Exception> extends Context<E> {

  ContextVisitSelector() {}

  @Override
  final Context<E> visitAttributeSelector(Adapter<E> a, String attributeName) throws E {
    a.visitAttributeSelector(attributeName);
    return toSelector();
  }

  @Override
  final Context<E> visitAttributeValueSelector(
      Adapter<E> a, String attributeName, AttributeValueOperator operator, String value)
      throws E {
    a.visitAttributeValueSelector(attributeName, operator, value);
    return toSelector();
  }

  @Override
  final Context<E> visitClassSelector(Adapter<E> a, String className) throws E {
    a.visitClassSelector(className);
    return toSelector();
  }

  @Override
  final Context<E> visitCombinator(Adapter<E> a, Combinator combinator) throws E {
    a.visitCombinator(combinator);
    return toSelector();
  }

  @Override
  final Context<E> visitIdSelector(Adapter<E> a, String id) throws E {
    a.visitIdSelector(id);
    return toSelector();
  }

  @Override
  final Context<E> visitSimpleSelector(Adapter<E> a, SimpleSelector selector) throws E {
    a.visitSimpleSelector(selector);
    return toSelector();
  }

  @Override
  final Context<E> visitUniversalSelector(Adapter<E> a, UniversalSelector selector) throws E {
    a.visitUniversalSelector(selector);
    return toSelector();
  }

}