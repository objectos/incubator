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
import objectos.html.HtmlTemplate.Visitor;
import objectos.html.SimpleCompiledTemplateVisitor;
import objectos.html.tmpl.AttributeName;
import objectos.html.tmpl.StandardAttributeName;
import objectos.html.tmpl.StandardElementName;
import objectos.util.GrowableSet;
import objectos.util.UnmodifiableList;

public final class StyleClassSet
    extends SimpleCompiledTemplateVisitor
    implements Predicate<String>, Visitor {

  private boolean collect;

  private final Set<String> names = new GrowableSet<>();

  @Override
  public final void attributeEnd() {
    collect = false;
  }

  @Override
  public final void attributeStart(AttributeName name) {
    if (name == StandardAttributeName.CLASS) {
      collect = true;
    }
  }

  @Override
  public final void attributeValue(String value) {
    if (collect) {
      names.add(value);
    }
  }

  public final void clear() {
    names.clear();
  }

  @Override
  public final void doctype() {}

  @Override
  public final void documentEnd() {}

  @Override
  public final void documentStart() {
    collect = false;

    names.clear();
  }

  @Override
  public final void endTag(StandardElementName name) {}

  @Override
  public final void raw(String value) {}

  @Override
  public final void selfClosingEnd() {}

  @Override
  public final void startTag(StandardElementName name) {}

  @Override
  public final void startTagEnd(StandardElementName name) {}

  @Override
  public final boolean test(String t) {
    return names.contains(t);
  }

  @Override
  public final void text(String value) {}

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