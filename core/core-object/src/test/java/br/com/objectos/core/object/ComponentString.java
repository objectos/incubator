/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

final class ComponentString {

  private final String value;

  ComponentString(String value) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    return obj == this || obj instanceof ComponentString && equals0((ComponentString) obj);
  }

  private boolean equals0(ComponentString that) {
    return Equals.objects(
        value, that.value
    );
  }

}