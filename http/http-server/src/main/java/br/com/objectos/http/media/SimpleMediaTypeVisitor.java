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
package br.com.objectos.http.media;

public class SimpleMediaTypeVisitor<R, P> implements MediaTypeVisitor<R, P> {

  private R defaultValue;

  protected SimpleMediaTypeVisitor() {
    this(null);
  }

  protected SimpleMediaTypeVisitor(R defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public R visitApplicationType(ApplicationType type, P p) {
    return defaultAction(type, p);
  }

  @Override
  public R visitFontType(FontType fontType, P p) {
    return defaultAction(fontType, p);
  }

  @Override
  public R visitImageType(ImageType type, P p) {
    return defaultAction(type, p);
  }

  @Override
  public R visitTextType(TextType type, P p) {
    return defaultAction(type, p);
  }

  protected R defaultAction(MediaType type, P p) {
    return defaultValue;
  }

}
