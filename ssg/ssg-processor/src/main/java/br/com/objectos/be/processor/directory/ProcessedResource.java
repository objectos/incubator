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
package br.com.objectos.be.processor.directory;

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.method;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.expression.production.ClassInstanceCreationExpression;
import br.com.objectos.code.java.type.NamedClass;

abstract class ProcessedResource {

  final NamedClass className;
  final String resourceName;

  ProcessedResource(AbstractBuilder<?> builder) {
    className = builder.className();
    resourceName = builder.resourceName();
  }

  public static String methodName(NamedClass className) {
    return JavaNames.toValidMethodName(className.getSimpleName());
  }

  public abstract void acceptDirectoryGenerator(DirectoryGenerator generator);

  public abstract void acceptPathGenerator(PathGenerator generator);

  public final MethodCode generatePathMethod(NamedClass implClassName) {
    return method(
        _public(), _final(), className, id(methodName(className)),
        _return(creationExpression(implClassName, invoke("get", l(resourceName))))
    );
  }

  ClassInstanceCreationExpression creationExpression(
      NamedClass implClassName,
      MethodInvocation getMethodInvocation) {
    return _new(implClassName, getMethodInvocation);
  }

  final PackageName packageName() {
    return className.getPackage();
  }

  abstract static class AbstractBuilder<R extends ProcessedResource> {

    AbstractBuilder() {}

    abstract NamedClass className();

    abstract String resourceName();

  }

}
