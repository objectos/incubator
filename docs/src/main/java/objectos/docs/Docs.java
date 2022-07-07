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
import java.util.List;
import objectos.lang.Check;
import objectos.util.UnmodifiableList;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;

public final class Docs implements AutoCloseable {

  private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

  private String baseHref = "";

  private final ArticlePage articlePage;

  private final IndexPage indexPage;

  private final Pages pages;

  private final TableOfContents tableOfContents;

  private final Path target;

  private final List<Version> versions;

  private Docs(Path target) {
    this.target = target;

    var nextBanner = new NextBanner();

    articlePage = new ArticlePage(nextBanner);

    pages = new Pages();

    tableOfContents = new TableOfContents();

    indexPage = new IndexPage(nextBanner, tableOfContents);

    versions = UnmodifiableList.of(
      Version.NEXT,

      Version.V0_2_0,

      Version.V0_1_0
    );

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
    for (var version : versions) {
      version.generate(this);
    }

    writeTemplate("versions.html", new VersionsPage(baseHref, versions));
  }

  final void generateVersion0Start(String slug) {
    pages.reset(baseHref, slug);

    tableOfContents.clear();
  }

  final void generateVersion1PrepareKey(
      String resourceDirectory, String key, Options options) throws IOException {
    // prepare
    pages.put(key);

    tableOfContents.put(key);

    // load document
    var resourceName = resourceDirectory + "/" + key + ".adoc";

    var output = new ByteArrayOutputStream();

    var thisClass = getClass();

    try (var input = thisClass.getResourceAsStream(resourceName)) {
      input.transferTo(output);
    } catch (IOException e) {
      err.println("Failed to load a resource");

      throw e;
    }

    var bytes = output.toByteArray();

    var contents = new String(bytes, StandardCharsets.UTF_8);

    var document = asciidoctor.load(contents, options);

    // set document
    pages.set(key, document);
  }

  final void generateVersion2Write(String slug, String key) throws IOException {
    pages.current(key);

    var templateName = pages.templateName();

    ThisTemplate tmpl = switch (templateName) {
      case "ArticlePage" -> articlePage;
      case "IndexPage" -> indexPage;
      default -> throw new IllegalArgumentException("Unexpected value: " + templateName);
    };

    tmpl.set(pages);

    writeTemplate(slug + "/" + key + ".html", tmpl);
  }

  private void writeTemplate(String pathName, Template tmpl) throws IOException {
    var writePath = target.resolve(pathName);

    var parent = writePath.getParent();

    Files.createDirectories(parent);

    try (var writer = Files.newBufferedWriter(writePath, StandardCharsets.UTF_8)) {
      var s = tmpl.toString();

      writer.write(s);
    } catch (IOException e) {
      err.println("Failed to write a file");

      throw e;
    }
  }

}
