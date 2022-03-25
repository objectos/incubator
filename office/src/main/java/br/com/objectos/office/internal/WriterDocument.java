/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.office.internal;

import br.com.objectos.core.object.Checks;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.office.writer.Format;
import br.com.objectos.office.writer.WriterDsl;
import br.com.objectos.office.writer.WriterScript;
import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XStorable;
import com.sun.star.lang.XComponent;
import com.sun.star.text.ControlCharacter;
import com.sun.star.text.XText;
import com.sun.star.text.XTextCursor;
import com.sun.star.text.XTextDocument;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.XReplaceDescriptor;
import com.sun.star.util.XReplaceable;
import java.io.IOException;
import java.net.URI;

final class WriterDocument extends Document implements WriterDsl {

  private final XTextDocument document;
  private final XText text;

  WriterDocument(XTextDocument document) {
    this.document = document;

    text = document.getText();
  }

  public static WriterDocument create(XComponent component) {
    XTextDocument document;
    document = UnoRuntime.queryInterface(XTextDocument.class, component);

    return new WriterDocument(document);
  }

  @Override
  public final void close() {
    document.dispose();
  }

  @Override
  public final String extractText() {
    return text.getString();
  }

  @Override
  public final void p(String... lines) {
    Checks.checkNotNull(lines, "lines == null");

    XTextCursor cursor;
    cursor = text.createTextCursor();

    for (int i = 0; i < lines.length; i++) {
      String line;
      line = lines[i];

      if (line == null) {
        throw new NullPointerException("lines[" + i + "] == null");
      }

      text.insertString(cursor, line, false);

      text.insertControlCharacter(cursor, ControlCharacter.LINE_BREAK, false);
    }

    text.insertControlCharacter(cursor, ControlCharacter.PARAGRAPH_BREAK, false);
  }

  @Override
  public final void replace(String target, String replacement) {
    Checks.checkNotNull(target, "target == null");
    Checks.checkNotNull(replacement, "replacement == null");

    XReplaceable replaceable;
    replaceable = queryInterface(XReplaceable.class);

    XReplaceDescriptor descriptor;
    descriptor = replaceable.createReplaceDescriptor();

    descriptor.setSearchString(target);

    descriptor.setReplaceString(replacement);

    replaceable.replaceAll(descriptor);
  }

  @Override
  final void executeWriterScript(WriterScript script) {
    Checks.checkNotNull(script, "script == null");

    script.acceptWriterDsl(this);
  }

  final RegularFile saveTo(Directory directory, Format format) throws IOException {
    XStorable storable;
    storable = queryInterface(XStorable.class);

    String fileName;
    fileName = format.nextFileName();

    ResolvedPath object;
    object = directory.resolve(fileName);

    URI uri;
    uri = object.toUri();

    String url;
    url = uri.toString();

    PropertyValue[] properties;
    properties = Uno.toFilterName(format);

    try {
      storable.storeToURL(url, properties);

      return LocalFs.getRegularFile(uri);
    } catch (com.sun.star.io.IOException e) {
      throw Uno.toJavaException(e);
    }
  }

  private <T> T queryInterface(Class<T> type) {
    return UnoRuntime.queryInterface(type, document);
  }

}
