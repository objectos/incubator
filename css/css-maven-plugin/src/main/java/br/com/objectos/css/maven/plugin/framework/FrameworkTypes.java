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
package br.com.objectos.css.maven.plugin.framework;

import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.type.Color;
import br.com.objectos.html.tmpl.AbstractTemplate;

public class FrameworkTypes {

  public static final NamedClass _AbstractStyleSheet = NamedClass.of(AbstractStyleSheet.class);
  public static final NamedClass _AbstractTemplate = NamedClass.of(AbstractTemplate.class);
  public static final NamedClass _ClassSelector = NamedClass.of(ClassSelector.class);
  public static final NamedClass _Color = NamedClass.of(Color.class);
  public static final NamedClass _Css = NamedClass.of(Css.class);
  public static final NamedClass _Keywords = NamedClass.of(Keywords.class);

  private FrameworkTypes() {}

}
