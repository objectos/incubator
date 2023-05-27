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
import objectos.css.config.framework.ConfigurationDsl.FrameworkPropertyState;
import objectos.css.keyword.Keywords;

final class CursorSpec extends AbstractConfiguration {

  private final FrameworkAtMediaSet responsive;

  CursorSpec(FrameworkAtMediaSet responsive) {
    this.responsive = responsive;
  }

  @Override
  protected final void configure() {
    property(
      FrameworkGroup.INTERACTIVITY,
      simpleName("Cursor"),
      methods("cursor"),
      valueSet(
        v("auto", Keywords.auto),
        v("defaultCursor", Keywords.defaultKw),
        v("pointer", Keywords.pointer),
        v("wait", Keywords.wait),
        v("text", Keywords.text),
        v("move", Keywords.move),
        v("help", Keywords.help),
        v("not-allowed", Keywords.notAllowed),
        v("none", Keywords.none),
        v("context-menu", Keywords.contextMenu),
        //v("progress", Keywords.progress),
        v("cell", Keywords.cell),
        v("crosshair", Keywords.crosshair),
        v("vertical-text", Keywords.verticalText),
        v("alias", Keywords.alias),
        v("copy", Keywords.copy),
        v("no-drop", Keywords.noDrop),
        v("grab", Keywords.grab),
        v("grabbing", Keywords.grabbing),
        v("all-scroll", Keywords.allScroll),
        v("col-resize", Keywords.colResize),
        v("row-resize", Keywords.rowResize),
        v("n-resize", Keywords.nResize),
        v("e-resize", Keywords.eResize),
        v("s-resize", Keywords.sResize),
        v("w-resize", Keywords.wResize),
        v("ne-resize", Keywords.neResize),
        v("nw-resize", Keywords.nwResize),
        v("se-resize", Keywords.seResize),
        v("sw-resize", Keywords.swResize),
        v("ew-resize", Keywords.ewResize),
        v("ns-resize", Keywords.nsResize),
        v("nesw-resize", Keywords.neswResize),
        v("nwse-resize", Keywords.nwseResize),
        v("zoom-in", Keywords.zoomIn),
        v("zoom-out", Keywords.zoomOut)
      ),
      responsive,
      FrameworkPropertyState.HOVER
    );
  }

}
