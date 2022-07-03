/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.css.sheet;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.sheet.ex.TestCase00;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PrettyStyleSheetWriterTest {

  private StyleSheetWriter writer;

  private StringBuilder out;

  @BeforeClass
  public void _beforeClass() {
    writer = StyleSheetWriter.ofPretty();

    out = new StringBuilder();
  }

  @BeforeMethod
  public void _beforeMethod() {
    out.setLength(0);
  }

  @Test
  public void testCase00() {
    test(
      new TestCase00(),
      "body {}"
    );
  }

  private void test(StyleSheet sheet, String expected) {
    try {
      writer.writeTo(sheet, out);

      assertEquals(out.toString(), expected);
    } catch (IOException e) {
      throw new AssertionError("StringBuilder does not throw IOException", e);
    }
  }

}