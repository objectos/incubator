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
import java.util.Objects;

public class Step implements Node {

  private final Keyword keyword;
  private final Description description;

  public Step(Keyword keyword, Iterable<StepText> textList) {
    this.keyword = keyword;
    description = new Description(textList);
  }

  public Step(Keyword keyword, Description description) {
    this.keyword = keyword;
    this.description = description;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visitStep(this);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof Step)) {
      return false;
    }
    Step that = (Step) obj;
    return Objects.equals(keyword, that.keyword)
        && Objects.equals(description, that.description);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(keyword, description);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder()
        .append(keyword);

    for (StepText text : description.textList) {
      s.append(", ");
      s.append(text);
    }

    return s.toString();
  }

}