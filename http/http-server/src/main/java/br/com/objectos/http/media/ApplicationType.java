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

public enum ApplicationType implements MediaType {

  JAVASCRIPT,

  JSON,

  OCTET_STREAM;

  private final String qualifiedName = MediaTypes.qualifiedNameImpl(this);

  @Override
  public final <R, P> R acceptMediaTypeVisitor(MediaTypeVisitor<R, P> visitor, P p) {
    return visitor.visitApplicationType(this, p);
  }

  @Override
  public final String qualifiedName() {
    return qualifiedName;
  }
  
  @Override
  public final String toString() {
    return qualifiedName;
  }

  @Override
  public final TopLevel type() {
    return TopLevel.APPLICATION;
  }

}
