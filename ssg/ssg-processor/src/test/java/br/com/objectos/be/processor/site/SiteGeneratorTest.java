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
import br.com.objectos.core.list.ImmutableList;
import org.testng.annotations.Test;

public class SiteGeneratorTest {

  br.com.objectos.code.java.declaration.PackageName testingBe = _package("testing.be");

  @Test
  public void addAtDirectory() {
    SiteGenerator generator;
    generator = SiteGenerator.build(testingBe);

    AtDirectoryFake beBuilder;
    beBuilder = new AtDirectoryFake();

    beBuilder.directoryName = testingBe.nestedClass("BeDirectory");
    beBuilder.identifier = id("be");
    beBuilder.parameters = ImmutableList.of(PathParameter.named("css"));
    beBuilder.path = "/";
    beBuilder.pathName = testingBe.nestedClass("BePath");

    AtDirectory be;
    be = beBuilder.build();

    generator.addAtDirectory(be);

    Util.assertHasLines(
        generator.generateJavaFile().toString(),
        "package testing.be;",
        "",
        "import br.com.objectos.be.resource.BaseUrl;",
        "import br.com.objectos.be.site.AbstractSite;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeSiteProcessor\")",
        "public class BeSite extends AbstractSite {",
        "",
        "  private void be(Helper helper) {",
        "    BaseUrl from = helper.be;",
        "    install(",
        "        from,",
        "        new BeDirectory(",
        "            helper.css(from)",
        "        )",
        "    );",
        "  }",
        "",
        "  @Override",
        "  protected final void configure() {",
        "    Helper helper = new Helper();",
        "    be(helper);",
        "  }",
        "",
        "  private class Helper {",
        "",
        "    final BaseUrl be = getBaseUrl(\"/\");",
        "",
        "    final BePath be(BaseUrl from) {",
        "      return new BePath(be.resolve(from));",
        "    }",
        "",
        "  }",
        "",
        "}"
    );
  }

}
