/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

public class PojoJava17 {

  private final String name;

  private final Subject subject;

  PojoJava17(String name, Subject subject) {
    this.name = name;
    this.subject = subject;
  }

  @Override
  public final boolean equals(Object obj) {
    return obj == this
        || obj instanceof PojoJava17 p
            && Equals.objects(
                name, p.name,
                subject, p.subject
            );
  }

}