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

import static br.com.objectos.collections.Collections.newOrderedGrowableMap;

import br.com.objectos.collections.GrowableMap;
import br.com.objectos.collections.ImmutableMap;
import br.com.objectos.http.server.RequestParameters;
import java.util.NoSuchElementException;

class NioRequestParameters implements RequestParameters {

  private ImmutableMap<String, String> map;
  private final SocketReader reader;
  private boolean ready = false;

  NioRequestParameters(SocketReader reader) {
    this.reader = reader;
  }

  @Override
  public String get(String key) {
    ensureReady();
    checkKey(key);
    return map.get(key);
  }

  private ImmutableMap<String, String> buildMap() {
    ParamMapBuilder map = new ParamMapBuilder();
    String all = reader.readAll();
    String[] lines = all.split("\n");
    for (String line : lines) {
      line = line.trim();

      if (line.isEmpty()) {
        continue;
      }

      if (line.contains(": ")) {
        continue;
      }

      if (line.startsWith("HTTP")) {
        continue;
      }

      char[] charArray = line.toCharArray();
      for (char c : charArray) {
        switch (c) {
          case '=':
            map.valueMode();
            break;
          case '&':
            map.reset();
            break;
          default:
            map.append(c);
        }
      }
    }
    map.reset();
    return map.build();
  }

  private void checkKey(String key) {
    if (!map.containsKey(key)) {
      throw new NoSuchElementException(key);
    }
  }

  private void ensureReady() {
    if (!ready) {
      synchronized (this) {
        map = buildMap();
        ready = true;
      }
    }
  }

  private static class ParamMapBuilder {

    GrowableMap<String, String> map = newOrderedGrowableMap();

    private StringBuilder key = new StringBuilder();
    private StringBuilder sb = key;
    private StringBuilder value = new StringBuilder();

    public void append(char c) {
      sb.append(c);
    }

    public ImmutableMap<String, String> build() {
      return map.toImmutableMap();
    }

    public void reset() {
      if (key.length() != 0) {
        map.put(key.toString(), value.toString());
      }
      key = new StringBuilder();
      value = new StringBuilder();
      sb = key;
    }

    public void valueMode() {
      sb = value;
    }

  }

}