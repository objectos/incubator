/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.objectos.http.server;

import java.util.Iterator;
import java.util.NoSuchElementException;
import objectos.lang.Check;

public class RequestedPath implements Iterator<RequestedPart> {

  private final char[] charArray;

  private boolean computed;
  private RequestedPart next;

  private int index;
  private Builder builder = new Builder(0);

  public RequestedPath(String path) {
    Check.notNull(path, "path == null");
    charArray = path.toCharArray();
  }

  @Override
  public boolean hasNext() {
    if (!computed) {
      compute();
    }
    return next != null;
  }

  @Override
  public RequestedPart next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    RequestedPart part = next;
    computed = false;
    next = null;
    return part;
  }

  public String substring(int offset) {
    return new String(charArray, offset, charArray.length - offset);
  }

  @Override
  public String toString() {
    return new String(charArray);
  }

  private void compute() {
    while (!endOfString()) {
      char nextChar = nextChar();

      switch (nextChar) {
      case '/':
        doSlash();
        break;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        doNumber();
        break;
      default:
        doChar();
        break;
      }

      if (computed) {
        return;
      }
    }

    processNext();
    computed = true;
  }

  private void doChar() {
    builder.text();
    builder.append();
  }

  private void doNumber() {
    builder.append();
  }

  private void doSlash() {
    builder.skip();
    processNext();
  }

  private boolean endOfString() {
    return index == charArray.length;
  }

  private void processNext() {
    if (!builder.isEmpty()) {
      computed = true;
      next = builder.build();
      builder = new Builder(index);
    }
  }

  private char nextChar() {
    return charArray[index++];
  }

  private class Builder {

    private int start;
    private int length;
    private boolean text;

    public Builder(int start) {
      this.start = start;
    }

    public RequestedPart build() {
      return text
          ? new StringRequestedPart(charArray, start, length)
          : new IntRequestedPart(charArray, start, length);
    }

    public void append() {
      length++;
    }

    public boolean isEmpty() {
      return length == 0;
    }

    public void text() {
      text = true;
    }

    public void skip() {
      if (isEmpty()) {
        start++;
      }
    }

  }

}