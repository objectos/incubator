/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.fs;

import br.com.objectos.latest.Concrete.Bridge;
import java.io.File;
import java.net.URI;
import objectos.lang.Check;

@Bridge
class LocalFsJava6 extends LocalFsJavaAny {

  @Override
  final ObjectJavaAny create(File file) {
    Check.notNull(file, "file == null");

    return new ObjectImpl(file);
  }

  @Override
  final ObjectJavaAny create(String first, String... more) {
    File file;
    file = getFile(first, more);

    return new ObjectImpl(file);
  }

  @Override
  final ObjectJavaAny create(URI uri) {
    Check.notNull(uri, "uri == null");

    File file;
    file = new File(uri);

    return new ObjectImpl(file);
  }

  private File getFile(String first, String[] more) {
    Check.notNull(first, "first == null");
    Check.notNull(more, "more == null");

    StringBuilder s;
    s = new StringBuilder(first);

    for (int i = 0; i < more.length; i++) {
      s.append(File.separatorChar);

      String part;
      part = more[i];

      s.append(
          Check.notNull(part, "more[", i, "] == null")
      );
    }

    String result;
    result = s.toString();

    return new File(result);
  }

}