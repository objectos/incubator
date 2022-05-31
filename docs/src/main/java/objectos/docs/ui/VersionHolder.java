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
package objectos.docs.ui;

import objectos.lang.Check;
import objectos.ssg.SiteDirectory;
import objectos.ssg.SitePage;

public final class VersionHolder {

  private static final String PREFIX = "objectos.docs.";

  private String value = "";

  public static String parse(SiteDirectory directory) {
    Class<?> type;
    type = directory.getClass();

    return parse0(type);
  }

  public static String parse(SitePage page) {
    Class<?> type;
    type = page.getClass();

    return parse0(type);
  }

  private static String parse0(Class<?> type) {
    String packageName;
    packageName = type.getPackageName();

    boolean skip;
    skip = !packageName.startsWith(PREFIX);

    if (skip) {
      throw new IllegalArgumentException(packageName);
    }

    int prefixLen;
    prefixLen = PREFIX.length();

    int dot;
    dot = packageName.indexOf('.', prefixLen);

    if (dot == -1) {
      return packageName.substring(prefixLen);
    } else {
      return packageName.substring(prefixLen, dot);
    }
  }

  public final String get() {
    return value;
  }

  public final void set(String value) {
    this.value = Check.notNull(value, "value == null");
  }

}