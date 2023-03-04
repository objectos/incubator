/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.shared;

import java.util.Set;
import java.util.function.Predicate;
import objectos.html.SimpleCompiledTemplateVisitor;
import objectos.html.tmpl.AttributeName;
import objectos.html.tmpl.StandardAttributeName;
import objectos.util.GrowableSet;
import objectos.util.UnmodifiableList;

public final class StyleClassSet
    extends SimpleCompiledTemplateVisitor
    implements Predicate<String> {

  private final Set<String> names = new GrowableSet<>();

  public final void clear() {
    names.clear();
  }

  @Override
  public final boolean test(String t) {
    return names.contains(t);
  }

  @Override
  public final void visitAttribute(AttributeName name, String value) {
    if (name == StandardAttributeName.CLASS) {
      names.add(value);
    }
  }

  @Override
  public final void visitAttribute(AttributeName name, UnmodifiableList<String> values) {
    if (name == StandardAttributeName.CLASS) {
      names.addAll(values);
    }
  }

  @Override
  public final void visitAttribute(String name, String value) {
    if (name.equals("class")) {
      names.add(value);
    }
  }

}