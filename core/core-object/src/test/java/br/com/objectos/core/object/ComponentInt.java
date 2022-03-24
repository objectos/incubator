/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

final class ComponentInt {

  private final int value;

  ComponentInt(int value) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    return obj == this || obj instanceof ComponentInt && equals0((ComponentInt) obj);
  }

  private boolean equals0(ComponentInt that) {
    return value == that.value;
  }

}