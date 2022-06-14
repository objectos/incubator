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
package br.com.objectos.css.specgen;

import br.com.objectos.css.specgen.spec.Property;
import br.com.objectos.css.specgen.spec.Spec;
import br.com.objectos.css.specgen.spec.Step;
import objectos.util.MutableList;

abstract class AbstractSpecgen {

  private final Spec spec;
  private Step step;

  AbstractSpecgen(Spec spec) {
    this.spec = spec;
  }

  public final void execute(Step step) {
    this.step = step;
    definition();
    step.execute();
  }

  protected abstract void definition();

  protected final void p(String name) {
    Property p = spec.getProperty(name);
    step.addProperty(p);
  }

  protected final void p(String name, String... more) {
    MutableList<Property> moreProperties = new MutableList<>();
    for (String propertyName : more) {
      moreProperties.add(spec.getProperty(propertyName));
    }

    Property p = spec.getProperty(name);
    step.addProperty(p, moreProperties.toImmutableList());
  }

}
