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
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._protected;
import static br.com.objectos.code.java.Java._this;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.assign;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.fieldAccess;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.javaFile;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;
import static br.com.objectos.code.java.Java.statements;

import br.com.objectos.be.processor.BeDirectoryProcessor;
import br.com.objectos.be.processor.Ids;
import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.ConstructorCode;
import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.declaration.ParameterCode;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.java.statement.Statement;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.core.list.MutableList;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class DirectoryGenerator implements DirectoryFacade {

  private final NamedClass className;

  private final MutableList<Statement> configureStatements = MutableList.create();
  private final Set<String> directoryIdentifiers = new HashSet<>();

  private final Map<PackageName, Directory> directoryMap = new HashMap<>();
  private final Map<NamedClass, MethodInvocation> methodInvocationMap = new HashMap<>();
  private final MutableList<ClassCode> nestedImpls = MutableList.create();

  private DirectoryGenerator(NamedClass className) {
    this.className = className;
  }

  public static DirectoryGenerator build(PackageName packageName) {
    String packageSimpleName;
    packageSimpleName = packageName.getSimpleName();

    String classSimpleName;
    classSimpleName = JavaNames.toValidClassName(packageSimpleName + "Directory");

    NamedClass className;
    className = packageName.nestedClass(classSimpleName);

    return new DirectoryGenerator(className);
  }

  public final void acceptJavaFileConsumer(JavaFileConsumer round) throws IOException {
    round.acceptJavaFile(generate());
  }

  public final void addProcessedResourceImage(ProcessedResourceImage image) {
    configureStatements.add(
        image.generateDirectoryConfigureStatement()
    );
  }

  public final void addProcessedResourceType(ProcessedResourceType type) {
    configureStatements.add(
        type.generateDirectoryConfigureStatement(className)
    );

    nestedImpls.add(
        type.generateDirectoryNestedImpl(this)
    );
  }

  public final JavaFile generate() {
    return javaFile(
        className.getPackage(),
        generateClassCode()
    );
  }

  @Override
  public final MethodInvocation getDirectoryMethodInvocation(NamedClass className) {
    MethodInvocation invocation;
    invocation = methodInvocationMap.get(className);

    if (invocation == null) {
      invocation = getDirectoryMethodInvocation0(className);

      methodInvocationMap.put(className, invocation);
    }

    return invocation;
  }

  private ClassCode generateClassCode() {
    ClassCode.Builder classCode = ClassCode.builder();

    classCode.addAnnotation(BeDirectoryProcessor.GENERATED);

    classCode.addModifier(Modifiers.PUBLIC);

    classCode.simpleName(className);

    classCode.superclass(TypeNames.AbstractDirectory);

    ConstructorCode.Builder constructor = ConstructorCode.builder();

    constructor.addModifier(Modifiers.PUBLIC);

    for (Directory directory : directoryMap.values()) {
      directory.acceptClassCodeBuilder(classCode);
      directory.acceptConstructorCodeBuilder(constructor);
    }

    classCode.addConstructor(constructor.build());

    classCode.addMethod(generateConfigureMethod());

    classCode.addTypes(nestedImpls);

    return classCode.build();
  }

  private MethodCode generateConfigureMethod() {
    return method(
        annotation(Override.class),
        _protected(), _final(), Ids.configure,
        statements(configureStatements)
    );
  }

  private Directory getDirectory(PackageName packageName) {
    NamedClass className;
    className = PathGenerator.getClassName(packageName);

    String directoryId;
    directoryId = JavaNames.toValidMethodName(className.getSimpleName());

    if (!directoryIdentifiers.add(directoryId)) {
      throw new UnsupportedOperationException("Implement me");
    }

    Identifier identifier;
    identifier = id(directoryId);

    return new Directory(className, identifier);
  }

  private MethodInvocation getDirectoryMethodInvocation0(NamedClass generatedResource) {
    PackageName generatedResourcePackageName;
    generatedResourcePackageName = generatedResource.getPackage();

    Directory directory;
    directory = directoryMap.get(generatedResourcePackageName);

    if (directory == null) {
      directory = getDirectory(generatedResourcePackageName);

      directoryMap.put(generatedResourcePackageName, directory);
    }

    return directory.getDirectoryMethodInvocation(generatedResource);
  }

  private static class Directory {
    final NamedClass className;
    final Identifier identifier;

    Directory(NamedClass className, Identifier identifier) {
      this.className = className;
      this.identifier = identifier;
    }

    final void acceptClassCodeBuilder(ClassCode.Builder builder) {
      builder.addField(generateField());
    }

    final void acceptConstructorCodeBuilder(ConstructorCode.Builder builder) {
      builder.addParameter(generateParameter());

      builder.addStatement(
          assign(fieldAccess(_this(), identifier), identifier)
      );
    }

    final MethodInvocation getDirectoryMethodInvocation(NamedClass generatedResource) {
      return invoke(identifier, ProcessedResource.methodName(generatedResource));
    }

    private FieldCode generateField() {
      return field(_private(), _final(), className, identifier);
    }

    private ParameterCode generateParameter() {
      return param(className, identifier);
    }
  }

}
