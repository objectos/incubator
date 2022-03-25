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

import br.com.objectos.fs.Directory;
import br.com.objectos.http.path.Location;
import java.io.IOException;

public abstract class BaseUrl {

  BaseUrl() {}

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof BaseUrl)) {
      return false;
    }
    return getClass().equals(obj.getClass())
        && toString().equals(obj.toString());
  }

  public abstract BaseUrl getChild(String slug);

  public abstract Directory getDirectory(Directory target) throws IOException;

  public abstract Location getLocation(String filename);

  @Override
  public final int hashCode() {
    return toString().hashCode();
  }

  public abstract ResolvedUrl resolve(BaseUrl from);

  @Override
  public abstract String toString();

  abstract String toString(String filename);

}
