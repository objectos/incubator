/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.resource;

import br.com.objectos.be.annotations.Be;
import br.com.objectos.be.annotations.Name;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.Template;

public class Resources {

  private Resources() {}

  public static String getFilename(String simpleName, String extension) {
    return new StringBuilder()
        .append(simpleName.toLowerCase())
        .append('.')
        .append(extension)
        .toString();
  }

  public static String getFilename(StyleSheet sheet) {
    Class<? extends StyleSheet> sheetClass;
    sheetClass = sheet.getClass();

    return getFilename0(sheetClass, "css");
  }

  public static String getFilename(Template template) {
    Class<? extends Template> templateClass;
    templateClass = template.getClass();

    return getFilename0(templateClass, "html");
  }

  private static String getFilename0(Class<?> thatClass, String extension) {
    Name name;
    name = thatClass.getAnnotation(Name.class);

    if (name != null) {
      return name.value();
    }

    Class<?> beClass;
    beClass = thatClass;

    while (!beClass.isAnnotationPresent(Be.class)) {
      Class<?> superclass;
      superclass = beClass.getSuperclass();

      if (superclass == null) {
        break;
      }

      if (superclass.equals(Object.class)) {
        break;
      }

      beClass = superclass;
    }

    String simpleName;
    simpleName = beClass.getSimpleName();

    return getFilename(simpleName, extension);
  }

}
