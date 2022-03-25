/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCss framework (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.css.framework.border;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.AbstractCssFrameworkTest;
import org.testng.annotations.Test;

public class BorderTest extends AbstractCssFrameworkTest {

  @Test
  public void styleSheet() {
    assertEquals(Border.v0, Css.dot("border-0"));
  }

}
