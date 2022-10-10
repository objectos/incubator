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

import java.time.LocalDate;
import objectos.util.UnmodifiableList;

enum Version {

  NEXT(name("0.4.0"), slug("next"), directory("next"),
      unreleased(), Status.DEVELOPMENT),

  V0_3_0(name("0.3.0"), slug("0.3"), directory("v0003"),
      releaseDate(2022, 10, 10), Status.LATEST),

  V0_2_0(name("0.2.0"), slug("0.2"), directory("v0002"),
      releaseDate(2022, 6, 13), Status.UNSUPPORTED),

  V0_1_0(name("0.1.0"), slug("0.1"), directory("v0001"),
      releaseDate(2022, 5, 16), Status.UNSUPPORTED);

  static final UnmodifiableList<Version> VALUES = UnmodifiableList.copyOf(values());

  final String name;

  final String slug;

  final String directory;

  final LocalDate releaseDate;

  final Status status;

  private Version(String name,
                  String slug,
                  String directory,
                  LocalDate releaseDate,
                  Status status) {
    this.name = name;
    this.slug = slug;
    this.directory = directory;
    this.releaseDate = releaseDate;
    this.status = status;
  }

  public static Version parse(String key) {
    return switch (key) {
      case "next" -> NEXT;
      case "v0003" -> V0_3_0;
      case "v0002" -> V0_2_0;
      case "v0001" -> V0_1_0;
      default -> throw new UnsupportedOperationException("Implement me :: key=" + key);
    };
  }

  public static Version parseCurrentKey(String key) {
    var index = key.indexOf('/');

    if (index > 0) {
      var prefix = key.substring(0, index);

      return parse(prefix);
    } else {
      return null;
    }
  }

  private static String directory(String s) { return s; }

  private static String name(String s) { return s; }

  private static LocalDate releaseDate(int year, int month, int day) {
    return LocalDate.of(year, month, day);
  }

  private static String slug(String s) { return s; }

  private static LocalDate unreleased() { return null; }

  final String key(String value) {
    return directory + "/" + value;
  }

  final String releaseDateString() {
    return releaseDate == null
        ? "unreleased"
        : releaseDate.toString();
  }

}