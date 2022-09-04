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
package objectos.shared;

import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextDecoration;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.html.element.StandardElementName;
import br.com.objectos.html.spi.type.Value;
import br.com.objectos.html.tmpl.AbstractTemplate;
import java.util.Arrays;
import objectos.asciidoc.AsciiDoc;
import objectos.asciidoc.DocumentAttributes;
import objectos.asciidoc.LinkText;
import objectos.util.IntArrays;
import objectos.util.ObjectArrays;

public abstract class SharedTemplate extends AbstractTemplate implements AsciiDoc.Processor {

  public static final ClassSelector LINK_COLOR = TextColor.blue600;

  private Value[] valueList = new Value[32];

  private int valueListIndex = 0;

  private int[] valueStack = new int[32];

  private int valueStackIndex = -1;

  @Override
  public final void boldEnd() {
    tagEnd(StandardElementName.STRONG);
  }

  @Override
  public final void boldStart() {
    tagStart();
  }

  @Override
  public void documentEnd() {
  }

  @Override
  public void documentStart(DocumentAttributes attributes) {
    valueListIndex = 0;

    valueStackIndex = -1;
  }

  @Override
  public void headingEnd(int level) {
    var tagName = switch (level) {
      case 1 -> StandardElementName.H1;

      case 2 -> StandardElementName.H2;

      case 3 -> StandardElementName.H3;

      case 4 -> StandardElementName.H4;

      case 5 -> StandardElementName.H5;

      case 6 -> StandardElementName.H6;

      default -> throw new UnsupportedOperationException(
        "Implement me :: headingLevel=" + level);
    };

    tagEnd(tagName);
  }

  @Override
  public void headingStart(int level) {
    tagStart();
  }

  @Override
  public final void italicEnd() {
    tagEnd(StandardElementName.EM);
  }

  @Override
  public final void italicStart() {
    tagStart();
  }

  @Override
  public void lineFeed() {}

  @Override
  public final void link(String href, LinkText text) {
    tagStart();
    linkValues(href);
    text.render();
    tagEnd(StandardElementName.A);
  }

  @Override
  public void listingBlockEnd() {}

  @Override
  public void listingBlockStart() {}

  @Override
  public final void listItemEnd() {
    tagEnd(StandardElementName.LI);
  }

  @Override
  public void listItemStart() {
    tagStart();
  }

  @Override
  public final void monospaceEnd() {
    tagEnd(StandardElementName.CODE);
  }

  @Override
  public void monospaceStart() {
    tagStart();
  }

  @Override
  public final void paragraphEnd() {
    tagEnd(StandardElementName.P);
  }

  @Override
  public void paragraphStart() {
    tagStart();
  }

  @Override
  public void preambleEnd() {}

  @Override
  public void preambleStart() {}

  @Override
  public final void sectionEnd() {}

  @Override
  public final void sectionStart(int level) {}

  @Override
  public final void sectionStart(int level, String style) {}

  @Override
  public void sourceCodeBlockEnd() {}

  @Override
  public void sourceCodeBlockStart(String language) {}

  @Override
  public void text(String s) {
    addValue0(t(s));
  }

  @Override
  public final void unorderedListEnd() {
    tagEnd(StandardElementName.UL);
  }

  @Override
  public void unorderedListStart() {
    tagStart();
  }

  protected final void addValue0(Value value) {
    valueList = ObjectArrays.copyIfNecessary(valueList, valueListIndex);

    valueList[valueListIndex++] = value;
  }

  protected final void addValue0(Value... values) {
    valueList = ObjectArrays.copyIfNecessary(valueList, valueListIndex + values.length - 1);

    for (var value : values) {
      valueList[valueListIndex++] = value;
    }
  }

  protected final void addValue0(Value v0, Value v1, Value v2) {
    valueList = ObjectArrays.copyIfNecessary(valueList, valueListIndex + 2);

    valueList[valueListIndex++] = v0;
    valueList[valueListIndex++] = v1;
    valueList[valueListIndex++] = v2;
  }

  protected void linkValues(String href) {
    addValue0(
      LINK_COLOR,
      TextColor.hover.blue900,
      TextDecoration.underline,

      href(href)
    );
  }

  protected final void tagEnd(StandardElementName name) {
    var values = popValues();

    var value = addStandardElement(name, values);

    addValue0(value);
  }

  protected final void tagStart() {
    valueStackIndex++;

    valueStack = IntArrays.copyIfNecessary(valueStack, valueStackIndex);

    valueStack[valueStackIndex] = valueListIndex;
  }

  private Value[] popValues() {
    var start = valueStack[valueStackIndex--];

    var values = Arrays.copyOfRange(valueList, start, valueListIndex);

    valueListIndex = start;

    return values;
  }

}