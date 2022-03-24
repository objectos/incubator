/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

final class SimpleToStringObject implements ToStringObject {

  private final Object value;

  SimpleToStringObject(Object value) {
    this.value = value;
  }

  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, this,
        "value", value
    );
  }

  @Override
  public final String toString() {
    return ToString.toString(this);
  }

}