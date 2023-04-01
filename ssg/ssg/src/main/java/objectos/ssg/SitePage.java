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

import br.com.objectos.http.media.TextType;
import java.io.IOException;
import objectos.html.HtmlSink;
import objectos.html.HtmlTemplate;
import objectos.html.tmpl.Instruction;
import objectos.lang.Check;
import objectos.ssg.Site.Context;
import objectos.util.UnmodifiableList;

public abstract class SitePage extends HtmlTemplate
    implements
    SiteComponent,
    SitePath,
    SiteResourceHolder,
    SiteWriteable {

  private Context context;

  private String path;

  @Override
  public final void configure(Context context) {
    Check.state(this.context == null, "context was already set");

    this.context = Check.notNull(context, "context == null");

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

    var sink = new HtmlSink();

    var out = new StringBuilder();

    sink.toStringBuilder(this, out);

    var contents = out.toString();

    writer.writeString(path, TextType.HTML, contents);

    writeEnd(contents);
  }

  protected void configure() {}

  protected final <T> T getObject(Class<? extends T> key) {
    return context.getObject(key);
  }

  protected final <T>
      UnmodifiableList<T> getObjectsByType(Class<? extends T> type) {
    return context.getObjectsByType(type);
  }

  protected final Instruction.HrefAttribute href(Class<? extends SitePath> key) {
    SitePath sitePath;
    sitePath = getObject(key);

    return href(sitePath);
  }

  protected final Instruction.HrefAttribute href(SitePath sitePath) {
    String path;
    path = sitePath.path();

    return href(path);
  }

  protected final Instruction.ElementContents link(Class<? extends SiteStyleSheet> key) {
    return link(href(key), rel("stylesheet"));
  }

  protected final Instruction.SrcAttribute src(SitePath path) {
    return src(path.path());
  }

  protected void writeEnd(String contents) {}

  protected void writeStart() {}

  final void setPath(String path) {
    Check.state(this.path == null, "path was already set");

    this.path = path;
  }

}
