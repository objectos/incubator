/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg;

import java.io.IOException;
import objectos.lang.Check;
import objectos.ssg.Site.RenderingOption;

final class SiteGeneration extends SiteConfiguration {

  private final SiteWriter writer;

  public SiteGeneration(SiteWriter writer) {
    this.writer = writer;
  }

  public static SiteGeneration create(SiteWriter writer, RenderingOption[] options) {
    Check.notNull(writer, "writer == null");
    Check.notNull(options, "options == null");

    return new SiteGeneration(writer);
  }

  public final void write() throws IOException {
    try {
      for (Object o : objects) {
        if (o instanceof SiteWriteable w) {
          w.writeTo(writer);
        }
      }
    } finally {
      postSiteGeneration();
    }
  }

}