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

import objectos.lang.Check;

final class Hrefs {

  private Hrefs() {}

  public static String sibling(String original, String fileName) {
    validateName(fileName);

    int last;
    last = original.lastIndexOf('/');

    if (last == -1) {
      String msg;
      msg = """
            Could not find the last '/' character in the following url:

                %s
            """.formatted(original);

      throw new IllegalArgumentException(msg);
    }

    String root;
    root = original.substring(0, last);

    return root + '/' + fileName;
  }

  public static void validateName(String fileName) {
    Check.notNull(fileName, "fileName == null");
    Check.argument(!fileName.isEmpty(), "fileName cannot be empty");

    // TODO: implement me
  }

}