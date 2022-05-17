/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.office.writer;

import objectos.lang.Checks;

public abstract class AbstractWriterScript implements WriterScript {

  private WriterDsl dsl;

  @Override
  public final synchronized void acceptWriterDsl(WriterDsl dsl) {
    this.dsl = Checks.checkNotNull(dsl, "dsl == null");

    try {
      definition();
    } finally {
      this.dsl = null;
    }
  }

  protected abstract void definition();

  protected final void p(String... lines) {
    dsl.p(lines);
  }

  protected final void replace(String target, String replacement) {
    dsl.replace(target, replacement);
  }

}
