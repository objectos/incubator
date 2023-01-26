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

final class LeftBarV0003 extends LeftBarFragment {

  public LeftBarV0003(LeftBar injector) { super(injector); }

  @Override
  final void definitionImpl() {
    ul(
      li(a0("v0003/index")),

      li(
        h2v0("Introduction"),

        ul(
          li(a0("v0003/intro/overview")),
          li(a0("v0003/intro/install"))
        )
      ),

      li(
        h2v0("Objectos Lang"),

        ul(
          li(a0("v0003/objectos-lang/index")),
          li(a0("v0003/objectos-lang/Check")),
          li(a0("v0003/objectos-lang/Equals")),
          li(a0("v0003/objectos-lang/HashCode")),
          li(a0("v0003/objectos-lang/ToString")),

          li(a0("v0003/objectos-lang/note-sink-api/index"),
            ul(
              li(a1("v0003/objectos-lang/note-sink-api/creating-notes")),
              li(a1("v0003/objectos-lang/note-sink-api/the-note-sink-interface")),
              li(a1("v0003/objectos-lang/note-sink-api/the-no-op-note-sink"))
            )
          )
        )
      ),

      li(
        h2v0("Objectos Util"),

        ul(
          li(a0("v0003/objectos-util/index")),
          li(a0("v0003/objectos-util/array-utilities")),
          li(a0("v0003/objectos-util/collections/index"),
            ul(
              li(a1("v0003/objectos-util/collections/limitations")),
              li(a1("v0003/objectos-util/collections/builders")),
              li(a1("v0003/objectos-util/collections/of")),
              li(a1("v0003/objectos-util/collections/null-handling")),
              li(a1("v0003/objectos-util/collections/to-string")),
              li(a1("v0003/objectos-util/collections/join-method"))
            )
          )
        )
      ),

      li(
        h2v0("Release Notes"),

        ul(
          li(a0("v0003/relnotes/0.3.0")),
          li(a0("v0003/relnotes/0.2.0")),
          li(a0("v0003/relnotes/0.1.0"))
        )
      )
    );
  }

}