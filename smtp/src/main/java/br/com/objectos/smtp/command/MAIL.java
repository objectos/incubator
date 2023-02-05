/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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

import br.com.objectos.smtp.mail.ReversePath;

final class MAIL extends Command {

  MAIL(int code) {
    super(code, "MAIL FROM:");
  }

  @Override
  final void execute(CommandInterpreter interpreter) {
    if (!interpreter.hasCode()) {
      throw new UnsupportedOperationException("Implement me");
    }

    String arg0;

    switch (interpreter.nextCode()) {
      default:
        throw new UnsupportedOperationException("Implement me");
      case ByteCodes.EOL:
        throw new UnsupportedOperationException("Implement me");
      case ByteCodes.PATH:
        arg0 = interpreter.getString();
        break;
    }

    ReversePath reversePath;
    reversePath = interpreter.getReversePath(arg0);

    switch (interpreter.nextCode()) {
      default:
        throw new UnsupportedOperationException("Implement me");
      case ByteCodes.EOL:
        interpreter.visitMAIL(reversePath);
        break;
    }
  }

}
