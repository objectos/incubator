/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.java.declaration;

import br.com.objectos.code.java.type.NamedClass;
import objectos.util.ImmutableList;

public class ThrowsShorthand implements MethodCodeElement {

  private final ImmutableList<? extends ThrowsElement> elements;

  private ThrowsShorthand(ImmutableList<? extends ThrowsElement> elements) {
    this.elements = elements;
  }

  public static ThrowsShorthand _throws(Class<? extends Throwable> throwable) {
    ThrowsElement e;
    e = NamedClass.ofWithNullMessage(throwable, "throwable == null");

    ImmutableList<ThrowsElement> set;
    set = ImmutableList.of(e);

    return new ThrowsShorthand(set);
  }

  public static ThrowsShorthand _throws(Iterable<? extends ThrowsElement> elements) {
    ImmutableList<? extends ThrowsElement> set;
    set = ImmutableList.copyOf(elements);

    return new ThrowsShorthand(set);
  }

  public static ThrowsShorthand _throws(ThrowsElement throwable) {
    ImmutableList<ThrowsElement> set;
    set = ImmutableList.of(throwable);

    return new ThrowsShorthand(set);
  }

  public static ThrowsShorthand _throws(
      ThrowsElement t1,
      ThrowsElement t2) {
    ImmutableList<ThrowsElement> set;
    set = ImmutableList.of(t1, t2);

    return new ThrowsShorthand(set);
  }

  public static ThrowsShorthand _throws(
      ThrowsElement t1,
      ThrowsElement t2,
      ThrowsElement t3) {
    ImmutableList<ThrowsElement> set;
    set = ImmutableList.of(t1, t2, t3);

    return new ThrowsShorthand(set);
  }

  @Override
  public final void acceptMethodCodeBuilder(MethodCode.Builder builder) {
    for (ThrowsElement element : elements) {
      element.acceptThrowsElementConsumer(builder);
    }
  }

}
