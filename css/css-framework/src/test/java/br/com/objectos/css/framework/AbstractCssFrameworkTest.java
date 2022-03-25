/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCss framework (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.css.framework;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.sheet.StyleSheet;

public abstract class AbstractCssFrameworkTest {

  protected final void test(StyleSheet sheet, String... expected) {
    assertHasLines(sheet.toString(), expected);
  }

  private void assertHasLines(String string, String[] expected) {
    String[] split = string.split("\n");

    assertEquals(split, expected);
  }

}
