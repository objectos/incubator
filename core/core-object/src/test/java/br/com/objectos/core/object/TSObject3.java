/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class TSObject3 extends TSObject2 {

  final String name3;

  final Object value3;

  TSObject3(Object typeName,
            String name1,
            Object value1,
            String name2,
            Object value2,
            String name3,
            Object value3) {
    super(typeName, name1, value1, name2, value2);
    this.name3 = name3;
    this.value3 = value3;
  }

  TSObject3(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    super(name1, value1, name2, value2);
    this.name3 = name3;
    this.value3 = value3;
  }

  @Override
  public void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, typeName,
        name1, value1,
        name2, value2,
        name3, value3
    );
  }

}