/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class TSObject4 extends TSObject3 {

  final String name4;

  final Object value4;

  TSObject4(Object typeName,
            String name1,
            Object value1,
            String name2,
            Object value2,
            String name3,
            Object value3,
            String name4,
            Object value4) {
    super(typeName, name1, value1, name2, value2, name3, value3);
    this.name4 = name4;
    this.value4 = value4;
  }

  TSObject4(String name1,
            Object value1,
            String name2,
            Object value2,
            String name3,
            Object value3,
            String name4,
            Object value4) {
    super(name1, value1, name2, value2, name3, value3);
    this.name4 = name4;
    this.value4 = value4;
  }

  @Override
  public void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, typeName,
        name1, value1,
        name2, value2,
        name3, value3,
        name4, value4
    );
  }

}