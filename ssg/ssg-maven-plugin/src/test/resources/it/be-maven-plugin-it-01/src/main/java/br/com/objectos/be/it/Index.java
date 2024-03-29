/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
package br.com.objectos.be.it;

import br.com.objectos.be.annotations.Be;
import br.com.objectos.be.it.css.StylesCss;
import br.com.objectos.html.tmpl.AbstractTemplate;

@Be
abstract class Index extends AbstractTemplate {

  abstract IndexHtml indexHtml();
  abstract StylesCss stylesCss();

  Index() {
    System.out.println("Hello from Index");
  }
  
  @Override
  protected final void definition() {
    doctype();
    html(
        f(this::head0),
        f(this::body0)
    );
  }

  private void head0() {
    head(
        meta(charset("utf-8")),
        title("objectos::be serve test")
    );
  }

  private void body0() {
    body(
        t("Hello world!")
    );
  }

}