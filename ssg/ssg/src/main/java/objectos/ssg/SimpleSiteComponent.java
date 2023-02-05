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

import objectos.lang.Check;
import objectos.ssg.Site.Context;
import objectos.util.UnmodifiableList;

public class SimpleSiteComponent
    implements
    SiteComponent,
    SiteResourceHolder {

  private Context context;

  @Override
  public final void configure(Context context) {
    Check.state(this.context == null, "context was already set");

    this.context = Check.notNull(context, "generator == null");

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

  protected final <T> UnmodifiableList<T> getObjectsByType(Class<? extends T> type) {
    return context.getObjectsByType(type);
  }

}