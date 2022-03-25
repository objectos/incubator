/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer.charexp;

import java.util.Objects;

public abstract class CharExpression {

  private static final CharExpression ANY = new PredicateCharExpression(
      "c -> true", c -> true);

  private static final CharExpression IS_DIGIT = CharExpression.range('0', '9');

  private static final CharExpression IS_ISO_CONTROL = new PredicateCharExpression(
      "Character::isISOControl", Character::isISOControl);

  private static final CharExpression IS_LETTER = new PredicateCharExpression(
      "Character::isLetter", Character::isLetter);

  private static final CharExpression IS_LETTER_OR_DIGIT = CharExpression.or(
      IS_LETTER,
      IS_DIGIT);

  private static final CharExpression IS_WHITESPACE = new PredicateCharExpression(
      "Character::isWhitespace", Character::isWhitespace);

  public final String description;

  CharExpression(String description) {
    this.description = description;
  }

  public static CharExpression and(CharExpression first, CharExpression second, CharExpression... more) {
    CharExpression[] values = values(first, second, more);
    return new AndCharExpression(values);
  }

  public static CharExpression any() {
    return ANY;
  }

  public static CharExpression is(char... values) {
    Objects.requireNonNull(values);

    switch (values.length) {
    case 0:
      throw new IllegalArgumentException("At least one value is required.");
    case 1:
      return new CharCharExpression(values[0]);
    default:
      return new CharArrayCharExpression(values);
    }
  }

  public static CharExpression isDigit() {
    return IS_DIGIT;
  }

  public static CharExpression isISOControl() {
    return IS_ISO_CONTROL;
  }

  public static CharExpression isLetter() {
    return IS_LETTER;
  }

  public static CharExpression isLetterOrDigit() {
    return IS_LETTER_OR_DIGIT;
  }

  public static CharExpression isWhitespace() {
    return IS_WHITESPACE;
  }

  public static CharExpression not(char c) {
    return new NotCharExpression(new CharCharExpression(c));
  }

  public static CharExpression not(char... chars) {
    return new NotCharExpression(new CharArrayCharExpression(chars));
  }

  public static CharExpression not(CharExpression expression) {
    Objects.requireNonNull(expression);
    return new NotCharExpression(expression);
  }

  public static CharExpression or(CharExpression first, CharExpression second, CharExpression... more) {
    CharExpression[] values = values(first, second, more);
    return new OrCharExpression(values);
  }

  public static CharExpression range(char min, char max) {
    return new RangeCharExpression(min, max);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!obj.getClass().equals(getClass())) {
      return false;
    }
    CharExpression that = (CharExpression) obj;
    return description.equals(that.description);
  }

  @Override
  public final int hashCode() {
    return description.hashCode();
  }

  @Override
  public final String toString() {
    return new StringBuilder("CharPredicate(")
        .append(description)
        .append(')')
        .toString();
  }

  public abstract boolean matches(char next);

  public final int matchingLength(String string) {
    int length = string.length();
    for (int i = 0; i < length; i++) {
      char next = string.charAt(i);
      if (!matches(next)) {
        return i;
      }
    }
    return length;
  }

  public abstract char[] toCharArray();

  private static CharExpression[] values(CharExpression first, CharExpression second, CharExpression... more) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);
    Objects.requireNonNull(more);
    CharExpression[] values = new CharExpression[more.length + 2];
    int i = 0;
    values[i++] = first;
    values[i++] = second;
    for (CharExpression charExpression : more) {
      values[i++] = Objects.requireNonNull(charExpression);
    }
    return values;
  }

}