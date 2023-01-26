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
package objectos.docs.util;

import objectos.lang.Check;
import objectos.util.GrowableSet;

public class PostBuilder {
  private String title = "Untitled";

  private final GrowableSet<String> tags = new GrowableSet<>();

  public final Post build() {
    return new Post(
      title,

      tags.toUnmodifiableSet()
    );
  }

  public final PostBuilder tag(String tag) {
    tags.add(tag);

    return this;
  }

  public final PostBuilder title(String title) {
    this.title = Check.notNull(title, "title == null");

    return this;
  }
}