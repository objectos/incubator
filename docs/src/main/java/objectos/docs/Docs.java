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

import static java.lang.System.err;
import static java.lang.System.out;

import br.com.objectos.html.tmpl.Template;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import objectos.lang.Check;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;
import org.asciidoctor.ast.Document;

final class Docs implements AutoCloseable {

  private final ArticlePage articlePage = new ArticlePage();
  private final IndexPage indexPage = new IndexPage();

  private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

  private final Pages pages = new Pages();

  private final Path target;

  private String baseHref = "";

  private Docs(Path target) {
    this.target = target;

    var registry = asciidoctor.javaExtensionRegistry();

    registry.inlineMacro(
      new InternalLinkInlineMacro(pages)
    );
  }

  public static Docs create(String targetPathName) {
    var target = Path.of(targetPathName);

    target = target.toAbsolutePath();

    Check.argument(Files.isDirectory(target), target, " is not a directory");

    out.println("Resolved target path: " + target);

    return new Docs(target);
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      out.println("Invocation: java objectos.docs.DocsSite target-path");

      return;
    }

    out.println("Running...");

    try (var site = Docs.create(args[0])) {
      site.development();

      site.generate();
    } catch (IOException e) {
      err.println("Failed to generate site");

      e.printStackTrace();
    }
  }

  @Override
  public final void close() {
    asciidoctor.shutdown();
  }

  public final void development() {
    baseHref = target.toString();
  }

  public final void generate() throws IOException {
    generateVersion(
      "0.3.0-SNAPSHOT", "next", "next",

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
    );
  }

  private void generateVersion(
      String version, String slug, String resourceDirectory, String... keys) throws IOException {
    pages.reset(baseHref, slug);

    var attributes = Attributes.builder()
        .attribute("objectos-version", version)
        .build();

    var options = Options.builder()
        .attributes(attributes)
        .build();

    for (int i = 0; i < keys.length; i++) {
      var key = keys[i];

      pages.put(key);

      var document = loadDocument(resourceDirectory, key, options);

      pages.set(key, document);
    }

    for (int i = 0; i < keys.length; i++) {
      var key = keys[i];

      pages.current(key);

      var templateName = pages.templateName();

      ThisTemplate tmpl = switch (templateName) {
        case "ArticlePage" -> articlePage;
        case "IndexPage" -> indexPage;
        default -> throw new IllegalArgumentException("Unexpected value: " + templateName);
      };

      tmpl.set(pages);

      var writePath = target.resolve(slug + "/" + key + ".html");

      writeDocument(tmpl, writePath);
    }
  }

  private Document loadDocument(
      String resourceDirectory, String key, Options options) throws IOException {
    var resourceName = resourceDirectory + "/" + key + ".adoc";

    var out = new ByteArrayOutputStream();

    var thisClass = getClass();

    try (var inputStream = thisClass.getResourceAsStream(resourceName)) {
      inputStream.transferTo(out);
    } catch (IOException e) {
      err.println("Failed to load a resource");

      throw e;
    }

    var bytes = out.toByteArray();

    var contents = new String(bytes, StandardCharsets.UTF_8);

    return asciidoctor.load(contents, options);
  }

  private void writeDocument(Template tmpl, Path target) throws IOException {
    var parent = target.getParent();

    Files.createDirectories(parent);

    try (var writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8)) {
      var s = tmpl.toString();

      writer.write(s);
    } catch (IOException e) {
      err.println("Failed to write a file");

      throw e;
    }
  }

}
