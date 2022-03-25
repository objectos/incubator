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
package br.com.objectos.be.processor.site;

import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java._var;
import static br.com.objectos.code.java.Java.javaFile;

import br.com.objectos.be.processor.BeSiteProcessor;
import br.com.objectos.be.processor.Ids;
import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.java.type.NamedClass;
import java.io.IOException;

class SiteGenerator {

  private final MethodCode.Builder configureMethod;
  private final HelperGenerator helperGenerator;
  private final ClassCode.Builder siteClass;
  private final PackageName sitePackage;

  private SiteGenerator(Builder builder) {
    sitePackage = builder.getSitePackage();
    siteClass = builder.getSiteClass();
    configureMethod = builder.getConfigureMethod();
    helperGenerator = builder.getHelperGenerator();
  }

  public static SiteGenerator build(PackageName sitePackage) {
    NamedPackageBuilder builder;
    builder = new NamedPackageBuilder(sitePackage);

    return builder.build();
  }

  public final void acceptJavaFileConsumer(JavaFileConsumer round) throws IOException {
    round.acceptJavaFile(generateJavaFile());
  }

  public final void addAtDirectory(AtDirectory directory) {
    configureMethod.addStatement(
        directory.generateConfigureStatement()
    );

    MethodCode siteMethod;
    siteMethod = directory.generateSiteMethod(helperName());

    siteClass.addMethod(siteMethod);

    helperGenerator.addAtDirectory(directory);
  }

  final JavaFile generateJavaFile() {
    return javaFile(sitePackage, generateClass());
  }

  private ClassCode generateClass() {
    siteClass.addMethod(configureMethod.build());

    siteClass.addType(helperGenerator.generateCodeClass());

    return siteClass.build();
  }

  private NamedClass helperName() {
    return helperGenerator.helperName;
  }

  private static abstract class Builder {

    Builder() {}

    public final SiteGenerator build() {
      return new SiteGenerator(this);
    }

    abstract MethodCode.Builder getConfigureMethod();

    abstract HelperGenerator getHelperGenerator();

    abstract ClassCode.Builder getSiteClass();

    abstract PackageName getSitePackage();

  }

  private static class NamedPackageBuilder extends Builder {

    private final HelperGenerator helperGenerator;
    private final NamedClass siteName;
    private final PackageName sitePackage;

    NamedPackageBuilder(PackageName sitePackage) {
      this.sitePackage = sitePackage;

      siteName = getSiteName(sitePackage);

      helperGenerator = HelperGenerator.build(siteName);
    }

    @Override
    final MethodCode.Builder getConfigureMethod() {
      MethodCode.Builder configureMethod;
      configureMethod = MethodCode.builder();

      configureMethod.addAnnotation(Override.class);

      configureMethod.addModifier(Modifiers.PROTECTED, Modifiers.FINAL);

      configureMethod.name("configure");

      NamedClass helperName;
      helperName = helperGenerator.helperName;

      configureMethod.addStatement(
          _var(helperName, Ids.helper, _new(helperName))
      );

      return configureMethod;
    }

    @Override
    final HelperGenerator getHelperGenerator() {
      return helperGenerator;
    }

    @Override
    final ClassCode.Builder getSiteClass() {
      ClassCode.Builder siteClass;
      siteClass = ClassCode.builder();

      siteClass.addAnnotation(BeSiteProcessor.GENERATED);

      siteClass.addModifier(Modifiers.PUBLIC);

      siteClass.simpleName(siteName);

      siteClass.superclass(TypeNames.AbstractSite);

      return siteClass;
    }

    @Override
    final PackageName getSitePackage() {
      return sitePackage;
    }

    private NamedClass getSiteName(PackageName sitePackage) {
      String packageSimpleName;
      packageSimpleName = sitePackage.getSimpleName();

      String siteSimpleName;
      siteSimpleName = JavaNames.toValidClassName(packageSimpleName + "Site");

      return sitePackage.nestedClass(siteSimpleName);
    }

  }

}
