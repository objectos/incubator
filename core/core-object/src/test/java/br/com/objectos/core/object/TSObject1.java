/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class TSObject1 extends TSObject0 {

  final String name1;

  final Object value1;

  TSObject1(Object typeName, String name1, Object value1) {
    super(typeName);
    this.name1 = name1;
    this.value1 = value1;
  }

  TSObject1(String name1, Object value1) {
    this.name1 = name1;
    this.value1 = value1;
  }

  @Override
  public void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, typeName,
        name1, value1
    );
  }

}