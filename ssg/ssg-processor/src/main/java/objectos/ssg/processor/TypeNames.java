/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
package objectos.ssg.processor;

import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.type.NamedClass;

public class TypeNames {

  public static final NamedClass AbstractFragment = t(
    objectos.html.HtmlTemplate.class
  );

  public static final NamedClass ImageType = t(
    br.com.objectos.http.media.ImageType.class
  );

  public static final NamedClass Markdown = t(
    objectos.ssg.Markdown.class
  );

  public static final NamedClass String = t(String.class);

  private TypeNames() {}

}
