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

import static br.com.objectos.code.java.Java._class;
import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._implements;
import static br.com.objectos.code.java.Java._int;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java._super;
import static br.com.objectos.code.java.Java.constructor;
import static br.com.objectos.code.java.Java.javaFile;
import static br.com.objectos.code.java.Java.methods;
import static br.com.objectos.code.java.Java.noop;
import static br.com.objectos.code.java.Java.param;

import br.com.objectos.be.processor.BeDirectoryProcessor;
import br.com.objectos.be.processor.Ids;
import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.ClassCodeElement;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.declaration.TypeCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.core.list.MutableList;
import java.io.IOException;

class PathGenerator {

  private final NamedClass className;

  private final NamedClass cssImplClassName;
  private final MutableList<NamedClass> cssInterfaces = MutableList.create();

  private final NamedClass htmlImplClassName;
  private final MutableList<NamedClass> htmlInterfaces = MutableList.create();

  private final NamedClass imageImplClassName;
  private final MutableList<NamedClass> imageInterfaces = MutableList.create();

  private final MutableList<MethodCode> resourceMethods = MutableList.create();

  PathGenerator(NamedClass className) {
    this.className = className;

    cssImplClassName = className.nestedClass("CssImpl");
    htmlImplClassName = className.nestedClass("HtmlImpl");
    imageImplClassName = className.nestedClass("ImageImpl");
  }

  public static PathGenerator build(PackageName packageName) {
    NamedClass className;
    className = getClassName(packageName);

    return new PathGenerator(className);
  }

  public static NamedClass getClassName(PackageName packageName) {
    String packageSimpleName;
    packageSimpleName = packageName.getSimpleName();

    String classSimpleName;
    classSimpleName = JavaNames.toValidClassName(packageSimpleName + "Path");

    return packageName.nestedClass(classSimpleName);
  }

  public final void acceptJavaFileConsumer(JavaFileConsumer round) throws IOException {
    round.acceptJavaFile(generate());
  }

  public final void addCssInstance(ProcessedResourceType instance) {
    addResourceMethod(instance.generatePathMethod(cssImplClassName));
    cssInterfaces.add(instance.className);
  }

  public final void addHtmlInstance(ProcessedResourceType instance) {
    addResourceMethod(instance.generatePathMethod(htmlImplClassName));
    htmlInterfaces.add(instance.className);
  }

  public final void addImageInstance(ProcessedResourceImage instance) {
    addResourceMethod(instance.generatePathMethod(imageImplClassName));
    imageInterfaces.add(instance.className);
  }

  final JavaFile generate() {
    return javaFile(
        className.getPackage(),
        generateClassCode()
    );
  }

  private void addResourceMethod(MethodCode method) {
    resourceMethods.add(method);
  }

  private TypeCode generateClassCode() {
    return _class(
        BeDirectoryProcessor.GENERATED,

        _public(), className, _extends(TypeNames.AbstractPath),

        constructor(
            _public(),
            param(TypeNames.ResolvedUrl, Ids.resolvedUrl),
            _super(Ids.resolvedUrl)
        ),

        methods(resourceMethods),

        generateCssImplIfNecessary(),

        generateHtmlImplIfNecessary(),

        generateImageImplIfNecessary()
    );
  }

  private ClassCode generateCssImpl() {
    return _class(
        _private(), _static(), cssImplClassName,
        _extends(TypeNames.AbstractCssResource),
        _implements(cssInterfaces),

        constructor(
            param(TypeNames.String, Ids.src),
            _super(Ids.src)
        )
    );
  }

  private ClassCodeElement generateCssImplIfNecessary() {
    return cssInterfaces.isEmpty() ? noop() : generateCssImpl();
  }

  private ClassCode generateHtmlImpl() {
    return _class(
        _private(), _static(), htmlImplClassName,
        _extends(TypeNames.AbstractHtmlResource),
        _implements(htmlInterfaces),

        constructor(
            param(TypeNames.String, Ids.src),
            _super(Ids.src)
        )
    );
  }

  private ClassCodeElement generateHtmlImplIfNecessary() {
    return htmlInterfaces.isEmpty() ? noop() : generateHtmlImpl();
  }

  private ClassCode generateImageImpl() {
    return _class(
        _private(), _static(), imageImplClassName,
        _extends(TypeNames.AbstractImageResource),
        _implements(imageInterfaces),

        constructor(
            param(TypeNames.String, Ids.src),
            _super(Ids.src)
        ),

        constructor(
            param(TypeNames.String, Ids.src),
            param(_int(), Ids.width),
            param(_int(), Ids.height),
            _super(Ids.src, Ids.width, Ids.height)
        )
    );
  }

  private ClassCodeElement generateImageImplIfNecessary() {
    return imageInterfaces.isEmpty() ? noop() : generateImageImpl();
  }

}
