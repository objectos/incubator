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
import br.com.objectos.html.attribute.StandardAttributeName.Href;
import br.com.objectos.html.tmpl.AbstractFragment;

public abstract class SiteFragment extends AbstractFragment
    implements
    SiteComponent {

  private Site.Context context;

  protected SiteFragment() {}

  @Override
  public final void configure(Site.Context context) {
    Checks.checkState(this.context == null, "context was already set");

    this.context = Checks.checkNotNull(context, "generator == null");

    configure();
  }

  @Override
  public final void generationOver() {
    context = null;
  }

  protected void configure() {}

  protected void generate() {}

  protected final String getHref(Class<?> key) {
    return context.getHref(key);
  }

  protected final <T> T getObject(Class<? extends T> key) {
    return context.getObject(key);
  }

  protected final <T>
      ImmutableList<T> getObjectsByType(Class<? extends T> type) {
    return context.getObjectsByType(type);
  }

  protected final Href href(Class<?> key) {
    String value;
    value = context.getHref(key);

    return path(value);
  }

  protected final Href href(Object o) {
    Class<? extends Object> key;
    key = o.getClass();

    return path(key);
  }

}