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

import br.com.objectos.html.spi.tmpl.Marker;
import br.com.objectos.html.spi.tmpl.Renderer;

public abstract class AbstractHtmlResource implements HtmlResource {

  private final String src;

  protected AbstractHtmlResource(String src) {
    this.src = src;
  }

  @Override
  public final void mark(Marker marker) {
    marker.markAttribute();
  }

  @Override
  public final void render(Renderer renderer) {
    renderer.addAttribute("href", src);
  }

}
