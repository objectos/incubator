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
package objectos.ssg;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.css.sheet.AbstractStyleSheet;

public abstract class SiteStyleSheet extends AbstractStyleSheet
    implements
    SiteArtifact,
    SiteComponent {

  private Site.Context context;

  @Override
  public final void configure(Site.Context context) {
    Checks.checkState(this.context == null, "context was already set");

    this.context = Checks.checkNotNull(context, "generator == null");

    configure();
  }

  @Override
  public final void generate(SiteArtifact.Generator generator) {
    generator.generateStyleSheet(this);
  }

  @Override
  public final void generationOver() {
    context = null;
  }

  protected void configure() {}

  protected final <T> T getObject(Class<? extends T> key) {
    return context.getObject(key);
  }

  protected final <T>
      ImmutableList<T> getObjectsByType(Class<? extends T> type) {
    return context.getObjectsByType(type);
  }

}