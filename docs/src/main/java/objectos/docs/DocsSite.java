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

import java.io.IOException;
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

public final class DocsSite extends Site {

  public static final Class<? extends SitePath> INDEX = V0002.INDEX;

  public static final Class<? extends SitePath> WHAT = V0002.WHAT;

  public static final Class<? extends SitePath> VERSIONS = Versions.class;

  public static final String VERSION = V0002.VERSION;

  private Path docs;

  private Path target;

  public DocsSite() {}

  private DocsSite(Path docs, Path target) {
    this.docs = docs;

    this.target = target;
  }

  public static DocsSite create(String docsPathName, String targetPathName) {
    var docs = toPath(docsPathName);

    out.println("Resolved docs path:   " + docs);

    var target = toPath(targetPathName);

    out.println("Resolved target path: " + target);

    return new DocsSite(docs, target);
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      out.println("Invocation: java objectos.docs.DocsSite docs-path target-path");

      return;
    }

    out.println("Running...");

    var site = DocsSite.create(args[0], args[1]);

    site.generate();
  }

  private static Path toPath(String name) {
    var path = Path.of(name);

    path = path.toAbsolutePath();

    Check.argument(Files.isDirectory(path), path, " is not a directory");

    return path;
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

  private void generate() {
    try (var asciidoctor = Asciidoctor.Factory.create()) {
      try (var walk = Files.walk(docs)) {
        walk.filter(Files::isRegularFile)
            .filter(this::isAdoc)
            .forEach(p -> processAdoc(asciidoctor, p));
      }
    } catch (IOException e) {
      err.println("Failed to open or read a file");

      e.printStackTrace();
    }
  }

  private boolean isAdoc(Path path) {
    var filePath = path.getFileName();

    var fileName = filePath.toString();

    return fileName.endsWith(".adoc");
  }

  private void processAdoc(Asciidoctor asciidoctor, Path path) {
    out.println("source: " + path);

    var relativePath = docs.relativize(path);

    var relativeParent = relativePath.getParent();

    var adocName = relativePath.getFileName().toString();

    var htmlName = adocName.replace(".adoc", ".html");

    var targetHtml = target.resolve(relativeParent).resolve(htmlName);

    out.println("target: " + targetHtml);
  }

}
