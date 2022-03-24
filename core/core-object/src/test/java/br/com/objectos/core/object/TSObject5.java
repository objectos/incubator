/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class TSObject5 extends TSObject4 {

  final String name5;

  final Object value5;

  TSObject5(Object typeName,
            String name1,
            Object value1,
            String name2,
            Object value2,
            String name3,
            Object value3,
            String name4,
            Object value4,
            String name5,
            Object value5) {
    super(typeName, name1, value1, name2, value2, name3, value3, name4, value4);
    this.name5 = name5;
    this.value5 = value5;
  }

  TSObject5(String name1,
            Object value1,
            String name2,
            Object value2,
            String name3,
            Object value3,
            String name4,
            Object value4,
            String name5,
            Object value5) {
    super(name1, value1, name2, value2, name3, value3, name4, value4);
    this.name5 = name5;
    this.value5 = value5;
  }

  @Override
  public void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, typeName,
        name1, value1,
        name2, value2,
        name3, value3,
        name4, value4,
        name5, value5
    );
  }

}