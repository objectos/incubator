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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import objectos.util.UnmodifiableList;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;

record Version(String name,
               String slug,
               String resourceDirectory,
               LocalDate releaseDate,
               List<String> keys) {

  static final Version NEXT = new Version(
    name("0.3.0-SNAPSHOT"), slug("next"), resourceDirectory("next"),
    unreleased(),

    keys(
      "index",

      "intro/index",
      "intro/overview",
      "intro/installation",

      "objectos-lang/index",
      "objectos-lang/Check",
      "objectos-lang/Equals",
      "objectos-lang/HashCode",
      "objectos-lang/ToString",
      "objectos-lang/note-sink-api/index",
      "objectos-lang/note-sink-api/creating-notes",
      "objectos-lang/note-sink-api/the-note-sink-interface",
      "objectos-lang/note-sink-api/the-no-op-note-sink",

      "relnotes/index",
      "relnotes/0.2.0",
      "relnotes/0.1.0"
    )
  );

  static final Version V0_2_0 = new Version(
    name("0.2.0"), slug("0.2"), resourceDirectory("v0002"),
    releaseDate(2022, 6, 13),

    keys(
      "index",

      "intro/index",
      "intro/overview",
      "intro/installation",

      "objectos-lang/index",
      "objectos-lang/Check",
      "objectos-lang/Equals",
      "objectos-lang/HashCode",
      "objectos-lang/ToString",
      "objectos-lang/note-sink-api/index",
      "objectos-lang/note-sink-api/creating-notes",
      "objectos-lang/note-sink-api/the-note-sink-interface",
      "objectos-lang/note-sink-api/the-no-op-note-sink",

      "relnotes/index",
      "relnotes/0.2.0",
      "relnotes/0.1.0"
    )
  );

  static final Version V0_1_0 = new Version(
    name("0.1.0"), slug("0.1"), resourceDirectory("v0001"),
    releaseDate(2022, 5, 16),

    keys(
      "index",

      "intro/index",
      "intro/overview",
      "intro/install",

      "logging/index",
      "logging/getting-started/index",
      "logging/getting-started/about-logging",
      "logging/getting-started/objectos-logging",
      "logging/getting-started/installing",
      "logging/getting-started/quick-start",
      "logging/logging-guide/index",
      "logging/logging-guide/events",
      "logging/logging-guide/logger",
      "logging/no-op-logger/index",

      "relnotes/index",
      "relnotes/0.1.0"
    )
  );

  private static UnmodifiableList<String> keys(String... keys) {
    return UnmodifiableList.copyOf(keys);
  }

  private static LocalDate releaseDate(int year, int month, int day) {
    return LocalDate.of(year, month, day);
  }

  private static LocalDate unreleased() { return null; }

  private static String name(String s) { return s; }

  private static String slug(String s) { return s; }

  private static String resourceDirectory(String s) { return s; }

  final void generate(Docs site) throws IOException {
    site.generateVersion0Start(slug);

    var attributes = Attributes.builder()
        .attribute("objectos-version", name)
        .build();

    var options = Options.builder()
        .attributes(attributes)
        .build();

    for (var key : keys) {
      site.generateVersion1PrepareKey(resourceDirectory, key, options);
    }

    for (var key : keys) {
      site.generateVersion2Write(slug, key);
    }
  }

}