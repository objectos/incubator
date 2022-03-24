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

import br.com.objectos.core.object.Checks;

public final class MojoUrl implements MojoMirrorElement {

  private final String url;

  private MojoUrl(String url) {
    this.url = url;
  }

  public static MojoUrl url(String url) {
    Checks.checkNotNull(url, "url == null");

    return new MojoUrl(url);
  }

  @Override
  public final void acceptMojoMirror(MojoMirror mirror) {
    mirror.setUrl(url);
  }

  @Override
  public final String toString() {
    return "<url>" + url + "</url>";
  }

}
