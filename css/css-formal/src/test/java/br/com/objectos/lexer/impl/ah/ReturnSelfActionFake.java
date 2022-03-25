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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.impl.ah.model.Answer;
import br.com.objectos.lexer.impl.ah.model.Bit;

class ReturnSelfActionFake {

  public static final ReturnSelfAction Answer_NO = new ReturnSelfAction(0, Answer.NO);
  public static final ReturnSelfAction Answer_YES = new ReturnSelfAction(1, Answer.YES);

  public static final ReturnSelfAction Bit_ZERO = new ReturnSelfAction(0, Bit.ZERO);
  public static final ReturnSelfAction Bit_ONE = new ReturnSelfAction(1, Bit.ONE);

  private ReturnSelfActionFake() {
  }

}