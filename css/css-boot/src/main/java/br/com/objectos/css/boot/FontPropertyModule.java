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
package br.com.objectos.css.boot;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.boot.spec.KeywordName;
import br.com.objectos.css.boot.spec.Source;
import br.com.objectos.css.boot.spec.ValueType;

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class FontPropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName caption = keyword("caption");
    KeywordName icon = keyword("icon");
    KeywordName menu = keyword("menu");
    KeywordName messageBox = keyword("message-box");
    KeywordName normal = keyword("normal");
    KeywordName smallCaps = keyword("small-caps");
    KeywordName smallCaption = keyword("small-caption");
    KeywordName statusBar = keyword("status-bar");

    ValueType systemFont = t(
        "SystemFontValue",
        caption, icon, menu, messageBox, smallCaption, statusBar
    );

    ValueType fontVariantCss21 = t(
        "FontVariantCss21Value",
        normal, smallCaps
    );

    property(
        "font",

        formal(
            Source.MDN,
            "[ [ <\'font-style\'> || <font-variant-css21> || <\'font-weight\'> || <\'font-stretch\'> ]? <\'font-size\'> [ / <\'line-height\'> ]? <\'font-family\'> ] | caption | icon | menu | message-box | small-caption | status-bar",

            "<font-variant-css21> = [ normal | small-caps ]"
        ),

        globalSig,

        sig(systemFont, "value"),
        sig(fontVariantCss21, "value")
    );
  }

}