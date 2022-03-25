/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.parser.sheet;

import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.css.type.LengthUnit;

enum LengthToken implements IsTerminal {

  em(LengthUnit.EM),

  ex(LengthUnit.EX),

  ch(LengthUnit.CH),

  rem(LengthUnit.REM),

  vw(LengthUnit.VW),

  vh(LengthUnit.VH),

  vmin(LengthUnit.VMIN),

  vmax(LengthUnit.VMAX),

  cm(LengthUnit.CM),

  mm(LengthUnit.MM),

  q(LengthUnit.Q),

  in(LengthUnit.IN),

  pt(LengthUnit.PT),

  pc(LengthUnit.PC),

  px(LengthUnit.PX);

  private final LengthUnit unit;

  private LengthToken(LengthUnit unit) {
    this.unit = unit;
  }

  static DoubleLength getDouble(DoubleValue value, LengthToken unit) {
    return value.toLength(unit.unit);
  }

  static IntLength getInt(IntValue value, LengthToken unit) {
    return value.toLength(unit.unit);
  }

}