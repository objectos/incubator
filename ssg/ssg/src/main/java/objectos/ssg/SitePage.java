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
import br.com.objectos.html.attribute.StandardAttributeName.Src;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.tmpl.AbstractTemplate;
import br.com.objectos.http.media.TextType;
import java.io.IOException;
import objectos.ssg.Site.Context;

public abstract class SitePage extends AbstractTemplate
    implements
    SiteComponent,
    SitePath,
    SiteResourceHolder,
    SiteWriteable {

  private Context context;

  private String path;

  @Override
  public final void configure(Context context) {
    Checks.checkState(this.context == null, "context was already set");

    this.context = Checks.checkNotNull(context, "context == null");

    configure();
  }

  @Override
  public final String path() {
    return path;
  }

  @Override
  public void releaseResources() {
    context = null;

    path = null;
  }

  @Override
  public final void writeTo(SiteWriter writer) throws IOException {
    writeStart();

    String contents;
    contents = printMinified();

    writer.writeString(path, TextType.HTML, contents);

    writeEnd(contents);
  }

  protected void configure() {}

  protected final <T> T getObject(Class<? extends T> key) {
    return context.getObject(key);
  }

  protected final <T>
      ImmutableList<T> getObjectsByType(Class<? extends T> type) {
    return context.getObjectsByType(type);
  }

  protected final Href href(Class<? extends SitePath> key) {
    SitePath sitePath;
    sitePath = getObject(key);

    return href(sitePath);
  }

  protected final Href href(SitePath sitePath) {
    String path;
    path = sitePath.path();

    return href(path);
  }

  protected final ElementName link(Class<? extends SiteStyleSheet> key) {
    return link(href(key), rel("stylesheet"));
  }

  protected final Src src(SitePath path) {
    return src(path.path());
  }

  protected void writeEnd(String contents) {}

  protected void writeStart() {}

  final void setPath(String path) {
    Checks.checkState(this.path == null, "path was already set");

    this.path = path;
  }

}
