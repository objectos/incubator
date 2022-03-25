/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCss framework (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.css.framework.reset;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class PreflightTest {

  @Test
  public void test() {
    Preflight preflight;
    preflight = new Preflight();

    String string;
    string = preflight.toString();

    assertNotNull(string);
  }

}
