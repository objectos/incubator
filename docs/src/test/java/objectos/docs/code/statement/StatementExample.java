/*
 * Copyright 2023 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.code.statement;

import objectos.code.JavaTemplate;

abstract class StatementExample extends JavaTemplate {
  static final ClassTypeName SYSTEM = classType(System.class);

  @Override
  protected final void definition() {
    autoImports();
    _class("Statement");
    body(
      method(this::example)
    );
  }

  abstract void example();

}