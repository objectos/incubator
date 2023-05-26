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

import static org.testng.Assert.assertEquals;

import objectos.css.select.ClassSelector;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class XmlRendererTest implements LanguageRenderer.Output {

  private final StringBuilder out = new StringBuilder();

  private final XmlRenderer renderer = new XmlRenderer();

  @BeforeMethod
  public void beforeMethod() {
    out.setLength(0);
  }

  @Test
  public void testCase01() {
    renderer.render(this, "<meta charset=\"utf-8\">");

    assertEquals(
      out.toString(),
      """
      <tag><</tag><tag>meta</tag><text> </text><attr-name>charset</attr-name><tag>=</tag><attr-value>"utf-8"</attr-value><tag>></tag>"""
    );
  }

  @Test
  public void testCase02() {
    renderer.render(this, "<!doctype html>");

    assertEquals(
      out.toString(),
      """
      <tag><!</tag><tag>doctype</tag><text> </text><attr-name>html</attr-name><tag>></tag>"""
    );
  }

  @Override
  public final void languageSpan(ClassSelector clazz, String contents) {
    String name = XmlStyles.toName(clazz);

    out.append("<");
    out.append(name);
    out.append(">");
    out.append(contents);
    out.append("</");
    out.append(name);
    out.append(">");
  }

  @Override
  public final void languageText(String text) {
    out.append(text);
  }

}
