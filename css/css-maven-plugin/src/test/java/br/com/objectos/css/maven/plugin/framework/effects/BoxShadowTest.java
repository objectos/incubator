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
package br.com.objectos.css.maven.plugin.framework.effects;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.maven.plugin.framework.AbstractCssMavenPluginFrameworkTest;
import objectos.css.config.framework.AbstractConfiguration;
import objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import objectos.css.keyword.Keywords;
import org.testng.annotations.Test;

public class BoxShadowTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    var javaFiles = executeProperty(
      new AbstractConfiguration() {
        @Override
        protected final void configure() {
          packageName("br.com.objectos.css.framework");

          property(
            FrameworkGroup.EFFECTS,
            simpleName("BoxShadow"),
            methods("boxShadow"),
            valueSet(
              v("standard",
                multi(_0, px(1), px(3), _0, rgba(0, 0, 0, 0.1)),
                multi(_0, px(1), px(2), _0, rgba(0, 0, 0, 0.06))
              ),
              v("none", Keywords.none)
            )
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    test(
      javaFiles.get("BoxShadow"),

      """
package br.com.objectos.css.framework.effects;

import br.com.objectos.code.annotations.Generated;
import objectos.css.Css;
import objectos.css.keyword.Keywords;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated(\"br.com.objectos.css.maven.plugin.framework.FrameworkMojo\")
public final class BoxShadow extends AbstractStyleSheet {

  public static final ClassSelector standard = Css.randomDot(5);

  public static final ClassSelector none = Css.randomDot(5);

  @Override
  protected final void definition() {
    definition0();
  }

  private void definition0() {
    style(
        standard,
        boxShadow(
            boxShadow(zero(), px(1), px(3), zero(), rgba(0, 0, 0, 0.1)),
            boxShadow(zero(), px(1), px(2), zero(), rgba(0, 0, 0, 0.06))
        )
    );
    style(
        none,
        boxShadow(Keywords.none)
    );
  }

}"""
    );
  }
}
