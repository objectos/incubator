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
package objectos.docs.internal;

import objectos.asciidoc.DocumentAttributes;

final class DocumentTitleProcessor extends SimpleAsciiDocProcessor {

  private final StringBuilder html = new StringBuilder();

  private final StringBuilder plain = new StringBuilder();

  private DocumentAttributes attributes;

  private boolean write;

  @Override
  public final void boldEnd() {
    tag("</strong>");
  }

  @Override
  public final void boldStart() {
    tag("<strong>");
  }

  @Override
  public final void documentStart(DocumentAttributes attr) {
    html.setLength(0);

    plain.setLength(0);

    attributes = attr;
  }

  @Override
  public final void headingEnd(int level) {
    write = false;
  }

  @Override
  public final void headingStart(int level) {
    write = level == 1;
  }

  @Override
  public final void italicEnd() {
    tag("</em>");
  }

  @Override
  public final void italicStart() {
    tag("<em>");
  }

  @Override
  public final void monospaceEnd() {
    tag("</code>");
  }

  @Override
  public final void monospaceStart() {
    tag("<code>");
  }

  @Override
  public final void text(String s) {
    if (write) {
      HtmlEscape.to(s, html);
      HtmlEscape.to(s, plain);
    }
  }

  final DocumentTitle create() {
    var altTitle = html.toString();

    return new DocumentTitle(
      html.toString(),
      plain.toString(),
      attributes.getOrDefault("toc-title", altTitle)
    );
  }

  private void tag(String string) {
    if (write) {
      html.append(string);
    }
  }

}