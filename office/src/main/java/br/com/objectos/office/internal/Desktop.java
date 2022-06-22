/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.office.internal;

import br.com.objectos.fs.RegularFile;
import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XDesktop2;
import com.sun.star.frame.XModel2;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import java.io.IOException;
import java.net.URI;
import objectos.util.UnmodifiableMap;
import objectos.util.GrowableMap;

class Desktop {

  private static final PropertyValue[] DEFAULT_PROPERTIES = new PropertyValue[] {
                                                                                 Uno.newPropertyValue(
                                                                                   "Hidden",
                                                                                   Boolean.TRUE)
  };

  private static final String NAME = "com.sun.star.frame.Desktop";

  private final XDesktop2 desktop;

  private Desktop(XDesktop2 desktop) {
    this.desktop = desktop;
  }

  public static Desktop create(XComponentContext componentContext) throws Exception {
    XMultiComponentFactory componentFactory;
    componentFactory = componentContext.getServiceManager();

    Object _desktop;
    _desktop = componentFactory.createInstanceWithContext(NAME, componentContext);

    XDesktop2 desktop;
    desktop = UnoRuntime.queryInterface(XDesktop2.class, _desktop);

    return new Desktop(desktop);
  }

  public final WriterDocument createWriterDocument() throws IOException {
    XComponent component;
    component = loadComponent("private:factory/swriter");

    return WriterDocument.create(component);
  }

  public final Document loadDocument(RegularFile file) throws IOException {
    String url;
    url = toUrl(file);

    XComponent component;
    component = loadComponent(url);

    XModel2 model;
    model = UnoRuntime.queryInterface(XModel2.class, component);

    UnmodifiableMap<String, Object> args;
    args = getArgs(model);

    Object _documentService;
    _documentService = args.get("DocumentService");

    String documentService;
    documentService = (String) _documentService;

    if (documentService.equals("com.sun.star.text.TextDocument")) {
      return WriterDocument.create(component);
    }

    throw new UnsupportedOperationException("Implement me");
  }

  public final void terminate() {
    desktop.terminate();
  }

  private UnmodifiableMap<String, Object> getArgs(XModel2 model) {
    GrowableMap<String, Object> map;
    map = new GrowableMap<>();

    PropertyValue[] args;
    args = model.getArgs();

    for (PropertyValue arg : args) {
      String key;
      key = arg.Name;

      Object value;
      value = arg.Value;

      map.put(key, value);
    }

    return map.toUnmodifiableMap();
  }

  private XComponent loadComponent(String url) throws IOException {
    try {
      return desktop.loadComponentFromURL(url, "_blank", 0, DEFAULT_PROPERTIES);
    } catch (com.sun.star.lang.IllegalArgumentException e) {
      throw Uno.toJavaException(e);
    } catch (com.sun.star.io.IOException e) {
      throw Uno.toJavaException(e);
    }
  }

  private String toUrl(RegularFile file) {
    URI uri;
    uri = file.toUri();

    String url;
    url = uri.toString();

    return url;
  }

}