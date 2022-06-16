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
package br.com.objectos.css.select;

import br.com.objectos.css.io.CssWriter;
import br.com.objectos.css.io.CssWriterVisitor;
import br.com.objectos.css.io.MinifiedCssWriter;
import br.com.objectos.css.io.PrettyCssWriter;
import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.css.sheet.StyleSheetDsl;
import java.io.IOException;
import objectos.util.UnmodifiableList;

public enum Combinator implements CssWriterVisitor, SelectorElement, IsTerminal {

  ADJACENT_SIBLING('+', "plus") {
    @Override
    final Selector combine(UnmodifiableList<Selector> selectors) {
      return new AdjacentSiblingSelector(selectors);
    }
  },

  CHILD('>', "gt") {
    @Override
    final Selector combine(UnmodifiableList<Selector> selectors) {
      return new ChildSelector(selectors);
    }
  },

  DESCENDANT(' ', "sp") {
    @Override
    public final void visitPrettyCssWriter(PrettyCssWriter w) throws IOException {
      w.write(' ');
    }

    @Override
    final Selector combine(UnmodifiableList<Selector> selectors) {
      return new DescendantSelector(selectors);
    }
  },

  GENERAL_SIBLING('~', "tilde") {
    @Override
    final Selector combine(UnmodifiableList<Selector> selectors) {
      return new GeneralSiblingSelector(selectors);
    }
  },

  LIST(',', "or") {
    @Override
    public final void visitPrettyCssWriter(PrettyCssWriter w) throws IOException {
      w.write(',');
      w.write(' ');
    }

    @Override
    final Selector combine(UnmodifiableList<Selector> selectors) {
      return new SelectorList(selectors);
    }
  };

  private static final Combinator[] ARRAY = values();

  private final String javaName;
  private final char symbol;

  private Combinator(char symbol, String javaName) {
    this.symbol = symbol;
    this.javaName = javaName;
  }

  public static Combinator getByCode(int code) {
    return ARRAY[code];
  }

  @Override
  public final void acceptCssWriter(CssWriter w) throws IOException {
    w.acceptCssWriterVisitor(this);
  }

  @Override
  public final void acceptRuleElementVisitor(StyleSheetDsl dsl) {
    dsl.addCombinator(this);
  }

  @Override
  public final void acceptSelectorBuilderDsl(Selector.BuilderDsl builder) {
    builder.addCombinator(this);
  }

  public final int getCode() {
    return ordinal();
  }

  public final String getJavaName() {
    return javaName;
  }

  @Override
  public final void visitMinifiedCssWriter(MinifiedCssWriter w) throws IOException {
    w.write(symbol);
  }

  @Override
  public void visitPrettyCssWriter(PrettyCssWriter w) throws IOException {
    w.write(' ');
    w.write(symbol);
    w.write(' ');
  }

  abstract Selector combine(UnmodifiableList<Selector> selectors);

}