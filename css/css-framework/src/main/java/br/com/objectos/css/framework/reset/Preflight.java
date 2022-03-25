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
 * Based on https://github.com/tailwindlabs/tailwindcss/blob/master/src/plugins/css/preflight.css
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
package br.com.objectos.css.framework.reset;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.transpiler.ToJavaMojo")
public class Preflight extends AbstractStyleSheet {

  @Override
  protected final void definition() {
    style(
        blockquote, or(),
        dl, or(),
        dd, or(),
        h1, or(),
        h2, or(),
        h3, or(),
        h4, or(),
        h5, or(),
        h6, or(),
        hr, or(),
        figure, or(),
        p, or(),
        pre,

        margin(zero())
    );
    style(
        button,

        backgroundColor(transparent),
        backgroundImage(none),
        padding(zero())
    );
    style(
        button, FOCUS,

        outline(px(1), dotted),
        outline(px(5), auto, keyword("-webkit-focus-ring-color"))
    );
    style(
        fieldset,

        margin(zero()),
        padding(zero())
    );
    style(
        ol, or(),
        ul,

        listStyle(none),
        margin(zero()),
        padding(zero())
    );
    style(
        html,

        fontFamily(
            fontFamily(keyword("system-ui")),
            fontFamily(keyword("-apple-system")),
            fontFamily(keyword("BlinkMacSystemFont")),
            fontFamily(l("Segoe UI")),
            fontFamily(keyword("Roboto")),
            fontFamily(l("Helvetica Neue")),
            fontFamily(keyword("Arial")),
            fontFamily(l("Noto Sans")),
            fontFamily(sansSerif),
            fontFamily(l("Apple Color Emoji")),
            fontFamily(l("Segoe UI Emoji")),
            fontFamily(l("Segoe UI Symbol")),
            fontFamily(l("Noto Color Emoji"))
        ),
        lineHeight(1.5)
    );
    style(
        any(), or(),
        BEFORE, or(),
        AFTER,

        boxSizing(borderBox),
        borderWidth(zero()),
        borderStyle(solid),
        borderColor(currentcolor)
    );
    style(
        hr,

        borderTopWidth(px(1))
    );
    style(
        img,

        borderStyle(solid)
    );
    style(
        textarea,

        resize(vertical)
    );
    style(
        input, PLACEHOLDER, or(),
        textarea, PLACEHOLDER,

        color(hex("#a0aec0"))
    );
    style(
        button, or(),
        attr("role", eq("button")),

        cursor(pointer)
    );
    style(
        table,

        borderCollapse(collapse)
    );
    style(
        h1, or(),
        h2, or(),
        h3, or(),
        h4, or(),
        h5, or(),
        h6,

        fontSize(inherit),
        fontWeight(inherit)
    );
    style(
        a,

        color(inherit),
        textDecoration(inherit)
    );
    style(
        button, or(),
        input, or(),
        optgroup, or(),
        select, or(),
        textarea,

        padding(zero()),
        lineHeight(inherit),
        color(inherit)
    );
    style(
        pre, or(),
        code, or(),
        kbd, or(),
        samp,

        fontFamily(
            fontFamily(keyword("SFMono-Regular")),
            fontFamily(keyword("Menlo")),
            fontFamily(keyword("Monaco")),
            fontFamily(keyword("Consolas")),
            fontFamily(l("Liberation Mono")),
            fontFamily(l("Courier New")),
            fontFamily(monospace)
        )
    );
  }

}