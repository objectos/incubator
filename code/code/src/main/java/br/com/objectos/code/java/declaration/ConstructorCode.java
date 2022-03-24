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
package br.com.objectos.code.java.declaration;

import static br.com.objectos.code.java.element.NewLine.nextLine;

import br.com.objectos.code.annotations.Ignore;
import br.com.objectos.code.java.element.AbstractCodeElement;
import br.com.objectos.code.java.io.CodeWriter;
import br.com.objectos.code.java.io.NewLineFormatting.NewLineFormattingAction;
import br.com.objectos.code.java.io.Section;
import br.com.objectos.code.java.statement.BlockElement;
import br.com.objectos.code.java.statement.BlockStatement;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;

public final class ConstructorCode
    extends AbstractCodeElement
    implements
    ClassBodyElement,
    EnumBodyElement {

  private static final ConstructorCode DEFAULT = builder().build();
  private static final ConstructorCode PRIVATE = ofModifier(Modifiers.PRIVATE);
  private static final ConstructorCode PROTECTED = ofModifier(Modifiers.PROTECTED);
  private static final ConstructorCode PUBLIC = ofModifier(Modifiers.PUBLIC);

  private final ImmutableList<BlockElement> body;

  private final ExplicitConstructorInvocation constructorInvocation;
  private final ConstructorModifier modifier;
  private final ImmutableList<ParameterCode> parameters;

  private ConstructorCode(ConstructorModifier modifier,
                          ImmutableList<ParameterCode> parameters,
                          ExplicitConstructorInvocation constructorInvocation,
                          ImmutableList<BlockElement> body) {
    this.modifier = modifier;
    this.parameters = parameters;
    this.constructorInvocation = constructorInvocation;
    this.body = body;
  }

  @Ignore("AggregatorGenProcessor")
  public static Builder builder() {
    return new Builder();
  }

  public static ConstructorCode constructor() {
    return DEFAULT;
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1) {
    Checks.checkNotNull(e1, "e1 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(ConstructorCodeElement... elements) {
    Checks.checkNotNull(elements, "elements == null");
    Builder b = builder();

    for (int i = 0; i < elements.length; i++) {
      ConstructorCodeElement element;
      element = elements[i];

      if (element == null) {
        throw new NullPointerException("elements[" + i + "] == null");
      }

      element.acceptConstructorCodeBuilder(b);
    }

    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2,
      ConstructorCodeElement e3) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    e3.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2,
      ConstructorCodeElement e3,
      ConstructorCodeElement e4) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    e3.acceptConstructorCodeBuilder(b);
    e4.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2,
      ConstructorCodeElement e3,
      ConstructorCodeElement e4,
      ConstructorCodeElement e5) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    e3.acceptConstructorCodeBuilder(b);
    e4.acceptConstructorCodeBuilder(b);
    e5.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2,
      ConstructorCodeElement e3,
      ConstructorCodeElement e4,
      ConstructorCodeElement e5,
      ConstructorCodeElement e6) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    e3.acceptConstructorCodeBuilder(b);
    e4.acceptConstructorCodeBuilder(b);
    e5.acceptConstructorCodeBuilder(b);
    e6.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2,
      ConstructorCodeElement e3,
      ConstructorCodeElement e4,
      ConstructorCodeElement e5,
      ConstructorCodeElement e6,
      ConstructorCodeElement e7) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    e3.acceptConstructorCodeBuilder(b);
    e4.acceptConstructorCodeBuilder(b);
    e5.acceptConstructorCodeBuilder(b);
    e6.acceptConstructorCodeBuilder(b);
    e7.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2,
      ConstructorCodeElement e3,
      ConstructorCodeElement e4,
      ConstructorCodeElement e5,
      ConstructorCodeElement e6,
      ConstructorCodeElement e7,
      ConstructorCodeElement e8) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Checks.checkNotNull(e8, "e8 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    e3.acceptConstructorCodeBuilder(b);
    e4.acceptConstructorCodeBuilder(b);
    e5.acceptConstructorCodeBuilder(b);
    e6.acceptConstructorCodeBuilder(b);
    e7.acceptConstructorCodeBuilder(b);
    e8.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode constructor(
      ConstructorCodeElement e1,
      ConstructorCodeElement e2,
      ConstructorCodeElement e3,
      ConstructorCodeElement e4,
      ConstructorCodeElement e5,
      ConstructorCodeElement e6,
      ConstructorCodeElement e7,
      ConstructorCodeElement e8,
      ConstructorCodeElement e9) {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    Checks.checkNotNull(e4, "e4 == null");
    Checks.checkNotNull(e5, "e5 == null");
    Checks.checkNotNull(e6, "e6 == null");
    Checks.checkNotNull(e7, "e7 == null");
    Checks.checkNotNull(e8, "e8 == null");
    Checks.checkNotNull(e9, "e9 == null");
    Builder b = builder();
    e1.acceptConstructorCodeBuilder(b);
    e2.acceptConstructorCodeBuilder(b);
    e3.acceptConstructorCodeBuilder(b);
    e4.acceptConstructorCodeBuilder(b);
    e5.acceptConstructorCodeBuilder(b);
    e6.acceptConstructorCodeBuilder(b);
    e7.acceptConstructorCodeBuilder(b);
    e8.acceptConstructorCodeBuilder(b);
    e9.acceptConstructorCodeBuilder(b);
    return b.build();
  }

  public static ConstructorCode privateConstructor() {
    return PRIVATE;
  }

  public static ConstructorCode protectedConstructor() {
    return PROTECTED;
  }

  public static ConstructorCode publicConstructor() {
    return PUBLIC;
  }

  private static ConstructorCode ofModifier(ConstructorModifier modifier) {
    return builder().addModifier(modifier).build();
  }

  @Override
  public final void acceptClassCodeBuilder(ClassCode.Builder builder) {
    builder.addConstructor(this);
  }

  @Override
  public final CodeWriter acceptCodeWriter(CodeWriter w) {
    w.writeCodeElement(modifier);
    w.writeCodeElement(space());
    w.writeCodeElement(simpleNameElement());
    w.writeCodeElement(parenthesized(commaSeparated(parameters)));
    w.writeCodeElement(space());
    writeBody(w);

    return w;
  }

  @Override
  public final void acceptEnumCodeBuilder(EnumCode.Builder builder) {
    builder.addConstructor(this);
  }

  @Override
  public final void acceptNewLineFormattingAction(NewLineFormattingAction action) {
    action.propagateElement(this);
  }

  @Override
  public final Kind kind() {
    return Kind.CONSTRUCTOR;
  }

  @Override
  public final String toString() {
    return acceptCodeWriter(
        CodeWriter.forToString().pushSimpleName("Constructor")
    ).popSimpleName().toString();
  }

  private void writeBody(CodeWriter w) {
    w.writeCodeElement(openBrace());
    w.beginSection(Section.BLOCK);

    int count = w.charCount();

    constructorInvocation.acceptConstructorCodeWriter(w);

    for (BlockElement element : body) {
      w.writeCodeElement(nextLine());
      w.beginSection(Section.STATEMENT);
      w.writeCodeElement(element);
      w.endSection();
      writeSemicolonIfNecessary(w, element);
    }

    if (count != w.charCount()) {
      w.writeCodeElement(nextLine());
    }

    w.endSection();
    w.writeCodeElement(closeBrace());
  }

  public static class Builder {

    private ConstructorModifier accessModifier = Nothing.INSTANCE;
    private final MutableList<BlockElement> body = MutableList.create();
    private ExplicitConstructorInvocation constructorInvocation = Nothing.INSTANCE;
    private final MutableList<ParameterCode> parameters = MutableList.create();

    private Builder() {}

    public final Builder addModifier(ConstructorModifier modifier) {
      accessModifier = Checks.checkNotNull(modifier, "modifier == null");
      return this;
    }

    public final Builder addParameter(ParameterCode parameter) {
      Checks.checkNotNull(parameter, "parameter == null");
      return withParameterUnchecked(parameter);
    }

    public final Builder addParameters(Iterable<ParameterCode> parameters) {
      Checks.checkNotNull(parameters, "parameters == null");
      for (ParameterCode parameter : parameters) {
        addParameter(parameter);
      }
      return this;
    }

    public final Builder addStatement(BlockStatement statement) {
      body.addWithNullMessage(statement, "statement == null");
      return this;
    }

    public final Builder addStatements(Iterable<? extends BlockStatement> statements) {
      body.addAllIterable(statements);
      return this;
    }

    public final ConstructorCode build() {
      return new ConstructorCode(
          accessModifier,
          parameters.toImmutableList(),
          constructorInvocation,
          body.toImmutableList()
      );
    }

    public final Builder constructorInvocation(ExplicitConstructorInvocation invocation) {
      constructorInvocation = Checks.checkNotNull(invocation, "invocation == null");
      return this;
    }

    private Builder withParameterUnchecked(ParameterCode parameter) {
      parameters.add(parameter);
      return this;
    }

  }

  private enum Nothing
      implements
      ConstructorModifier,
      ExplicitConstructorInvocation {

    INSTANCE;

    @Override
    public final CodeWriter acceptCodeWriter(CodeWriter w) {
      // noop
      return w;
    }

    @Override
    public final void acceptConstructorCodeBuilder(Builder builder) {
      throw new AssertionError();
    }

    @Override
    public final void acceptConstructorCodeWriter(CodeWriter w) {
      // noop
    }

  }

}