/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class TSObject6 extends TSObject5 {

  final String name6;

  final Object value6;

  TSObject6(Object typeName,
            String name1,
            Object value1,
            String name2,
            Object value2,
            String name3,
            Object value3,
            String name4,
            Object value4,
            String name5,
            Object value5,
            String name6,
            Object value6) {
    super(typeName, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5);
    this.name6 = name6;
    this.value6 = value6;
  }

  TSObject6(String name1,
            Object value1,
            String name2,
            Object value2,
            String name3,
            Object value3,
            String name4,
            Object value4,
            String name5,
            Object value5,
            String name6,
            Object value6) {
    super(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5);
    this.name6 = name6;
    this.value6 = value6;
  }

  @Override
  public void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, typeName,
        name1, value1,
        name2, value2,
        name3, value3,
        name4, value4,
        name5, value5,
        name6, value6
    );
  }

}