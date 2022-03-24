/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

class Subject {

  private final ComponentInt a;

  private final ComponentString b;

  Subject(ComponentInt a, ComponentString b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public final boolean equals(Object obj) {
    return obj == this || obj instanceof Subject && equals0((Subject) obj);
  }

  private boolean equals0(Subject obj) {
    return Equals.objects(
        a, obj.a,
        b, obj.b
    );
  }

}