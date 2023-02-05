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
 * Based on https://github.com/tailwindlabs/tailwindcss/blob/master/src/css/preflight.css
 *
 * MIT License
 *
 * Copyright (c) Nicolas Gallagher
 * Copyright (c) Jonathan Neal
 * Copyright (c) Sindre Sorhus <sindresorhus@gmail.com> (sindresorhus.com)
 * Copyright (c) Adam Wathan
 * Copyright (c) Jonathan Reinink
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
        any(), or(),
        BEFORE, or(),
        AFTER,

        boxSizing(borderBox),
        borderWidth(zero()),
        borderStyle(solid),
        borderColor(currentcolor)
    );
    style(
        html,

        lineHeight(1.5),
        webkitTextSizeAdjust(pct(100)),
        mozTabSize(4),
        tabSize(4),
        fontFamily(
            fontFamily(keyword("ui-sans-serif")),
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
        )
    );
    style(
        body,

        margin(zero()),
        lineHeight(inherit)
    );
    style(
        hr,

        height(zero()),
        color(inherit),
        borderTopWidth(px(1))
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
        b, or(),
        strong,

        fontWeight(bolder)
    );
    style(
        code, or(),
        kbd, or(),
        samp, or(),
        pre,

        fontFamily(
            fontFamily(keyword("ui-monospace")),
            fontFamily(keyword("SFMono-Regular")),
            fontFamily(keyword("Menlo")),
            fontFamily(keyword("Monaco")),
            fontFamily(keyword("Consolas")),
            fontFamily(l("Liberation Mono")),
            fontFamily(l("Courier New")),
            fontFamily(monospace)
        ),
        fontSize(em(1))
    );
    style(
        small,

        fontSize(pct(80))
    );
    style(
        sub, or(),
        sup,

        fontSize(pct(75)),
        lineHeight(zero()),
        position(relative),
        verticalAlign(baseline)
    );
    style(
        sub,

        bottom(em(-0.25))
    );
    style(
        sup,

        top(em(-0.5))
    );
    style(
        table,

        textIndent(zero()),
        borderColor(inherit),
        borderCollapse(collapse)
    );
    style(
        button, or(),
        input, or(),
        optgroup, or(),
        select, or(),
        textarea,

        fontFamily(inherit),
        fontSize(pct(100)),
        fontWeight(inherit),
        lineHeight(inherit),
        color(inherit),
        margin(zero()),
        padding(zero())
    );
    style(
        button, or(),
        select,

        textTransform(none)
    );
    style(
        button, or(),
        attr("type", eq("button")), or(),
        attr("type", eq("reset")), or(),
        attr("type", eq("submit")),

        webkitAppearance(buttonKw),
        backgroundColor(transparent),
        backgroundImage(none)
    );
    style(
        _MOZ_FOCUSRING,

        outline(auto)
    );
    style(
        _MOZ_UI_INVALID,

        boxShadow(none)
    );
    style(
        progress,

        verticalAlign(baseline)
    );
    style(
        _WEBKIT_INNER_SPIN_BUTTON, or(),
        _WEBKIT_OUTER_SPIN_BUTTON,

        height(auto)
    );
    style(
        attr("type", eq("search")),

        webkitAppearance(textfield),
        outlineOffset(px(-2))
    );
    style(
        _WEBKIT_SEARCH_DECORATION,

        webkitAppearance(none)
    );
    style(
        _WEBKIT_FILE_UPLOAD_BUTTON,

        webkitAppearance(buttonKw),
        font(inherit)
    );
    style(
        summary,

        display(listItem)
    );
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
        fieldset,

        margin(zero()),
        padding(zero())
    );
    style(
        legend,

        padding(zero())
    );
    style(
        ol, or(),
        ul, or(),
        menu,

        listStyle(none),
        margin(zero()),
        padding(zero())
    );
    style(
        textarea,

        resize(vertical)
    );
    style(
        input, PLACEHOLDER, or(),
        textarea, PLACEHOLDER,

        opacity(1),
        color(hex("#9ca3af"))
    );
    style(
        button, or(),
        attr("role", eq("button")),

        cursor(pointer)
    );
    style(
        DISABLED,

        cursor(defaultKw)
    );
    style(
        img, or(),
        svg,

        display(block),
        verticalAlign(middle)
    );
    style(
        img,

        maxWidth(pct(100)),
        height(auto)
    );
  }

}