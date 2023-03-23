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
package objectos.docs.internal;

import static objectos.docs.internal.Version2.link;
import static objectos.docs.internal.Version2.name;
import static objectos.docs.internal.Version2.navigation;
import static objectos.docs.internal.Version2.releaseDate;
import static objectos.docs.internal.Version2.section;
import static objectos.docs.internal.Version2.status;

import objectos.docs.internal.Version2.VersionOption;

final class Versions {

  @SuppressWarnings("unused")
  private final DocsInjector injector;

  public Versions(DocsInjector injector) {
    this.injector = injector;
  }

  public final void init() {
    version(
      name("0.5.2"),
      releaseDate(2023, 3, 24),
      status(Status.LATEST),

      navigation(
        link("/index"),

        section(
          "Web",

          link("/objectos-html/index", "Objectos HTML")
        ),

        section(
          "Core",

          link("/objectos-code/index", "Objectos Code"),
          link("/objectos-lang/index", "Objectos Lang"),
          link("/objectos-util/index", "Objectos Util")
        ),

        section(
          "Project",

          link("/intro/overview"),
          link("/intro/install"),
          link("/relnotes/index")
        )
      ),

      navigation(
        "objectos-code",

        link("/index", "Back"),

        section(
          "Objectos Code",

          link("/objectos-code/index"),
          link("/objectos-code/tutorial")
        ),

        section(
          "Java Template",

          link("/objectos-code/template/index"),
          link("/objectos-code/template/auto-imports"),
          link("/objectos-code/template/include"),
          link("/objectos-code/template/recommended-usage")
        ),

        section(
          "Class Declarations",

          link("/objectos-code/class/index"),
          link("/objectos-code/class/name"),
          link("/objectos-code/class/modifiers"),
          link("/objectos-code/class/type-parameters"),
          link("/objectos-code/class/extends"),
          link("/objectos-code/class/implements")
        ),

        section(
          "Field Declarations",

          link("/objectos-code/field/index"),
          link("/objectos-code/field/type"),
          link("/objectos-code/field/name"),
          link("/objectos-code/field/modifiers"),
          link("/objectos-code/field/annotations"),
          link("/objectos-code/field/initializer")
        ),

        section(
          "Method Declarations",

          link("/objectos-code/method/index"),
          link("/objectos-code/method/name"),
          link("/objectos-code/method/return-type"),
          link("/objectos-code/method/modifiers"),
          link("/objectos-code/method/annotations"),
          link("/objectos-code/method/parameters"),
          link("/objectos-code/method/type-parameters"),
          link("/objectos-code/method/throws"),
          link("/objectos-code/method/body")
        ),

        section(
          "Statements",

          link("/objectos-code/statement/index"),
          link("/objectos-code/statement/if"),
          link("/objectos-code/statement/return")
        ),

        section(
          "Expressions",

          link("/objectos-code/expression/class-instance-creation"),
          link("/objectos-code/expression/assignment-operator")
        )
      ),

      navigation(
        "objectos-html",

        link("/index", "Back"),

        section(
          "Objectos HTML",

          link("/objectos-html/index")
        )
      ),

      navigation(
        "objectos-lang",

        link("/index", "Back"),

        section(
          "Objectos Lang",

          link("/objectos-lang/index"),
          link("/objectos-lang/Check"),
          link("/objectos-lang/Equals"),
          link("/objectos-lang/HashCode"),
          link("/objectos-lang/ToString"),
          link("/objectos-lang/note-sink-api/index",
            link("/objectos-lang/note-sink-api/creating-notes"),
            link("/objectos-lang/note-sink-api/the-note-sink-interface"),
            link("/objectos-lang/note-sink-api/the-no-op-note-sink")
          )
        )
      ),

      navigation(
        "objectos-util",

        link("/index", "Back"),

        section(
          "Objectos Util",

          link("/objectos-util/index"),
          link("/objectos-util/array-utilities"),
          link("/objectos-util/collections/index",
            link("/objectos-util/collections/limitations"),
            link("/objectos-util/collections/builders"),
            link("/objectos-util/collections/of"),
            link("/objectos-util/collections/null-handling"),
            link("/objectos-util/collections/to-string"),
            link("/objectos-util/collections/join-method")
          )
        )
      )
    );
  }

  private void version(VersionOption... options) {
    throw new UnsupportedOperationException("Implement me");
  }

}