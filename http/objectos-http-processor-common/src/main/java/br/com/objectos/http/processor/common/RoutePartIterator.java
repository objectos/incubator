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
package br.com.objectos.http.processor.common;

import static br.com.objectos.preconditions.Preconditions.checkNotNull;

import java.util.Iterator;

class RoutePartIterator implements Iterator<RoutePart> {

  private final char[] charArray;
  private int start;
  private int index;
  private int length;
  private boolean parameter;

  private boolean computed;
  private RoutePart next;

  public RoutePartIterator(String path) {
    charArray = checkNotNull(path, "path == null").toCharArray();
  }

  @Override
  public boolean hasNext() {
    if (!computed) {
      compute();
    }
    return next != null;
  }

  @Override
  public RoutePart next() {
    RoutePart part = next;

    start = start + length + 1;
    length = 0;
    parameter = false;

    computed = false;
    next = null;

    return part;
  }

  private void compute() {
    while (!endOfString()) {
      char nextChar = nextChar();
      switch (nextChar) {
        case '/':
          computeNext();
          break;
        case ':':
          parameter = true;
          start++;
          break;
        default:
          increment();
          break;
      }

      if (computed) {
        return;
      }
    }

    computeNext();
    computed = true;
  }

  private void computeNext() {
    if (length != 0) {
      String value = new String(charArray, start, length);
      next = new RoutePart(value, parameter);
      computed = true;
    } else {
      start++;
    }
  }

  private boolean endOfString() {
    return index == charArray.length;
  }

  private void increment() {
    length++;
  }

  private char nextChar() {
    return charArray[index++];
  }

}