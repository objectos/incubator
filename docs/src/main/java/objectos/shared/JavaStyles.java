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
package objectos.shared;

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.select.ClassSelector;

final class JavaStyles {

  public static final ClassSelector _ANNOTATION = TextColor.pink600;

  public static final ClassSelector _COMMENT = TextColor.fuchsia700;

  public static final ClassSelector _IDENTIFIER = Css.randomDot(3);

  public static final ClassSelector _KEYWORD = TextColor.blue600;

  public static final ClassSelector _DIGITS = TextColor.red600;

  public static final ClassSelector _STRING = TextColor.green700;

  public static final ClassSelector _TOKEN = Css.randomDot(3);

  public static final ClassSelector _WS = Css.randomDot(3);

  private JavaStyles() {}

}