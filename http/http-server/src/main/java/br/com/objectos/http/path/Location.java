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
package br.com.objectos.http.path;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.RequestProto;
import java.io.IOException;
import objectos.lang.Check;

public class Location {

  private static final Location CATCH_ALL = Location.builder()
      .catchAll()
      .build();

  private static final Location ROOT = new Location(ImmutableList.of());

  private final ImmutableList<Slug> slugs;

  Location(ImmutableList<Slug> slugs) {
    this.slugs = slugs;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Location parse(String string) {
    Check.notNull(string, "string == null");
    return new LocationParser(string).parse();
  }

  static Location catchAll() {
    return CATCH_ALL;
  }

  static Location root() {
    return ROOT;
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Location)) {
      return false;
    }
    Location that = (Location) obj;
    return slugs.equals(that.slugs);
  }

  public final RegularFile getOrCreateRegularFile(Directory target) throws IOException {
    String name;
    name = slugs.join("/");

    ResolvedPath object;
    object = target.resolve(name);

    if (object.exists()) {
      return object.toRegularFile();
    } else {
      object.createParents();

      return object.createRegularFile();
    }
  }

  @Override
  public final int hashCode() {
    return slugs.hashCode();
  }

  @Override
  public final String toString() {
    return slugs.join("/", "/", "");
  }

  final RouteParser newRouteParser(HttpAction action, RequestProto request) {
    return new RouteParser(slugs, action, request);
  }

  public static class Builder {

    private final MutableList<Slug> slugs = MutableList.create();

    private Builder() {}

    public final Location build() {
      return slugs.isEmpty() ? ROOT : new Location(slugs.toImmutableList());
    }

    public final Builder catchAll() {
      slugs.add(Slug.catchAll());
      return this;
    }

    public final Builder fixed(String part) {
      slugs.add(Slug.fixed(part));
      return this;
    }

    public final Builder intParam() {
      slugs.add(Slug.intParam());
      return this;
    }

    public final Builder stringParam() {
      slugs.add(Slug.stringParam());
      return this;
    }

  }

}