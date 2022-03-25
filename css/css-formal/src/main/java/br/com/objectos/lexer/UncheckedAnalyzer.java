/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;

public class UncheckedAnalyzer<T> implements AutoCloseable, Iterator<T> {

  private final Analyzer<T> analyzer;

  public UncheckedAnalyzer(Analyzer<T> analyzer) {
    this.analyzer = analyzer;
  }

  @Override
  public final void close() {
    try {
      analyzer.close();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public final boolean hasNext() {
    try {
      return analyzer.hasNext();
    } catch (UndefinedTokenException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public final T next() {
    try {
      return analyzer.next();
    } catch (UndefinedTokenException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public final String toString() {
    return analyzer.toString();
  }

}