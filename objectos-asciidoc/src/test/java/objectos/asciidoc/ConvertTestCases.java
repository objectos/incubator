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
package objectos.asciidoc;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;

public class ConvertTestCases {
  private static class HtmlWriter extends Writer {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void close() throws IOException {}

    @Override
    public void flush() throws IOException {}

    @Override
    public String toString() {
      var res = sb.toString();

      sb.setLength(0);

      return res;
    }

    @Override
    public void write(char[] cbuf, int off, int len) {
      sb.append(cbuf, off, len);
    }
  }

  public static void main(String[] args) throws URISyntaxException, IOException {
    if (args.length != 1) {
      System.err.println("ConvertTestCases <src/test/resources path>");

      return;
    }

    var dir = Path.of(args[0], "src/test/resources/objectos/asciidoc");

    var options = Options.builder()
        .headerFooter(true)
        .build();

    try (var doctor = Asciidoctor.Factory.create()) {
      try (var stream = Files.newDirectoryStream(dir, "*.adoc")) {
        var writer = new HtmlWriter();

        for (var path : stream) {
          try (var reader = Files.newBufferedReader(path, UTF_8)) {
            doctor.convert(reader, writer, options);
          }

          var contents = writer.toString();

          var fileName = path.getFileName().toString();

          var target = dir.resolve(fileName.replace(".adoc", ".html"));

          try (var out = Files.newBufferedWriter(target, UTF_8, CREATE, TRUNCATE_EXISTING, WRITE)) {
            out.write(contents);
          }
        }
      }
    }
  }

}