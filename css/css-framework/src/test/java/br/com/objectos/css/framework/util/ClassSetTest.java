/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCss framework (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.css.framework.util;

import static br.com.objectos.css.select.SelectorFactory.dot;
import static org.testng.Assert.assertEquals;

import br.com.objectos.html.tmpl.AbstractTemplate;
import org.testng.annotations.Test;

public class ClassSetTest {
  
  @Test
  public void ofTest() {
    assertEquals(
        div(ClassSet.of()),
        "<div></div>"
    );
    assertEquals(
        div(ClassSet.of(dot("a"))),
        "<div class=\"a\"></div>"
    );
    assertEquals(
        div(ClassSet.of(dot("a"), dot("b"))),
        "<div class=\"a b\"></div>"
    );
    assertEquals(
        div(ClassSet.of(dot("a"), dot("b"), dot("c"))),
        "<div class=\"a b c\"></div>"
    );
  }

  private String div(ClassSet set) {
    return new AbstractTemplate() {
      @Override
      protected final void definition() {
        div(set);
      }
    }.printMinified();
  }
  
}
