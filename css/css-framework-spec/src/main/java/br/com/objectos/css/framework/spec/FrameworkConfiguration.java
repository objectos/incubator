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
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValueSet;
import br.com.objectos.css.type.Color;
import br.com.objectos.css.type.Zero;

public class FrameworkConfiguration extends AbstractConfiguration {

  @Override
  protected final void configure() {
    packageName("br.com.objectos.css.framework");

    var responsive = mediaSet(
      media("sm", SCREEN, declaration(MIN_WIDTH, px(640))),
      media("md", SCREEN, declaration(MIN_WIDTH, px(768))),
      media("lg", SCREEN, declaration(MIN_WIDTH, px(1024))),
      media("xl", SCREEN, declaration(MIN_WIDTH, px(1280))),
      media("x2l", SCREEN, declaration(MIN_WIDTH, px(1440)))
    );

    var colors = defineColors();
    var spacing = defineSpacing();

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
    install(new FlexDirection(responsive));
    install(new FlexGrow(responsive));
    install(new JustifyContent(responsive));

    // layout
    install(new DisplaySpec(responsive));
    install(new FloatSpec(responsive));
    install(new Inset(spacing, responsive));
    install(new OverflowSpec(responsive));
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
    install(new LetterSpacingSpec(responsive));
    install(new ListStylePositionSpec(responsive));
    install(new ListStyleTypeSpec(responsive));
    install(new TextAlign(responsive));
    install(new TextColorSpec(colors, responsive));
    install(new TextDecorationSpec(responsive));
    install(new TextTransform(responsive));
  }

  private FrameworkNamedValueSet defineColors() {
    return valueSet(
      v("transparent", Color.transparent),

      v("black", rgb(0x000)),
      v("white", rgb(0xffffff)),

      v("slate-050", rgb(0xf8fafc)),
      v("slate-100", rgb(0xf1f5f9)),
      v("slate-200", rgb(0xe2e8f0)),
      v("slate-300", rgb(0xcbd5e1)),
      v("slate-400", rgb(0x94a3b8)),
      v("slate-500", rgb(0x64748b)),
      v("slate-600", rgb(0x475569)),
      v("slate-700", rgb(0x334155)),
      v("slate-800", rgb(0x1e293b)),
      v("slate-900", rgb(0x0f172a)),

      v("gray-050", rgb(0xf9fafb)),
      v("gray-100", rgb(0xf3f4f6)),
      v("gray-200", rgb(0xe5e7eb)),
      v("gray-300", rgb(0xd1d5db)),
      v("gray-400", rgb(0x9ca3af)),
      v("gray-500", rgb(0x6b7280)),
      v("gray-600", rgb(0x4b5563)),
      v("gray-700", rgb(0x374151)),
      v("gray-800", rgb(0x1f2937)),
      v("gray-900", rgb(0x111827)),

      v("zinc-050", rgb(0xfafafa)),
      v("zinc-100", rgb(0xf4f4f5)),
      v("zinc-200", rgb(0xe4e4e7)),
      v("zinc-300", rgb(0xd4d4d8)),
      v("zinc-400", rgb(0xa1a1aa)),
      v("zinc-500", rgb(0x71717a)),
      v("zinc-600", rgb(0x52525b)),
      v("zinc-700", rgb(0x3f3f46)),
      v("zinc-800", rgb(0x27272a)),
      v("zinc-900", rgb(0x18181b)),

      v("neutral-050", rgb(0xfafafa)),
      v("neutral-100", rgb(0xf5f5f5)),
      v("neutral-200", rgb(0xe5e5e5)),
      v("neutral-300", rgb(0xd4d4d4)),
      v("neutral-400", rgb(0xa3a3a3)),
      v("neutral-500", rgb(0x737373)),
      v("neutral-600", rgb(0x525252)),
      v("neutral-700", rgb(0x404040)),
      v("neutral-800", rgb(0x262626)),
      v("neutral-900", rgb(0x171717)),

      v("stone-050", rgb(0xfafaf9)),
      v("stone-100", rgb(0xf5f5f4)),
      v("stone-200", rgb(0xe7e5e4)),
      v("stone-300", rgb(0xd6d3d1)),
      v("stone-400", rgb(0xa8a29e)),
      v("stone-500", rgb(0x78716c)),
      v("stone-600", rgb(0x57534e)),
      v("stone-700", rgb(0x44403c)),
      v("stone-800", rgb(0x292524)),
      v("stone-900", rgb(0x1c1917)),

      v("red-050", rgb(0xfef2f2)),
      v("red-100", rgb(0xfee2e2)),
      v("red-200", rgb(0xfecaca)),
      v("red-300", rgb(0xfca5a5)),
      v("red-400", rgb(0xf87171)),
      v("red-500", rgb(0xef4444)),
      v("red-600", rgb(0xdc2626)),
      v("red-700", rgb(0xb91c1c)),
      v("red-800", rgb(0x991b1b)),
      v("red-900", rgb(0x7f1d1d)),

      v("orange-050", rgb(0xfff7ed)),
      v("orange-100", rgb(0xffedd5)),
      v("orange-200", rgb(0xfed7aa)),
      v("orange-300", rgb(0xfdba74)),
      v("orange-400", rgb(0xfb923c)),
      v("orange-500", rgb(0xf97316)),
      v("orange-600", rgb(0xea580c)),
      v("orange-700", rgb(0xc2410c)),
      v("orange-800", rgb(0x9a3412)),
      v("orange-900", rgb(0x7c2d12)),

      v("amber-050", rgb(0xfffbeb)),
      v("amber-100", rgb(0xfef3c7)),
      v("amber-200", rgb(0xfde68a)),
      v("amber-300", rgb(0xfcd34d)),
      v("amber-400", rgb(0xfbbf24)),
      v("amber-500", rgb(0xf59e0b)),
      v("amber-600", rgb(0xd97706)),
      v("amber-700", rgb(0xb45309)),
      v("amber-800", rgb(0x92400e)),
      v("amber-900", rgb(0x78350f)),

      v("yellow-050", rgb(0xfefce8)),
      v("yellow-100", rgb(0xfef9c3)),
      v("yellow-200", rgb(0xfef08a)),
      v("yellow-300", rgb(0xfde047)),
      v("yellow-400", rgb(0xfacc15)),
      v("yellow-500", rgb(0xeab308)),
      v("yellow-600", rgb(0xca8a04)),
      v("yellow-700", rgb(0xa16207)),
      v("yellow-800", rgb(0x854d0e)),
      v("yellow-900", rgb(0x713f12)),

      v("lime-050", rgb(0xf7fee7)),
      v("lime-100", rgb(0xecfccb)),
      v("lime-200", rgb(0xd9f99d)),
      v("lime-300", rgb(0xbef264)),
      v("lime-400", rgb(0xa3e635)),
      v("lime-500", rgb(0x84cc16)),
      v("lime-600", rgb(0x65a30d)),
      v("lime-700", rgb(0x4d7c0f)),
      v("lime-800", rgb(0x3f6212)),
      v("lime-900", rgb(0x365314)),

      v("green-050", rgb(0xf0fdf4)),
      v("green-100", rgb(0xdcfce7)),
      v("green-200", rgb(0xbbf7d0)),
      v("green-300", rgb(0x86efac)),
      v("green-400", rgb(0x4ade80)),
      v("green-500", rgb(0x22c55e)),
      v("green-600", rgb(0x16a34a)),
      v("green-700", rgb(0x15803d)),
      v("green-800", rgb(0x166534)),
      v("green-900", rgb(0x14532d)),

      v("emerald-050", rgb(0xecfdf5)),
      v("emerald-100", rgb(0xd1fae5)),
      v("emerald-200", rgb(0xa7f3d0)),
      v("emerald-300", rgb(0x6ee7b7)),
      v("emerald-400", rgb(0x34d399)),
      v("emerald-500", rgb(0x10b981)),
      v("emerald-600", rgb(0x059669)),
      v("emerald-700", rgb(0x047857)),
      v("emerald-800", rgb(0x065f46)),
      v("emerald-900", rgb(0x064e3b)),

      v("teal-050", rgb(0xf0fdfa)),
      v("teal-100", rgb(0xccfbf1)),
      v("teal-200", rgb(0x99f6e4)),
      v("teal-300", rgb(0x5eead4)),
      v("teal-400", rgb(0x2dd4bf)),
      v("teal-500", rgb(0x14b8a6)),
      v("teal-600", rgb(0x0d9488)),
      v("teal-700", rgb(0x0f766e)),
      v("teal-800", rgb(0x115e59)),
      v("teal-900", rgb(0x134e4a)),

      v("cyan-050", rgb(0xecfeff)),
      v("cyan-100", rgb(0xcffafe)),
      v("cyan-200", rgb(0xa5f3fc)),
      v("cyan-300", rgb(0x67e8f9)),
      v("cyan-400", rgb(0x22d3ee)),
      v("cyan-500", rgb(0x06b6d4)),
      v("cyan-600", rgb(0x0891b2)),
      v("cyan-700", rgb(0x0e7490)),
      v("cyan-800", rgb(0x155e75)),
      v("cyan-900", rgb(0x164e63)),

      v("sky-050", rgb(0xf0f9ff)),
      v("sky-100", rgb(0xe0f2fe)),
      v("sky-200", rgb(0xbae6fd)),
      v("sky-300", rgb(0x7dd3fc)),
      v("sky-400", rgb(0x38bdf8)),
      v("sky-500", rgb(0x0ea5e9)),
      v("sky-600", rgb(0x0284c7)),
      v("sky-700", rgb(0x0369a1)),
      v("sky-800", rgb(0x075985)),
      v("sky-900", rgb(0x0c4a6e)),

      v("blue-050", rgb(0xeff6ff)),
      v("blue-100", rgb(0xdbeafe)),
      v("blue-200", rgb(0xbfdbfe)),
      v("blue-300", rgb(0x93c5fd)),
      v("blue-400", rgb(0x60a5fa)),
      v("blue-500", rgb(0x3b82f6)),
      v("blue-600", rgb(0x2563eb)),
      v("blue-700", rgb(0x1d4ed8)),
      v("blue-800", rgb(0x1e40af)),
      v("blue-900", rgb(0x1e3a8a)),

      v("indigo-050", rgb(0xeef2ff)),
      v("indigo-100", rgb(0xe0e7ff)),
      v("indigo-200", rgb(0xc7d2fe)),
      v("indigo-300", rgb(0xa5b4fc)),
      v("indigo-400", rgb(0x818cf8)),
      v("indigo-500", rgb(0x6366f1)),
      v("indigo-600", rgb(0x4f46e5)),
      v("indigo-700", rgb(0x4338ca)),
      v("indigo-800", rgb(0x3730a3)),
      v("indigo-900", rgb(0x312e81)),

      v("violet-050", rgb(0xf5f3ff)),
      v("violet-100", rgb(0xede9fe)),
      v("violet-200", rgb(0xddd6fe)),
      v("violet-300", rgb(0xc4b5fd)),
      v("violet-400", rgb(0xa78bfa)),
      v("violet-500", rgb(0x8b5cf6)),
      v("violet-600", rgb(0x7c3aed)),
      v("violet-700", rgb(0x6d28d9)),
      v("violet-800", rgb(0x5b21b6)),
      v("violet-900", rgb(0x4c1d95)),

      v("purple-050", rgb(0xfaf5ff)),
      v("purple-100", rgb(0xf3e8ff)),
      v("purple-200", rgb(0xe9d5ff)),
      v("purple-300", rgb(0xd8b4fe)),
      v("purple-400", rgb(0xc084fc)),
      v("purple-500", rgb(0xa855f7)),
      v("purple-600", rgb(0x9333ea)),
      v("purple-700", rgb(0x7e22ce)),
      v("purple-800", rgb(0x6b21a8)),
      v("purple-900", rgb(0x581c87)),

      v("fuchsia-050", rgb(0xfdf4ff)),
      v("fuchsia-100", rgb(0xfae8ff)),
      v("fuchsia-200", rgb(0xf5d0fe)),
      v("fuchsia-300", rgb(0xf0abfc)),
      v("fuchsia-400", rgb(0xe879f9)),
      v("fuchsia-500", rgb(0xd946ef)),
      v("fuchsia-600", rgb(0xc026d3)),
      v("fuchsia-700", rgb(0xa21caf)),
      v("fuchsia-800", rgb(0x86198f)),
      v("fuchsia-900", rgb(0x701a75)),

      v("pink-050", rgb(0xfdf2f8)),
      v("pink-100", rgb(0xfce7f3)),
      v("pink-200", rgb(0xfbcfe8)),
      v("pink-300", rgb(0xf9a8d4)),
      v("pink-400", rgb(0xf472b6)),
      v("pink-500", rgb(0xec4899)),
      v("pink-600", rgb(0xdb2777)),
      v("pink-700", rgb(0xbe185d)),
      v("pink-800", rgb(0x9d174d)),
      v("pink-900", rgb(0x831843)),

      v("rose-050", rgb(0xfff1f2)),
      v("rose-100", rgb(0xffe4e6)),
      v("rose-200", rgb(0xfecdd3)),
      v("rose-300", rgb(0xfda4af)),
      v("rose-400", rgb(0xfb7185)),
      v("rose-500", rgb(0xf43f5e)),
      v("rose-600", rgb(0xe11d48)),
      v("rose-700", rgb(0xbe123c)),
      v("rose-800", rgb(0x9f1239)),
      v("rose-900", rgb(0x881337))
    );
  }

  private FrameworkNamedValueSet defineSpacing() {
    return valueSet(
      v("px", px(1)),
      v("zero", Zero.INSTANCE),
      v("0", Zero.INSTANCE),
      v("00_5", rem(0.125)),
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
      v("68", rem(17)),
      v("72", rem(18)),
      v("80", rem(20)),
      v("96", rem(24))
    );
  }

}
