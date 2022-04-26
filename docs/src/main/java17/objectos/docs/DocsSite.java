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

import objectos.docs.next.NextDir;
import objectos.docs.ui.Md;
import objectos.ssg.Site;
import objectos.ssg.SitePath;

public final class DocsSite extends Site {

  public static final Class<? extends SitePath> INDEX = NextDir.INDEX;

  @Override
  protected final void configure() {
    addObject(new Md());
    addObject(new StringBuilder());

    addDirectory("next", new NextDir());
  }

}
