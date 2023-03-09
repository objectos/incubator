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
package objectos.docs.html;

import java.util.List;
import objectos.html.HtmlTemplate;

public class Example extends HtmlTemplate {
  public static void main(String[] args) {
    System.out.println(new Example());
  }

  @Override
  protected final void definition() {
    doctype();
    html(
      lang("en"),
      head(
        meta(charset("utf-8")),
        title("Objectos HTML example")
      ),
      body(
        h1("Objectos Libraries"),
        ul(
          f(this::items)
        )
      )
    );
  }

  private void items() {
    var names = List.of(
      "Objectos HTML",
      "Objectos Code",
      "Objectos Lang",
      "Objectos Util");

    for (var name : names) {
      li(name);
    }
  }
}