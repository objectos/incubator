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
package br.com.objectos.css.framework.layout;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Display extends AbstractStyleSheet {

  public static final ClassSelector hidden = Css.randomDot(5);

  public static final ClassSelector block = Css.randomDot(5);

  public static final ClassSelector flowRoot = Css.randomDot(5);

  public static final ClassSelector inlineBlock = Css.randomDot(5);

  public static final ClassSelector inline = Css.randomDot(5);

  public static final ClassSelector flex = Css.randomDot(5);

  public static final ClassSelector inlineFlex = Css.randomDot(5);

  public static final ClassSelector grid = Css.randomDot(5);

  public static final ClassSelector inlineGrid = Css.randomDot(5);

  public static final ClassSelector table = Css.randomDot(5);

  public static final ClassSelector tableCaption = Css.randomDot(5);

  public static final ClassSelector tableCell = Css.randomDot(5);

  public static final ClassSelector tableColumn = Css.randomDot(5);

  public static final ClassSelector tableColumnGroup = Css.randomDot(5);

  public static final ClassSelector tableFooterGroup = Css.randomDot(5);

  public static final ClassSelector tableHeaderGroup = Css.randomDot(5);

  public static final ClassSelector tableRowGroup = Css.randomDot(5);

  public static final ClassSelector tableRow = Css.randomDot(5);

  @Override
  protected final void definition() {
    style(
        hidden,
        display(Keywords.none)
    );
    style(
        block,
        display(Keywords.block)
    );
    style(
        flowRoot,
        display(Keywords.flowRoot)
    );
    style(
        inlineBlock,
        display(Keywords.inlineBlock)
    );
    style(
        inline,
        display(Keywords.inline)
    );
    style(
        flex,
        display(Keywords.flex)
    );
    style(
        inlineFlex,
        display(Keywords.inlineFlex)
    );
    style(
        grid,
        display(Keywords.grid)
    );
    style(
        inlineGrid,
        display(Keywords.inlineGrid)
    );
    style(
        table,
        display(Keywords.tableKw)
    );
    style(
        tableCaption,
        display(Keywords.tableCaption)
    );
    style(
        tableCell,
        display(Keywords.tableCell)
    );
    style(
        tableColumn,
        display(Keywords.tableColumn)
    );
    style(
        tableColumnGroup,
        display(Keywords.tableColumnGroup)
    );
    style(
        tableFooterGroup,
        display(Keywords.tableFooterGroup)
    );
    style(
        tableHeaderGroup,
        display(Keywords.tableHeaderGroup)
    );
    style(
        tableRowGroup,
        display(Keywords.tableRowGroup)
    );
    style(
        tableRow,
        display(Keywords.tableRow)
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(640)),

        style(
            sm.hidden,
            display(Keywords.none)
        ),

        style(
            sm.block,
            display(Keywords.block)
        ),

        style(
            sm.flowRoot,
            display(Keywords.flowRoot)
        ),

        style(
            sm.inlineBlock,
            display(Keywords.inlineBlock)
        ),

        style(
            sm.inline,
            display(Keywords.inline)
        ),

        style(
            sm.flex,
            display(Keywords.flex)
        ),

        style(
            sm.inlineFlex,
            display(Keywords.inlineFlex)
        ),

        style(
            sm.grid,
            display(Keywords.grid)
        ),

        style(
            sm.inlineGrid,
            display(Keywords.inlineGrid)
        ),

        style(
            sm.table,
            display(Keywords.tableKw)
        ),

        style(
            sm.tableCaption,
            display(Keywords.tableCaption)
        ),

        style(
            sm.tableCell,
            display(Keywords.tableCell)
        ),

        style(
            sm.tableColumn,
            display(Keywords.tableColumn)
        ),

        style(
            sm.tableColumnGroup,
            display(Keywords.tableColumnGroup)
        ),

        style(
            sm.tableFooterGroup,
            display(Keywords.tableFooterGroup)
        ),

        style(
            sm.tableHeaderGroup,
            display(Keywords.tableHeaderGroup)
        ),

        style(
            sm.tableRowGroup,
            display(Keywords.tableRowGroup)
        ),

        style(
            sm.tableRow,
            display(Keywords.tableRow)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(768)),

        style(
            md.hidden,
            display(Keywords.none)
        ),

        style(
            md.block,
            display(Keywords.block)
        ),

        style(
            md.flowRoot,
            display(Keywords.flowRoot)
        ),

        style(
            md.inlineBlock,
            display(Keywords.inlineBlock)
        ),

        style(
            md.inline,
            display(Keywords.inline)
        ),

        style(
            md.flex,
            display(Keywords.flex)
        ),

        style(
            md.inlineFlex,
            display(Keywords.inlineFlex)
        ),

        style(
            md.grid,
            display(Keywords.grid)
        ),

        style(
            md.inlineGrid,
            display(Keywords.inlineGrid)
        ),

        style(
            md.table,
            display(Keywords.tableKw)
        ),

        style(
            md.tableCaption,
            display(Keywords.tableCaption)
        ),

        style(
            md.tableCell,
            display(Keywords.tableCell)
        ),

        style(
            md.tableColumn,
            display(Keywords.tableColumn)
        ),

        style(
            md.tableColumnGroup,
            display(Keywords.tableColumnGroup)
        ),

        style(
            md.tableFooterGroup,
            display(Keywords.tableFooterGroup)
        ),

        style(
            md.tableHeaderGroup,
            display(Keywords.tableHeaderGroup)
        ),

        style(
            md.tableRowGroup,
            display(Keywords.tableRowGroup)
        ),

        style(
            md.tableRow,
            display(Keywords.tableRow)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1024)),

        style(
            lg.hidden,
            display(Keywords.none)
        ),

        style(
            lg.block,
            display(Keywords.block)
        ),

        style(
            lg.flowRoot,
            display(Keywords.flowRoot)
        ),

        style(
            lg.inlineBlock,
            display(Keywords.inlineBlock)
        ),

        style(
            lg.inline,
            display(Keywords.inline)
        ),

        style(
            lg.flex,
            display(Keywords.flex)
        ),

        style(
            lg.inlineFlex,
            display(Keywords.inlineFlex)
        ),

        style(
            lg.grid,
            display(Keywords.grid)
        ),

        style(
            lg.inlineGrid,
            display(Keywords.inlineGrid)
        ),

        style(
            lg.table,
            display(Keywords.tableKw)
        ),

        style(
            lg.tableCaption,
            display(Keywords.tableCaption)
        ),

        style(
            lg.tableCell,
            display(Keywords.tableCell)
        ),

        style(
            lg.tableColumn,
            display(Keywords.tableColumn)
        ),

        style(
            lg.tableColumnGroup,
            display(Keywords.tableColumnGroup)
        ),

        style(
            lg.tableFooterGroup,
            display(Keywords.tableFooterGroup)
        ),

        style(
            lg.tableHeaderGroup,
            display(Keywords.tableHeaderGroup)
        ),

        style(
            lg.tableRowGroup,
            display(Keywords.tableRowGroup)
        ),

        style(
            lg.tableRow,
            display(Keywords.tableRow)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1280)),

        style(
            xl.hidden,
            display(Keywords.none)
        ),

        style(
            xl.block,
            display(Keywords.block)
        ),

        style(
            xl.flowRoot,
            display(Keywords.flowRoot)
        ),

        style(
            xl.inlineBlock,
            display(Keywords.inlineBlock)
        ),

        style(
            xl.inline,
            display(Keywords.inline)
        ),

        style(
            xl.flex,
            display(Keywords.flex)
        ),

        style(
            xl.inlineFlex,
            display(Keywords.inlineFlex)
        ),

        style(
            xl.grid,
            display(Keywords.grid)
        ),

        style(
            xl.inlineGrid,
            display(Keywords.inlineGrid)
        ),

        style(
            xl.table,
            display(Keywords.tableKw)
        ),

        style(
            xl.tableCaption,
            display(Keywords.tableCaption)
        ),

        style(
            xl.tableCell,
            display(Keywords.tableCell)
        ),

        style(
            xl.tableColumn,
            display(Keywords.tableColumn)
        ),

        style(
            xl.tableColumnGroup,
            display(Keywords.tableColumnGroup)
        ),

        style(
            xl.tableFooterGroup,
            display(Keywords.tableFooterGroup)
        ),

        style(
            xl.tableHeaderGroup,
            display(Keywords.tableHeaderGroup)
        ),

        style(
            xl.tableRowGroup,
            display(Keywords.tableRowGroup)
        ),

        style(
            xl.tableRow,
            display(Keywords.tableRow)
        )
    );
    media(
        AbstractStyleSheet.screen, minWidth(px(1440)),

        style(
            x2l.hidden,
            display(Keywords.none)
        ),

        style(
            x2l.block,
            display(Keywords.block)
        ),

        style(
            x2l.flowRoot,
            display(Keywords.flowRoot)
        ),

        style(
            x2l.inlineBlock,
            display(Keywords.inlineBlock)
        ),

        style(
            x2l.inline,
            display(Keywords.inline)
        ),

        style(
            x2l.flex,
            display(Keywords.flex)
        ),

        style(
            x2l.inlineFlex,
            display(Keywords.inlineFlex)
        ),

        style(
            x2l.grid,
            display(Keywords.grid)
        ),

        style(
            x2l.inlineGrid,
            display(Keywords.inlineGrid)
        ),

        style(
            x2l.table,
            display(Keywords.tableKw)
        ),

        style(
            x2l.tableCaption,
            display(Keywords.tableCaption)
        ),

        style(
            x2l.tableCell,
            display(Keywords.tableCell)
        ),

        style(
            x2l.tableColumn,
            display(Keywords.tableColumn)
        ),

        style(
            x2l.tableColumnGroup,
            display(Keywords.tableColumnGroup)
        ),

        style(
            x2l.tableFooterGroup,
            display(Keywords.tableFooterGroup)
        ),

        style(
            x2l.tableHeaderGroup,
            display(Keywords.tableHeaderGroup)
        ),

        style(
            x2l.tableRowGroup,
            display(Keywords.tableRowGroup)
        ),

        style(
            x2l.tableRow,
            display(Keywords.tableRow)
        )
    );
  }

  public interface sm {

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector block = Css.randomDot(5);

    ClassSelector flowRoot = Css.randomDot(5);

    ClassSelector inlineBlock = Css.randomDot(5);

    ClassSelector inline = Css.randomDot(5);

    ClassSelector flex = Css.randomDot(5);

    ClassSelector inlineFlex = Css.randomDot(5);

    ClassSelector grid = Css.randomDot(5);

    ClassSelector inlineGrid = Css.randomDot(5);

    ClassSelector table = Css.randomDot(5);

    ClassSelector tableCaption = Css.randomDot(5);

    ClassSelector tableCell = Css.randomDot(5);

    ClassSelector tableColumn = Css.randomDot(5);

    ClassSelector tableColumnGroup = Css.randomDot(5);

    ClassSelector tableFooterGroup = Css.randomDot(5);

    ClassSelector tableHeaderGroup = Css.randomDot(5);

    ClassSelector tableRowGroup = Css.randomDot(5);

    ClassSelector tableRow = Css.randomDot(5);

  }

  public interface md {

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector block = Css.randomDot(5);

    ClassSelector flowRoot = Css.randomDot(5);

    ClassSelector inlineBlock = Css.randomDot(5);

    ClassSelector inline = Css.randomDot(5);

    ClassSelector flex = Css.randomDot(5);

    ClassSelector inlineFlex = Css.randomDot(5);

    ClassSelector grid = Css.randomDot(5);

    ClassSelector inlineGrid = Css.randomDot(5);

    ClassSelector table = Css.randomDot(5);

    ClassSelector tableCaption = Css.randomDot(5);

    ClassSelector tableCell = Css.randomDot(5);

    ClassSelector tableColumn = Css.randomDot(5);

    ClassSelector tableColumnGroup = Css.randomDot(5);

    ClassSelector tableFooterGroup = Css.randomDot(5);

    ClassSelector tableHeaderGroup = Css.randomDot(5);

    ClassSelector tableRowGroup = Css.randomDot(5);

    ClassSelector tableRow = Css.randomDot(5);

  }

  public interface lg {

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector block = Css.randomDot(5);

    ClassSelector flowRoot = Css.randomDot(5);

    ClassSelector inlineBlock = Css.randomDot(5);

    ClassSelector inline = Css.randomDot(5);

    ClassSelector flex = Css.randomDot(5);

    ClassSelector inlineFlex = Css.randomDot(5);

    ClassSelector grid = Css.randomDot(5);

    ClassSelector inlineGrid = Css.randomDot(5);

    ClassSelector table = Css.randomDot(5);

    ClassSelector tableCaption = Css.randomDot(5);

    ClassSelector tableCell = Css.randomDot(5);

    ClassSelector tableColumn = Css.randomDot(5);

    ClassSelector tableColumnGroup = Css.randomDot(5);

    ClassSelector tableFooterGroup = Css.randomDot(5);

    ClassSelector tableHeaderGroup = Css.randomDot(5);

    ClassSelector tableRowGroup = Css.randomDot(5);

    ClassSelector tableRow = Css.randomDot(5);

  }

  public interface xl {

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector block = Css.randomDot(5);

    ClassSelector flowRoot = Css.randomDot(5);

    ClassSelector inlineBlock = Css.randomDot(5);

    ClassSelector inline = Css.randomDot(5);

    ClassSelector flex = Css.randomDot(5);

    ClassSelector inlineFlex = Css.randomDot(5);

    ClassSelector grid = Css.randomDot(5);

    ClassSelector inlineGrid = Css.randomDot(5);

    ClassSelector table = Css.randomDot(5);

    ClassSelector tableCaption = Css.randomDot(5);

    ClassSelector tableCell = Css.randomDot(5);

    ClassSelector tableColumn = Css.randomDot(5);

    ClassSelector tableColumnGroup = Css.randomDot(5);

    ClassSelector tableFooterGroup = Css.randomDot(5);

    ClassSelector tableHeaderGroup = Css.randomDot(5);

    ClassSelector tableRowGroup = Css.randomDot(5);

    ClassSelector tableRow = Css.randomDot(5);

  }

  public interface x2l {

    ClassSelector hidden = Css.randomDot(5);

    ClassSelector block = Css.randomDot(5);

    ClassSelector flowRoot = Css.randomDot(5);

    ClassSelector inlineBlock = Css.randomDot(5);

    ClassSelector inline = Css.randomDot(5);

    ClassSelector flex = Css.randomDot(5);

    ClassSelector inlineFlex = Css.randomDot(5);

    ClassSelector grid = Css.randomDot(5);

    ClassSelector inlineGrid = Css.randomDot(5);

    ClassSelector table = Css.randomDot(5);

    ClassSelector tableCaption = Css.randomDot(5);

    ClassSelector tableCell = Css.randomDot(5);

    ClassSelector tableColumn = Css.randomDot(5);

    ClassSelector tableColumnGroup = Css.randomDot(5);

    ClassSelector tableFooterGroup = Css.randomDot(5);

    ClassSelector tableHeaderGroup = Css.randomDot(5);

    ClassSelector tableRowGroup = Css.randomDot(5);

    ClassSelector tableRow = Css.randomDot(5);

  }

}