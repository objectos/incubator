/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

import java.util.Arrays;

final class SimpleToStringList implements ToStringObject {

  private final int size;

  SimpleToStringList(int size) {
    this.size = size;
  }

  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.formatStart(sb, this);

    if (size > 0) {
      ToString.formatFirstPair(sb, depth, "0", "x");

      for (int i = 1; i < size; i++) {
        char[] c;
        c = new char[i + 1];

        Arrays.fill(c, 'x');

        ToString.formatNextPair(
            sb, depth,
            Integer.toString(i), new String(c)
        );
      }
    }

    ToString.formatEnd(sb, depth);
  }

  @Override
  public final String toString() {
    return ToString.toString(this);
  }

}