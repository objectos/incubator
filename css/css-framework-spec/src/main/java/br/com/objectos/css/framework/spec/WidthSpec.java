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

import objectos.css.config.framework.AbstractConfiguration;
import objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaSet;
import objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValueSet;
import objectos.css.keyword.Keywords;

final class WidthSpec extends AbstractConfiguration {

  private final FrameworkNamedValueSet spacing;
  private final FrameworkAtMediaSet responsive;

  WidthSpec(FrameworkNamedValueSet spacing, FrameworkAtMediaSet responsive) {
    this.spacing = spacing;
    this.responsive = responsive;
  }

  @Override
  protected final void configure() {
    FrameworkNamedValueSet width = valueSet(
      v("auto", Keywords.auto),
      spacing,
      v("p1o2", pct(50)),
      v("p1o3", pct(33.333333)),
      v("p2o3", pct(66.666667)),
      v("p1o4", pct(25)),
      v("p2o4", pct(50)),
      v("p3o4", pct(75)),
      v("p1o5", pct(20)),
      v("p2o5", pct(40)),
      v("p3o5", pct(60)),
      v("p4o5", pct(80)),
      v("p1o6", pct(16.666667)),
      v("p2o6", pct(33.333333)),
      v("p3o6", pct(50)),
      v("p4o6", pct(66.666667)),
      v("p5o6", pct(83.333333)),
      v("p1o12", pct(8.333333)),
      v("p2o12", pct(16.666667)),
      v("p3o12", pct(25)),
      v("p4o12", pct(33.333333)),
      v("p5o12", pct(41.666667)),
      v("p6o12", pct(50)),
      v("p7o12", pct(58.333333)),
      v("p8o12", pct(66.666667)),
      v("p9o12", pct(75)),
      v("p10o12", pct(83.333333)),
      v("p11o12", pct(91.666667)),
      v("full", pct(100)),
      v("screen", vw(100)),
      v("min", Keywords.minContent),
      v("max", Keywords.maxContent)
    //v("fit", Keywords.fitContent)
    );

    property(
      FrameworkGroup.SIZING,
      simpleName("Width"),
      methods("width"),
      width,
      responsive
    );
  }

}
