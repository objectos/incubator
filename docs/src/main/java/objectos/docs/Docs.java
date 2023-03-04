/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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

import static java.lang.System.out;

import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.css.sheet.StyleSheetWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import objectos.asciidoc.AsciiDoc;
import objectos.asciidoc.Document;
import objectos.html.HtmlFragment;
import objectos.lang.Check;
import objectos.shared.HtmlWriter;
import objectos.shared.StyleClassSet;
import objectos.util.GrowableMap;

public final class Docs extends DocsInjector {

  public interface BottomBar {
    HtmlFragment toFragment();
  }

  public interface TopBar {
    String javaScript();

    HtmlFragment toFragment();
  }

  public static final String INDEX = "docs/0.4/index";

  public static final String OVERVIEW = "docs/0.4/intro/overview";

  public static final String LATEST = "0.4.4";

  private final AsciiDoc asciiDoc = AsciiDoc.create();

  private final BottomBar bottomBar;

  private final Map<String, DocumentRecord> documents = new GrowableMap<>();

  private final DocumentTitleProcessor documentTitleProcessor = new DocumentTitleProcessor();

  private final HtmlWriter htmlWriter = new HtmlWriter();

  private final LeftBar leftBar = new LeftBar(this);

  private final Path source;

  private final Path target;

  private final Map<String, DocsTemplate> templates;

  private final TopBar topBar;

  private final StyleClassSet styleClassSet = new StyleClassSet();

  private final StyleSheet styleSheet = new DocsCss();

  private final StyleSheetWriter styleSheetWriter = StyleSheetWriter.ofPretty();

  private String baseHref = "";

  private String currentKey;

  private HtmlFragment currentLeftBar;

  private DocumentRecord currentRecord;

  private Version currentVersion;

  private IOException rethrow;

  private Predicate<Path> sourceFilter = (path) -> true;

  Docs(Path source, Path target, TopBar topBar, BottomBar bottomBar) {
    this.source = source;

    this.target = target;

    this.topBar = topBar;

    this.bottomBar = bottomBar;

    templates = _templates(
      new ArticleTemplate(this),

      new VersionsTemplate(this)
    );
  }

  public static Docs create(Path source, Path target, TopBar topBar, BottomBar bottomBar) {
    Check.notNull(source, "source == null");
    Check.notNull(target, "target == null");
    Check.notNull(topBar, "topBar == null");
    Check.notNull(bottomBar, "bottomBar == null");

    return new Docs(source, target, topBar, bottomBar);
  }

  public static void main(String[] args) throws IOException {
    if (args.length < 2 || args.length > 3) {
      out.println("Invocation: java objectos.docs.DocsSite source-path target-path [key]");

      return;
    }

    out.println("Running...");

    var site = new Docs(
      main0ParseDirectory("source", args[0]),

      main0ParseDirectory("target", args[1]),

      new DocsTopBar(),

      new DocsBottomBar()
    );

    site.development();

    site.parseArgs(args);

    site.execute();
  }

  private void parseArgs(String[] args) {
    if (args.length == 3) {
      leftBar.skip();

      var a = args[2];

      sourceFilter = (path) -> path.endsWith(a);
    }
  }

  private static Path main0ParseDirectory(String name, String pathName) throws IOException {
    var path = Path.of(pathName);

    path = path.toAbsolutePath();

    if (!Files.exists(path)) {
      Files.createDirectory(path);
    }

    Check.argument(Files.isDirectory(path), path, " is not a directory");

    out.println("Resolved " + name + " path: " + path);

    return path;
  }

  public final void development() {
    baseHref = target.toString();
  }

  public final void execute() throws IOException {
    scan();

    generate();
  }

  public final void production() {
    baseHref = "/docs";
  }

  @Override
  final HtmlFragment $bottomBar() { return bottomBar.toFragment(); }

  @Override
  final Document $document() { return currentRecord.document(); }

  @Override
  final String $elink(String target) {
    var first = target.indexOf('/');

    var versionKey = target.substring(0, first);

    var version = Version.parse(versionKey);

    var key = target.substring(first + 1);

    return baseHref + "/" + version.slug + "/" + key + ".html";
  }

  @Override
  final String $href() {
    var location = currentRecord.location();

    return location.href();
  }

  @Override
  final String $href(String key) {
    var record = documents.get(key);

    if (record == null) {
      throw new NoSuchElementException(key);
    }

    var location = record.location();

    return location.href();
  }

  @Override
  final String $ilink(String target) {
    var key = toKey(target);

    return $href(key);
  }

  @Override
  final boolean $isCurrentKey(String key) { return Objects.equals(currentKey, key); }

  @Override
  final boolean $isNext() { return currentKey.startsWith("next/"); }

  @Override
  final HtmlFragment $leftBar() { return currentLeftBar; }

  @Override
  final DocumentRecord $record(String key) {
    var record = documents.get(key);

    if (record == null) {
      throw new NoSuchElementException(key);
    }

    return record;
  }

  @Override
  final DocumentTitle $title() { return currentRecord.title(); }

  @Override
  final TopBar $topBar() { return topBar; }

  @Override
  final Version $version() { return currentVersion; }

  private String _key(Path file, int fileExtLength) {
    var path = source.relativize(file);

    var key = path.toString();

    return key.substring(0, key.length() - fileExtLength);
  }

  private DocsTemplate _template(String templateName) {
    var tmpl = templates.get(templateName);

    if (tmpl == null) {
      throw new NoSuchElementException(templateName);
    }

    return tmpl;
  }

  private Map<String, DocsTemplate> _templates(DocsTemplate... templates) {
    var map = new GrowableMap<String, DocsTemplate>();

    for (var template : templates) {
      var clazz = template.getClass();

      var key = clazz.getSimpleName();

      map.put(key, template);
    }

    return map.toUnmodifiableMap();
  }

  private void _write(Path file, String html) throws IOException {
    var parent = file.getParent();

    Files.createDirectories(parent);

    Files.writeString(
      file, html, StandardCharsets.UTF_8,
      StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE
    );
  }

  private void generate() throws IOException {
    for (var entry : documents.entrySet()) {
      currentKey = entry.getKey();

      currentRecord = entry.getValue();

      currentVersion = Version.parseCurrentKey(currentKey);

      if (currentVersion != null) {
        currentLeftBar = leftBar.get(currentKey, currentVersion);
      } else {
        currentLeftBar = null;
      }

      var writePath = currentRecord.resolvePath(target);

      var templateName = currentRecord.templateName();

      var template = _template(templateName);

      template.rawStyle(null);

      var firstPass = template.compile();

      styleClassSet.clear();

      firstPass.acceptTemplateVisitor(styleClassSet);

      styleSheetWriter.filterClassSelectorsByName(styleClassSet);

      template.rawStyle(styleSheetWriter.toString(styleSheet));

      htmlWriter.reset();

      htmlWriter.write(template);

      var html = htmlWriter.toString();

      _write(writePath, html);
    }
  }

  private void scan() throws IOException {
    rethrow = null;

    try (Stream<Path> walk = Files.walk(source)) {
      walk.filter(sourceFilter)
          .filter(Files::isRegularFile)
          .forEach(this::scan);
    }

    if (rethrow != null) {
      throw rethrow;
    }
  }

  private void scan(Path file) {
    try {
      scan0(file);
    } catch (IOException e) {
      if (rethrow == null) {
        rethrow = e;
      } else {
        rethrow.addSuppressed(e);
      }
    }
  }

  private void scan0(Path file) throws IOException {
    var fileName = file.getFileName().toString();

    int lastDot = fileName.lastIndexOf('.');

    var extension = fileName.substring(lastDot);

    switch (extension) {
      case ".adoc" -> scan0AsciiDoc(file);

      default -> throw new UnsupportedOperationException("Implement me :: extension=" + extension);
    }
  }

  private void scan0AsciiDoc(Path file) throws IOException {
    var key = _key(file, 5);

    var source = Files.readString(file, StandardCharsets.UTF_8);

    var document = asciiDoc.parse(source);

    document.process(documentTitleProcessor);

    var documentLocation = DocumentLocation.of(baseHref, key);

    var documentTitle = documentTitleProcessor.create();

    var value = new DocumentRecord(document, documentLocation, documentTitle);

    documents.put(key, value);
  }

  private String toKey(String target) {
    Check.state(currentVersion != null, "currentVersion is not set");

    return currentVersion.directory + "/" + target;
  }

}