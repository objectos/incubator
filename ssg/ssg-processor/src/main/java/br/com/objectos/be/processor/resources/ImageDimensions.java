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
package br.com.objectos.be.processor.resources;

import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.noop;
import static br.com.objectos.code.java.Java.value;

import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.declaration.InterfaceCodeElement;
import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.http.media.ImageType;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

abstract class ImageDimensions {

  ImageDimensions() {}

  static ImageDimensions dim(int width, int height) {
    return new Standard(width, height);
  }

  static ImageDimensions none() {
    return None.INSTANCE;
  }

  static ImageDimensions ofResource(InputStreamSource source, ImageType type) {
    try {
      return ofResource0(source, type);
    } catch (IOException e) {
      throw new ResourcesRuntimeException(e);
    }
  }

  static ImageDimensions ofResource0(InputStreamSource source, ImageType type) throws IOException {
    switch (type) {
      default:
        return ofResourceDefault(source, type);
      case ICON:
        return IcoImageDimensions.of(source);
      case SVG:
        return None.INSTANCE;
    }
  }

  private static ImageDimensions ofResourceDefault(
      InputStreamSource source, ImageType type) throws IOException {
    Iterator<ImageReader> readers = ImageIO.getImageReadersByMIMEType(type.qualifiedName());

    while (readers.hasNext()) {
      ImageReader reader = readers.next();
      try (ImageInputStream stream = new MemoryCacheImageInputStream(source.openInputStream())) {
        reader.setInput(stream);
        int width = reader.getWidth(0);
        int height = reader.getHeight(0);
        return dim(width, height);
      } finally {
        reader.dispose();
      }
    }

    throw new IOException("Could not get image dimensions: " + source);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof ImageDimensions)) {
      return false;
    }
    ImageDimensions that = (ImageDimensions) obj;
    return getClass().equals(that.getClass())
        && equalsImpl(that);
  }

  public abstract InterfaceCodeElement generateMetaAnnotationIfPossible();

  @Override
  public final int hashCode() {
    return hashCodeImpl();
  }

  abstract boolean equalsImpl(ImageDimensions obj);

  abstract int hashCodeImpl();

  private static class None extends ImageDimensions {

    static final None INSTANCE = new None();

    @Override
    public final InterfaceCodeElement generateMetaAnnotationIfPossible() {
      return noop();
    }

    @Override
    final boolean equalsImpl(ImageDimensions obj) {
      return true;
    }

    @Override
    final int hashCodeImpl() {
      return 0;
    }

  }

  private static class Standard extends ImageDimensions {

    private final int height;
    private final int width;

    Standard(int width, int height) {
      this.width = width;
      this.height = height;
    }

    @Override
    public final InterfaceCodeElement generateMetaAnnotationIfPossible() {
      return annotation(
          TypeNames.MetaBeImageDimensions,
          value("width", l(width)),
          value("height", l(height))
      );
    }

    @Override
    final boolean equalsImpl(ImageDimensions obj) {
      Standard that = (Standard) obj;
      return width == that.width
          && height == that.height;
    }

    @Override
    final int hashCodeImpl() {
      return Objects.hash(width, height);
    }

  }

}
