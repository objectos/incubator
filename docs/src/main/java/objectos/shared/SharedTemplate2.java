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

import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import objectos.html.HtmlTemplate;

public abstract class SharedTemplate2 extends HtmlTemplate {

  public static final IdSelector BODY = SharedTemplate.BODY;

  public static final ClassSelector LINK_COLOR = SharedTemplate.LINK_COLOR;

  public static String init() {
    return BODY.toString();
  }

}