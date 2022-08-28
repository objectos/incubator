/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.css.framework.background;

import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.css.sheet.StyleSheetWriter;
import java.util.Set;
import org.testng.annotations.Test;

public class BackgroundColorTest {

  private final StyleSheet sheet = new BackgroundColor();

  @Test
  public void filter() {
    var writer = StyleSheetWriter.ofMinified();

    var filter = Set.of(
      BackgroundColor.amber050.className(),
      BackgroundColor.hover.blue050.className()
    );

    writer.filterClassSelectorsByName(filter::contains);

    System.out.println(writer.toString(sheet));
  }

}