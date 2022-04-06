/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package br.com.objectos.docs;

import br.com.objectos.css.parser.sheet.Length;
import br.com.objectos.css.type.LengthOrPercentageValue;
import br.com.objectos.css.type.Zero;

public final class Spacing {

  public static final Length PX = Length.px(1);

  public static final LengthOrPercentageValue V0 = Zero.INSTANCE;

  public static final Length V01 = Length.rem(0.25); // 4px

  public static final Length V02 = Length.rem(0.5); // 8px

  public static final Length V03 = Length.rem(0.75); // 12px

  public static final Length V04 = Length.rem(1); // 16px

  public static final Length V05 = Length.rem(1.25);

  public static final Length V06 = Length.rem(1.5); // 24px

  public static final Length V08 = Length.rem(2); // 32px

  public static final Length V10 = Length.rem(2.5); // 40px

  public static final Length V11 = Length.rem(2.75); // 44px

  public static final Length V12 = Length.rem(3); // 48px

  public static final Length V14 = Length.rem(3.5); // 56px

  public static final Length V16 = Length.rem(4);

  public static final Length V20 = Length.rem(5);

  public static final Length V24 = Length.rem(6);

  public static final Length V32 = Length.rem(8);

  public static final Length V40 = Length.rem(10);

  public static final Length V48 = Length.rem(12);

  public static final Length V56 = Length.rem(14);

  public static final Length V64 = Length.rem(16);

  private Spacing() {}

}