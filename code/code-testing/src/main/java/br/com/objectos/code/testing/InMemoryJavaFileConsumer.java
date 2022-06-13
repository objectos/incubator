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
package br.com.objectos.code.testing;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.java.type.NamedClass;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import objectos.lang.Check;

public class InMemoryJavaFileConsumer implements JavaFileConsumer {

  private final Map<String, JavaFile> map = new TreeMap<>();

  @Override
  public final void acceptJavaFile(JavaFile javaFile) {
    Check.notNull(javaFile, "javaFile == null");

    NamedClass className;
    className = javaFile.className();

    String qualifiedName;
    qualifiedName = className.getCanonicalName();

    if (map.containsKey(qualifiedName)) {
      throw new IllegalArgumentException("Duplicate: " + qualifiedName);
    }

    map.put(qualifiedName, javaFile);
  }

  public final boolean contains(NamedClass className) {
    Check.notNull(className, "className == null");

    return contains(className.getCanonicalName());
  }

  public final boolean contains(String qualifiedName) {
    return map.containsKey(qualifiedName);
  }

  public final JavaFile get(NamedClass className) {
    Check.notNull(className, "className == null");

    return get(className.getCanonicalName());
  }

  public final JavaFile get(String qualifiedName) {
    if (!map.containsKey(qualifiedName)) {
      throw new NoSuchElementException(qualifiedName);
    }

    return map.get(qualifiedName);
  }

  public final int size() {
    return map.size();
  }

}
