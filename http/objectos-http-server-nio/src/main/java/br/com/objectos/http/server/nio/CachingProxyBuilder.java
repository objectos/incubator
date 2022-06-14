/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.server.nio;

import static br.com.objectos.collections.Collections.newMutableList;
import static br.com.objectos.preconditions.Preconditions.checkNotNull;

import br.com.objectos.collections.MutableList;
import br.com.objectos.io.Directory;

public class CachingProxyBuilder {

  private final Directory cache;
  private final MutableList<String> specList = new MutableList<>();

  public CachingProxyBuilder(Directory cache) {
    this.cache = checkNotNull(cache, "cache == null");
  }

  public CachingProxyBuilder addMirror(String spec) {
    specList.add(spec);
    return this;
  }

  public CachingProxyBuilder addMirrors(Iterable<String> mirrors) {
    specList.addAllIterable(mirrors);

    return this;
  }

  public CachingProxy build() {
    return new CachingProxy(cache, specList.toImmutableList());
  }

}