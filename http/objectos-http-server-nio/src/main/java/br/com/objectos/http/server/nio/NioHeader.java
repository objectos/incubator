/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.server.nio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class NioHeader {

  private final String name;

  NioHeader(String name) {
    this.name = name;
  }

  public static NioHeader parse(String line) {
    StringBuilder key = new StringBuilder();
    StringBuilder value = new StringBuilder();
    StringBuilder current = key;

    char[] charArray = line.toCharArray();
    for (char c : charArray) {
      switch (c) {
      case ':':
        if (current != value) {
          current = value;
          break;
        }
      default:
        current.append(c);
        break;
      }
    }

    return new Single(key.toString().trim(), value.toString().trim());
  }

  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return value();
  }

  public abstract String value();

  abstract void addTo(Map<String, NioHeader> headerMap);

  abstract NioHeader merge(Single single);

  private static class Single extends NioHeader {

    private final String value;

    Single(String name, String value) {
      super(name);
      this.value = value;
    }

    @Override
    public String value() {
      return value;
    }

    @Override
    void addTo(Map<String, NioHeader> headerMap) {
      String key = name();
      if (!headerMap.containsKey(key)) {
        headerMap.put(key, this);
      } else {
        NioHeader current = headerMap.get(key);
        headerMap.put(key, current.merge(this));
      }
    }

    @Override
    NioHeader merge(Single single) {
      Many many = new Many(name());
      many.merge(single);
      return many;
    }

  }

  private static class Many extends NioHeader {

    private final List<String> valueList = new ArrayList<>();
    private String value;

    Many(String name) {
      super(name);
    }

    @Override
    public String value() {
      if (value == null) {
        synchronized (this) {
          value = valueList.stream().collect(Collectors.joining(", "));
        }
      }
      return value;
    }

    @Override
    void addTo(Map<String, NioHeader> headerMap) {
      throw new UnsupportedOperationException();
    }

    @Override
    NioHeader merge(Single single) {
      valueList.add(single.value);
      return this;
    }

  }

}