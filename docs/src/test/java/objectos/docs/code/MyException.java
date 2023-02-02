/*
 * Copyright 2023 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.code;

import java.io.IOException;
import objectos.code.JavaTemplate;

public class MyException extends JavaTemplate {
  public static void main(String[] args) {
    System.out.println(new MyException());
  }
  @Override
  protected final void definition() {
    code(
      _package("com.example"),

      autoImports(),

      _public(), _class("MyException"), _extends(), t(IOException.class), body()
    );
  }
}