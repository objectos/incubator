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

import objectos.asciidoc.AsciiDoc.Processor;
import objectos.asciidoc.AttrValue.Empty;
import objectos.asciidoc.AttrValue.TextAttr;

sealed interface AttrValue permits Empty, TextAttr {

  static final class Empty implements AttrValue {
    @Override
    public void render(Processor processor) {
      // noop
    }

    @Override
    public String stringValue() { return ""; }
  }

  record TextAttr(String value) implements AttrValue {
    @Override
    public void render(Processor processor) {
      processor.text(value);
    }

    @Override
    public String stringValue() { return value; }
  }

  static Empty EMPTY = new Empty();

  static AttrValue text(String s) {
    return new TextAttr(s);
  }

  void render(Processor processor);

  String stringValue();

}