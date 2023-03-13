/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
import java.util.IdentityHashMap;
import java.util.Map;

final class XmlStyles {

  public static final ClassSelector _TEXT = Css.randomDot(3);

  public static final ClassSelector _TAG_NAME = TextColor.blue600;

  public static final ClassSelector _SYMBOL = TextColor.blue600;

  public static final ClassSelector _ATTR_NAME = TextColor.pink600;

  public static final ClassSelector _ATTR_VALUE = TextColor.green700;

  private static Map<ClassSelector, String> NAMES;

  private XmlStyles() {}

  public static void init() {}

  public static String toName(ClassSelector clazz) {
    initNames();

    return NAMES.getOrDefault(clazz, "null");
  }

  private static void initNames() {
    if (NAMES == null) {
      NAMES = new IdentityHashMap<>();
      NAMES.put(_TEXT, "text");
      NAMES.put(_TAG_NAME, "tag");
      NAMES.put(_SYMBOL, "tag");
      NAMES.put(_ATTR_NAME, "attr-name");
      NAMES.put(_ATTR_VALUE, "attr-value");
    }
  }

}