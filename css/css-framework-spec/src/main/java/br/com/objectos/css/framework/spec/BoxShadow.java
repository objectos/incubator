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

import br.com.objectos.css.config.framework.AbstractConfiguration;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaSet;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import br.com.objectos.css.keyword.Keywords;

class BoxShadow extends AbstractConfiguration {

  private final FrameworkAtMediaSet responsive;

  BoxShadow(FrameworkAtMediaSet responsive) {
    this.responsive = responsive;
  }

  @Override
  protected final void configure() {
    property(
        FrameworkGroup.EFFECTS,
        simpleName("BoxShadow"),
        prefix("shadow"),
        methods("boxShadow"),
        valueSet(
            v("xSmall", _0, _0, _0, px(1), rgba(0, 0, 0, 0.05)),
            v("small", _0, px(1), px(2), _0, rgba(0, 0, 0, 0.05)),
            v("standard",
                multi(_0, px(1), px(3), _0, rgba(0, 0, 0, 0.1)),
                multi(_0, px(1), px(2), _0, rgba(0, 0, 0, 0.06))
            ),
            v("medium",
                multi(_0, px(4), px(6), px(-1), rgba(0, 0, 0, 0.1)),
                multi(_0, px(2), px(4), px(-1), rgba(0, 0, 0, 0.06))
            ),
            v("large",
                multi(_0, px(10), px(15), px(-3), rgba(0, 0, 0, 0.1)),
                multi(_0, px(4), px(6), px(-2), rgba(0, 0, 0, 0.05))
            ),
            v("xLarge",
                multi(_0, px(20), px(25), px(-5), rgba(0, 0, 0, 0.1)),
                multi(_0, px(10), px(10), px(-5), rgba(0, 0, 0, 0.04))
            ),
            v("xLarge2", _0, px(25), px(50), px(-12), rgba(0, 0, 0, 0.25)),
            v("inner", Keywords.inset, _0, px(2), px(4), _0, rgba(0, 0, 0, 0.06)),
            v("outline", _0, _0, _0, px(3), rgba(66, 153, 225, 0.5)),
            v("none", Keywords.none)
        ),
        responsive
    );
  }

}
