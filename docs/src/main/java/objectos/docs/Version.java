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

enum Version {

  NEXT(
      name("0.3.0-SNAPSHOT"), slug("next"), resourceDirectory("next"),
      unreleased()
  ),

  V0_2_0(
      name("0.2.0"), slug("0.2"), resourceDirectory("v0002"),
      releaseDate(2022, 6, 13)
  ),

  V0_1_0(
      name("0.1.0"), slug("0.1"), resourceDirectory("v0001"),
      releaseDate(2022, 5, 16)

  //    ,    keys(
  //      "index",
  //
  //      "intro/index",
  //      "intro/overview",
  //      "intro/install",
  //
  //      "logging/index",
  //      "logging/getting-started/index",
  //      "logging/getting-started/about-logging",
  //      "logging/getting-started/objectos-logging",
  //      "logging/getting-started/installing",
  //      "logging/getting-started/quick-start",
  //      "logging/logging-guide/index",
  //      "logging/logging-guide/events",
  //      "logging/logging-guide/logger",
  //      "logging/no-op-logger/index",
  //
  //      "relnotes/index",
  //      "relnotes/0.1.0"
  //    )
  );

  final String name;

  final String slug;

  final String resourceDirectory;

  final LocalDate releaseDate;

  private Version(String name, String slug, String resourceDirectory, LocalDate releaseDate) {
    this.name = name;
    this.slug = slug;
    this.resourceDirectory = resourceDirectory;
    this.releaseDate = releaseDate;
  }

  public static Version parse(String key) {
    return switch (key) {
      case "v0001" -> V0_1_0;

      case "v0002" -> V0_2_0;

      case "next" -> NEXT;

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

  private static String name(String s) { return s; }

  private static LocalDate releaseDate(int year, int month, int day) {
    return LocalDate.of(year, month, day);
  }

  private static String resourceDirectory(String s) { return s; }

  private static String slug(String s) { return s; }

  private static LocalDate unreleased() { return null; }

  public final String fullName() {
    return "Objectos v" + name;
  }

}