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
package br.com.objectos.css.maven.plugin.framework.background;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.maven.plugin.framework.AbstractCssMavenPluginFrameworkTest;
import objectos.css.config.framework.AbstractConfiguration;
import objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import objectos.css.keyword.Keywords;
import org.testng.annotations.Test;

public class BackgroundSizeTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    var javaFiles = executeProperty(
      new AbstractConfiguration() {
        @Override
        protected final void configure() {
          packageName("br.com.objectos.css.framework");

          property(
            FrameworkGroup.BACKGROUND,
            simpleName("BackgroundSize"),
            methods("backgroundSize"),
            valueSet(
              v("auto", Keywords.auto)
            )
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    test(
      javaFiles.get("BackgroundSize"),

      """
package br.com.objectos.css.framework.background;

import br.com.objectos.code.annotations.Generated;
import objectos.css.Css;
import objectos.css.keyword.Keywords;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class BackgroundSize extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  @Override
  protected final void definition() {
    definition0();
  }

  private void definition0() {
    style(
        auto,
        backgroundSize(Keywords.auto)
    );
  }

}"""
    );
  }

}
