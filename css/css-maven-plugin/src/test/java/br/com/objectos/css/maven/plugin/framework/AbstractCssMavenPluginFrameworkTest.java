/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.maven.plugin.framework;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.core.map.ImmutableMap;
import br.com.objectos.core.map.MutableMap;
import br.com.objectos.css.config.framework.Configuration;

public abstract class AbstractCssMavenPluginFrameworkTest {

  protected final ImmutableMap<String, JavaFile> executeFramework(Configuration config) {
    ThisConfigurationAdapter adapter = new ThisConfigurationAdapter();
    PluginConfigurationDsl dsl = new PluginConfigurationDsl();
    config.acceptConfigurationDsl(dsl);
    dsl.executeFramework(adapter);
    return adapter.toMap();
  }

  protected final ImmutableMap<String, JavaFile> executeProperty(Configuration config) {
    ThisConfigurationAdapter adapter = new ThisConfigurationAdapter();
    PluginConfigurationDsl dsl = new PluginConfigurationDsl();
    config.acceptConfigurationDsl(dsl);
    dsl.executeProperties(adapter);
    return adapter.toMap();
  }

  protected final void testLines(Object o, String... expected) {
    assertHasLines(o.toString(), expected);
  }

  private void assertHasLines(String string, String[] expected) {
    String[] split = string.split("\n");

    assertEquals(split, expected);
  }

  private static class ThisConfigurationAdapter implements ConfigurationAdapter {

    private final MutableMap<String, JavaFile> map = MutableMap.create();

    @Override
    public final void writeJavaFile(JavaFile file) {
      map.put(file.simpleName(), file);
    }

    final ImmutableMap<String, JavaFile> toMap() {
      return map.toImmutableMap();
    }

  }

}
