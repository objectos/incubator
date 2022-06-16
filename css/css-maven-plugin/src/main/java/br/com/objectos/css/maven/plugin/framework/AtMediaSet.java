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
package br.com.objectos.css.maven.plugin.framework;

import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMedia;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaSet;
import objectos.util.UnmodifiableList;

class AtMediaSet extends AbstractFrameworkObject implements FrameworkAtMediaSet {

  static final AtMediaSet EMPTY = new AtMediaSet(UnmodifiableList.of());

  private final UnmodifiableList<FrameworkAtMedia> set;

  AtMediaSet(UnmodifiableList<FrameworkAtMedia> set) {
    this.set = set;
  }

  @Override
  final void acceptNamedAtMediaSetAdapter(NamedAtMediaSet.Adapter adapter) {
    for (FrameworkAtMedia media : set) {
      media.acceptFrameworkObjectVisitor(adapter);
    }
  }

  @Override
  final void acceptPropertyBuilder(Property.Builder builder) {
    for (FrameworkAtMedia media : set) {
      media.acceptFrameworkObjectVisitor(builder);
    }
  }

}
