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

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.ClassCode.Builder;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.type.NamedClass;

class HelperGenerator {

  final NamedClass helperName;
  private final ClassCode.Builder codeClass;

  private HelperGenerator(NamedClass helperName, Builder codeClass) {
    this.helperName = helperName;
    this.codeClass = codeClass;
  }

  public static HelperGenerator build(NamedClass siteName) {
    NamedClass helperName;
    helperName = siteName.nestedClass("Helper");

    ClassCode.Builder builder;
    builder = ClassCode.builder();

    builder.addModifier(Modifiers.PRIVATE);

    builder.simpleName(helperName);

    return new HelperGenerator(
        helperName,
        builder
    );
  }

  public final void addAtDirectory(AtDirectory directory) {
    codeClass.addField(directory.generateHelperField());

    codeClass.addMethod(directory.generateHelperMethod());
  }

  final ClassCode generateCodeClass() {
    return codeClass.build();
  }

}
