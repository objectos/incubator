/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.mojo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;

abstract class AbstractPrintStream extends PrintStream {

  private static final OutputStream NOOP = new NoopOutputStream();

  private final StringBuilder out = new StringBuilder();

  AbstractPrintStream() {
    super(NOOP);
  }

  @Override
  public final PrintStream append(char c) {
    print(c);

    return this;
  }

  @Override
  public final PrintStream append(CharSequence csq) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final PrintStream append(CharSequence csq, int start, int end) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void close() {
    flush();
  }

  @Override
  public final void flush() {
    if (out.length() > 0) {
      println();
    }
  }

  @Override
  public final PrintStream format(Locale l, String format, Object... args) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final PrintStream format(String format, Object... args) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void print(boolean b) {
    synchronized (out) {
      out.append(b);
    }
  }

  @Override
  public final void print(char c) {
    synchronized (out) {
      out.append(c);
    }
  }

  @Override
  public final void print(char[] s) {
    synchronized (out) {
      out.append(s);
    }
  }

  @Override
  public final void print(double d) {
    synchronized (out) {
      out.append(d);
    }
  }

  @Override
  public final void print(float f) {
    synchronized (out) {
      out.append(f);
    }
  }

  @Override
  public final void print(int i) {
    synchronized (out) {
      out.append(i);
    }
  }

  @Override
  public final void print(long l) {
    synchronized (out) {
      out.append(l);
    }
  }

  @Override
  public final void print(Object obj) {
    String s;

    if (obj != null) {
      s = obj.toString();
    } else {
      s = "null";
    }

    print(s);
  }

  @Override
  public final void print(String s) {
    synchronized (out) {
      out.append(s);
    }
  }

  @Override
  public final PrintStream printf(Locale l, String format, Object... args) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final PrintStream printf(String format, Object... args) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void println() {
    synchronized (out) {
      String msg;
      msg = out.toString();

      writeMessage(msg);

      out.setLength(0);
    }
  }

  @Override
  public final void println(boolean x) {
    print(x);
    println();
  }

  @Override
  public final void println(char x) {
    print(x);
    println();
  }

  @Override
  public final void println(char[] x) {
    print(x);
    println();
  }

  @Override
  public final void println(double x) {
    print(x);
    println();
  }

  @Override
  public final void println(float x) {
    print(x);
    println();
  }

  @Override
  public final void println(int x) {
    print(x);
    println();
  }

  @Override
  public final void println(long x) {
    print(x);
    println();
  }

  @Override
  public final void println(Object x) {
    print(x);
    println();
  }

  @Override
  public final void println(String x) {
    print(x);
    println();
  }

  @Override
  public final void write(byte[] b) throws IOException {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void write(byte[] buf, int off, int len) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void write(int b) {
    throw new UnsupportedOperationException("Implement me");
  }

  abstract void writeMessage(String msg);

  private static class NoopOutputStream extends OutputStream {
    @Override
    public final void write(int b) throws IOException {
      // noop
    }
  }

}
