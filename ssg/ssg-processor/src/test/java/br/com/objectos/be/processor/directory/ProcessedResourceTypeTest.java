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

import static br.com.objectos.code.java.Java._package;
import static org.testng.Assert.assertEquals;

import br.com.objectos.be.processor.Util;
import br.com.objectos.be.processor.testing.iter01.Index01Html;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.statement.Statement;
import br.com.objectos.code.java.type.NamedClass;
import org.testng.annotations.Test;

public class ProcessedResourceTypeTest extends AbstractBeProcessorDirectoryTest
    implements DirectoryFacade {

  @Test
  public void generateDirectoryConfigureStatement() {
    ProcessedResourceType index01Html;
    index01Html = getProcessedResourceType(Index01Html.class);

    PackageName iter01;
    iter01 = index01Html.packageName();

    NamedClass module01Name;
    module01Name = iter01.nestedClass("Iter01Directory");

    Statement result;
    result = index01Html.generateDirectoryConfigureStatement(module01Name);

    assertEquals(
        result.toString(),
        "addTemplate(new br.com.objectos.be.processor.testing.iter01.Iter01Directory.Index01Impl())"
    );
  }

  @Test
  public void generateDirectoryNestedImpl() {
    ProcessedResourceType index01Html;
    index01Html = getProcessedResourceType(Index01Html.class);

    ClassCode impl;
    impl = index01Html.generateDirectoryNestedImpl(this);

    Util.assertHasLines(
        impl.toString(),
        "private class Index01Impl extends br.com.objectos.be.processor.testing.iter01.Index01 {",
        "",
        "  @java.lang.Override",
        "  final br.com.objectos.be.processor.testing.iter01.Styles01Css styles() {",
        "    return br.com.objectos.be.processor.testing.iter01.Styles01Css.testingOnly();",
        "  }",
        "",
        "}"
    );
  }

  @Test
  public void generatePathMethod() {
    PackageName bePackage;
    bePackage = _package("testing.be");

    NamedClass implClassName;
    implClassName = bePackage.nestedClass("BeDirectory").nestedClass("HtmlImpl");

    ProcessedResourceType index01Html;
    index01Html = getProcessedResourceType(Index01Html.class);

    MethodCode method;
    method = index01Html.generatePathMethod(implClassName);

    Util.assertHasLines(
        method.toString(),
        "public final br.com.objectos.be.processor.testing.iter01.Index01Html index01Html() {",
        "  return new testing.be.BeDirectory.HtmlImpl(get(\"index01.html\"));",
        "}"
    );
  }

  // DirectoryFacade

  @Override
  public final MethodInvocation getDirectoryMethodInvocation(NamedClass className) {
    return className.invoke("testingOnly");
  }

}
