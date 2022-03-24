/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.java.statement;

import br.com.objectos.code.annotations.Ignore;
import br.com.objectos.code.java.element.Keywords;
import br.com.objectos.code.java.expression.Expression;
import br.com.objectos.code.java.io.CodeWriter;
import br.com.objectos.core.object.Checks;

public class IfStatement extends AbstractSimpleStatement {

  private final Expression condition;
  private final Statement elseStatement;
  private final Statement ifStatement;

  IfStatement(Expression condition, Statement ifStatement, Statement elseStatement) {
    this.condition = condition;
    this.ifStatement = ifStatement;
    this.elseStatement = elseStatement;
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1) {
    Checks.checkNotNull(e1, "e1 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement... elements) {
    Checks.checkNotNull(elements, "elements == null");
    Builder b = builder0(condition);
    for (int i = 0; i < elements.length; i++) {
      IfStatementElement e = elements[i];
      if (e == null) {
        throw new NullPointerException("elements[" + i + "] == null");
      }
      e.acceptIfStatementBuilder(b);
    }
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2,
      IfStatementElement e3) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    e3.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2,
      IfStatementElement e3,
      IfStatementElement e4) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    e3.acceptIfStatementBuilder(b);
    e4.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2,
      IfStatementElement e3,
      IfStatementElement e4,
      IfStatementElement e5) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    e3.acceptIfStatementBuilder(b);
    e4.acceptIfStatementBuilder(b);
    e5.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2,
      IfStatementElement e3,
      IfStatementElement e4,
      IfStatementElement e5,
      IfStatementElement e6) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    e3.acceptIfStatementBuilder(b);
    e4.acceptIfStatementBuilder(b);
    e5.acceptIfStatementBuilder(b);
    e6.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2,
      IfStatementElement e3,
      IfStatementElement e4,
      IfStatementElement e5,
      IfStatementElement e6,
      IfStatementElement e7) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    e3.acceptIfStatementBuilder(b);
    e4.acceptIfStatementBuilder(b);
    e5.acceptIfStatementBuilder(b);
    e6.acceptIfStatementBuilder(b);
    e7.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2,
      IfStatementElement e3,
      IfStatementElement e4,
      IfStatementElement e5,
      IfStatementElement e6,
      IfStatementElement e7,
      IfStatementElement e8) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Checks.checkNotNull(e8, "e8 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    e3.acceptIfStatementBuilder(b);
    e4.acceptIfStatementBuilder(b);
    e5.acceptIfStatementBuilder(b);
    e6.acceptIfStatementBuilder(b);
    e7.acceptIfStatementBuilder(b);
    e8.acceptIfStatementBuilder(b);
    return b.build();
  }

  public static IfStatement _if(
      Expression condition,
      IfStatementElement e1,
      IfStatementElement e2,
      IfStatementElement e3,
      IfStatementElement e4,
      IfStatementElement e5,
      IfStatementElement e6,
      IfStatementElement e7,
      IfStatementElement e8,
      IfStatementElement e9) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Checks.checkNotNull(e8, "e8 == null");
    Checks.checkNotNull(e9, "e9 == null");
    Builder b = builder0(condition);
    e1.acceptIfStatementBuilder(b);
    e2.acceptIfStatementBuilder(b);
    e3.acceptIfStatementBuilder(b);
    e4.acceptIfStatementBuilder(b);
    e5.acceptIfStatementBuilder(b);
    e6.acceptIfStatementBuilder(b);
    e7.acceptIfStatementBuilder(b);
    e8.acceptIfStatementBuilder(b);
    e9.acceptIfStatementBuilder(b);
    return b.build();
  }

  @Ignore
  public static Builder builder() {
    return new Builder();
  }

  private static Builder builder0(Expression condition) {
    return builder().condition(condition);
  }

  @Override
  public final CodeWriter acceptCodeWriter(CodeWriter w) {
    w.writeCodeElement(Keywords._if());
    w.writeCodeElement(space());
    w.writeCodeElement(parenthesized(condition));
    w.writeCodeElement(space());
    w.writeCodeElement(wrapWithBlockIfNecessary(ifStatement));

    if (elseStatement != null) {
      w.writeCodeElement(space());
      w.writeCodeElement(Keywords._else());
      w.writeCodeElement(space());
      w.writeCodeElement(wrapWithBlockIfNecessary(elseStatement));
    }

    return w;
  }

  @Override
  public final void acceptSemicolon(Semicolon semicolon) {
    // noop
  }

  public static class Builder {

    private Expression condition;

    private StatementOrBlockBuilder currentBlock;

    private StatementOrBlockBuilder elseBlock;

    private final StatementOrBlockBuilder ifBlock = new StatementOrBlockBuilder();

    private Builder() {
      currentBlock = ifBlock;
    }

    public final Builder addToCurrentBlock(BlockElement element) {
      Checks.checkNotNull(element, "element == null");
      element.acceptStatementOrBlockBuilder(currentBlock);
      return this;
    }

    public final IfStatement build() {
      if (condition == null) {
        throw new IllegalStateException("The condition expression was not set");
      }
      return new IfStatement(
          condition,
          ifBlock.build(),
          elseBlock != null ? elseBlock.build() : null
      );
    }

    public final Builder condition(Expression condition) {
      this.condition = Checks.checkNotNull(condition, "condition == null");
      return this;
    }

    public final Builder elseBlock() {
      initElseBlock();
      currentBlock = elseBlock;
      return this;
    }

    private void initElseBlock() {
      if (elseBlock == null) {
        elseBlock = new StatementOrBlockBuilder();
      }
    }

  }

}
