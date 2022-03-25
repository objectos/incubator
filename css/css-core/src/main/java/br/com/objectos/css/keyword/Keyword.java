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
package br.com.objectos.css.keyword;

import br.com.objectos.core.object.ToString;
import br.com.objectos.css.type.Value;

public abstract class Keyword implements Value {

  protected Keyword() {}

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Keyword)) {
      return false;
    }
    Keyword that = (Keyword) obj;
    return getClass().equals(that.getClass())
        && equalsImpl(that);
  }

  public abstract String getJavaName();

  public abstract String getName();

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public final String toString() {
    return ToString.toString(this, "", getName());
  }

  protected abstract boolean equalsImpl(Keyword obj);

}
