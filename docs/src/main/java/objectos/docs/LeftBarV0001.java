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

final class LeftBarV0001 extends LeftBarFragment {

  public LeftBarV0001(LeftBar injector) {
    super(injector);
  }

  @Override
  final void definitionImpl() {
    ul(
      li(a0("v0001/index")),

      li(
        h2v0("Introduction"),

        ul(
          li(a0("v0001/intro/overview")),
          li(a0("v0001/intro/install"))
        )
      ),

      li(
        h2v0("Objectos Logging"),

        ul(
          li(a0("v0001/logging/index")),

          li(a0("v0001/logging/getting-started/index"),
            ul(
              li(a1("v0001/logging/getting-started/about-logging")),
              li(a1("v0001/logging/getting-started/objectos-logging")),
              li(a1("v0001/logging/getting-started/installing")),
              li(a1("v0001/logging/getting-started/quick-start"))
            )
          ),

          li(a0("v0001/logging/logging-guide/index"),
            ul(
              li(a1("v0001/logging/logging-guide/events")),
              li(a1("v0001/logging/logging-guide/logger"))
            )
          ),

          li(a0("v0001/logging/no-op-logger/index"))
        )
      ),

      li(
        h2v0("Release Notes"),

        ul(
          li(a0("v0001/relnotes/0.1.0"))
        )
      )
    );
  }

}