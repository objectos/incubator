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
package br.com.objectos.lexer.impl.ah;

import static org.testng.Assert.assertEquals;

import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.impl.ah.model.IsBrick;
import br.com.objectos.lexer.lang.Constructor1;
import java.util.Arrays;
import java.util.List;

abstract class AbstractLinkTest {

  private int rank;
  
  final void assertToString(Link link, String... expected) {
    List<String> toStringList = Arrays.asList(link.toString().split("\n"));
    assertEquals(toStringList, Arrays.asList(expected));
  }

  final MarkerLink marker(String name) {
    return MarkerLink.get(name);
  }

  final CharSingleOneLink oneLink(char value, Link link) {
    return new CharSingleOneLink(value, link);
  }

  final CharExpressionOneLink oneLink(CharExpression expression, Link link) {
    return new CharExpressionOneLink(expression, link);
  }

  final BrickValueOneLink oneLink(IsBrick value, Link link) {
    return new BrickValueOneLink(value, link);
  }

  final StringOneLink oneLink(String value, Link link) {
    return new StringOneLink(value, link);
  }

  final CharSingleOneOrMoreLink oneOrMoreLink(char value, Link link) {
    return new CharSingleOneOrMoreLink(value, link);
  }

  final CharExpressionOneOrMoreLink oneOrMoreLink(CharExpression expression, Link link) {
    return new CharExpressionOneOrMoreLink(expression, link);
  }

  final StringValueLink stringValueLink(Link link) {
    return new StringValueLink(link);
  }

  final <E, A1> TrailingLink trailingLink(Class<E> type, Constructor1<E, A1> constructor, Link link) {
    CreateWith createWith = CreateWith.of(constructor);
    return new TrailingLink(new CreateWithAction(rank++, type, createWith), link);
  }

  final TrailingLink trailingLink(Object value, Link link) {
    return new TrailingLink(returnSelfAction(value), link);
  }

  final CharExpressionZeroOrMoreLink zeroOrMoreLink(CharExpression expression, Link link) {
    return new CharExpressionZeroOrMoreLink(expression, link);
  }

  final CharSingleOptionalLink optionalLink(char value, Link link) {
    return new CharSingleOptionalLink(value, link);
  }

  private ReturnSelfAction returnSelfAction(Object value) {
    return new ReturnSelfAction(rank++, value);
  }

}