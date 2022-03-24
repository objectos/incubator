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
package br.com.objectos.http.media;

import java.util.Locale;

public enum TopLevel {

  APPLICATION(ApplicationType.class),

  FONT(FontType.class),

  IMAGE(ImageType.class),

  TEXT(TextType.class);

  private final String simpleName;

  private final Class<? extends Enum<? extends MediaType>> subtypeEnumClass;

  private TopLevel(Class<? extends Enum<? extends MediaType>> subtypeEnumClass) {
    this.subtypeEnumClass = subtypeEnumClass;

    simpleName = name().toLowerCase(Locale.US);
  }

  public final String simpleName() {
    return simpleName;
  }

  public final Class<? extends Enum<? extends MediaType>> subtypeEnumClass() {
    return subtypeEnumClass;
  }

}
