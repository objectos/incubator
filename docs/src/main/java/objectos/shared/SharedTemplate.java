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
package objectos.shared;

import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.framework.typography.TextDecoration;
import java.util.Arrays;
import objectos.asciidoc.DocumentAttributes;
import objectos.asciidoc.LinkText;
import objectos.css.Css;
import objectos.css.select.ClassSelector;
import objectos.css.select.IdSelector;
import objectos.html.HtmlTemplate;
import objectos.html.tmpl.Instruction;
import objectos.html.tmpl.StandardElementName;
import objectos.util.IntArrays;
import objectos.util.ObjectArrays;

public abstract class SharedTemplate extends HtmlTemplate {

  public static final IdSelector BODY = Css.randomHash(3);

  public static final ClassSelector LINK_COLOR = TextColor.blue600;

  private Instruction[] valueList = new Instruction[32];

  private int valueListIndex = 0;

  private int[] valueStack = new int[32];

  private int valueStackIndex = -1;

  public static String init() {
    return BODY.toString();
  }

  public final void boldEnd() {
    tagEnd(StandardElementName.STRONG);
  }

  public final void boldStart() {
    tagStart();
  }

  public void documentEnd() {
  }

  public void documentStart(DocumentAttributes attributes) {
    valueListIndex = 0;

    valueStackIndex = -1;
  }

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

  public void headingStart(int level) {
    tagStart();
  }

  public final void italicEnd() {
    tagEnd(StandardElementName.EM);
  }

  public final void italicStart() {
    tagStart();
  }

  public void lineFeed() {}

  public final void link(String href, LinkText text) {
    tagStart();
    linkValues(href);
    text.render();
    tagEnd(StandardElementName.A);
  }

  public void listingBlockEnd() {}

  public void listingBlockStart() {}

  public final void listItemEnd() {
    tagEnd(StandardElementName.LI);
  }

  public void listItemStart() {
    tagStart();
  }

  public final void monospaceEnd() {
    tagEnd(StandardElementName.CODE);
  }

  public void monospaceStart() {
    tagStart();
  }

  public final void paragraphEnd() {
    tagEnd(StandardElementName.P);
  }

  public void paragraphStart() {
    tagStart();
  }

  public void preambleEnd() {}

  public void preambleStart() {}

  public final void sectionEnd() {}

  public final void sectionStart(int level) {}

  public final void sectionStart(int level, String style) {}

  public void sourceCodeBlockEnd() {}

  public void sourceCodeBlockStart(String language) {}

  public void text(String s) {
    addValue0(t(s));
  }

  public final void unorderedListEnd() {
    tagEnd(StandardElementName.UL);
  }

  public void unorderedListStart() {
    tagStart();
  }

  protected final void addValue0(Instruction value) {
    valueList = ObjectArrays.growIfNecessary(valueList, valueListIndex);

    valueList[valueListIndex++] = value;
  }

  protected final void addValue0(Instruction... values) {
    valueList = ObjectArrays.growIfNecessary(valueList, valueListIndex + values.length - 1);

    for (var value : values) {
      valueList[valueListIndex++] = value;
    }
  }

  protected final void addValue0(Instruction v0, Instruction v1, Instruction v2) {
    valueList = ObjectArrays.growIfNecessary(valueList, valueListIndex + 2);

    valueList[valueListIndex++] = v0;
    valueList[valueListIndex++] = v1;
    valueList[valueListIndex++] = v2;
  }

  protected void linkPathTo(String href) {
    addValue0(
      LINK_COLOR,
      TextColor.hover.blue900,
      TextDecoration.underline,

      pathTo(href)
    );
  }

  protected void linkValues(String href) {
    addValue0(
      LINK_COLOR,
      TextColor.hover.blue900,
      TextDecoration.underline,

      href(href)
    );
  }

  @SuppressWarnings("deprecation")
  protected final void tagEnd(StandardElementName name) {
    var values = popValues();

    element(name, values);

    addValue0(elementContents());
  }

  protected final void tagStart() {
    valueStackIndex++;

    valueStack = IntArrays.growIfNecessary(valueStack, valueStackIndex);

    valueStack[valueStackIndex] = valueListIndex;
  }

  private Instruction[] popValues() {
    var start = valueStack[valueStackIndex--];

    var values = Arrays.copyOfRange(valueList, start, valueListIndex);

    valueListIndex = start;

    return values;
  }

}