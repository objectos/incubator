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
package br.com.objectos.css.boot;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.spec.Source;
import br.com.objectos.css.boot.type.ValueType;

@Generated("br.com.objectos.css.specgen.SpecgenBoot")
@DoNotOverwrite
final class BackgroundImagePropertyModule extends AbstractPropertyModule {

  @Override
  final void propertyDefinition() {
    KeywordName none = keyword("none");

    ValueType bgImage = t("BackgroundImageValue", none, uri, image);

    property(
        "background-image",

        formal(
            Source.MDN,
            "<bg-image># ",

            "<bg-image> = none | <image>",
            "<image> = <url> | <image()> | <image-set()> | <element()> | <paint()> | <cross-fade()> | <gradient>",
            "<image()> = image( <image-tags>? [ <image-src>? , <color>? ]! )",
            "<image-set()> = image-set( <image-set-option># )",
            "<element()> = element( <id-selector> )",
            "<paint()> = paint( <ident>, <declaration-value>? )",
            "<cross-fade()> = cross-fade( <cf-mixing-image> , <cf-final-image>? )",
            "<gradient> = <linear-gradient()> | <repeating-linear-gradient()> | <radial-gradient()> | <repeating-radial-gradient()> | <conic-gradient()>",
            "<image-tags> = ltr | rtl",
            "<image-src> = <url> | <string>",
            "<color> = <rgb()> | <rgba()> | <hsl()> | <hsla()> | <hex-color> | <named-color> | currentcolor | <deprecated-system-color>",
            "<image-set-option> = [ <image> | <string> ] <resolution>",
            "<id-selector> = <hash-token>",
            "<cf-mixing-image> = <percentage>? && <image>",
            "<cf-final-image> = <image> | <color>",
            "<linear-gradient()> = linear-gradient( [ <angle> | to <side-or-corner> ]? , <color-stop-list> )",
            "<repeating-linear-gradient()> = repeating-linear-gradient( [ <angle> | to <side-or-corner> ]? , <color-stop-list> )",
            "<radial-gradient()> = radial-gradient( [ <ending-shape> || <size> ]? [ at <position> ]? , <color-stop-list> )",
            "<repeating-radial-gradient()> = repeating-radial-gradient( [ <ending-shape> || <size> ]? [ at <position> ]? , <color-stop-list> )",
            "<conic-gradient()> = conic-gradient( [ from <angle> ]? [ at <position> ]?, <angular-color-stop-list> )",
            "<rgb()> = rgb( <percentage>{3} [ / <alpha-value> ]? ) | rgb( <number>{3} [ / <alpha-value> ]? ) | rgb( <percentage>#{3} , <alpha-value>? ) | rgb( <number>#{3} , <alpha-value>? )",
            "<rgba()> = rgba( <percentage>{3} [ / <alpha-value> ]? ) | rgba( <number>{3} [ / <alpha-value> ]? ) | rgba( <percentage>#{3} , <alpha-value>? ) | rgba( <number>#{3} , <alpha-value>? )",
            "<hsl()> = hsl( <hue> <percentage> <percentage> [ / <alpha-value> ]? ) | hsl( <hue>, <percentage>, <percentage>, <alpha-value>? )",
            "<hsla()> = hsla( <hue> <percentage> <percentage> [ / <alpha-value> ]? ) | hsla( <hue>, <percentage>, <percentage>, <alpha-value>? )",
            "<side-or-corner> = [ left | right ] || [ top | bottom ]",
            "<color-stop-list> = [ <linear-color-stop> [, <linear-color-hint>]? ]# , <linear-color-stop>",
            "<ending-shape> = circle | ellipse",
            "<size> = closest-side | farthest-side | closest-corner | farthest-corner | <length> | <length-percentage>{2}",
            "<position> = [ [ left | center | right ] || [ top | center | bottom ] | [ left | center | right | <length-percentage> ] [ top | center | bottom | <length-percentage> ]? | [ [ left | right ] <length-percentage> ] && [ [ top | bottom ] <length-percentage> ] ]",
            "<angular-color-stop-list> = [ <angular-color-stop> [, <angular-color-hint>]? ]# , <angular-color-stop>",
            "<alpha-value> = <number> | <percentage>",
            "<hue> = <number> | <angle>",
            "<linear-color-stop> = <color> <color-stop-length>?",
            "<linear-color-hint> = <length-percentage>",
            "<length-percentage> = <length> | <percentage>",
            "<angular-color-stop> = <color> && <color-stop-angle>?",
            "<angular-color-hint> = <angle-percentage>",
            "<color-stop-length> = <length-percentage>{1,2}",
            "<color-stop-angle> = <angle-percentage>{1,2}",
            "<angle-percentage> = <angle> | <percentage>"
        ),

        globalSig,

        sig(bgImage, "image")
    );
  }

}