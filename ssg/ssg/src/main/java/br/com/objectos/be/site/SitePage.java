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
package br.com.objectos.be.site;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.html.attribute.StandardAttributeName.Href;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.tmpl.AbstractTemplate;

public abstract class SitePage extends AbstractTemplate implements HasHref {

  private SiteDirectory directory;

  private String fileName;

  @Override
  public final String getHref() {
    StringBuilder href;
    href = directory.hrefBuilder();

    href.append(fileName);

    return href.toString();
  }

  protected final <T> T getInstance(Class<? extends T> key) {
    return directory.getInstance(key);
  }

  protected final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    return directory.getInstancesByType(type);
  }

  protected final Href href(Class<? extends HasHref> key) {
    HasHref value;
    value = directory.getInstance(key);

    return href(value);
  }

  protected final Href href(HasHref value) {
    String href;
    href = value.getHref();

    return href(href);
  }

  protected final StringBuilder hrefBuilder() {
    return directory.hrefBuilder();
  }

  protected final ElementName link(Class<? extends SiteStyleSheet> key) {
    return link(rel("stylesheet"), href(key));
  }

  protected final void putInstance(Object value) {
    directory.putInstance(value);
  }

  protected void renderSitePage() {
    String href;
    href = getHref();

    directory.addTemplate(href, this);
  }

  final void setDirectory(SiteDirectory directory) {
    Checks.checkState(this.directory == null, "directory was already set");

    this.directory = directory;
  }

  final void setFileName(String fileName) {
    Checks.checkState(this.fileName == null, "fileName was already set");

    this.fileName = Checks.checkNotNull(fileName, "fileName == null");
  }

}
