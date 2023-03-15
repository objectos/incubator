/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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

import objectos.html.HtmlTemplate;
import objectos.html.tmpl.StandardAttributeName.Href;
import objectos.lang.Check;
import objectos.ssg.Site.Context;
import objectos.util.UnmodifiableList;

public abstract class SiteFragment extends HtmlTemplate
    implements
    SiteComponent,
    SiteResourceHolder {

  private Site.Context context;

  protected SiteFragment() {}

  @Override
  public final void configure(Context context) {
    Check.state(this.context == null, "context was already set");

    this.context = Check.notNull(context, "context == null");

    configure();
  }

  @Override
  public void releaseResources() {
    context = null;
  }

  protected void configure() {}

  protected final <T> T getObject(Class<? extends T> key) {
    return context.getObject(key);
  }

  protected final <T>
      UnmodifiableList<T> getObjectsByType(Class<? extends T> type) {
    return context.getObjectsByType(type);
  }

  protected final Href href(Class<? extends SitePath> key) {
    SitePath sitePath;
    sitePath = getObject(key);

    return href(sitePath);
  }

  protected final Href href(SitePath sitePath) {
    String value;
    value = sitePath.path();

    return href(value);
  }

}