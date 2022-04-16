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

public abstract class SiteFragment extends AbstractFragment {

  private AbstractSiteDsl site;

  protected SiteFragment() {}

  protected final <T> T getInstance(Class<? extends T> key) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final Href href(Class<?> key) {
    throw new UnsupportedOperationException("Implement me");
  }

  final void setSite(AbstractSiteDsl site) {
    Checks.checkState(this.site == null, "site was already set");

    this.site = Checks.checkNotNull(site, "site == null");
  }

}