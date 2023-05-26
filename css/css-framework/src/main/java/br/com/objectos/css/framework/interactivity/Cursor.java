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
 * Based on Tailwind CSS
 *
 * MIT License
 *
 * Copyright (c) Tailwind Labs, Inc.
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
 * Footer
 */
package br.com.objectos.css.framework.interactivity;

import br.com.objectos.code.annotations.Generated;
import objectos.css.Css;
import objectos.css.keyword.Keywords;
import objectos.css.select.ClassSelector;
import objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Cursor extends AbstractStyleSheet {

  public static final ClassSelector auto = Css.randomDot(5);

  public static final ClassSelector defaultCursor = Css.randomDot(5);

  public static final ClassSelector pointer = Css.randomDot(5);

  public static final ClassSelector wait = Css.randomDot(5);

  public static final ClassSelector text = Css.randomDot(5);

  public static final ClassSelector move = Css.randomDot(5);

  public static final ClassSelector help = Css.randomDot(5);

  public static final ClassSelector notAllowed = Css.randomDot(5);

  public static final ClassSelector none = Css.randomDot(5);

  public static final ClassSelector contextMenu = Css.randomDot(5);

  public static final ClassSelector cell = Css.randomDot(5);

  public static final ClassSelector crosshair = Css.randomDot(5);

  public static final ClassSelector verticalText = Css.randomDot(5);

  public static final ClassSelector alias = Css.randomDot(5);

  public static final ClassSelector copy = Css.randomDot(5);

  public static final ClassSelector noDrop = Css.randomDot(5);

  public static final ClassSelector grab = Css.randomDot(5);

  public static final ClassSelector grabbing = Css.randomDot(5);

  public static final ClassSelector allScroll = Css.randomDot(5);

  public static final ClassSelector colResize = Css.randomDot(5);

  public static final ClassSelector rowResize = Css.randomDot(5);

  public static final ClassSelector nResize = Css.randomDot(5);

  public static final ClassSelector eResize = Css.randomDot(5);

  public static final ClassSelector sResize = Css.randomDot(5);

  public static final ClassSelector wResize = Css.randomDot(5);

  public static final ClassSelector neResize = Css.randomDot(5);

  public static final ClassSelector nwResize = Css.randomDot(5);

  public static final ClassSelector seResize = Css.randomDot(5);

  public static final ClassSelector swResize = Css.randomDot(5);

  public static final ClassSelector ewResize = Css.randomDot(5);

  public static final ClassSelector nsResize = Css.randomDot(5);

  public static final ClassSelector neswResize = Css.randomDot(5);

  public static final ClassSelector nwseResize = Css.randomDot(5);

  public static final ClassSelector zoomIn = Css.randomDot(5);

  public static final ClassSelector zoomOut = Css.randomDot(5);

  @Override
  protected final void definition() {
    definition0();
    definition1();
    definition2();
    definition3();
    definition4();
    definition5();
    definition6();
  }

  private void definition0() {
    style(
        auto,
        cursor(Keywords.auto)
    );
    style(
        defaultCursor,
        cursor(Keywords.defaultKw)
    );
    style(
        pointer,
        cursor(Keywords.pointer)
    );
    style(
        wait,
        cursor(Keywords.wait)
    );
    style(
        text,
        cursor(Keywords.text)
    );
    style(
        move,
        cursor(Keywords.move)
    );
    style(
        help,
        cursor(Keywords.help)
    );
    style(
        notAllowed,
        cursor(Keywords.notAllowed)
    );
    style(
        none,
        cursor(Keywords.none)
    );
    style(
        contextMenu,
        cursor(Keywords.contextMenu)
    );
    style(
        cell,
        cursor(Keywords.cell)
    );
    style(
        crosshair,
        cursor(Keywords.crosshair)
    );
    style(
        verticalText,
        cursor(Keywords.verticalText)
    );
    style(
        alias,
        cursor(Keywords.alias)
    );
    style(
        copy,
        cursor(Keywords.copy)
    );
    style(
        noDrop,
        cursor(Keywords.noDrop)
    );
    style(
        grab,
        cursor(Keywords.grab)
    );
    style(
        grabbing,
        cursor(Keywords.grabbing)
    );
    style(
        allScroll,
        cursor(Keywords.allScroll)
    );
    style(
        colResize,
        cursor(Keywords.colResize)
    );
    style(
        rowResize,
        cursor(Keywords.rowResize)
    );
    style(
        nResize,
        cursor(Keywords.nResize)
    );
    style(
        eResize,
        cursor(Keywords.eResize)
    );
    style(
        sResize,
        cursor(Keywords.sResize)
    );
    style(
        wResize,
        cursor(Keywords.wResize)
    );
    style(
        neResize,
        cursor(Keywords.neResize)
    );
    style(
        nwResize,
        cursor(Keywords.nwResize)
    );
    style(
        seResize,
        cursor(Keywords.seResize)
    );
    style(
        swResize,
        cursor(Keywords.swResize)
    );
    style(
        ewResize,
        cursor(Keywords.ewResize)
    );
    style(
        nsResize,
        cursor(Keywords.nsResize)
    );
    style(
        neswResize,
        cursor(Keywords.neswResize)
    );
    style(
        nwseResize,
        cursor(Keywords.nwseResize)
    );
    style(
        zoomIn,
        cursor(Keywords.zoomIn)
    );
    style(
        zoomOut,
        cursor(Keywords.zoomOut)
    );
  }

  private void definition1() {
    style(
        hover.auto, HOVER,
        cursor(Keywords.auto)
    );
    style(
        hover.defaultCursor, HOVER,
        cursor(Keywords.defaultKw)
    );
    style(
        hover.pointer, HOVER,
        cursor(Keywords.pointer)
    );
    style(
        hover.wait, HOVER,
        cursor(Keywords.wait)
    );
    style(
        hover.text, HOVER,
        cursor(Keywords.text)
    );
    style(
        hover.move, HOVER,
        cursor(Keywords.move)
    );
    style(
        hover.help, HOVER,
        cursor(Keywords.help)
    );
    style(
        hover.notAllowed, HOVER,
        cursor(Keywords.notAllowed)
    );
    style(
        hover.none, HOVER,
        cursor(Keywords.none)
    );
    style(
        hover.contextMenu, HOVER,
        cursor(Keywords.contextMenu)
    );
    style(
        hover.cell, HOVER,
        cursor(Keywords.cell)
    );
    style(
        hover.crosshair, HOVER,
        cursor(Keywords.crosshair)
    );
    style(
        hover.verticalText, HOVER,
        cursor(Keywords.verticalText)
    );
    style(
        hover.alias, HOVER,
        cursor(Keywords.alias)
    );
    style(
        hover.copy, HOVER,
        cursor(Keywords.copy)
    );
    style(
        hover.noDrop, HOVER,
        cursor(Keywords.noDrop)
    );
    style(
        hover.grab, HOVER,
        cursor(Keywords.grab)
    );
    style(
        hover.grabbing, HOVER,
        cursor(Keywords.grabbing)
    );
    style(
        hover.allScroll, HOVER,
        cursor(Keywords.allScroll)
    );
    style(
        hover.colResize, HOVER,
        cursor(Keywords.colResize)
    );
    style(
        hover.rowResize, HOVER,
        cursor(Keywords.rowResize)
    );
    style(
        hover.nResize, HOVER,
        cursor(Keywords.nResize)
    );
    style(
        hover.eResize, HOVER,
        cursor(Keywords.eResize)
    );
    style(
        hover.sResize, HOVER,
        cursor(Keywords.sResize)
    );
    style(
        hover.wResize, HOVER,
        cursor(Keywords.wResize)
    );
    style(
        hover.neResize, HOVER,
        cursor(Keywords.neResize)
    );
    style(
        hover.nwResize, HOVER,
        cursor(Keywords.nwResize)
    );
    style(
        hover.seResize, HOVER,
        cursor(Keywords.seResize)
    );
    style(
        hover.swResize, HOVER,
        cursor(Keywords.swResize)
    );
    style(
        hover.ewResize, HOVER,
        cursor(Keywords.ewResize)
    );
    style(
        hover.nsResize, HOVER,
        cursor(Keywords.nsResize)
    );
    style(
        hover.neswResize, HOVER,
        cursor(Keywords.neswResize)
    );
    style(
        hover.nwseResize, HOVER,
        cursor(Keywords.nwseResize)
    );
    style(
        hover.zoomIn, HOVER,
        cursor(Keywords.zoomIn)
    );
    style(
        hover.zoomOut, HOVER,
        cursor(Keywords.zoomOut)
    );
  }

  private void definition2() {
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.auto,
            cursor(Keywords.auto)
        ),

        style(
            sm.defaultCursor,
            cursor(Keywords.defaultKw)
        ),

        style(
            sm.pointer,
            cursor(Keywords.pointer)
        ),

        style(
            sm.wait,
            cursor(Keywords.wait)
        ),

        style(
            sm.text,
            cursor(Keywords.text)
        ),

        style(
            sm.move,
            cursor(Keywords.move)
        ),

        style(
            sm.help,
            cursor(Keywords.help)
        ),

        style(
            sm.notAllowed,
            cursor(Keywords.notAllowed)
        ),

        style(
            sm.none,
            cursor(Keywords.none)
        ),

        style(
            sm.contextMenu,
            cursor(Keywords.contextMenu)
        ),

        style(
            sm.cell,
            cursor(Keywords.cell)
        ),

        style(
            sm.crosshair,
            cursor(Keywords.crosshair)
        ),

        style(
            sm.verticalText,
            cursor(Keywords.verticalText)
        ),

        style(
            sm.alias,
            cursor(Keywords.alias)
        ),

        style(
            sm.copy,
            cursor(Keywords.copy)
        ),

        style(
            sm.noDrop,
            cursor(Keywords.noDrop)
        ),

        style(
            sm.grab,
            cursor(Keywords.grab)
        ),

        style(
            sm.grabbing,
            cursor(Keywords.grabbing)
        ),

        style(
            sm.allScroll,
            cursor(Keywords.allScroll)
        ),

        style(
            sm.colResize,
            cursor(Keywords.colResize)
        ),

        style(
            sm.rowResize,
            cursor(Keywords.rowResize)
        ),

        style(
            sm.nResize,
            cursor(Keywords.nResize)
        ),

        style(
            sm.eResize,
            cursor(Keywords.eResize)
        ),

        style(
            sm.sResize,
            cursor(Keywords.sResize)
        ),

        style(
            sm.wResize,
            cursor(Keywords.wResize)
        ),

        style(
            sm.neResize,
            cursor(Keywords.neResize)
        ),

        style(
            sm.nwResize,
            cursor(Keywords.nwResize)
        ),

        style(
            sm.seResize,
            cursor(Keywords.seResize)
        ),

        style(
            sm.swResize,
            cursor(Keywords.swResize)
        ),

        style(
            sm.ewResize,
            cursor(Keywords.ewResize)
        ),

        style(
            sm.nsResize,
            cursor(Keywords.nsResize)
        ),

        style(
            sm.neswResize,
            cursor(Keywords.neswResize)
        ),

        style(
            sm.nwseResize,
            cursor(Keywords.nwseResize)
        ),

        style(
            sm.zoomIn,
            cursor(Keywords.zoomIn)
        ),

        style(
            sm.zoomOut,
            cursor(Keywords.zoomOut)
        )
    );
  }

  private void definition3() {
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.auto,
            cursor(Keywords.auto)
        ),

        style(
            md.defaultCursor,
            cursor(Keywords.defaultKw)
        ),

        style(
            md.pointer,
            cursor(Keywords.pointer)
        ),

        style(
            md.wait,
            cursor(Keywords.wait)
        ),

        style(
            md.text,
            cursor(Keywords.text)
        ),

        style(
            md.move,
            cursor(Keywords.move)
        ),

        style(
            md.help,
            cursor(Keywords.help)
        ),

        style(
            md.notAllowed,
            cursor(Keywords.notAllowed)
        ),

        style(
            md.none,
            cursor(Keywords.none)
        ),

        style(
            md.contextMenu,
            cursor(Keywords.contextMenu)
        ),

        style(
            md.cell,
            cursor(Keywords.cell)
        ),

        style(
            md.crosshair,
            cursor(Keywords.crosshair)
        ),

        style(
            md.verticalText,
            cursor(Keywords.verticalText)
        ),

        style(
            md.alias,
            cursor(Keywords.alias)
        ),

        style(
            md.copy,
            cursor(Keywords.copy)
        ),

        style(
            md.noDrop,
            cursor(Keywords.noDrop)
        ),

        style(
            md.grab,
            cursor(Keywords.grab)
        ),

        style(
            md.grabbing,
            cursor(Keywords.grabbing)
        ),

        style(
            md.allScroll,
            cursor(Keywords.allScroll)
        ),

        style(
            md.colResize,
            cursor(Keywords.colResize)
        ),

        style(
            md.rowResize,
            cursor(Keywords.rowResize)
        ),

        style(
            md.nResize,
            cursor(Keywords.nResize)
        ),

        style(
            md.eResize,
            cursor(Keywords.eResize)
        ),

        style(
            md.sResize,
            cursor(Keywords.sResize)
        ),

        style(
            md.wResize,
            cursor(Keywords.wResize)
        ),

        style(
            md.neResize,
            cursor(Keywords.neResize)
        ),

        style(
            md.nwResize,
            cursor(Keywords.nwResize)
        ),

        style(
            md.seResize,
            cursor(Keywords.seResize)
        ),

        style(
            md.swResize,
            cursor(Keywords.swResize)
        ),

        style(
            md.ewResize,
            cursor(Keywords.ewResize)
        ),

        style(
            md.nsResize,
            cursor(Keywords.nsResize)
        ),

        style(
            md.neswResize,
            cursor(Keywords.neswResize)
        ),

        style(
            md.nwseResize,
            cursor(Keywords.nwseResize)
        ),

        style(
            md.zoomIn,
            cursor(Keywords.zoomIn)
        ),

        style(
            md.zoomOut,
            cursor(Keywords.zoomOut)
        )
    );
  }

  private void definition4() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.auto,
            cursor(Keywords.auto)
        ),

        style(
            lg.defaultCursor,
            cursor(Keywords.defaultKw)
        ),

        style(
            lg.pointer,
            cursor(Keywords.pointer)
        ),

        style(
            lg.wait,
            cursor(Keywords.wait)
        ),

        style(
            lg.text,
            cursor(Keywords.text)
        ),

        style(
            lg.move,
            cursor(Keywords.move)
        ),

        style(
            lg.help,
            cursor(Keywords.help)
        ),

        style(
            lg.notAllowed,
            cursor(Keywords.notAllowed)
        ),

        style(
            lg.none,
            cursor(Keywords.none)
        ),

        style(
            lg.contextMenu,
            cursor(Keywords.contextMenu)
        ),

        style(
            lg.cell,
            cursor(Keywords.cell)
        ),

        style(
            lg.crosshair,
            cursor(Keywords.crosshair)
        ),

        style(
            lg.verticalText,
            cursor(Keywords.verticalText)
        ),

        style(
            lg.alias,
            cursor(Keywords.alias)
        ),

        style(
            lg.copy,
            cursor(Keywords.copy)
        ),

        style(
            lg.noDrop,
            cursor(Keywords.noDrop)
        ),

        style(
            lg.grab,
            cursor(Keywords.grab)
        ),

        style(
            lg.grabbing,
            cursor(Keywords.grabbing)
        ),

        style(
            lg.allScroll,
            cursor(Keywords.allScroll)
        ),

        style(
            lg.colResize,
            cursor(Keywords.colResize)
        ),

        style(
            lg.rowResize,
            cursor(Keywords.rowResize)
        ),

        style(
            lg.nResize,
            cursor(Keywords.nResize)
        ),

        style(
            lg.eResize,
            cursor(Keywords.eResize)
        ),

        style(
            lg.sResize,
            cursor(Keywords.sResize)
        ),

        style(
            lg.wResize,
            cursor(Keywords.wResize)
        ),

        style(
            lg.neResize,
            cursor(Keywords.neResize)
        ),

        style(
            lg.nwResize,
            cursor(Keywords.nwResize)
        ),

        style(
            lg.seResize,
            cursor(Keywords.seResize)
        ),

        style(
            lg.swResize,
            cursor(Keywords.swResize)
        ),

        style(
            lg.ewResize,
            cursor(Keywords.ewResize)
        ),

        style(
            lg.nsResize,
            cursor(Keywords.nsResize)
        ),

        style(
            lg.neswResize,
            cursor(Keywords.neswResize)
        ),

        style(
            lg.nwseResize,
            cursor(Keywords.nwseResize)
        ),

        style(
            lg.zoomIn,
            cursor(Keywords.zoomIn)
        ),

        style(
            lg.zoomOut,
            cursor(Keywords.zoomOut)
        )
    );
  }

  private void definition5() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.auto,
            cursor(Keywords.auto)
        ),

        style(
            xl.defaultCursor,
            cursor(Keywords.defaultKw)
        ),

        style(
            xl.pointer,
            cursor(Keywords.pointer)
        ),

        style(
            xl.wait,
            cursor(Keywords.wait)
        ),

        style(
            xl.text,
            cursor(Keywords.text)
        ),

        style(
            xl.move,
            cursor(Keywords.move)
        ),

        style(
            xl.help,
            cursor(Keywords.help)
        ),

        style(
            xl.notAllowed,
            cursor(Keywords.notAllowed)
        ),

        style(
            xl.none,
            cursor(Keywords.none)
        ),

        style(
            xl.contextMenu,
            cursor(Keywords.contextMenu)
        ),

        style(
            xl.cell,
            cursor(Keywords.cell)
        ),

        style(
            xl.crosshair,
            cursor(Keywords.crosshair)
        ),

        style(
            xl.verticalText,
            cursor(Keywords.verticalText)
        ),

        style(
            xl.alias,
            cursor(Keywords.alias)
        ),

        style(
            xl.copy,
            cursor(Keywords.copy)
        ),

        style(
            xl.noDrop,
            cursor(Keywords.noDrop)
        ),

        style(
            xl.grab,
            cursor(Keywords.grab)
        ),

        style(
            xl.grabbing,
            cursor(Keywords.grabbing)
        ),

        style(
            xl.allScroll,
            cursor(Keywords.allScroll)
        ),

        style(
            xl.colResize,
            cursor(Keywords.colResize)
        ),

        style(
            xl.rowResize,
            cursor(Keywords.rowResize)
        ),

        style(
            xl.nResize,
            cursor(Keywords.nResize)
        ),

        style(
            xl.eResize,
            cursor(Keywords.eResize)
        ),

        style(
            xl.sResize,
            cursor(Keywords.sResize)
        ),

        style(
            xl.wResize,
            cursor(Keywords.wResize)
        ),

        style(
            xl.neResize,
            cursor(Keywords.neResize)
        ),

        style(
            xl.nwResize,
            cursor(Keywords.nwResize)
        ),

        style(
            xl.seResize,
            cursor(Keywords.seResize)
        ),

        style(
            xl.swResize,
            cursor(Keywords.swResize)
        ),

        style(
            xl.ewResize,
            cursor(Keywords.ewResize)
        ),

        style(
            xl.nsResize,
            cursor(Keywords.nsResize)
        ),

        style(
            xl.neswResize,
            cursor(Keywords.neswResize)
        ),

        style(
            xl.nwseResize,
            cursor(Keywords.nwseResize)
        ),

        style(
            xl.zoomIn,
            cursor(Keywords.zoomIn)
        ),

        style(
            xl.zoomOut,
            cursor(Keywords.zoomOut)
        )
    );
  }

  private void definition6() {
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.auto,
            cursor(Keywords.auto)
        ),

        style(
            x2l.defaultCursor,
            cursor(Keywords.defaultKw)
        ),

        style(
            x2l.pointer,
            cursor(Keywords.pointer)
        ),

        style(
            x2l.wait,
            cursor(Keywords.wait)
        ),

        style(
            x2l.text,
            cursor(Keywords.text)
        ),

        style(
            x2l.move,
            cursor(Keywords.move)
        ),

        style(
            x2l.help,
            cursor(Keywords.help)
        ),

        style(
            x2l.notAllowed,
            cursor(Keywords.notAllowed)
        ),

        style(
            x2l.none,
            cursor(Keywords.none)
        ),

        style(
            x2l.contextMenu,
            cursor(Keywords.contextMenu)
        ),

        style(
            x2l.cell,
            cursor(Keywords.cell)
        ),

        style(
            x2l.crosshair,
            cursor(Keywords.crosshair)
        ),

        style(
            x2l.verticalText,
            cursor(Keywords.verticalText)
        ),

        style(
            x2l.alias,
            cursor(Keywords.alias)
        ),

        style(
            x2l.copy,
            cursor(Keywords.copy)
        ),

        style(
            x2l.noDrop,
            cursor(Keywords.noDrop)
        ),

        style(
            x2l.grab,
            cursor(Keywords.grab)
        ),

        style(
            x2l.grabbing,
            cursor(Keywords.grabbing)
        ),

        style(
            x2l.allScroll,
            cursor(Keywords.allScroll)
        ),

        style(
            x2l.colResize,
            cursor(Keywords.colResize)
        ),

        style(
            x2l.rowResize,
            cursor(Keywords.rowResize)
        ),

        style(
            x2l.nResize,
            cursor(Keywords.nResize)
        ),

        style(
            x2l.eResize,
            cursor(Keywords.eResize)
        ),

        style(
            x2l.sResize,
            cursor(Keywords.sResize)
        ),

        style(
            x2l.wResize,
            cursor(Keywords.wResize)
        ),

        style(
            x2l.neResize,
            cursor(Keywords.neResize)
        ),

        style(
            x2l.nwResize,
            cursor(Keywords.nwResize)
        ),

        style(
            x2l.seResize,
            cursor(Keywords.seResize)
        ),

        style(
            x2l.swResize,
            cursor(Keywords.swResize)
        ),

        style(
            x2l.ewResize,
            cursor(Keywords.ewResize)
        ),

        style(
            x2l.nsResize,
            cursor(Keywords.nsResize)
        ),

        style(
            x2l.neswResize,
            cursor(Keywords.neswResize)
        ),

        style(
            x2l.nwseResize,
            cursor(Keywords.nwseResize)
        ),

        style(
            x2l.zoomIn,
            cursor(Keywords.zoomIn)
        ),

        style(
            x2l.zoomOut,
            cursor(Keywords.zoomOut)
        )
    );
  }

  public interface hover {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector defaultCursor = Css.randomDot(5);

    ClassSelector pointer = Css.randomDot(5);

    ClassSelector wait = Css.randomDot(5);

    ClassSelector text = Css.randomDot(5);

    ClassSelector move = Css.randomDot(5);

    ClassSelector help = Css.randomDot(5);

    ClassSelector notAllowed = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector contextMenu = Css.randomDot(5);

    ClassSelector cell = Css.randomDot(5);

    ClassSelector crosshair = Css.randomDot(5);

    ClassSelector verticalText = Css.randomDot(5);

    ClassSelector alias = Css.randomDot(5);

    ClassSelector copy = Css.randomDot(5);

    ClassSelector noDrop = Css.randomDot(5);

    ClassSelector grab = Css.randomDot(5);

    ClassSelector grabbing = Css.randomDot(5);

    ClassSelector allScroll = Css.randomDot(5);

    ClassSelector colResize = Css.randomDot(5);

    ClassSelector rowResize = Css.randomDot(5);

    ClassSelector nResize = Css.randomDot(5);

    ClassSelector eResize = Css.randomDot(5);

    ClassSelector sResize = Css.randomDot(5);

    ClassSelector wResize = Css.randomDot(5);

    ClassSelector neResize = Css.randomDot(5);

    ClassSelector nwResize = Css.randomDot(5);

    ClassSelector seResize = Css.randomDot(5);

    ClassSelector swResize = Css.randomDot(5);

    ClassSelector ewResize = Css.randomDot(5);

    ClassSelector nsResize = Css.randomDot(5);

    ClassSelector neswResize = Css.randomDot(5);

    ClassSelector nwseResize = Css.randomDot(5);

    ClassSelector zoomIn = Css.randomDot(5);

    ClassSelector zoomOut = Css.randomDot(5);

  }

  public interface sm {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector defaultCursor = Css.randomDot(5);

    ClassSelector pointer = Css.randomDot(5);

    ClassSelector wait = Css.randomDot(5);

    ClassSelector text = Css.randomDot(5);

    ClassSelector move = Css.randomDot(5);

    ClassSelector help = Css.randomDot(5);

    ClassSelector notAllowed = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector contextMenu = Css.randomDot(5);

    ClassSelector cell = Css.randomDot(5);

    ClassSelector crosshair = Css.randomDot(5);

    ClassSelector verticalText = Css.randomDot(5);

    ClassSelector alias = Css.randomDot(5);

    ClassSelector copy = Css.randomDot(5);

    ClassSelector noDrop = Css.randomDot(5);

    ClassSelector grab = Css.randomDot(5);

    ClassSelector grabbing = Css.randomDot(5);

    ClassSelector allScroll = Css.randomDot(5);

    ClassSelector colResize = Css.randomDot(5);

    ClassSelector rowResize = Css.randomDot(5);

    ClassSelector nResize = Css.randomDot(5);

    ClassSelector eResize = Css.randomDot(5);

    ClassSelector sResize = Css.randomDot(5);

    ClassSelector wResize = Css.randomDot(5);

    ClassSelector neResize = Css.randomDot(5);

    ClassSelector nwResize = Css.randomDot(5);

    ClassSelector seResize = Css.randomDot(5);

    ClassSelector swResize = Css.randomDot(5);

    ClassSelector ewResize = Css.randomDot(5);

    ClassSelector nsResize = Css.randomDot(5);

    ClassSelector neswResize = Css.randomDot(5);

    ClassSelector nwseResize = Css.randomDot(5);

    ClassSelector zoomIn = Css.randomDot(5);

    ClassSelector zoomOut = Css.randomDot(5);

  }

  public interface md {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector defaultCursor = Css.randomDot(5);

    ClassSelector pointer = Css.randomDot(5);

    ClassSelector wait = Css.randomDot(5);

    ClassSelector text = Css.randomDot(5);

    ClassSelector move = Css.randomDot(5);

    ClassSelector help = Css.randomDot(5);

    ClassSelector notAllowed = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector contextMenu = Css.randomDot(5);

    ClassSelector cell = Css.randomDot(5);

    ClassSelector crosshair = Css.randomDot(5);

    ClassSelector verticalText = Css.randomDot(5);

    ClassSelector alias = Css.randomDot(5);

    ClassSelector copy = Css.randomDot(5);

    ClassSelector noDrop = Css.randomDot(5);

    ClassSelector grab = Css.randomDot(5);

    ClassSelector grabbing = Css.randomDot(5);

    ClassSelector allScroll = Css.randomDot(5);

    ClassSelector colResize = Css.randomDot(5);

    ClassSelector rowResize = Css.randomDot(5);

    ClassSelector nResize = Css.randomDot(5);

    ClassSelector eResize = Css.randomDot(5);

    ClassSelector sResize = Css.randomDot(5);

    ClassSelector wResize = Css.randomDot(5);

    ClassSelector neResize = Css.randomDot(5);

    ClassSelector nwResize = Css.randomDot(5);

    ClassSelector seResize = Css.randomDot(5);

    ClassSelector swResize = Css.randomDot(5);

    ClassSelector ewResize = Css.randomDot(5);

    ClassSelector nsResize = Css.randomDot(5);

    ClassSelector neswResize = Css.randomDot(5);

    ClassSelector nwseResize = Css.randomDot(5);

    ClassSelector zoomIn = Css.randomDot(5);

    ClassSelector zoomOut = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector defaultCursor = Css.randomDot(5);

    ClassSelector pointer = Css.randomDot(5);

    ClassSelector wait = Css.randomDot(5);

    ClassSelector text = Css.randomDot(5);

    ClassSelector move = Css.randomDot(5);

    ClassSelector help = Css.randomDot(5);

    ClassSelector notAllowed = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector contextMenu = Css.randomDot(5);

    ClassSelector cell = Css.randomDot(5);

    ClassSelector crosshair = Css.randomDot(5);

    ClassSelector verticalText = Css.randomDot(5);

    ClassSelector alias = Css.randomDot(5);

    ClassSelector copy = Css.randomDot(5);

    ClassSelector noDrop = Css.randomDot(5);

    ClassSelector grab = Css.randomDot(5);

    ClassSelector grabbing = Css.randomDot(5);

    ClassSelector allScroll = Css.randomDot(5);

    ClassSelector colResize = Css.randomDot(5);

    ClassSelector rowResize = Css.randomDot(5);

    ClassSelector nResize = Css.randomDot(5);

    ClassSelector eResize = Css.randomDot(5);

    ClassSelector sResize = Css.randomDot(5);

    ClassSelector wResize = Css.randomDot(5);

    ClassSelector neResize = Css.randomDot(5);

    ClassSelector nwResize = Css.randomDot(5);

    ClassSelector seResize = Css.randomDot(5);

    ClassSelector swResize = Css.randomDot(5);

    ClassSelector ewResize = Css.randomDot(5);

    ClassSelector nsResize = Css.randomDot(5);

    ClassSelector neswResize = Css.randomDot(5);

    ClassSelector nwseResize = Css.randomDot(5);

    ClassSelector zoomIn = Css.randomDot(5);

    ClassSelector zoomOut = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector defaultCursor = Css.randomDot(5);

    ClassSelector pointer = Css.randomDot(5);

    ClassSelector wait = Css.randomDot(5);

    ClassSelector text = Css.randomDot(5);

    ClassSelector move = Css.randomDot(5);

    ClassSelector help = Css.randomDot(5);

    ClassSelector notAllowed = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector contextMenu = Css.randomDot(5);

    ClassSelector cell = Css.randomDot(5);

    ClassSelector crosshair = Css.randomDot(5);

    ClassSelector verticalText = Css.randomDot(5);

    ClassSelector alias = Css.randomDot(5);

    ClassSelector copy = Css.randomDot(5);

    ClassSelector noDrop = Css.randomDot(5);

    ClassSelector grab = Css.randomDot(5);

    ClassSelector grabbing = Css.randomDot(5);

    ClassSelector allScroll = Css.randomDot(5);

    ClassSelector colResize = Css.randomDot(5);

    ClassSelector rowResize = Css.randomDot(5);

    ClassSelector nResize = Css.randomDot(5);

    ClassSelector eResize = Css.randomDot(5);

    ClassSelector sResize = Css.randomDot(5);

    ClassSelector wResize = Css.randomDot(5);

    ClassSelector neResize = Css.randomDot(5);

    ClassSelector nwResize = Css.randomDot(5);

    ClassSelector seResize = Css.randomDot(5);

    ClassSelector swResize = Css.randomDot(5);

    ClassSelector ewResize = Css.randomDot(5);

    ClassSelector nsResize = Css.randomDot(5);

    ClassSelector neswResize = Css.randomDot(5);

    ClassSelector nwseResize = Css.randomDot(5);

    ClassSelector zoomIn = Css.randomDot(5);

    ClassSelector zoomOut = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector auto = Css.randomDot(5);

    ClassSelector defaultCursor = Css.randomDot(5);

    ClassSelector pointer = Css.randomDot(5);

    ClassSelector wait = Css.randomDot(5);

    ClassSelector text = Css.randomDot(5);

    ClassSelector move = Css.randomDot(5);

    ClassSelector help = Css.randomDot(5);

    ClassSelector notAllowed = Css.randomDot(5);

    ClassSelector none = Css.randomDot(5);

    ClassSelector contextMenu = Css.randomDot(5);

    ClassSelector cell = Css.randomDot(5);

    ClassSelector crosshair = Css.randomDot(5);

    ClassSelector verticalText = Css.randomDot(5);

    ClassSelector alias = Css.randomDot(5);

    ClassSelector copy = Css.randomDot(5);

    ClassSelector noDrop = Css.randomDot(5);

    ClassSelector grab = Css.randomDot(5);

    ClassSelector grabbing = Css.randomDot(5);

    ClassSelector allScroll = Css.randomDot(5);

    ClassSelector colResize = Css.randomDot(5);

    ClassSelector rowResize = Css.randomDot(5);

    ClassSelector nResize = Css.randomDot(5);

    ClassSelector eResize = Css.randomDot(5);

    ClassSelector sResize = Css.randomDot(5);

    ClassSelector wResize = Css.randomDot(5);

    ClassSelector neResize = Css.randomDot(5);

    ClassSelector nwResize = Css.randomDot(5);

    ClassSelector seResize = Css.randomDot(5);

    ClassSelector swResize = Css.randomDot(5);

    ClassSelector ewResize = Css.randomDot(5);

    ClassSelector nsResize = Css.randomDot(5);

    ClassSelector neswResize = Css.randomDot(5);

    ClassSelector nwseResize = Css.randomDot(5);

    ClassSelector zoomIn = Css.randomDot(5);

    ClassSelector zoomOut = Css.randomDot(5);

  }

}