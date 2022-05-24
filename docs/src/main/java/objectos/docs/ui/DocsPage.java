/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.ui;

import br.com.objectos.core.list.MutableList;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.http.media.ImageType;
import objectos.docs.style.ArticleCss;
import objectos.docs.style.BreadcrumbCss;
import objectos.docs.style.ContainerCss;
import objectos.docs.style.JavaCss;
import objectos.docs.style.NextBannerCss;
import objectos.docs.style.PageSwitcherCss;
import objectos.docs.style.ResetCss;
import objectos.docs.style.SyntaxCss;
import objectos.docs.style.XmlCss;
import objectos.ssg.SitePage;
import objectos.ssg.SitePath;

public abstract class DocsPage extends SitePage {

  protected String titleText = "";

  private NextBanner nextBanner;

  @Override
  public final void releaseResources() {
    super.releaseResources();

    titleText = null;

    nextBanner = null;
  }

  public final String titleText() {
    return titleText;
  }

  protected final ElementName a(Class<? extends SitePath> href, String t) {
    return a(
      href(href),

      t(t)
    );
  }

  protected void body0() {
    Breadcrumbs bc;
    bc = getObject(Breadcrumbs.class);

    bc.set(this);

    PageSwitcher ps;
    ps = getObject(PageSwitcher.class);

    ps.set(this);

    body(
      nextBanner.shouldRender ? f(nextBanner) : noop(),

      bc.shouldRender() ? f(bc) : noop(),

      main(
        f(this::main0)
      ),

      f(ps)
    );
  }

  @Override
  protected final void definition() {
    nextBanner = getObject(NextBanner.class);

    nextBanner.set(this);

    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      f(this::body0)
    );
  }

  protected void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type(ImageType.ICON.qualifiedName()), href("/favicon.ico"));

    StringBuilder css;
    css = getObject(StringBuilder.class);

    css.setLength(0);

    MutableList<StyleSheet> sheets;
    sheets = styleSheets();

    for (int i = 0; i < sheets.size(); i++) {
      StyleSheet sheet;
      sheet = sheets.get(i);

      String minified;
      minified = sheet.printMinified();

      css.append(minified);
    }

    style(
      raw(css.toString())
    );
  }

  protected void main0() {
    Md md;
    md = getObject(Md.class);

    String html;
    html = md.render(this);

    VersionHolder vh;
    vh = getObject(VersionHolder.class);

    html = html.replace("{{version}}", vh.get());

    article(
      raw(html)
    );
  }

  protected MutableList<StyleSheet> styleSheets() {
    MutableList<StyleSheet> list;
    list = new MutableList<>();

    list.add(new ResetCss());

    list.add(new ArticleCss());

    list.add(new BreadcrumbCss());

    list.add(new ContainerCss());

    list.add(new PageSwitcherCss());

    list.add(new SyntaxCss());

    list.add(new JavaCss());

    list.add(new XmlCss());

    if (nextBanner.shouldRender) {
      list.add(NextBannerCss.INSTANCE);
    }

    return list;
  }

}