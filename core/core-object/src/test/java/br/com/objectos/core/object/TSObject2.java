/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class TSObject2 extends TSObject1 {

  final String name2;

  final Object value2;

  TSObject2(Object typeName, String name1, Object value1, String name2, Object value2) {
    super(typeName, name1, value1);
    this.name2 = name2;
    this.value2 = value2;
  }

  TSObject2(String name1, Object value1, String name2, Object value2) {
    super(name1, value1);
    this.name2 = name2;
    this.value2 = value2;
  }

  @Override
  public void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, typeName,
        name1, value1,
        name2, value2
    );
  }

}