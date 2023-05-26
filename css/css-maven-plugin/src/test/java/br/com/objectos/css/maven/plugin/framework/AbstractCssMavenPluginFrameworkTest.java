/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
import objectos.css.config.framework.Configuration;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;
import org.testng.Assert;

public abstract class AbstractCssMavenPluginFrameworkTest {

  private static class ThisConfigurationAdapter implements ConfigurationAdapter {

    private final GrowableMap<String, JavaFile> map = new GrowableMap<>();

    @Override
    public final void writeJavaFile(JavaFile file) {
      map.put(file.simpleName(), file);
    }

    final UnmodifiableMap<String, JavaFile> toMap() {
      return map.toUnmodifiableMap();
    }

  }

  protected final UnmodifiableMap<String, JavaFile> executeFramework(Configuration config) {
    ThisConfigurationAdapter adapter = new ThisConfigurationAdapter();
    PluginConfigurationDsl dsl = new PluginConfigurationDsl();
    config.acceptConfigurationDsl(dsl);
    dsl.executeFramework(adapter);
    return adapter.toMap();
  }

  protected final UnmodifiableMap<String, JavaFile> executeProperty(Configuration config) {
    ThisConfigurationAdapter adapter = new ThisConfigurationAdapter();
    PluginConfigurationDsl dsl = new PluginConfigurationDsl();
    config.acceptConfigurationDsl(dsl);
    dsl.executeProperties(adapter);
    return adapter.toMap();
  }

  protected final void test(JavaFile file, String expected) {
    var actual = file.toString();

    if (!actual.equals(expected)) {
      int len = Math.min(actual.length(), expected.length());

      var index = 0;

      for (int i = 0; i < len; i++) {
        char ca = actual.charAt(i);
        char ce = expected.charAt(i);

        if (ca == ce) {
          continue;
        }

        index = i;

        break;
      }

      var start = Math.max(0, index - 20);

      var end = Math.min(len, index + 30);

      Assert.fail(
        """

          ----
          %s
          %s
          ----
          %s
          ----
          %s
          ----
          """.formatted(
          actual.substring(start, end),
          expected.subSequence(start, end),
          actual,
          expected
        ));
    }
  }

  protected final void testLines(Object o, String... expected) {
    assertHasLines(o.toString(), expected);
  }

  private void assertHasLines(String string, String[] expected) {
    String[] split = string.split("\n");

    assertEquals(split, expected, string);
  }

}
