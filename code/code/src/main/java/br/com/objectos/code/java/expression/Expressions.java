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
package br.com.objectos.code.java.expression;

import static br.com.objectos.code.java.expression.Arguments.args;

import br.com.objectos.code.java.declaration.ParameterCode;
import br.com.objectos.code.java.expression.MethodInvocation.Unqualified;
import br.com.objectos.code.java.statement.VariableInitializer;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedPrimitive;
import br.com.objectos.code.java.type.NamedReferenceType;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;

public class Expressions {

  private Expressions() {}

  // AdditiveExpression

  public static Literal _false() {
    return LiteralImpl.FALSE;
  }

  public static ArrayCreationExpression _new(
      NamedArray type, ArrayInitializer initializer) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(initializer, "initializer == null");
    return ArrayCreationExpressionImpl._new0(type, initializer);
  }

  // AndExpression

  public static ArrayCreationExpression _new(
      NamedArray type, Expression dim0) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(dim0, "dim0 == null");
    return ArrayCreationExpressionImpl._new0(type, dim0);
  }

  // ArrayAccess

  public static ArrayCreationExpression _new(
      NamedArray type, Expression dim0, Expression dim1) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(dim0, "dim0 == null");
    Checks.checkNotNull(dim1, "dim1 == null");
    return ArrayCreationExpressionImpl._new0(type, dim0, dim1);
  }

  public static ArrayCreationExpression _new(
      NamedArray type, Expression dim0, Expression dim1, Expression dim2) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(dim0, "dim0 == null");
    Checks.checkNotNull(dim1, "dim1 == null");
    Checks.checkNotNull(dim2, "dim2 == null");
    return ArrayCreationExpressionImpl._new0(type, dim0, dim1, dim2);
  }

  public static ArrayCreationExpression _new(
      NamedArray type,
      Expression dim0, Expression dim1, Expression dim2, Expression dim3) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(dim0, "dim0 == null");
    Checks.checkNotNull(dim1, "dim1 == null");
    Checks.checkNotNull(dim2, "dim2 == null");
    Checks.checkNotNull(dim3, "dim3 == null");
    return ArrayCreationExpressionImpl._new0(type, dim0, dim1, dim2, dim3);
  }

  public static ArrayCreationExpression _new(
      NamedArray type, Iterable<? extends Expression> dims) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(dims, "dims == null");
    return ArrayCreationExpressionImpl._new0(type, dims);
  }

  public static Literal _null() {
    return LiteralImpl.NULL;
  }

  public static Literal _true() {
    return LiteralImpl.TRUE;
  }

  public static ArrayInitializer a() {
    return ArrayInitializerImpl.EMPTY;
  }

  public static ArrayInitializer a(Iterable<? extends VariableInitializer> elements) {
    Checks.checkNotNull(elements, "elements == null");

    MutableList<VariableInitializer> list = MutableList.create();

    list.addAllIterable(elements);

    return ArrayInitializerImpl.a0(list.toImmutableList());
  }

  public static ArrayInitializer a(
      VariableInitializer v0) {
    Checks.checkNotNull(v0, "v0 == null");
    return ArrayInitializerImpl.a0(v0);
  }

  public static ArrayInitializer a(VariableInitializer... elements) {
    Checks.checkNotNull(elements, "elements == null");

    VariableInitializer[] copy = new VariableInitializer[elements.length];

    for (int i = 0; i < elements.length; i++) {
      VariableInitializer el = elements[i];

      if (el == null) {
        throw new NullPointerException("elements[" + i + "] == null");
      }

      copy[i] = el;
    }

    return ArrayInitializerImpl.a0(copy);
  }

  // ArrayCreationExpression

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    return ArrayInitializerImpl.a0(v0, v1);
  }

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2);
  }

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2,
      VariableInitializer v3) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2, v3);
  }

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2,
      VariableInitializer v3,
      VariableInitializer v4) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2, v3, v4);
  }

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2,
      VariableInitializer v3,
      VariableInitializer v4,
      VariableInitializer v5) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    Checks.checkNotNull(v5, "v5 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2, v3, v4, v5);
  }

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2,
      VariableInitializer v3,
      VariableInitializer v4,
      VariableInitializer v5,
      VariableInitializer v6) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    Checks.checkNotNull(v5, "v5 == null");
    Checks.checkNotNull(v6, "v6 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2, v3, v4, v5, v6);
  }

  // ArrayInitializer

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2,
      VariableInitializer v3,
      VariableInitializer v4,
      VariableInitializer v5,
      VariableInitializer v6,
      VariableInitializer v7) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    Checks.checkNotNull(v5, "v5 == null");
    Checks.checkNotNull(v6, "v6 == null");
    Checks.checkNotNull(v7, "v7 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2, v3, v4, v5, v6, v7);
  }

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2,
      VariableInitializer v3,
      VariableInitializer v4,
      VariableInitializer v5,
      VariableInitializer v6,
      VariableInitializer v7,
      VariableInitializer v8) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    Checks.checkNotNull(v5, "v5 == null");
    Checks.checkNotNull(v6, "v6 == null");
    Checks.checkNotNull(v7, "v7 == null");
    Checks.checkNotNull(v8, "v8 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2, v3, v4, v5, v6, v7, v8);
  }

  public static ArrayInitializer a(
      VariableInitializer v0,
      VariableInitializer v1,
      VariableInitializer v2,
      VariableInitializer v3,
      VariableInitializer v4,
      VariableInitializer v5,
      VariableInitializer v6,
      VariableInitializer v7,
      VariableInitializer v8,
      VariableInitializer v9) {
    Checks.checkNotNull(v0, "v0 == null");
    Checks.checkNotNull(v1, "v1 == null");
    Checks.checkNotNull(v2, "v2 == null");
    Checks.checkNotNull(v3, "v3 == null");
    Checks.checkNotNull(v4, "v4 == null");
    Checks.checkNotNull(v5, "v5 == null");
    Checks.checkNotNull(v6, "v6 == null");
    Checks.checkNotNull(v7, "v7 == null");
    Checks.checkNotNull(v8, "v8 == null");
    Checks.checkNotNull(v9, "v9 == null");
    return ArrayInitializerImpl.a0(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9);
  }

  public static AdditiveExpression add(AdditiveExpression lhs, MultiplicativeExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return AdditiveExpressionImpl.of0(AdditiveOperator.ADDITION, lhs, rhs);
  }

  public static ArrayAccess aget(ArrayReferenceExpression ref, Expression e0) {
    return arrayAccess(ref, e0);
  }

  public static ArrayAccess aget(ArrayReferenceExpression ref,
      Expression e0, Expression e1) {
    return arrayAccess(ref, e0, e1);
  }

  public static ArrayAccess aget(ArrayReferenceExpression ref,
      Expression e0, Expression e1, Expression e2) {
    return arrayAccess(ref, e0, e1, e2);
  }

  public static ArrayAccess aget(ArrayReferenceExpression ref,
      Expression e0, Expression e1, Expression e2, Expression e3) {
    return arrayAccess(ref, e0, e1, e2, e3);
  }

  public static ArrayAccess aget(
      ArrayReferenceExpression ref, Iterable<? extends Expression> expressions) {
    return arrayAccess(ref, expressions);
  }

  public static ConditionalAndExpression and(
      ConditionalAndExpression lhs, InclusiveOrExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return ConditionalAndExpressionImpl.and0(lhs, rhs);
  }

  public static ArrayAccess arrayAccess(ArrayReferenceExpression ref, Expression e0) {
    Checks.checkNotNull(ref, "ref == null");
    Checks.checkNotNull(e0, "e0 == null");
    return ArrayAccessImpl.arrayAccess0(ref, e0);
  }

  public static ArrayAccess arrayAccess(
      ArrayReferenceExpression ref,
      Expression e0, Expression e1) {
    Checks.checkNotNull(ref, "ref == null");
    Checks.checkNotNull(e0, "e0 == null");
    Checks.checkNotNull(e1, "e1 == null");
    return ArrayAccessImpl.arrayAccess0(ref, e0, e1);
  }

  public static ArrayAccess arrayAccess(
      ArrayReferenceExpression ref,
      Expression e0, Expression e1, Expression e2) {
    Checks.checkNotNull(ref, "ref == null");
    Checks.checkNotNull(e0, "e0 == null");
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    return ArrayAccessImpl.arrayAccess0(ref, e0, e1, e2);
  }

  // Assignment

  public static ArrayAccess arrayAccess(
      ArrayReferenceExpression ref,
      Expression e0, Expression e1, Expression e2, Expression e3) {
    Checks.checkNotNull(ref, "ref == null");
    Checks.checkNotNull(e0, "e0 == null");
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");
    return ArrayAccessImpl.arrayAccess0(ref, e0, e1, e2, e3);
  }

  public static ArrayAccess arrayAccess(
      ArrayReferenceExpression ref,
      Iterable<? extends Expression> expressions) {
    Checks.checkNotNull(ref, "ref == null");
    Checks.checkNotNull(expressions, "expressions == null");
    return ArrayAccessImpl.arrayAccess0(ref, expressions);
  }

  // CastExpression

  public static Assignment assign(
      AssignmentOperator operator, LeftHandSide lhs, Expression rhs) {
    Checks.checkNotNull(operator, "operator == null");
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return AssignmentImpl.assign0(operator, lhs, rhs);
  }

  public static Assignment assign(LeftHandSide lhs, Expression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return AssignmentImpl.assign0(AssignmentOperator.SIMPLE, lhs, rhs);
  }

  // ConditionalAndExpression

  public static AndExpression bitwiseAnd(AndExpression lhs, EqualityExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return AndExpressionImpl.bitwiseAnd0(lhs, rhs);
  }

  // ConditionalExpression

  public static InclusiveOrExpression bitwiseOr(
      InclusiveOrExpression lhs, ExclusiveOrExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return InclusiveOrExpressionImpl.bitwiseOr0(lhs, rhs);
  }

  public static ExclusiveOrExpression bitwiseXor(ExclusiveOrExpression lhs, AndExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return ExclusiveOrExpressionImpl.bitwiseXor0(lhs, rhs);
  }

  // ConditionalOrExpression

  public static CastExpression cast(
      NamedPrimitive type, UnaryExpression expression) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(expression, "expression == null");
    return CastExpressionImpl.cast0(type, expression);
  }

  // ExclusiveOrExpression

  public static CastExpression cast(
      NamedReferenceType type, UnaryExpressionNotPlusMinus expression) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(expression, "expression == null");
    return CastExpressionImpl.cast0(type, expression);
  }

  // EqualityExpression

  public static MultiplicativeExpression divide(
      MultiplicativeExpression lhs, UnaryExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return MultiplicativeExpressionImpl.of0(MultiplicativeOperator.DIVISION, lhs, rhs);
  }

  public static EmptyExpression empty() {
    return EmptyExpression.empty();
  }

  // EmptyExpression

  public static EqualityExpression eq(EqualityExpression lhs, RelationalExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return EqualityExpressionImpl.eq0(lhs, rhs);
  }

  // ExpresssionName

  public static ExpressionName expressionName(Class<?> type, String id) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(id, "id == null");
    return ExpressionNameImpl.expressionName0(NamedClass.of(type), id(id));
  }

  public static ExpressionName expressionName(ExpressionName name, Identifier id) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(id, "id == null");
    return ExpressionNameImpl.expressionName0(name, id);
  }

  public static ExpressionName expressionName(ExpressionName name, String id) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(id, "id == null");
    return ExpressionNameImpl.expressionName0(name, id(id));
  }

  public static ExpressionName expressionName(NamedClass className, Identifier id) {
    Checks.checkNotNull(className, "className == null");
    Checks.checkNotNull(id, "id == null");
    return ExpressionNameImpl.expressionName0(className, id);
  }

  public static ExpressionName expressionName(NamedClass className, String id) {
    Checks.checkNotNull(className, "className == null");
    Checks.checkNotNull(id, "id == null");
    return ExpressionNameImpl.expressionName0(className, id(id));
  }

  // FieldAccess

  public static FieldAccess fieldAccess(FieldAccessReferenceExpression ref, Identifier id) {
    Checks.checkNotNull(ref, "ref == null");
    Checks.checkNotNull(id, "id == null");
    return FieldAccessImpl.fieldAccess0(ref, id);
  }

  public static FieldAccess fieldAccess(FieldAccessReferenceExpression ref, String id) {
    Checks.checkNotNull(ref, "ref == null");
    Checks.checkNotNull(id, "id == null");
    return FieldAccessImpl.fieldAccess0(ref, id(id));
  }

  // Identifier

  public static RelationalExpression ge(
      RelationalExpression lhs, ShiftExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return RelationalExpressionImpl.of0(RelationalOperator.GE, lhs, rhs);
  }

  public static RelationalExpression gt(
      RelationalExpression lhs, ShiftExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return RelationalExpressionImpl.of0(RelationalOperator.GT, lhs, rhs);
  }

  // InclusiveOrExpression

  public static TypeWitness hint() {
    return witness();
  }

  // LambdaExpression

  public static TypeWitness hint(Iterable<? extends NamedType> types) {
    return witness(types);
  }

  public static TypeWitness hint(NamedType t1) {
    return witness(t1);
  }

  public static TypeWitness hint(NamedType t1, NamedType t2) {
    return witness(t1, t2);
  }

  public static TypeWitness hint(NamedType t1, NamedType t2, NamedType t3) {
    return witness(t1, t2, t3);
  }

  public static Identifier id(String name) {
    Checks.checkNotNull(name, "name == null");
    return IdentifierImpl.id0(name);
  }

  public static IdentifierBuilder idBuilder() {
    return new IdentifierBuilder();
  }

  public static RelationalExpression instanceOf(
      RelationalExpression subject, NamedReferenceType test) {
    Checks.checkNotNull(subject, "subject == null");
    Checks.checkNotNull(test, "test == null");
    return RelationalExpressionImpl.instanceOf0(subject, test);
  }

  // Literal

  public static MethodInvocation invoke(
      Callee callee, String methodName) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args());
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName, ArgumentsElement... args) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(args));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1, a2));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1, a2, a3));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1, a2, a3, a4));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1, a2, a3, a4, a5));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1, a2, a3, a4, a5, a6));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6,
      ArgumentsElement a7) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1, a2, a3, a4, a5, a6, a7));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6,
      ArgumentsElement a7,
      ArgumentsElement a8) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(a1, a2, a3, a4, a5, a6, a7, a8));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6,
      ArgumentsElement a7,
      ArgumentsElement a8,
      ArgumentsElement a9) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName,
        args(a1, a2, a3, a4, a5, a6, a7, a8, a9));
  }

  public static MethodInvocation invoke(
      Callee callee, String methodName, Iterable<? extends ArgumentsElement> args) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, methodName, args(args));
  }

  // MethodReference

  public static MethodInvocation invoke(
      Callee callee, TypeWitness witness, String methodName) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, witness, methodName, args());
  }

  public static MethodInvocation invoke(
      Callee callee, TypeWitness witness, String methodName,
      ArgumentsElement a1) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, witness, methodName, args(a1));
  }

  public static MethodInvocation invoke(
      Callee callee, TypeWitness witness, String methodName,
      ArgumentsElement a1, ArgumentsElement a2) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, witness, methodName, args(a1, a2));
  }

  public static MethodInvocation invoke(
      Callee callee, TypeWitness witness, String methodName,
      ArgumentsElement a1, ArgumentsElement a2, ArgumentsElement a3) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, witness, methodName, args(a1, a2, a3));
  }

  // MethodInvocation

  public static MethodInvocation invoke(
      Callee callee, TypeWitness witness, String methodName,
      ArgumentsElement a1, ArgumentsElement a2, ArgumentsElement a3, ArgumentsElement a4) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, witness, methodName, args(a1, a2, a3, a4));
  }

  public static MethodInvocation invoke(
      Callee callee, TypeWitness witness, String methodName,
      Iterable<? extends ArgumentsElement> args) {
    Checks.checkNotNull(callee, "callee == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(callee, witness, methodName, args(args));
  }

  public static Unqualified invoke(
      String methodName) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args());
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1, ArgumentsElement a2) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1, ArgumentsElement a2, ArgumentsElement a3) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2, a3));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2, a3, a4));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2, a3, a4, a5));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2, a3, a4, a5, a6));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6,
      ArgumentsElement a7) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2, a3, a4, a5, a6, a7));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6,
      ArgumentsElement a7,
      ArgumentsElement a8) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2, a3, a4, a5, a6, a7, a8));
  }

  public static Unqualified invoke(
      String methodName,
      ArgumentsElement a1,
      ArgumentsElement a2,
      ArgumentsElement a3,
      ArgumentsElement a4,
      ArgumentsElement a5,
      ArgumentsElement a6,
      ArgumentsElement a7,
      ArgumentsElement a8,
      ArgumentsElement a9) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(a1, a2, a3, a4, a5, a6, a7, a8, a9));
  }

  public static Unqualified invoke(
      String methodName, Iterable<? extends ArgumentsElement> args) {
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodInvocation.invoke0(methodName, args(args));
  }

  public static Literal l(boolean value) {
    return LiteralImpl.l0(value);
  }

  public static Literal l(char value) {
    return LiteralImpl.l0(value);
  }

  public static Literal l(Class<?> type) {
    Checks.checkNotNull(type, "type == null");
    return LiteralImpl.l0(type);
  }

  public static Literal l(double value) {
    return LiteralImpl.l0(value);
  }

  public static Literal l(float value) {
    return LiteralImpl.l0(value);
  }

  public static Literal l(int value) {
    return LiteralImpl.l0(value);
  }

  public static Literal l(long value) {
    return LiteralImpl.l0(value);
  }

  public static Literal l(NamedClass className) {
    Checks.checkNotNull(className, "className == null");
    return LiteralImpl.l0(className);
  }

  public static Literal l(String s) {
    Checks.checkNotNull(s, "s == null");
    return LiteralImpl.l0(s);
  }

  public static LambdaExpression lambda(
      Identifier p1,
      LambdaBody body) {
    Checks.checkNotNull(p1, "p1 == null");
    Checks.checkNotNull(body, "body == null");
    return LambdaExpressionImpl.lambda0(body, p1);
  }

  public static LambdaExpression lambda(
      Iterable<? extends LambdaParameter> params,
      LambdaBody body) {
    Checks.checkNotNull(params, "params == null");
    Checks.checkNotNull(body, "body == null");
    return LambdaExpressionImpl.lambda0(body, params);
  }

  public static LambdaExpression lambda(
      LambdaBody body) {
    Checks.checkNotNull(body, "body == null");
    return LambdaExpressionImpl.lambda0(body);
  }

  public static LambdaExpression lambda(
      LambdaParameter p1, LambdaParameter p2,
      LambdaBody body) {
    Checks.checkNotNull(p1, "p1 == null");
    Checks.checkNotNull(p2, "p2 == null");
    Checks.checkNotNull(body, "body == null");
    return LambdaExpressionImpl.lambda0(body, p1, p2);
  }

  public static LambdaExpression lambda(
      LambdaParameter p1, LambdaParameter p2, LambdaParameter p3,
      LambdaBody body) {
    Checks.checkNotNull(p1, "p1 == null");
    Checks.checkNotNull(p2, "p2 == null");
    Checks.checkNotNull(p3, "p3 == null");
    Checks.checkNotNull(body, "body == null");
    return LambdaExpressionImpl.lambda0(body, p1, p2, p3);
  }

  public static LambdaExpression lambda(
      LambdaParameter p1, LambdaParameter p2, LambdaParameter p3, LambdaParameter p4,
      LambdaBody body) {
    Checks.checkNotNull(p1, "p1 == null");
    Checks.checkNotNull(p2, "p2 == null");
    Checks.checkNotNull(p3, "p3 == null");
    Checks.checkNotNull(p4, "p4 == null");
    Checks.checkNotNull(body, "body == null");
    return LambdaExpressionImpl.lambda0(body, p1, p2, p3, p4);
  }

  public static LambdaExpression lambda(
      ParameterCode p1,
      LambdaBody body) {
    Checks.checkNotNull(p1, "p1 == null");
    Checks.checkNotNull(body, "body == null");
    return LambdaExpressionImpl.lambda0(body, p1);
  }

  // MultiplicativeExpression

  public static RelationalExpression le(
      RelationalExpression lhs, ShiftExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return RelationalExpressionImpl.of0(RelationalOperator.LE, lhs, rhs);
  }

  public static ShiftExpression leftShift(ShiftExpression lhs, AdditiveExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return ShiftExpressionImpl.leftShift0(lhs, rhs);
  }

  public static RelationalExpression lt(
      RelationalExpression lhs, ShiftExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return RelationalExpressionImpl.of0(RelationalOperator.LT, lhs, rhs);
  }

  // ParenthesizedExpression

  public static MultiplicativeExpression multiply(
      MultiplicativeExpression lhs, UnaryExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return MultiplicativeExpressionImpl.of0(MultiplicativeOperator.MULTIPLICATION, lhs, rhs);
  }

  // PostDecrementExpression

  public static EqualityExpression ne(EqualityExpression lhs, RelationalExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return EqualityExpressionImpl.ne0(lhs, rhs);
  }

  // PostIncrementExpression

  public static UnaryExpressionNotPlusMinus not(UnaryExpression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return UnaryExpressionImpl.NotPlusOrMinus.of0(UnaryOperator.NOT, expression);
  }

  // PreDecrementExpression

  public static ConditionalOrExpression or(
      ConditionalOrExpression lhs, ConditionalAndExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return ConditionalOrExpressionImpl.or0(lhs, rhs);
  }

  // PreIncrementExpression

  public static ParenthesizedExpression parens(Expression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return ParenthesizedExpressionImpl.parens0(expression);
  }

  // RelationalExpression

  public static PostDecrementExpression postDec(PostfixExpression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return PostDecrementExpressionImpl.postDec0(expression);
  }

  public static PostIncrementExpression postInc(PostfixExpression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return PostIncrementExpressionImpl.postInc0(expression);
  }

  public static PreDecrementExpression preDec(UnaryExpression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return PreDecrementExpressionImpl.preDec0(expression);
  }

  public static PreIncrementExpression preInc(UnaryExpression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return PreIncrementExpressionImpl.preInc0(expression);
  }

  public static MethodReference ref(
      MethodReferenceReferenceExpression expression, String methodName) {
    Checks.checkNotNull(expression, "expression == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodReferenceImpl.ref0(expression, methodName);
  }

  // ShiftExpression

  public static MethodReference ref(
      MethodReferenceReferenceExpression expression, TypeWitness witness, String methodName) {
    Checks.checkNotNull(expression, "expression == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodReferenceImpl.ref0(expression, witness, methodName);
  }

  public static MethodReference ref(
      NamedReferenceType typeName, String methodName) {
    Checks.checkNotNull(typeName, "typeName == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodReferenceImpl.ref0(typeName, methodName);
  }

  public static MethodReference ref(
      NamedReferenceType typeName, TypeWitness witness, String methodName) {
    Checks.checkNotNull(typeName, "typeName == null");
    Checks.checkNotNull(witness, "witness == null");
    Checks.checkNotNull(methodName, "methodName == null");
    return MethodReferenceImpl.ref0(typeName, witness, methodName);
  }

  // TypeWitness

  public static MultiplicativeExpression remainder(
      MultiplicativeExpression lhs, UnaryExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return MultiplicativeExpressionImpl.of0(MultiplicativeOperator.REMAINDER, lhs, rhs);
  }

  public static ShiftExpression rightShift(ShiftExpression lhs, AdditiveExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return ShiftExpressionImpl.rightShift0(lhs, rhs);
  }

  public static AdditiveExpression subtract(AdditiveExpression lhs, MultiplicativeExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return AdditiveExpressionImpl.of0(AdditiveOperator.SUBTRACTION, lhs, rhs);
  }

  public static ConditionalExpression ternary(
      ConditionalOrExpression condition,
      Expression trueExpression, ConditionalExpression falseExpression) {
    Checks.checkNotNull(condition, "condition == null");
    Checks.checkNotNull(trueExpression, "trueExpression == null");
    Checks.checkNotNull(falseExpression, "falseExpression == null");
    return ConditionalExpressionImpl.ternary0(condition, trueExpression, falseExpression);
  }

  public static ConditionalExpression ternary(
      ConditionalOrExpression condition,
      Expression trueExpression, LambdaExpression falseExpression) {
    Checks.checkNotNull(condition, "condition == null");
    Checks.checkNotNull(trueExpression, "trueExpression == null");
    Checks.checkNotNull(falseExpression, "falseExpression == null");
    return ConditionalExpressionImpl.ternary0(condition, trueExpression, falseExpression);
  }

  public static UnaryExpression unaryMinus(UnaryExpression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return UnaryExpressionImpl.Standard.of0(UnaryOperator.MINUS, expression);
  }

  public static UnaryExpression unaryPlus(UnaryExpression expression) {
    Checks.checkNotNull(expression, "expression == null");
    return UnaryExpressionImpl.Standard.of0(UnaryOperator.PLUS, expression);
  }

  public static ShiftExpression unsignedRightShift(ShiftExpression lhs, AdditiveExpression rhs) {
    Checks.checkNotNull(lhs, "lhs == null");
    Checks.checkNotNull(rhs, "rhs == null");
    return ShiftExpressionImpl.unsignedRightShift0(lhs, rhs);
  }

  public static TypeWitness witness() {
    return TypeWitness.witness0();
  }

  public static TypeWitness witness(Iterable<? extends NamedType> types) {
    Checks.checkNotNull(types, "types == null");
    return TypeWitness.witness0(types);
  }

  // UnaryExpression

  public static TypeWitness witness(NamedType t1) {
    Checks.checkNotNull(t1, "t1 == null");
    return TypeWitness.witness0(t1);
  }

  public static TypeWitness witness(NamedType t1, NamedType t2) {
    Checks.checkNotNull(t1, "t1 == null");
    Checks.checkNotNull(t2, "t2 == null");
    return TypeWitness.witness0(t1, t2);
  }

  // UnaryExpressionNotPlusMinus

  public static TypeWitness witness(NamedType t1, NamedType t2, NamedType t3) {
    Checks.checkNotNull(t1, "t1 == null");
    Checks.checkNotNull(t2, "t2 == null");
    Checks.checkNotNull(t3, "t3 == null");
    return TypeWitness.witness0(t1, t2, t3);
  }

}
