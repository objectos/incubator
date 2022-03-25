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
package br.com.objectos.css.framework.layout;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.Css;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

@Generated("br.com.objectos.css.maven.plugin.framework.FrameworkMojo")
public final class Display extends AbstractStyleSheet {

  public static final ClassSelector hidden = Css.dot("hidden");

  public static final ClassSelector block = Css.dot("block");

  public static final ClassSelector flowRoot = Css.dot("flow-root");

  public static final ClassSelector inlineBlock = Css.dot("inline-block");

  public static final ClassSelector inline = Css.dot("inline");

  public static final ClassSelector flex = Css.dot("flex");

  public static final ClassSelector inlineFlex = Css.dot("inline-flex");

  public static final ClassSelector grid = Css.dot("grid");

  public static final ClassSelector inlineGrid = Css.dot("inline-grid");

  public static final ClassSelector table = Css.dot("table");

  public static final ClassSelector tableCaption = Css.dot("table-caption");

  public static final ClassSelector tableCell = Css.dot("table-cell");

  public static final ClassSelector tableColumn = Css.dot("table-column");

  public static final ClassSelector tableColumnGroup = Css.dot("table-column-group");

  public static final ClassSelector tableFooterGroup = Css.dot("table-footer-group");

  public static final ClassSelector tableHeaderGroup = Css.dot("table-header-group");

  public static final ClassSelector tableRowGroup = Css.dot("table-row-group");

  public static final ClassSelector tableRow = Css.dot("table-row");

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
  }

  public interface sm {

    ClassSelector hidden = Css.dot("sm-hidden");

    ClassSelector block = Css.dot("sm-block");

    ClassSelector flowRoot = Css.dot("sm-flow-root");

    ClassSelector inlineBlock = Css.dot("sm-inline-block");

    ClassSelector inline = Css.dot("sm-inline");

    ClassSelector flex = Css.dot("sm-flex");

    ClassSelector inlineFlex = Css.dot("sm-inline-flex");

    ClassSelector grid = Css.dot("sm-grid");

    ClassSelector inlineGrid = Css.dot("sm-inline-grid");

    ClassSelector table = Css.dot("sm-table");

    ClassSelector tableCaption = Css.dot("sm-table-caption");

    ClassSelector tableCell = Css.dot("sm-table-cell");

    ClassSelector tableColumn = Css.dot("sm-table-column");

    ClassSelector tableColumnGroup = Css.dot("sm-table-column-group");

    ClassSelector tableFooterGroup = Css.dot("sm-table-footer-group");

    ClassSelector tableHeaderGroup = Css.dot("sm-table-header-group");

    ClassSelector tableRowGroup = Css.dot("sm-table-row-group");

    ClassSelector tableRow = Css.dot("sm-table-row");

  }

  public interface md {

    ClassSelector hidden = Css.dot("md-hidden");

    ClassSelector block = Css.dot("md-block");

    ClassSelector flowRoot = Css.dot("md-flow-root");

    ClassSelector inlineBlock = Css.dot("md-inline-block");

    ClassSelector inline = Css.dot("md-inline");

    ClassSelector flex = Css.dot("md-flex");

    ClassSelector inlineFlex = Css.dot("md-inline-flex");

    ClassSelector grid = Css.dot("md-grid");

    ClassSelector inlineGrid = Css.dot("md-inline-grid");

    ClassSelector table = Css.dot("md-table");

    ClassSelector tableCaption = Css.dot("md-table-caption");

    ClassSelector tableCell = Css.dot("md-table-cell");

    ClassSelector tableColumn = Css.dot("md-table-column");

    ClassSelector tableColumnGroup = Css.dot("md-table-column-group");

    ClassSelector tableFooterGroup = Css.dot("md-table-footer-group");

    ClassSelector tableHeaderGroup = Css.dot("md-table-header-group");

    ClassSelector tableRowGroup = Css.dot("md-table-row-group");

    ClassSelector tableRow = Css.dot("md-table-row");

  }

  public interface lg {

    ClassSelector hidden = Css.dot("lg-hidden");

    ClassSelector block = Css.dot("lg-block");

    ClassSelector flowRoot = Css.dot("lg-flow-root");

    ClassSelector inlineBlock = Css.dot("lg-inline-block");

    ClassSelector inline = Css.dot("lg-inline");

    ClassSelector flex = Css.dot("lg-flex");

    ClassSelector inlineFlex = Css.dot("lg-inline-flex");

    ClassSelector grid = Css.dot("lg-grid");

    ClassSelector inlineGrid = Css.dot("lg-inline-grid");

    ClassSelector table = Css.dot("lg-table");

    ClassSelector tableCaption = Css.dot("lg-table-caption");

    ClassSelector tableCell = Css.dot("lg-table-cell");

    ClassSelector tableColumn = Css.dot("lg-table-column");

    ClassSelector tableColumnGroup = Css.dot("lg-table-column-group");

    ClassSelector tableFooterGroup = Css.dot("lg-table-footer-group");

    ClassSelector tableHeaderGroup = Css.dot("lg-table-header-group");

    ClassSelector tableRowGroup = Css.dot("lg-table-row-group");

    ClassSelector tableRow = Css.dot("lg-table-row");

  }

  public interface xl {

    ClassSelector hidden = Css.dot("xl-hidden");

    ClassSelector block = Css.dot("xl-block");

    ClassSelector flowRoot = Css.dot("xl-flow-root");

    ClassSelector inlineBlock = Css.dot("xl-inline-block");

    ClassSelector inline = Css.dot("xl-inline");

    ClassSelector flex = Css.dot("xl-flex");

    ClassSelector inlineFlex = Css.dot("xl-inline-flex");

    ClassSelector grid = Css.dot("xl-grid");

    ClassSelector inlineGrid = Css.dot("xl-inline-grid");

    ClassSelector table = Css.dot("xl-table");

    ClassSelector tableCaption = Css.dot("xl-table-caption");

    ClassSelector tableCell = Css.dot("xl-table-cell");

    ClassSelector tableColumn = Css.dot("xl-table-column");

    ClassSelector tableColumnGroup = Css.dot("xl-table-column-group");

    ClassSelector tableFooterGroup = Css.dot("xl-table-footer-group");

    ClassSelector tableHeaderGroup = Css.dot("xl-table-header-group");

    ClassSelector tableRowGroup = Css.dot("xl-table-row-group");

    ClassSelector tableRow = Css.dot("xl-table-row");

  }

}