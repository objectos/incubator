/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

final class Util {

  private Util() {}

  public static String getCanonicalName(TypeElement element, String simpleName) {
    StringBuilder result;
    result = new StringBuilder();

    String packageName;
    packageName = getPackageName(element);

    result.append(packageName);

    result.append('.');

    result.append(simpleName);

    return result.toString();
  }

  public static PackageElement getPackageElement(Element element) {
    ElementKind kind;
    kind = element.getKind();

    while (kind != ElementKind.PACKAGE) {
      element = element.getEnclosingElement();

      kind = element.getKind();
    }

    return PackageElement.class.cast(element);
  }

  public static String getPackageName(Element element) {
    PackageElement packageElement;
    packageElement = Util.getPackageElement(element);

    Name qualifiedName;
    qualifiedName = packageElement.getQualifiedName();

    return qualifiedName.toString();
  }

}