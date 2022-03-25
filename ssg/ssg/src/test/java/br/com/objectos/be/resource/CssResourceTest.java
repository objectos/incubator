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
package br.com.objectos.be.resource;

import static org.testng.Assert.assertEquals;

import br.com.objectos.html.tmpl.AbstractTemplate;
import br.com.objectos.html.writer.SimpleTemplateWriter;
import org.testng.annotations.Test;

public class CssResourceTest {

  @Test
  public void templateTest() {
    StringBuilder out = new StringBuilder();
    new SimpleTemplateWriter(out).write(
        new AbstractTemplate() {
          @Override
          protected final void definition() {
            link(new CssResourceImpl("https://example.com/styles.css"));
          }
        }
    );
    assertEquals(
        out.toString(),
        "<link rel=\"stylesheet\" href=\"https://example.com/styles.css\">"
    );
  }
  
  private static class CssResourceImpl extends AbstractCssResource {
    protected CssResourceImpl(String url) {
      super(url);
    }
  }
  
}
