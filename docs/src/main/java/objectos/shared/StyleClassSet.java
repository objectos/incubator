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
import objectos.html.pseudom.DocumentProcessor;
import objectos.html.pseudom.HtmlAttribute;
import objectos.html.pseudom.HtmlDocument;
import objectos.html.pseudom.HtmlElement;
import objectos.html.pseudom.HtmlNode;
import objectos.html.tmpl.StandardAttributeName;
import objectos.util.GrowableSet;

public final class StyleClassSet implements DocumentProcessor, Predicate<String> {

  private final Set<String> names = new GrowableSet<>();

  public final void clear() {
    names.clear();
  }

  @Override
  public final void process(HtmlDocument document) {
    names.clear();

    for (var node : document.nodes()) {
      processNode(node);
    }
  }

  @Override
  public final boolean test(String t) {
    return names.contains(t);
  }

  private void processClassAttribute(HtmlAttribute attribute) {
    for (var value : attribute.values()) {
      names.add(value);
    }
  }

  private void processElement(HtmlElement element) {
    for (var attribute : element.attributes()) {
      if (attribute.hasName(StandardAttributeName.CLASS)) {
        processClassAttribute(attribute);
      }
    }

    for (var node : element.nodes()) {
      processNode(node);
    }
  }

  private void processNode(HtmlNode node) {
    if (node instanceof HtmlElement element) {
      processElement(element);
    }
  }

}