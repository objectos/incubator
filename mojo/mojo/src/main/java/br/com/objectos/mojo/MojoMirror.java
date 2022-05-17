/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.mojo;

import objectos.lang.Checks;
import org.apache.maven.settings.Mirror;

public final class MojoMirror implements MojoRuntimeElement {

  private String id;

  private String url;

  private String mirrorOf;

  private MojoMirror() {}

  public static MojoMirror mirror(
      MojoMirrorElement e1,
      MojoMirrorElement e2,
      MojoMirrorElement e3) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");

    MojoMirror m;
    m = new MojoMirror();

    e1.acceptMojoMirror(m);
    e2.acceptMojoMirror(m);
    e3.acceptMojoMirror(m);

    return m.check();
  }

  public static MojoMirrorElement mirrorOf(final String value) {
    Checks.checkNotNull(value, "value == null");

    return new MojoMirrorElement() {
      @Override
      public final void acceptMojoMirror(MojoMirror mirror) {
        mirror.mirrorOf = value;
      }
    };
  }

  @Override
  public final void acceptMojoRuntimeBuilder(MojoRuntime.Builder builder) {
    Mirror mirror;
    mirror = get();

    builder.addMirror(mirror);
  }

  final Mirror get() {
    Mirror mirror;
    mirror = new Mirror();

    mirror.setId(id);

    mirror.setUrl(url);

    mirror.setMirrorOf(mirrorOf);

    return mirror;
  }

  final void setId(String id) {
    this.id = id;
  }

  final void setUrl(String url) {
    this.url = url;
  }

  private MojoMirror check() {
    check0(id, "id");
    check0(url, "url");
    check0(mirrorOf, "mirrorOf");

    return this;
  }

  private void check0(String value, String name) {
    if (value == null) {
      throw new NullPointerException(name + " was not set");
    }

    if (value.isEmpty()) {
      throw new IllegalArgumentException(name + " is empty");
    }
  }

}