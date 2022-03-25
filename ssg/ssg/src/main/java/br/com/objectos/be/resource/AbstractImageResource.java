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

import br.com.objectos.css.type.Creator;
import br.com.objectos.html.spi.tmpl.Marker;
import br.com.objectos.html.spi.tmpl.Renderer;
import br.com.objectos.html.spi.type.Value;

public abstract class AbstractImageResource implements ImageResource {

  private final String src;
  private final Dimension dimension;

  protected AbstractImageResource(String src) {
    this(src, NoIntrinsicDimension.INSTANCE);
  }

  protected AbstractImageResource(String src, int width, int height) {
    this(src, new StandardDimension(width, height));
  }

  private AbstractImageResource(String src, Dimension dimension) {
    this.src = src;
    this.dimension = dimension;
  }

  @Override
  public void acceptValueCreator(Creator creator) {}

  @Override
  public void acceptValueMarker(br.com.objectos.css.type.Marker marker) {}

  @Override
  public final void mark(Marker marker) {
    marker.markAttribute();
    dimension.mark(marker);
  }

  @Override
  public final void render(Renderer renderer) {
    renderer.addAttribute("src", src);
    dimension.render(renderer);
  }

  private interface Dimension extends Value {}

  private enum NoIntrinsicDimension implements Dimension {
    INSTANCE;

    @Override
    public final void mark(Marker marker) {
      // noop
    }

    @Override
    public final void render(Renderer renderer) {
      // noop
    }
  }

  private static class StandardDimension implements Dimension {

    private final int width;
    private final int height;

    StandardDimension(int width, int height) {
      this.width = width;
      this.height = height;
    }

    @Override
    public final boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (!(obj instanceof StandardDimension)) {
        return false;
      }
      StandardDimension that = (StandardDimension) obj;
      return width == that.width
          && height == that.height;
    }

    @Override
    public final int hashCode() {
      int result = 1;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public final void mark(Marker marker) {
      marker.markAttribute();
      marker.markAttribute();
    }

    @Override
    public final void render(Renderer renderer) {
      renderer.addAttribute("width", Integer.toString(width));
      renderer.addAttribute("height", Integer.toString(height));
    }

  }

}
