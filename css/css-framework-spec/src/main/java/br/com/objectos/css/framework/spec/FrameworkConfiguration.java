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
 *
 * Based on:
 * https://github.com/tailwindcss/tailwindcss/blob/master/stubs/defaultConfig.stub.js
 *
 * Copyright (c) Adam Wathan <adam.wathan@gmail.com>
 * Copyright (c) Jonathan Reinink <jonathan@reinink.ca>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package br.com.objectos.css.framework.spec;

import static br.com.objectos.css.property.StandardPropertyName.MIN_WIDTH;
import static br.com.objectos.css.sheet.MediaType.SCREEN;

import br.com.objectos.css.config.framework.AbstractConfiguration;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaSet;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValueSet;
import br.com.objectos.css.type.Color;
import br.com.objectos.css.type.Zero;

public class FrameworkConfiguration extends AbstractConfiguration {

  @Override
  protected final void configure() {
    packageName("br.com.objectos.css.framework");

    FrameworkAtMediaSet responsive = mediaSet(
      media("sm", SCREEN, declaration(MIN_WIDTH, px(640))),
      media("md", SCREEN, declaration(MIN_WIDTH, px(768))),
      media("lg", SCREEN, declaration(MIN_WIDTH, px(1024))),
      media("xl", SCREEN, declaration(MIN_WIDTH, px(1280)))
    );

    FrameworkNamedValueSet colors = defineColors();
    FrameworkNamedValueSet spacing = defineSpacing();

    // background
    install(new Background(colors, responsive));

    // border
    install(new Border(colors, responsive));
    install(new Rounded(responsive));

    // effects
    install(new BoxShadow(responsive));

    // flexbox
    install(new AlignContent(responsive));
    install(new AlignItems(responsive));
    install(new AlignSelf(responsive));
    install(new Flex(responsive));
    install(new JustifyContent(responsive));

    // layout
    install(new Display(responsive));
    install(new Float(responsive));
    install(new Inset(responsive));
    install(new Overflow(responsive));
    install(new Position(responsive));

    // sizing
    install(new Height(spacing, responsive));
    install(new MaxHeight(responsive));
    install(new MinHeight(responsive));

    install(new Width(spacing, responsive));
    install(new MaxWidth(responsive));
    install(new MinWidth(responsive));

    // spacing
    install(new Margin(spacing, responsive));
    install(new Padding(spacing, responsive));

    // typography
    install(new FontSize(responsive));
    install(new FontStyle(responsive));
    install(new FontWeight(responsive));
    install(new Leading(responsive));
    install(new TextAlign(responsive));
    install(new TextColor(colors, responsive));
    install(new TextDecoration(responsive));
    install(new TextTransform(responsive));
  }

  private FrameworkNamedValueSet defineColors() {
    return valueSet(
      v("transparent", Color.transparent),

      v("black", rgb(0x000)),
      v("white", rgb(0xffffff)),

      v("gray-100", rgb(0xf7fafc)),
      v("gray-200", rgb(0xedf2f7)),
      v("gray-300", rgb(0xe2e8f0)),
      v("gray-400", rgb(0xcbd5e0)),
      v("gray-500", rgb(0xa0aec0)),
      v("gray-600", rgb(0x718096)),
      v("gray-700", rgb(0x4a5568)),
      v("gray-800", rgb(0x2d3748)),
      v("gray-900", rgb(0x1a202c)),

      v("red-100", rgb(0xfff5f5)),
      v("red-200", rgb(0xfed7d7)),
      v("red-300", rgb(0xfeb2b2)),
      v("red-400", rgb(0xfc8181)),
      v("red-500", rgb(0xf56565)),
      v("red-600", rgb(0xe53e3e)),
      v("red-700", rgb(0xc53030)),
      v("red-800", rgb(0x9b2c2c)),
      v("red-900", rgb(0x742a2a)),

      v("orange-100", rgb(0xfffaf0)),
      v("orange-200", rgb(0xfeebc8)),
      v("orange-300", rgb(0xfbd38d)),
      v("orange-400", rgb(0xf6ad55)),
      v("orange-500", rgb(0xed8936)),
      v("orange-600", rgb(0xdd6b20)),
      v("orange-700", rgb(0xc05621)),
      v("orange-800", rgb(0x9c4221)),
      v("orange-900", rgb(0x7b341e)),

      v("yellow-100", rgb(0xfffff0)),
      v("yellow-200", rgb(0xfefcbf)),
      v("yellow-300", rgb(0xfaf089)),
      v("yellow-400", rgb(0xf6e05e)),
      v("yellow-500", rgb(0xecc94b)),
      v("yellow-600", rgb(0xd69e2e)),
      v("yellow-700", rgb(0xb7791f)),
      v("yellow-800", rgb(0x975a16)),
      v("yellow-900", rgb(0x744210)),

      v("green-100", rgb(0xf0fff4)),
      v("green-200", rgb(0xc6f6d5)),
      v("green-300", rgb(0x9ae6b4)),
      v("green-400", rgb(0x68d391)),
      v("green-500", rgb(0x48bb78)),
      v("green-600", rgb(0x38a169)),
      v("green-700", rgb(0x2f855a)),
      v("green-800", rgb(0x276749)),
      v("green-900", rgb(0x22543d)),

      v("teal-100", rgb(0xe6fffa)),
      v("teal-200", rgb(0xb2f5ea)),
      v("teal-300", rgb(0x81e6d9)),
      v("teal-400", rgb(0x4fd1c5)),
      v("teal-500", rgb(0x38b2ac)),
      v("teal-600", rgb(0x319795)),
      v("teal-700", rgb(0x2c7a7b)),
      v("teal-800", rgb(0x285e61)),
      v("teal-900", rgb(0x234e52)),

      v("blue-100", rgb(0xebf8ff)),
      v("blue-200", rgb(0xbee3f8)),
      v("blue-300", rgb(0x90cdf4)),
      v("blue-400", rgb(0x63b3ed)),
      v("blue-500", rgb(0x4299e1)),
      v("blue-600", rgb(0x3182ce)),
      v("blue-700", rgb(0x2b6cb0)),
      v("blue-800", rgb(0x2c5282)),
      v("blue-900", rgb(0x2a4365)),

      v("indigo-100", rgb(0xebf4ff)),
      v("indigo-200", rgb(0xc3dafe)),
      v("indigo-300", rgb(0xa3bffa)),
      v("indigo-400", rgb(0x7f9cf5)),
      v("indigo-500", rgb(0x667eea)),
      v("indigo-600", rgb(0x5a67d8)),
      v("indigo-700", rgb(0x4c51bf)),
      v("indigo-800", rgb(0x434190)),
      v("indigo-900", rgb(0x3c366b)),

      v("purple-100", rgb(0xfaf5ff)),
      v("purple-200", rgb(0xe9d8fd)),
      v("purple-300", rgb(0xd6bcfa)),
      v("purple-400", rgb(0xb794f4)),
      v("purple-500", rgb(0x9f7aea)),
      v("purple-600", rgb(0x805ad5)),
      v("purple-700", rgb(0x6b46c1)),
      v("purple-800", rgb(0x553c9a)),
      v("purple-900", rgb(0x44337a)),

      v("pink-100", rgb(0xfff5f7)),
      v("pink-200", rgb(0xfed7e2)),
      v("pink-300", rgb(0xfbb6ce)),
      v("pink-400", rgb(0xf687b3)),
      v("pink-500", rgb(0xed64a6)),
      v("pink-600", rgb(0xd53f8c)),
      v("pink-700", rgb(0xb83280)),
      v("pink-800", rgb(0x97266d)),
      v("pink-900", rgb(0x702459))
    );
  }

  private FrameworkNamedValueSet defineSpacing() {
    return valueSet(
      v("px", px(1)),
      v("zero", Zero.INSTANCE),
      v("0", Zero.INSTANCE),
      v("0_5", rem(0.125)),
      v("01", rem(0.25)),
      v("01_5", rem(0.375)),
      v("02", rem(0.5)),
      v("02_5", rem(0.625)),
      v("03", rem(0.75)),
      v("03_5", rem(0.875)),
      v("04", rem(1)),
      v("05", rem(1.25)),
      v("06", rem(1.5)),
      v("07", rem(1.75)),
      v("08", rem(2)),
      v("09", rem(2.25)),
      v("10", rem(2.5)),
      v("11", rem(2.75)),
      v("12", rem(3)),
      v("14", rem(3.5)),
      v("16", rem(4)),
      v("20", rem(5)),
      v("24", rem(6)),
      v("28", rem(7)),
      v("32", rem(8)),
      v("36", rem(9)),
      v("40", rem(10)),
      v("44", rem(11)),
      v("48", rem(12)),
      v("52", rem(13)),
      v("56", rem(14)),
      v("60", rem(15)),
      v("64", rem(16)),
      v("72", rem(18)),
      v("80", rem(20)),
      v("96", rem(24))
    );
  }

}
