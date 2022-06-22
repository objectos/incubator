/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.logger;

import objectos.util.GrowableList;

final class ReadJobThrowable {

  String canonicalName;

  ReadJobThrowable cause;

  String message;

  StackTraceElement[] stackTrace;

  GrowableList<ReadJobThrowable> suppressed;

  final void addSuppressed(ReadJobThrowable throwable) {
    if (suppressed == null) {
      suppressed = new GrowableList<>();
    }

    suppressed.add(throwable);
  }

  final void printStackTrace(StringBuilder out) {
    printSelf(out);

    for (StackTraceElement element : stackTrace) {
      out.append("\tat ");

      out.append(element);

      out.append('\n');
    }

    if (suppressed != null) {
      for (int i = 0, size = suppressed.size(); i < size; i++) {
        ReadJobThrowable se;
        se = suppressed.get(i);

        se.printEnclosedStackTrace(out, stackTrace, "Suppressed: ", "\t");
      }
    }

    if (cause != null) {
      cause.printEnclosedStackTrace(out, stackTrace, "Caused by: ", "");
    }
  }

  private void printEnclosedStackTrace(StringBuilder out,
      StackTraceElement[] enclosingTrace,
      String caption,
      String prefix) {
    out.append(prefix);

    out.append(caption);

    printSelf(out);

    for (StackTraceElement element : stackTrace) {
      out.append(prefix);

      out.append("\tat ");

      out.append(element);

      out.append('\n');
    }

    if (suppressed != null) {
      for (int i = 0, size = suppressed.size(); i < size; i++) {
        ReadJobThrowable se;
        se = suppressed.get(i);

        se.printEnclosedStackTrace(out, stackTrace, "Suppressed: ", prefix + "\t");
      }
    }

    if (cause != null) {
      cause.printEnclosedStackTrace(out, stackTrace, "Caused by: ", prefix);
    }
  }

  private void printSelf(StringBuilder out) {
    out.append(canonicalName);

    out.append(": ");

    out.append(message);

    out.append('\n');
  }

}