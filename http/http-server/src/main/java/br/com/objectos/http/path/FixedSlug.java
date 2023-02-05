/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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

import br.com.objectos.http.server.RequestedPart;
import objectos.lang.Check;

final class FixedSlug extends Slug {

  private final String value;

  private FixedSlug(String value) {
    this.value = value;
  }

  static Slug of(String slug) {
    Check.notNull(slug, "slug == null");
    return new FixedSlug(slug);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof FixedSlug)) {
      return false;
    }
    FixedSlug that = (FixedSlug) obj;
    return value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final boolean matches(RouteParser parser, RequestedPart requested) {
    return requested.matchesFixedSlug(parser, value);
  }

  @Override
  public final boolean matchesEmpty() {
    return false;
  }

  @Override
  public final String toString() {
    return value;
  }

}