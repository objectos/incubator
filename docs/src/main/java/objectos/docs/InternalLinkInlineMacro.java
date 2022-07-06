/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs;

import java.util.HashMap;
import java.util.Map;
import org.asciidoctor.ast.ContentNode;
import org.asciidoctor.extension.InlineMacroProcessor;
import org.asciidoctor.extension.Name;

@Name("ilink")
final class InternalLinkInlineMacro extends InlineMacroProcessor {

  private final Pages pages;

  InternalLinkInlineMacro(Pages pages) {
    this.pages = pages;
  }

  @Override
  public final Object process(ContentNode parent, String target, Map<String, Object> attributes) {
    var href = pages.href(target);

    var text = (String) attributes.get("1");

    var options = new HashMap<String, Object>();
    options.put("type", ":link");
    options.put("target", href);

    return createPhraseNode(parent, "anchor", text, attributes, options);
  }

}