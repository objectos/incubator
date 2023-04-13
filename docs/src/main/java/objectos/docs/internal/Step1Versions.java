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

import static objectos.docs.internal.Version.directory;
import static objectos.docs.internal.Version.link;
import static objectos.docs.internal.Version.name;
import static objectos.docs.internal.Version.navigation;
import static objectos.docs.internal.Version.releaseDate;
import static objectos.docs.internal.Version.section;
import static objectos.docs.internal.Version.status;

import java.util.LinkedHashMap;
import java.util.Map;
import objectos.docs.internal.Version.VersionOption;

class Step1Versions extends Step0Config {

  final Map<String, Version> versions = new LinkedHashMap<>();

  public final void executeVersions() {
    release20230414();

    archive();
  }

  private void archive() {
    if (mainOnly) {
      return;
    }

    release20230407();
    release20230331();
    release20230324();
    release20230317();
    release20230310();
    release20230303();
    release20221010();
    release20220613();
    release20220516();
  }

  private void release20220516() {
    version(
      name("0.1.0"), directory("0.1"),
      releaseDate(2022, 5, 16),
      status(Status.UNSUPPORTED),

      navigation(
        link("/index"),

        section(
          "Introduction",

          link("/intro/overview"),
          link("/intro/install")
        ),

        section(
          "Objectos Logging",

          link("/logging/index"),

          link("/logging/getting-started/index",
            link("/logging/getting-started/about-logging"),
            link("/logging/getting-started/objectos-logging"),
            link("/logging/getting-started/installing"),
            link("/logging/getting-started/quick-start")
          ),

          link("/logging/logging-guide/index",
            link("/logging/logging-guide/events"),
            link("/logging/logging-guide/logger")
          ),

          link("/logging/no-op-logger/index")
        ),

        section(
          "Release Notes",

          link("/relnotes/0.1.0")
        )
      )
    );
  }

  private void release20220613() {
    version(
      name("0.2.0"), directory("0.2"),
      releaseDate(2022, 6, 13),
      status(Status.UNSUPPORTED),

      navigation(
        link("/index"),

        section(
          "Introduction",

          link("/intro/overview"),
          link("/intro/install")
        ),

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
        ),

        section(
          "Release Notes",

          link("/relnotes/0.2.0"),
          link("/relnotes/0.1.0")
        )
      )
    );
  }

  private void release20221010() {
    version(
      name("0.3.0"), directory("0.3"),
      releaseDate(2022, 10, 10),
      status(Status.UNSUPPORTED),

      navigation(
        link("/index"),

        section(
          "Introduction",

          link("/intro/overview"),
          link("/intro/install")
        ),

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
        ),

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
        ),

        section(
          "Release Notes",

          link("/relnotes/0.3.0"),
          link("/relnotes/0.2.0"),
          link("/relnotes/0.1.0")
        )
      )
    );
  }

  private void release20230303() {
    version(
      name("0.4.4"), directory("0.4"),
      releaseDate(2023, 3, 3),
      status(Status.UNSUPPORTED),

      navigation(
        link("/index"),

        section(
          "Libraries",

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
          "Method Declarations",

          link("/objectos-code/method/index")
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

  private void release20230310() {
    version(
      name("0.5.0"),
      releaseDate(2023, 3, 10),
      status(Status.UNSUPPORTED),

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
          link("/objectos-code/field/modifiers")
        ),

        section(
          "Method Declarations",

          link("/objectos-code/method/index")
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

  private void release20230317() {
    version(
      name("0.5.1"),
      releaseDate(2023, 3, 17),
      status(Status.UNSUPPORTED),

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
          link("/objectos-code/method/modifiers")
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

  private void release20230324() {
    version(
      name("0.5.2"),
      releaseDate(2023, 3, 24),
      status(Status.UNSUPPORTED),

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

  private void release20230331() {
    version(
      name("0.5.3"),
      releaseDate(2023, 3, 31),
      status(Status.UNSUPPORTED),

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
          link("/objectos-code/class/annotations"),
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

  private void release20230407() {
    version(
      name("0.6.0"),
      releaseDate(2023, 4, 7),
      status(Status.UNSUPPORTED),

      navigation(
        link("/index"),

        section(
          "Web",

          link("/objectos-html/index", "Objectos HTML")
        ),

        section(
          "Authoring",

          link("/objectos-asciidoc/index", "Objectos AsciiDoc")
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
          link("/objectos-code/class/annotations"),
          link("/objectos-code/class/type-parameters"),
          link("/objectos-code/class/extends"),
          link("/objectos-code/class/implements"),
          link("/objectos-code/class/permits"),
          link("/objectos-code/class/body")
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
          "Constructor Declarations",

          link("/objectos-code/constructor/index")
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
        "objectos-asciidoc",

        link("/index", "Back"),

        section(
          "Objectos AsciiDoc",

          link("/objectos-asciidoc/index")
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

  private void release20230414() {
    version(
      name("0.6.1"),
      releaseDate(2023, 4, 14),
      status(Status.LATEST),

      navigation(
        link("/index"),

        section(
          "Web",

          link("/objectos-html/index", "Objectos HTML")
        ),

        section(
          "Authoring",

          link("/objectos-asciidoc/index", "Objectos AsciiDoc")
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
          link("/objectos-code/class/annotations"),
          link("/objectos-code/class/type-parameters"),
          link("/objectos-code/class/extends"),
          link("/objectos-code/class/implements"),
          link("/objectos-code/class/permits"),
          link("/objectos-code/class/body")
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
          "Constructor Declarations",

          link("/objectos-code/constructor/index"),
          link("/objectos-code/constructor/modifiers"),
          link("/objectos-code/constructor/annotations")
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
        "objectos-asciidoc",

        link("/index", "Back"),

        section(
          "Objectos AsciiDoc",

          link("/objectos-asciidoc/index")
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
    var version = Version.create(options);

    versions.put(version.directory, version);
  }

}