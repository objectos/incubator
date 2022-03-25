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
package br.com.objectos.be.processor;

import static br.com.objectos.code.java.Java.t;

import br.com.objectos.be.meta.ResourceKind;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;

public class TypeNames {

  public static final NamedClass AbstractCssResource = t(
      br.com.objectos.be.resource.AbstractCssResource.class
  );

  public static final NamedClass AbstractDirectory = t(
      br.com.objectos.be.site.AbstractDirectory.class
  );

  public static final NamedClass AbstractFragment = t(
      br.com.objectos.html.tmpl.AbstractFragment.class
  );

  public static final NamedClass AbstractHtmlResource = t(
      br.com.objectos.be.resource.AbstractHtmlResource.class
  );

  public static final NamedClass AbstractImageResource = t(
      br.com.objectos.be.resource.AbstractImageResource.class
  );

  public static final NamedClass AbstractPath = t(
      br.com.objectos.be.resource.AbstractPath.class
  );

  public static final NamedClass AbstractSite = t(
      br.com.objectos.be.site.AbstractSite.class
  );

  public static final NamedClass BaseUrl = t(
      br.com.objectos.be.resource.BaseUrl.class
  );

  public static final NamedClass CssResource = t(
      br.com.objectos.be.resource.CssResource.class
  );

  public static final NamedClass HtmlResource = t(
      br.com.objectos.be.resource.HtmlResource.class
  );

  public static final NamedClass ImageResource = t(
      br.com.objectos.be.resource.ImageResource.class
  );

  public static final NamedClass ImageType = t(
      br.com.objectos.http.media.ImageType.class
  );

  public static final NamedClass Markdown = t(
      br.com.objectos.be.annotations.Markdown.class
  );

  public static final NamedClass MetaBeCss = t(
      br.com.objectos.be.meta.MetaBeCss.class
  );

  public static final NamedClass MetaBeHtml = t(
      br.com.objectos.be.meta.MetaBeHtml.class
  );

  public static final NamedClass MetaBeImage = t(
      br.com.objectos.be.meta.MetaBeImage.class
  );

  public static final NamedClass MetaBeImageDimensions = t(
      br.com.objectos.be.meta.MetaBeImageDimensions.class
  );

  public static final NamedClass MetaBeResource
      = PackageName.of(ResourceKind.class).nestedClass("MetaBeResource");

  public static final NamedClass ResolvedUrl = t(
      br.com.objectos.be.resource.ResolvedUrl.class
  );

  public static final NamedClass String = t(String.class);

  private TypeNames() {}

}
