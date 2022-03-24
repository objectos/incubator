/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class SimpleToStringMap implements ToStringObject {

  private final Map<String, Object> map = new LinkedHashMap<String, Object>();

  SimpleToStringMap() {}

  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.formatStart(sb, this);

    Set<Entry<String, Object>> entrySet;
    entrySet = map.entrySet();

    Iterator<Entry<String, Object>> iterator;
    iterator = entrySet.iterator();

    if (iterator.hasNext()) {
      Entry<String, Object> entry;
      entry = iterator.next();

      String key;
      key = entry.getKey();

      Object value;
      value = entry.getValue();

      ToString.formatFirstPair(sb, depth, key, value);

      while (iterator.hasNext()) {
        entry = iterator.next();

        key = entry.getKey();

        value = entry.getValue();

        ToString.formatNextPair(sb, depth, key, value);
      }
    }

    ToString.formatEnd(sb, depth);
  }

  public final void put(String key, Object value) {
    map.put(key, value);
  }

  @Override
  public final String toString() {
    return ToString.toString(this);
  }

}