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
package objectos.docs;

import objectos.html.HtmlFragment;

final class LeftBarV000500 extends LeftBarFragment {

  private static class ObjectosCode extends LeftBarFragment {
    public ObjectosCode(LeftBar injector) {
      super(injector);
    }

    @Override
    final void definitionImpl() {
      ul(
        li(a0("v000500/index", "Back")),

        li(
          h2v0("Objectos Code"),

          ul(
            li(a0("v000500/objectos-code/index")),
            li(a0("v000500/objectos-code/tutorial"))
          )
        ),

        li(
          h2v0("Java Template"),

          ul(
            li(a0("v000500/objectos-code/template/index")),
            li(a0("v000500/objectos-code/template/auto-imports")),
            li(a0("v000500/objectos-code/template/include")),
            li(a0("v000500/objectos-code/template/recommended-usage"))
          )
        ),

        li(
          h2v0("Class Declarations"),

          ul(
            li(a0("v000500/objectos-code/class/index")),
            li(a0("v000500/objectos-code/class/name")),
            li(a0("v000500/objectos-code/class/modifiers")),
            li(a0("v000500/objectos-code/class/type-parameters")),
            li(a0("v000500/objectos-code/class/extends")),
            li(a0("v000500/objectos-code/class/implements"))
          )
        ),

        li(
          h2v0("Field Declarations"),

          ul(
            li(a0("v000500/objectos-code/field/index")),
            li(a0("v000500/objectos-code/field/name"))
          )
        ),

        li(
          h2v0("Method Declarations"),

          ul(
            li(a0("v000500/objectos-code/method/index"))
          )
        ),

        li(
          h2v0("Statements"),

          ul(
            li(a0("v000500/objectos-code/statement/index")),
            li(a0("v000500/objectos-code/statement/if")),
            li(a0("v000500/objectos-code/statement/return"))
          )
        ),

        li(
          h2v0("Expressions"),

          ul(
            li(a0("v000500/objectos-code/expression/class-instance-creation")),
            li(a0("v000500/objectos-code/expression/assignment-operator"))
          )
        )
      );
    }
  }

  private static class ObjectosHtml extends LeftBarFragment {
    public ObjectosHtml(LeftBar injector) { super(injector); }

    @Override
    final void definitionImpl() {
      ul(
        li(a0("v000500/index", "Back")),

        li(
          h2v0("Objectos HTML"),

          ul(
            li(a0("v000500/objectos-html/index"))
          )
        )
      );
    }
  }

  private static class ObjectosLang extends LeftBarFragment {
    public ObjectosLang(LeftBar injector) { super(injector); }

    @Override
    final void definitionImpl() {
      ul(
        li(a0("v000500/index", "Back")),

        li(
          h2v0("Objectos Lang"),

          ul(
            li(a0("v000500/objectos-lang/index")),
            li(a0("v000500/objectos-lang/Check")),
            li(a0("v000500/objectos-lang/Equals")),
            li(a0("v000500/objectos-lang/HashCode")),
            li(a0("v000500/objectos-lang/ToString")),

            li(a0("v000500/objectos-lang/note-sink-api/index"),
              ul(
                li(a1("v000500/objectos-lang/note-sink-api/creating-notes")),
                li(a1("v000500/objectos-lang/note-sink-api/the-note-sink-interface")),
                li(a1("v000500/objectos-lang/note-sink-api/the-no-op-note-sink"))
              )
            )
          )
        )
      );
    }
  }

  private static class ObjectosUtil extends LeftBarFragment {
    public ObjectosUtil(LeftBar injector) { super(injector); }

    @Override
    final void definitionImpl() {
      ul(
        li(a0("v000500/index", "Back")),

        li(
          h2v0("Objectos Util"),

          ul(
            li(a0("v000500/objectos-util/index")),
            li(a0("v000500/objectos-util/array-utilities")),
            li(a0("v000500/objectos-util/collections/index"),
              ul(
                li(a1("v000500/objectos-util/collections/limitations")),
                li(a1("v000500/objectos-util/collections/builders")),
                li(a1("v000500/objectos-util/collections/of")),
                li(a1("v000500/objectos-util/collections/null-handling")),
                li(a1("v000500/objectos-util/collections/to-string")),
                li(a1("v000500/objectos-util/collections/join-method"))
              )
            )
          )
        )
      );
    }
  }

  private static final int OFFSET = "v000500/".length();

  private final ObjectosCode objectosCode;

  private final ObjectosHtml objectosHtml;

  private final ObjectosLang objectosLang;

  private final ObjectosUtil objectosUtil;

  public LeftBarV000500(LeftBar injector) {
    super(injector);

    objectosCode = new ObjectosCode(injector);

    objectosHtml = new ObjectosHtml(injector);

    objectosLang = new ObjectosLang(injector);

    objectosUtil = new ObjectosUtil(injector);
  }

  public final HtmlFragment get(String key) {
    int slash = key.indexOf('/', OFFSET);

    if (slash < 0) {
      return this;
    }

    var component = key.substring(OFFSET, slash);

    return switch (component) {
      case "objectos-code" -> objectosCode;

      case "objectos-html" -> objectosHtml;

      case "objectos-lang" -> objectosLang;

      case "objectos-util" -> objectosUtil;

      default -> this;
    };
  }

  @Override
  final void definitionImpl() {
    ul(
      li(a0("v000500/index")),

      li(
        h2v0("Web"),

        ul(
          li(a0("v000500/objectos-html/index", "Objectos HTML"))
        )
      ),

      li(
        h2v0("Core"),

        ul(
          li(a0("v000500/objectos-code/index", "Objectos Code")),
          li(a0("v000500/objectos-lang/index", "Objectos Lang")),
          li(a0("v000500/objectos-util/index", "Objectos Util"))
        )
      ),

      li(
        h2v0("Project"),

        ul(
          li(a0("v000500/intro/overview")),
          li(a0("v000500/intro/install")),
          li(a0("v000500/relnotes/index"))
        )
      )
    );
  }

}