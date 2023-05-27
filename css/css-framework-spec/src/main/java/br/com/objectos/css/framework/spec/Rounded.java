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
import objectos.css.type.Zero;

class Rounded extends AbstractConfiguration {

  private final FrameworkAtMediaSet responsive;

  Rounded(FrameworkAtMediaSet responsive) {
    this.responsive = responsive;
  }

  @Override
  protected final void configure() {
    FrameworkNamedValueSet radius = valueSet(
      v("none", Zero.INSTANCE),
      v("small", em(0.125)),
      v("standard", em(0.25)),
      v("medium", em(0.375)),
      v("large", em(0.5)),
      v("full", px(9999))
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("Rounded"),
      methods("borderRadius"),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedTop"),
      methods(
        "borderTopLeftRadius",
        "borderTopRightRadius"
      ),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedRight"),
      methods(
        "borderTopRightRadius",
        "borderBottomRightRadius"
      ),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedBottom"),
      methods(
        "borderBottomLeftRadius",
        "borderBottomRightRadius"
      ),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedLeft"),
      methods(
        "borderTopLeftRadius",
        "borderBottomLeftRadius"
      ),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedTopLeft"),
      methods("borderTopLeftRadius"),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedTopRight"),
      methods("borderTopRightRadius"),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedBottomRight"),
      methods("borderBottomRightRadius"),
      radius,
      responsive
    );

    property(
      FrameworkGroup.BORDER,
      simpleName("RoundedBottomLeft"),
      methods("borderBottomLeftRadius"),
      radius,
      responsive
    );
  }

}
