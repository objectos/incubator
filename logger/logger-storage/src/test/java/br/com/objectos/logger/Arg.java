/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.logger;

import objectos.lang.ToString;

abstract class Arg implements ToString.Formattable {

  @Override
  public final String toString() {
    return ToString.of(this);
  }

}