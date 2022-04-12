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

import br.com.objectos.be.site.HasHref;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import java.util.Map;
import java.util.NoSuchElementException;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

public final class Md {

  private final Locator locator;

  private final Parser parser;

  private final HtmlRenderer renderer;

  public Md(Locator locator) {
    this.locator = locator;

    parser = Parser.builder().build();

    renderer = HtmlRenderer.builder()
        .attributeProviderFactory(new HrefChecker())
        .build();
  }

  public final String render(ArticlePage page) {
    Class<? extends ArticlePage> pageClass;
    pageClass = null;

    try {
      pageClass = page.getClass();

      String simpleName;
      simpleName = pageClass.getSimpleName();

      String resourceName;
      resourceName = simpleName + ".md";

      Resource resource;
      resource = Resource.getResource(pageClass, resourceName);

      String string;
      string = Read.string(resource, Charsets.utf8());

      Node document;
      document = parser.parse(string);

      return renderer.render(document);
    } catch (Exception e) {
      throw new RenderException("pageClass=" + pageClass, e);
    }
  }

  private class HrefChecker implements AttributeProvider, AttributeProviderFactory {
    @Override
    public final AttributeProvider create(AttributeProviderContext context) {
      return new HrefChecker();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
      if (!(node instanceof Link)) {
        return;
      }

      String href;
      href = attributes.get("href");

      if (!href.startsWith("href:")) {
        return;
      }

      String className;
      className = href.replace("href:", "objectos.docs.");

      Class<? extends HasHref> pageClass;

      try {
        Class<? extends HrefChecker> type;
        type = getClass();

        ClassLoader loader;
        loader = type.getClassLoader();

        pageClass = (Class<? extends HasHref>) loader.loadClass(className);
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }

      HasHref page;
      page = locator.getInstance(pageClass);

      if (page == null) {
        throw new NoSuchElementException("No page for: " + pageClass);
      }

      href = page.getHref();

      attributes.put("href", href);
    }
  }

  private class RenderException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RenderException(String message, Throwable cause) {
      super(message, cause);
    }
  }

}