/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
package br.com.objectos.formal.testing.driven;

import br.com.objectos.formal.testing.Node;
import br.com.objectos.formal.testing.NodeVisitor;
import java.util.Arrays;
import java.util.Objects;
import objectos.util.UnmodifiableList;

public class Scenario implements Node {

  public final Description description;
  public final UnmodifiableList<Step> stepList;

  public Scenario(Description description, Iterable<Step> stepList) {
    this.description = description;
    this.stepList = UnmodifiableList.copyOf(stepList);
  }

  public Scenario(Iterable<Step> stepList) {
    this(Description.EMPTY, stepList);
  }

  public Scenario(Symbol h2, Description description, Iterable<Step> stepList) {
    this(description, stepList);
  }

  public static Scenario of(Step... steps) {
    return new Scenario(Arrays.asList(steps));
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visitScenario(this);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof Scenario)) {
      return false;
    }
    Scenario that = (Scenario) obj;
    return description.equals(that.description)
        && stepList.equals(that.stepList);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(description, stepList);
  }

}