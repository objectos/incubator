/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import javax.tools.JavaFileObject;
import org.testng.annotations.Test;

public class SingletonProcessorTest extends AbstractMultiReleaseProcessorTest {

  @Test
  public void process() throws IOException {
    process(
        new SingletonProcessor(),
        new StringJavaFileObject(
            "objectos.testing.Escaper",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Singleton;"
                + "class FunctionsImplJava6 extends Functions.Impl {"
                + "  @Singleton.Field"
                + "  static final Functions.Impl INSTANCE = new FunctionsImplJava6();"
                + "}"
        ),
        new StringJavaFileObject(
            "objectos.testing.Escaper",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Singleton;"
                + "class FunctionsImplJava11 extends Functions.Impl {"
                + "  @Singleton.Field"
                + "  static final Functions.Impl INSTANCE = new FunctionsImplJava11();"
                + ""
                + "  static final int OTHER = 0;"
                + "}"
        ),
        new StringJavaFileObject(
            "objectos.testing.Functions",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Singleton;"
                + "public class Functions {"
                + "  @Singleton"
                + "  abstract static class Impl {"
                + "    Impl() {}"
                + "  }"
                + "}"
        )
    );

    JavaFileObject factory;
    factory = getGeneratedJavaFile("objectos.testing.FunctionsImplSingleton");

    assertNotNull(factory);

    testToString(
        factory,
        "package objectos.testing;",
        "",
        "import br.com.objectos.latest.Generated;",
        "",
        "@Generated(\"br.com.objectos.latest.processor.SingletonProcessor\")",
        "final class FunctionsImplSingleton {",
        "  static final objectos.testing.Functions.Impl INSTANCE = FunctionsImplJava11.INSTANCE;",
        "",
        "  private FunctionsImplSingleton() {}",
        "}"
    );
  }

}
