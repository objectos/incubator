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

import java.nio.file.Path;

record DocumentLocation(String writePath, String href) {

  public static DocumentLocation of(String baseHref, String key) {
    var index = key.indexOf('/');

    var writePath = "";

    if (index > 0) {
      var prefix = key.substring(0, index);

      var version = Version.parse(prefix);

      var shortKey = key.substring(index);

      writePath = version.slug + shortKey + ".html";
    } else {
      writePath = key + ".html";
    }

    return new DocumentLocation(
      writePath, baseHref + "/" + writePath
    );
  }

  public final Path resolvePath(Path target) {
    return target.resolve(writePath);
  }

}