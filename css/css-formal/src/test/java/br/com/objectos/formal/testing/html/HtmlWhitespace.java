/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
package br.com.objectos.formal.testing.html;

import br.com.objectos.formal.testing.Token;
import java.util.Arrays;

public class HtmlWhitespace implements Token {

  private final Iterable<HtmlWhitespaceChar> charList;

  public HtmlWhitespace(Iterable<HtmlWhitespaceChar> charList) {
    this.charList = charList;
  }

  public HtmlWhitespace(HtmlWhitespaceChar... chars) {
    charList = Arrays.asList(chars);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HtmlWhitespace)) {
      return false;
    }
    HtmlWhitespace that = (HtmlWhitespace) obj;
    return toString().equals(that.toString());
  }

  @Override
  public final int hashCode() {
    return toString().hashCode();
  }

  @Override
  public final String toString() {
    StringBuilder b = new StringBuilder();
    for (HtmlWhitespaceChar c : charList) {
      c.toString(b);
    }
    return b.toString();
  }

}