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
package br.com.objectos.css.maven.plugin.framework.spacing;

import static br.com.objectos.css.property.StandardPropertyName.MIN_WIDTH;
import static br.com.objectos.css.sheet.MediaType.SCREEN;
import static org.testng.Assert.assertEquals;

import br.com.objectos.css.config.framework.AbstractConfiguration;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkPropertyState;
import br.com.objectos.css.maven.plugin.framework.AbstractCssMavenPluginFrameworkTest;
import br.com.objectos.css.type.Zero;
import org.testng.annotations.Test;

public class MarginLeftTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    var javaFiles = executeProperty(
      new AbstractConfiguration() {
        @Override
        protected final void configure() {
          packageName("br.com.objectos.css.framework");

          var spacing = valueSet(
            v("0", Zero.INSTANCE),
            v("2", rem(0.5)),
            v("8", rem(2))
          );

          var responsive = mediaSet(
            media("md", SCREEN, declaration(MIN_WIDTH, px(768)))
          );

          property(
            FrameworkGroup.SPACING,
            simpleName("MarginLeft"),
            methods("marginLeft"),
            spacing,
            responsive,
            FrameworkPropertyState.FIRST_CHILD
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    var file = javaFiles.get("MarginLeft");

    assertEquals(
      file.toString(),

      """
package br.com.objectos.css.framework.spacing;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class MarginLeft extends AbstractStyleSheet {

  public static final ClassSelector v0 = Css.randomDot(5);

  public static final ClassSelector v2 = Css.randomDot(5);

  public static final ClassSelector v8 = Css.randomDot(5);

  @Override
  protected final void definition() {
    definition0();
    definition1();
    definition2();
  }

  private void definition0() {
    style(
        v0,
        marginLeft(zero())
    );
    style(
        v2,
        marginLeft(rem(0.5))
    );
    style(
        v8,
        marginLeft(rem(2))
    );
  }

  private void definition1() {
    style(
        firstChild.v0, FIRST_CHILD,
        marginLeft(zero())
    );
    style(
        firstChild.v2, FIRST_CHILD,
        marginLeft(rem(0.5))
    );
    style(
        firstChild.v8, FIRST_CHILD,
        marginLeft(rem(2))
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.v0,
            marginLeft(zero())
        ),

        style(
            md.v2,
            marginLeft(rem(0.5))
        ),

        style(
            md.v8,
            marginLeft(rem(2))
        )
    );
  }

  public interface firstChild {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

  public interface md {

    ClassSelector v0 = Css.randomDot(5);

    ClassSelector v2 = Css.randomDot(5);

    ClassSelector v8 = Css.randomDot(5);

  }

}"""
    );
  }

}
