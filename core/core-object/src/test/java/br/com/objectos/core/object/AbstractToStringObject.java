/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

abstract class AbstractToStringObject implements ToStringObject {

  @Override
  public final String toString() {
    return ToString.toString(this);
  }

}