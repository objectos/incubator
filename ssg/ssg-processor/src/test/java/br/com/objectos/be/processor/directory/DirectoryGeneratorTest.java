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

import br.com.objectos.be.processor.Util;
import br.com.objectos.be.processor.testing.iter01.Index01Html;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.io.JavaFile;
import org.testng.annotations.Test;

public class DirectoryGeneratorTest extends AbstractBeProcessorDirectoryTest {

  @Test
  public void addProcessedResourceType() {
    PackageName packageName;
    packageName = PackageName.of(Index01Html.class);

    DirectoryGenerator generator;
    generator = DirectoryGenerator.build(packageName);

    ProcessedResourceType indexHtml;
    indexHtml = getProcessedResourceType(Index01Html.class);

    generator.addProcessedResourceType(indexHtml);

    JavaFile javaFile;
    javaFile = generator.generate();

    Util.assertHasLines(
        javaFile.toString(),

        "package br.com.objectos.be.processor.testing.iter01;",
        "",
        "import br.com.objectos.be.site.AbstractDirectory;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeDirectoryProcessor\")",
        "public class Iter01Directory extends AbstractDirectory {",
        "",
        "  private final Iter01Path iter01Path;",
        "",
        "  public Iter01Directory(Iter01Path iter01Path) {",
        "    this.iter01Path = iter01Path;",
        "  }",
        "",
        "  @Override",
        "  protected final void configure() {",
        "    addTemplate(new Index01Impl());",
        "  }",
        "",
        "  private class Index01Impl extends Index01 {",
        "",
        "    @Override",
        "    final Styles01Css styles() {",
        "      return iter01Path.styles01Css();",
        "    }",
        "",
        "  }",
        "",
        "}"
    );
  }

}
