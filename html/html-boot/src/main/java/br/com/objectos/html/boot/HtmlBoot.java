/*
 * Copyright (C) 2015-2023 Objectos Software LTDA.
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
package br.com.objectos.html.boot;

import br.com.objectos.html.boot.spec.AbstractSpec;
import br.com.objectos.html.boot.spec.SpecDsl;
import java.io.IOException;
import java.nio.file.Path;
import objectos.code.JavaSink;

public class HtmlBoot {

  private final SpecDsl spec;

  public HtmlBoot() {
    this(HtmlSpec.INSTANCE);
  }

  HtmlBoot(AbstractSpec spec) {
    this.spec = spec.toSpecDsl();
  }

  public static void main(String[] args) throws IOException {
    HtmlBoot boot = new HtmlBoot();

    boot.execute(args[0], args[1]);
  }

  private void execute(String moduleName, String srcDir) throws IOException {
    var directory = Path.of(srcDir);

    var sink = JavaSink.ofDirectory(
      directory,
      JavaSink.overwriteExisting()
    );

    switch (moduleName) {
      case "html" -> {
        executeTemplate(sink, new GeneratedAbstractTemplateStep());

        executeTemplate(sink, new StandardAttributeNameStep());

        executeTemplate(sink, new StandardElementNameStep());
      }

      case "spi" -> {
        executeTemplate(sink, new AnyElementValueStep());

        executeTemplate(sink, new ElementValueIfaceStep());

        executeTemplate(sink, new NonVoidElementValueStep());
      }

      default -> throw new IllegalArgumentException("Unknown module: " + moduleName);
    }
  }

  private void executeTemplate(JavaSink sink, ThisTemplate template) throws IOException {
    template.write(sink, spec);
  }

}