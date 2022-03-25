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
 * Based on https://github.com/necolas/normalize.css
 *
 * Copyright © Nicolas Gallagher and Jonathan Neal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
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
public class Normalize extends AbstractStyleSheet {

  @Override
  protected final void definition() {
    style(
        html,

        lineHeight(1.15),
        webkitTextSizeAdjust(pct(100))
    );
    style(
        body,

        margin(zero())
    );
    style(
        h1,

        fontSize(em(2)),
        margin(em(0.67), zero())
    );
    style(
        hr,

        boxSizing(contentBox),
        height(zero()),
        overflow(visible)
    );
    style(
        pre,

        fontFamily(
            fontFamily(monospace),
            fontFamily(monospace)
        ),
        fontSize(em(1))
    );
    style(
        a,

        backgroundColor(transparent)
    );
    style(
        abbr, attr("title"),

        borderBottom(none),
        textDecoration(underline),
        textDecoration(underline, dotted)
    );
    style(
        b, or(),
        strong,

        fontWeight(bolder)
    );
    style(
        code, or(),
        kbd, or(),
        samp,

        fontFamily(
            fontFamily(monospace),
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
        img,

        borderStyle(none)
    );
    style(
        button, or(),
        input, or(),
        optgroup, or(),
        select, or(),
        textarea,

        fontFamily(inherit),
        fontSize(pct(100)),
        lineHeight(1.15),
        margin(zero())
    );
    style(
        button, or(),
        input,

        overflow(visible)
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

        webkitAppearance(buttonKw)
    );
    style(
        button, _MOZ_FOCUS_INNER, or(),
        attr("type", eq("button")), _MOZ_FOCUS_INNER, or(),
        attr("type", eq("reset")), _MOZ_FOCUS_INNER, or(),
        attr("type", eq("submit")), _MOZ_FOCUS_INNER,

        borderStyle(none),
        padding(zero())
    );
    style(
        button, _MOZ_FOCUSRING, or(),
        attr("type", eq("button")), _MOZ_FOCUSRING, or(),
        attr("type", eq("reset")), _MOZ_FOCUSRING, or(),
        attr("type", eq("submit")), _MOZ_FOCUSRING,

        outline(px(1), dotted, ButtonText)
    );
    style(
        fieldset,

        padding(em(0.35), em(0.75), em(0.625))
    );
    style(
        legend,

        boxSizing(borderBox),
        color(inherit),
        display(tableKw),
        maxWidth(pct(100)),
        padding(zero()),
        whiteSpace(normal)
    );
    style(
        progress,

        verticalAlign(baseline)
    );
    style(
        textarea,

        overflow(auto)
    );
    style(
        attr("type", eq("checkbox")), or(),
        attr("type", eq("radio")),

        boxSizing(borderBox),
        padding(zero())
    );
    style(
        attr("type", eq("number")), _WEBKIT_INNER_SPIN_BUTTON, or(),
        attr("type", eq("number")), _WEBKIT_OUTER_SPIN_BUTTON,

        height(auto)
    );
    style(
        attr("type", eq("search")),

        webkitAppearance(textfield),
        outlineOffset(px(-2))
    );
    style(
        attr("type", eq("search")), _WEBKIT_SEARCH_DECORATION,

        webkitAppearance(none)
    );
    style(
        _WEBKIT_FILE_UPLOAD_BUTTON,

        webkitAppearance(buttonKw),
        font(inherit)
    );
    style(
        details,

        display(block)
    );
    style(
        summary,

        display(listItem)
    );
    style(
        template,

        display(none)
    );
    style(
        attr("hidden"),

        display(none)
    );
  }

}