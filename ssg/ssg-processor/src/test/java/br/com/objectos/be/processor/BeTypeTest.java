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
package br.com.objectos.be.processor;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.model.element.ProcessingType;
import org.testng.annotations.Test;

public class BeTypeTest extends AbstractBeProcessorTest {

  @Test
  public void testCase01() {
    BeType type;
    type = of0(TestCase01.class);

    JavaFile iface;
    iface = type.resourceIfaceGen();

    Util.assertHasLines(
        iface.toString(),

        "package br.com.objectos.be.processor;",
        "",
        "import br.com.objectos.be.meta.MetaBeHtml;",
        "import br.com.objectos.be.resource.HtmlResource;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeProcessor\")",
        "@MetaBeHtml(TestCase01.class)",
        "public interface TestCase01Html extends HtmlResource {}"
    );

    assertFalse(type.generatesIntermediateType());
  }

  @Test
  public void testCase02() {
    BeType type;
    type = of0(TestCase02.class);

    JavaFile iface;
    iface = type.resourceIfaceGen();

    Util.assertHasLines(
        iface.toString(),

        "package br.com.objectos.be.processor;",
        "",
        "import br.com.objectos.be.meta.MetaBeCss;",
        "import br.com.objectos.be.resource.CssResource;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeProcessor\")",
        "@MetaBeCss(TestCase02.class)",
        "public interface TestCase02Css extends CssResource {}"
    );

    assertFalse(type.generatesIntermediateType());
  }

  @Test
  public void testCase03() {
    BeType type;
    type = of0(TestCase03.class);

    JavaFile iface;
    iface = type.resourceIfaceGen();

    Util.assertHasLines(
        iface.toString(),

        "package br.com.objectos.be.processor;",
        "",
        "import br.com.objectos.be.meta.MetaBeHtml;",
        "import br.com.objectos.be.resource.HtmlResource;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeProcessor\")",
        "@MetaBeHtml(MarkdownTestCase03.class)",
        "public interface TestCase03Html extends HtmlResource {}"
    );

    assertTrue(type.generatesIntermediateType());

    JavaFile inter;
    inter = type.intermediateTypeGen();

    Util.assertHasLines(
        inter.toString(),

        "package br.com.objectos.be.processor;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeProcessor\")",
        "class MarkdownTestCase03 extends TestCase03 {",
        "",
        "  @Override",
        "  final void body0();",
        "",
        "}"
    );
  }

  private BeType of0(Class<?> candidate) {
    ProcessingType query = testingEnv.getProcessingType(candidate);
    return BeType.of(query);
  }

}
