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
import br.com.objectos.html.attribute.StandardAttributeName.Href;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.tmpl.AbstractTemplate;
import objectos.ssg.stage.SiteRenderable;

public abstract class SitePage extends AbstractTemplate implements SiteRenderable {

  private Context context;

  @Override
  public final void render(AbstractSiteDsl dsl) {
    dsl.renderSitePage(this);
  }

  protected void configure() {}

  protected final <T> T getInstance(Class<? extends T> key) {
    return context.getInstance(key);
  }

  protected final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    return context.getInstancesByType(type);
  }

  protected final Href href(Class<?> key) {
    String value;
    value = context.getHref(key);

    return href(value);
  }

  protected final ElementName link(Class<? extends SiteStyleSheet> key) {
    return link(rel("stylesheet"), href(key));
  }

  public static interface Context {

    String getHref(Class<?> key);

    <T> T getInstance(Class<? extends T> key);

    <T> ImmutableList<T> getInstancesByType(Class<? extends T> type);

  }

}
