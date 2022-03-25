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

import br.com.objectos.be.processor.Util;
import br.com.objectos.code.java.type.NamedClass;
import org.testng.annotations.Test;

public class HelperGeneratorTest {

  br.com.objectos.code.java.declaration.PackageName testingBe = _package("testing.be");

  @Test
  public void addAtDirectory() {
    NamedClass siteName;
    siteName = testingBe.nestedClass("BeSite");

    HelperGenerator generator;
    generator = HelperGenerator.build(siteName);

    AtDirectoryFake directoryBuilder;
    directoryBuilder = new AtDirectoryFake();

    directoryBuilder.identifier = id("subject");
    directoryBuilder.path = "/subject";
    directoryBuilder.pathName = testingBe.nestedClass("SubjectPath");

    AtDirectory directory;
    directory = directoryBuilder.build();

    generator.addAtDirectory(directory);

    Util.assertHasLines(
        generator.generateCodeClass().toString(),
        "private class Helper {",
        "",
        "  final br.com.objectos.be.resource.BaseUrl subject = getBaseUrl(\"/subject\");",
        "",
        "  final testing.be.SubjectPath subject(br.com.objectos.be.resource.BaseUrl from) {",
        "    return new testing.be.SubjectPath(subject.resolve(from));",
        "  }",
        "",
        "}"
    );
  }

}
