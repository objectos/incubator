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

import br.com.objectos.formal.testing.IsBrick;
import objectos.lang.ToString;

public class HtmlWhitespaceChar implements IsBrick, ToString.Formattable {

  private final IsBrick brick;

  public HtmlWhitespaceChar(IsBrick brick) {
    this.brick = brick;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HtmlWhitespaceChar)) {
      return false;
    }
    HtmlWhitespaceChar that = (HtmlWhitespaceChar) obj;
    return brick.equals(that.brick);
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "", brick
    );
  }

  @Override
  public final int hashCode() {
    return brick.hashCode();
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

  @Override
  public final void toString(StringBuilder b) {
    brick.toString(b);
  }

}