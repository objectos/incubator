/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
import java.util.Iterator;
import objectos.util.ImmutableList;

public class Description implements Node {

  public static final Description EMPTY = new Description(ImmutableList.of());

  public final ImmutableList<StepText> textList;

  public Description(Iterable<StepText> textList) {
    this.textList = ImmutableList.copyOf(textList);
  }

  public static Description of(StepText... texts) {
    return new Description(Arrays.asList(texts));
  }

  @Override
  public void accept(NodeVisitor visitor) {}

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof Description)) {
      return false;
    }
    Description that = (Description) obj;
    return textList.equals(that.textList);
  }

  @Override
  public final int hashCode() {
    return textList.hashCode();
  }

  @Override
  public String toString() {
    return value();
  }

  public String value() {
    StringBuilder s = new StringBuilder();

    Iterator<StepText> iterator = textList.iterator();
    if (iterator.hasNext()) {
      s.append(iterator.next().value);
      while (iterator.hasNext()) {
        s.append(' ');
        s.append(iterator.next().value);
      }
    }

    return s.toString();
  }

}