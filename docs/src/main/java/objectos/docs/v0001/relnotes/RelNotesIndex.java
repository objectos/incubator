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
package objectos.docs.v0001.relnotes;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Release notes

Release notes for all of the Objectos releases.
Each release note will tell you what’s new in each version,
improvements made and fixes included. If a version
includes backwards-incompatible changes we will describe them
in the release notes.

## 0.x releases

- [Objectos 0.1.0 release notes](href:v0001.relnotes.RelNotes0_1_0)

*/
//@formatter:on
@Markdown
final class RelNotesIndex extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Release notes";
  }
}
