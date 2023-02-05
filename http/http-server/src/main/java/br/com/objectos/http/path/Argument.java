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
package br.com.objectos.http.path;

import java.util.Arrays;
import java.util.Objects;

public abstract class Argument {

  Argument() {
  }

  public static Argument intArg(int value) {
    return new IntArgument(value);
  }

  public static Argument stringArg(String value) {
    return new StringArgument(value);
  }

  public static Argument stringArrayArg(String[] value) {
    return new StringArrayArgument(value);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Argument)) {
      return false;
    }
    Argument that = (Argument) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(value(), that.value());
  }

  public int getInt() {
    throw new UnsupportedOperationException();
  }

  public String getString() {
    throw new UnsupportedOperationException();
  }

  public String[] getStringArray() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final int hashCode() {
    return value().hashCode();
  }

  @Override
  public String toString() {
    return value().toString();
  }

  abstract Object value();

  private static class IntArgument extends Argument {

    private final int value;

    public IntArgument(int value) {
      this.value = value;
    }

    @Override
    public int getInt() {
      return value;
    }

    @Override
    Object value() {
      return value;
    }

  }

  private static class StringArgument extends Argument {

    private final String value;

    public StringArgument(String value) {
      this.value = value;
    }

    @Override
    public String getString() {
      return value;
    }

    @Override
    Object value() {
      return value;
    }

  }

  private static class StringArrayArgument extends Argument {

    private final String[] value;

    public StringArrayArgument(String[] value) {
      this.value = value;
    }

    @Override
    public String[] getStringArray() {
      return value;
    }

    @Override
    Object value() {
      return Arrays.asList(value);
    }

  }

}