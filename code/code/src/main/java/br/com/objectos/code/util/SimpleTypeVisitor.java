package br.com.objectos.code.util;

public class SimpleTypeVisitor<R, P> extends SimpleTypeVisitorJava8<R, P> {

  protected SimpleTypeVisitor() {
    super();
  }

  protected SimpleTypeVisitor(R defaultValue) {
    super(defaultValue);
  }

}