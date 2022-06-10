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
package objectos.docs.next.relnotes;

import objectos.ssg.SiteDirectory;

public final class RelNotes extends SiteDirectory {

  @Override
  protected final void configure() {
    addPage("index.html", new RelNotesIndex());

    addPage("0.2.0.html", new RelNotes0_2_0());
    addPage("0.1.0.html", new RelNotes0_1_0());
  }

}
