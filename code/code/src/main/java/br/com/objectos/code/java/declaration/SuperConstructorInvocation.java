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

import br.com.objectos.code.annotations.Ignore;
import br.com.objectos.code.java.element.CodeElement;
import br.com.objectos.code.java.element.Keywords;
import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import java.util.Arrays;

public class SuperConstructorInvocation extends AbstractConstructorInvocation {

  private static final SuperConstructorInvocation EMPTY = _super0(
      ImmutableList.<Argument> of()
  );

  private SuperConstructorInvocation(CodeElement... elements) {
    super(elements);
  }

  @Ignore("AggregatorGenProcessor")
  public static SuperConstructorInvocation _super() {
    return EMPTY;
  }

  public static SuperConstructorInvocation _super(
      Argument arg1) {
    Checks.checkNotNull(arg1, "arg1 == null");
    return _super0(Arrays.asList(arg1));
  }

  public static SuperConstructorInvocation _super(Argument... arguments) {
    return _super0(ImmutableList.copyOf(arguments));
  }

  public static SuperConstructorInvocation _super(
      Argument arg1,
      Argument arg2) {
    Checks.checkNotNull(arg1, "arg1 == null");
    Checks.checkNotNull(arg2, "arg2 == null");
    return _super0(Arrays.asList(arg1, arg2));
  }

  public static SuperConstructorInvocation _super(
      Argument arg1,
      Argument arg2,
      Argument arg3) {
    Checks.checkNotNull(arg1, "arg1 == null");
    Checks.checkNotNull(arg2, "arg2 == null");
    Checks.checkNotNull(arg3, "arg3 == null");
    return _super0(Arrays.asList(arg1, arg2, arg3));
  }

  private static SuperConstructorInvocation _super0(Iterable<Argument> arguments) {
    return new SuperConstructorInvocation(
        Keywords._super(),
        parenthesized(
            commaSeparated(arguments)
        )
    );
  }

}
