/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class TSObject0 extends AbstractToStringObject {

  final Object typeName;

  TSObject0() {
    this.typeName = this;
  }

  TSObject0(Object typeName) {
    this.typeName = typeName;
  }

  @Override
  public void formatToString(StringBuilder toString, int level) {
    ToString.formatToString(
        toString, level, typeName
    );
  }

}