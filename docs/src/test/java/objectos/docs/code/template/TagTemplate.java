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
package objectos.docs.code.template;

import java.util.Set;
import java.util.TreeSet;
import objectos.code.JavaTemplate;

public class TagTemplate extends JavaTemplate {

  private final Set<String> tagNames = new TreeSet<>();

  public static void main(String[] args) {
    var tmpl = new TagTemplate();

    tmpl.add("div");
    tmpl.add("a");
    tmpl.add("ul");
    tmpl.add("li");
    tmpl.add("table");

    System.out.println(tmpl.toString());
  }

  public final void add(String name) {
    tagNames.add(name);
  }

  @Override
  protected final void definition() {
    // @formatter:off
    _package("com.example");

    autoImports();

    _public(); _enum("Tag"); body(
      include(this::constants),

      _private(), _final(), t(String.class), id("name"),

      _private(), constructor(t(String.class), id("name")), block(
        _this(), n("name"), gets(), n("name")
      )
    );
    // @formatter:on
  }

  private void constants() {
    for (var tagName : tagNames) {
      var fieldName = tagName.toUpperCase();

      enumConstant(fieldName, s(tagName));
    }
  }

}