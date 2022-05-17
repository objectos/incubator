/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.model.element;

import br.com.objectos.code.java.declaration.Modifier;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.processing.Reprocessor;
import br.com.objectos.core.set.ImmutableSet;
import java.io.IOException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.tools.StandardLocation;
import objectos.lang.Checks;

public final class ProcessingPackage extends ProcessingElement<PackageElement> {

  ProcessingPackage(ProcessingEnvironment processingEnv, PackageElement subject) {
    super(processingEnv, subject);
  }

  public static ProcessingPackage adapt(
      ProcessingEnvironment processingEnv, PackageElement packageElement) {
    Checks.checkNotNull(processingEnv, "processingEnv == null");
    Checks.checkNotNull(packageElement, "packageElement == null");
    return new ProcessingPackage(processingEnv, packageElement);
  }

  public final void acceptReprocessor(Reprocessor reprocessor) {
    Checks.checkNotNull(reprocessor, "reprocessor == null");
    reprocessor.reprocessPackage(element);
  }

  public final String getCanonicalName() {
    Name qualifiedName;
    qualifiedName = element.getQualifiedName();

    return qualifiedName.toString();
  }

  @Override
  public final ImmutableSet<Modifier> getModifiers() {
    return getModifiersImpl();
  }

  public final ProcessingResource getResource(
      StandardLocation location, String resourceName)
      throws IOException {
    return getResource0(location, toNamedPackage(), resourceName);
  }

  public final String getSimpleName() {
    return toNamedPackage().getSimpleName();
  }

  public final PackageName toNamedPackage() {
    return PackageName.of(this);
  }

}
