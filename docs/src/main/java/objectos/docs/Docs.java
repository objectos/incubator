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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import objectos.lang.Check;
import objectos.util.UnmodifiableList;

public final class Docs extends DocsInjector {

  private String baseHref = "";

  private final ArticlePage articlePage = new ArticlePage(this);

  private final IndexPage indexPage = new IndexPage(this);

  private final NextBanner nextBanner = new NextBanner(this);

  private final Pages pages = new Pages();

  private final DocumentProcessor processor = new DocumentProcessor();

  private final TableOfContents tableOfContents = new TableOfContents(this);

  private final Path target;

  private final List<Version> versions;

  private final StringBuilder sb = new StringBuilder();

  private Version version;

  private Docs(Path target) {
    this.target = target;

    versions = UnmodifiableList.of(
      Version.NEXT,

      Version.V0_2_0,

      Version.V0_1_0
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

    try {
      var site = Docs.create(args[0]);

      site.development();

      site.generate();
    } catch (IOException e) {
      err.println("Failed to generate site");

      e.printStackTrace();
    }
  }

  public final void development() {
    baseHref = target.toString();
  }

  public final void generate() throws IOException {
    for (var v : versions) {
      version = v;

      version.generate(this);
    }

    writeTemplate("versions.html", new VersionsPage(baseHref, versions));
  }

  @Override
  final String $contents() {
    sb.setLength(0);

    var document = pages.document();

    return document.contents();
  }

  @Override
  final String $doctitle() {
    var document = pages.document();

    return document.title();
  }

  @Override
  final String $doctitle(String key) {
    var document = pages.document(key);

    return document.title();
  }

  @Override
  final String $href() { return pages.href(); }

  @Override
  final String $href(String key) { return pages.href(key); }

  @Override
  final NextBanner $nextBanner() { return nextBanner; }

  @Override
  final String $nextKey() { return pages.nextKey(); }

  @Override
  final String $prevKey() { return pages.prevKey(); }

  @Override
  final TableOfContents $tableOfContents() { return tableOfContents; }

  @Override
  final UnmodifiableList<String> $trail() { return pages.trail(); }

  @Override
  final String $trailTitle(String key) {
    var document = pages.document(key);

    var defaultValue = document.title();

    var title = document.getAttribute("trail-title", defaultValue);

    return pages.stripTags(title);
  }

  @Override
  final Version $version() { return version; }

  final void generateVersion0Start(String slug) {
    pages.reset(baseHref, slug);

    processor.slug(baseHref + "/" +slug);

    tableOfContents.clear();
  }

  final void generateVersion1PrepareKey(String resourceDirectory, String key) throws IOException {
    // prepare
    pages.put(key);

    tableOfContents.put(key);

    // load document
    var document = processor.load(resourceDirectory, key);

    // set document
    pages.set(key, document);
  }

  final void generateVersion2Write(String slug, String key) throws IOException {
    pages.current(key);

    var templateName = pages.templateName();

    BaseTemplate tmpl = switch (templateName) {
      case "ArticlePage" -> articlePage;

      case "IndexPage" -> indexPage;

      default -> throw new IllegalArgumentException("Unexpected value: " + templateName);
    };

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
