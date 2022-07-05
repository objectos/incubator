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
import objectos.docs.next.Next;
import objectos.docs.ui.Breadcrumbs;
import objectos.docs.ui.Md;
import objectos.docs.ui.NextBanner;
import objectos.docs.ui.PageSwitcher;
import objectos.docs.ui.Pages;
import objectos.docs.ui.TableOfContents;
import objectos.docs.ui.VersionHolder;
import objectos.docs.v0001.V0001;
import objectos.docs.v0002.V0002;
import objectos.lang.Check;
import objectos.ssg.Site;
import objectos.ssg.SitePath;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.ast.Document;

public final class DocsSite extends Site implements AutoCloseable {

  public static final Class<? extends SitePath> INDEX = V0002.INDEX;

  public static final Class<? extends SitePath> WHAT = V0002.WHAT;

  public static final Class<? extends SitePath> VERSIONS = Versions.class;

  public static final String VERSION = V0002.VERSION;

  private final ArticlePage articlePage = new ArticlePage();

  private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

  private Path target;

  public DocsSite() {}

  private DocsSite(Path target) {
    this.target = target;
  }

  public static DocsSite create(String docsPathName, String targetPathName) {
    var target = toPath(targetPathName);

    out.println("Resolved target path: " + target);

    return new DocsSite(target);
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      out.println("Invocation: java objectos.docs.DocsSite docs-path target-path");

      return;
    }

    out.println("Running...");

    try (var site = DocsSite.create(args[0], args[1])) {
      site.generate();
    } catch (IOException e) {
      err.println("Failed to generate site");

      e.printStackTrace();
    }
  }

  private static Path toPath(String name) {
    var path = Path.of(name);

    path = path.toAbsolutePath();

    Check.argument(Files.isDirectory(path), path, " is not a directory");

    return path;
  }

  @Override
  public final void close() {
    asciidoctor.shutdown();
  }

  public final void generate() throws IOException {
    generateVersion(
      "next", "next",

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

  @Override
  protected final void configure() {
    addObject(new Breadcrumbs());
    addObject(new Md());
    addObject(new NextBanner());
    addObject(new Pages());
    addObject(new PageSwitcher());
    addObject(new StringBuilder());
    addObject(new TableOfContents());
    addObject(new VersionHolder());

    addPage("versions.html", new Versions());

    addDirectory("next", new Next());
    addDirectory("0.1", new V0001());
    addDirectory("0.2", new V0002());
  }

  private void generateVersion(
      String slug, String directoryName, String... keys) throws IOException {
    articlePage.setNext(slug.equals("next"));

    for (int i = 0; i < keys.length; i++) {
      var key = keys[i];

      var document = loadDocument(directoryName, key);

      articlePage.set(document);

      var writePath = target.resolve(slug + "/" + key + ".html");

      writeDocument(articlePage, writePath);
    }
  }

  private Document loadDocument(String directoryName, String key) throws IOException {
    var resourceName = directoryName + "/" + key + ".adoc";

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

    return asciidoctor.load(contents, Options.builder().build());
  }

  private void writeDocument(Template tmpl, Path target) throws IOException {
    var parent = target.getParent();

    Files.createDirectories(parent);

    try (var writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8)) {
      var s = articlePage.toString();

      writer.write(s);
    } catch (IOException e) {
      err.println("Failed to write a file");

      throw e;
    }
  }

}
