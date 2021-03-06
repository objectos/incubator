/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.tmpl;

import br.com.objectos.html.attribute.AttributeName;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.spi.tmpl.Marker;
import br.com.objectos.html.spi.tmpl.Renderer;
import br.com.objectos.html.spi.type.Value;

public interface TemplateDsl extends Marker, Renderer {

  void addAttribute(AttributeName name);

  void addAttribute(AttributeName name, String value);

  void addAttributeOrElement(AttributeOrElement value, String text);

  void addDoctype();

  void addElement(ElementName name, String text);

  void addElement(ElementName name, Value... values);

  void addFragment(AbstractFragment fragment);

  void addLambda(Lambda lambda);

  void addRaw(String text);

  void addText(String text);

}