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

public class JavaRendererTest implements LanguageRenderer.Output {

  private final StringBuilder out = new StringBuilder();

  private final JavaRenderer renderer = new JavaRenderer();

  @BeforeMethod
  public void beforeMethod() {
    out.setLength(0);
  }

  @Test(enabled = false)
  public void testCase01() {
    renderer.render(this, "package com.example;");
    assertEquals(
      out,
      """
      <span mikkk>package</span><span hsf> </span><span xzx>com</span><span wdm>.</span><span xzx>example</span><span wdm>;</span>"""
    );
  }

  @Test(enabled = false)
  public void testCase02() {
    renderer.render(this, "char c = 'a';");
    assertEquals(
      out,
      """
      <span mikkk>char</span>\
      <span hsf> </span>\
      <span xzx>c</span>\
      <span hsf> </span>\
      <span xzx>=</span>\
      <span xzx> </span>\
      <span xzx>'a'</span>\
      <span wdm>;</span>"""
    );
  }

  @Override
  public final void languageSpan(ClassSelector clazz, String contents) {
    out.append("<span ");
    out.append(clazz.className());
    out.append(">");
    out.append(contents);
    out.append("</span>");
  }

  @Override
  public final void languageText(String text) {
    out.append(text);
  }

}
