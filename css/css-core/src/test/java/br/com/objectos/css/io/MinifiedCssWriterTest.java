/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.io;

import br.com.objectos.css.AbstractCssCoreTest;
import java.io.IOException;
import org.testng.annotations.Test;

public class MinifiedCssWriterTest extends AbstractCssCoreTest {

  @Test
  public void writeUrl() throws IOException {
    testMinified(
        new Subject() {
          @Override
          public final void visitMinifiedCssWriter(MinifiedCssWriter w) throws IOException {
            w.writeUrl("abc");
          }
        },
        "url(\"abc\")"
    );
  }

  private abstract static class Subject implements CssWritable, CssWriterVisitor {

    @Override
    public final void acceptCssWriter(CssWriter w) throws IOException {
      w.acceptCssWriterVisitor(this);
    }

    @Override
    public final void visitPrettyCssWriter(PrettyCssWriter w) throws IOException {
      throw new AssertionError();
    }

  }

}
