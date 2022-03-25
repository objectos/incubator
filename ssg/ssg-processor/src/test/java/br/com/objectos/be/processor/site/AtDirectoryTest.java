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

import static br.com.objectos.code.java.Java._package;
import static br.com.objectos.code.java.Java.id;
import static org.testng.Assert.assertEquals;

import br.com.objectos.be.annotations.BeSite;
import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.Util;
import br.com.objectos.be.processor.testing.iter02.Iter02Directory;
import br.com.objectos.be.processor.testing.iter02.Iter02Path;
import br.com.objectos.code.java.Java;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.processing.type.ErrorTypeException;
import br.com.objectos.core.list.ImmutableList;
import org.testng.annotations.Test;

public class AtDirectoryTest extends AbstractBeProcessorTest {

  @Test
  public void _iter02() throws ErrorTypeException {
    ProcessingAnnotation iter02;
    iter02 = getBeSiteAnnotation(Iter02Directory.class);

    ImmutableList<AtDirectory> directories;
    directories = AtDirectory.fromBeSiteAnnotation(iter02);

    assertEquals(directories.size(), 1);

    AtDirectory root;
    root = directories.get(0);

    assertEquals(root.directoryName, NamedClass.of(Iter02Directory.class));
    assertEquals(root.identifier, Java.id("iter02"));
    assertEquals(root.parameters, ImmutableList.of(PathParameter.named("iter02")));
    assertEquals(root.path, "/");
    assertEquals(root.pathName, NamedClass.of(Iter02Path.class));
  }

  @Test
  public void generateHelperField() {
    AtDirectoryFake builder;
    builder = new AtDirectoryFake();

    builder.identifier = id("subject");
    builder.path = "/subject";

    AtDirectory subject = builder.build();

    Util.assertHasLines(
        subject.generateHelperField().toString(),
        "final br.com.objectos.be.resource.BaseUrl subject = getBaseUrl(\"/subject\");"
    );
  }

  @Test
  public void generateHelperMethod() {
    AtDirectoryFake builder;
    builder = new AtDirectoryFake();

    builder.identifier = id("subject");
    builder.pathName = _package("testing.be").nestedClass("SubjectPath");

    AtDirectory subject = builder.build();

    Util.assertHasLines(
        subject.generateHelperMethod().toString(),
        "final testing.be.SubjectPath subject(br.com.objectos.be.resource.BaseUrl from) {",
        "  return new testing.be.SubjectPath(subject.resolve(from));",
        "}"
    );
  }

  @Test
  public void generateSiteMethod() {
    AtDirectoryFake builder;
    builder = new AtDirectoryFake();

    builder.directoryName = _package("testing.be").nestedClass("SubjectDirectory");
    builder.identifier = id("subject");
    builder.parameters = ImmutableList.of(PathParameter.named("css"));

    AtDirectory subject = builder.build();

    NamedClass fakeHelperName;
    fakeHelperName = _package("testing.be").nestedClass("Helper");

    MethodCode rootSiteMethod;
    rootSiteMethod = subject.generateSiteMethod(fakeHelperName);

    Util.assertHasLines(
        rootSiteMethod.toString(),
        "private void subject(testing.be.Helper helper) {",
        "  br.com.objectos.be.resource.BaseUrl from = helper.subject;",
        "  install(",
        "      from,",
        "      new testing.be.SubjectDirectory(",
        "          helper.css(from)",
        "      )",
        "  );",
        "}"
    );
  }

  private ProcessingAnnotation getBeSiteAnnotation(Class<?> type) {
    ProcessingPackage annotatedPackage;
    annotatedPackage = testingEnv.getProcessingPackage(type);

    return annotatedPackage.getDirectlyPresentAnnotation(BeSite.class);
  }

}
