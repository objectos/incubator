/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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

import objectos.lang.Check;

public final class MojoId implements MojoMirrorElement {

  private final String id;

  private MojoId(String id) {
    this.id = id;
  }

  public static MojoId id(String id) {
    Check.notNull(id, "id == null");

    return new MojoId(id);
  }

  @Override
  public final void acceptMojoMirror(MojoMirror mirror) {
    mirror.setId(id);
  }

  @Override
  public final String toString() {
    return "<id>" + id + "</id>";
  }

}
