/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.writer;

import br.com.objectos.html.attribute.AttributeName;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.tmpl.CompiledTemplate;
import br.com.objectos.html.tmpl.CompiledTemplateVisitor;
import br.com.objectos.html.tmpl.Template;
import java.io.IOException;
import java.io.UncheckedIOException;
import objectos.lang.Check;
import objectos.util.UnmodifiableList;

public class SimpleTemplateWriter implements CompiledTemplateVisitor {

  private final Appendable out;

  public SimpleTemplateWriter(Appendable out) {
    this.out = Check.notNull(out, "out == null");
  }

  @Override
  public final void visitAttribute(AttributeName name) {
    visitAttribute(name.getName());
  }

  @Override
  public final void visitAttribute(AttributeName name, UnmodifiableList<String> values) {
    visitAttribute(name.getName(), values.join(" "));
  }

  @Override
  public final void visitAttribute(AttributeName name, String value) {
    visitAttribute(name.getName(), value);
  }

  @Override
  public final void visitAttribute(String name) {
    append(' ');
    append(name);
  }

  @Override
  public final void visitAttribute(String name, String value) {
    append(' ');
    append(name);
    append('=');
    append('"');
    append(value);
    append('"');
  }

  @Override
  public final void visitEndTag(ElementName element) {
    visitEndTag(element.getName());
  }

  @Override
  public final void visitEndTag(String name) {
    append('<');
    append('/');
    append(name);
    append('>');
  }

  @Override
  public final void visitRaw(String raw) {
    append(raw);
  }

  @Override
  public final void visitStartTag(ElementName element) {
    visitStartTag(element.getName());
  }

  @Override
  public final void visitStartTag(String name) {
    append('<');
    append(name);
  }

  @Override
  public final void visitStartTagEnd() {
    append('>');
  }

  @Override
  public final void visitStartTagEndSelfClosing() {
    append('>');
  }

  @Override
  public final void visitText(String text) {
    try {
      visitText0(text);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public final void write(Template template) {
    CompiledTemplate compiled = template.compile();
    compiled.acceptTemplateVisitor(this);
  }

  private void append(char c) {
    try {
      out.append(c);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void append(String s) {
    try {
      out.append(s);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void visitText0(String text) throws IOException {
    char[] charArray;
    charArray = text.toCharArray();

    for (char c : charArray) {
      switch (c) {
        default:
          out.append(c);
          break;
        case '"':
          out.append("&quot;");
          break;
        case '&':
          out.append("&amp;");
          break;
        case '<':
          out.append("&lt;");
          break;
        case '>':
          out.append("&gt;");
          break;
        case '\u00A9':
          out.append("&copy;");
          break;
      }
    }
  }

}
