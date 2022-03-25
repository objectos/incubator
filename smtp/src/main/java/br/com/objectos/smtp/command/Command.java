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
package br.com.objectos.smtp.command;

import java.util.Locale;

abstract class Command {

  static final Command EHLO = new EHLO(0);
  static final Command MAIL = new MAIL(1);
  static final Command RCPT = new RCPT(2);
  static final Command DATA = new DATA(3);
  static final Command QUIT = new QUIT(4);

  private static final Command[] VALUES = {
      EHLO,
      MAIL,
      RCPT,
      DATA,
      QUIT
  };

  private final int code;
  private final char[] upper;
  private final char[] lower;

  Command(int code, String upper) {
    this.code = code;
    this.upper = upper.toCharArray();
    this.lower = upper.toLowerCase(Locale.US).toCharArray();
  }

  static Command getByCode(int code) {
    return VALUES[code];
  }

  abstract void execute(CommandInterpreter interpreter);

  final int getCode() {
    return code;
  }

  final boolean matches(CommandParser o) {
    return o.matches(upper, lower);
  }

}
