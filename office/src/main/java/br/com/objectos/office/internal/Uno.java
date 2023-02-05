/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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

import br.com.objectos.office.writer.Format;
import com.sun.star.beans.PropertyValue;
import java.io.IOException;

final class Uno {

  private Uno() {}

  public static PropertyValue newPropertyValue(String name, Object value) {
    PropertyValue propertyValue;
    propertyValue = new PropertyValue();

    propertyValue.Name = name;

    propertyValue.Value = value;

    return propertyValue;
  }

  public static PropertyValue[] toFilterName(Format format) {
    return new PropertyValue[] {
        newPropertyValue("FilterName", format.filterName)
    };
  }

  public static IOException
      toJavaException(com.sun.star.io.IOException e) {
    return new IOException(e);
  }

  public static IllegalArgumentException
      toJavaException(com.sun.star.lang.IllegalArgumentException e) {
    return new IllegalArgumentException(e);
  }

}