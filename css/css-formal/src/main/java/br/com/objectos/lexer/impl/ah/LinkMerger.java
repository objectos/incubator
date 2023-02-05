/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

interface LinkMerger {

  default Link mergeBrickStartLink(BrickStartLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeBrickValueOneLink(BrickValueOneLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharArrayLink(CharArrayLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharExpressionOneLink(CharExpressionOneLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharExpressionZeroOrMoreLink(CharExpressionZeroOrMoreLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharSingleConditionLink(CharSingleConditionLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharSingleOneLink(CharSingleOneLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharSingleOneOrMoreLink(CharSingleOneOrMoreLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharSingleZeroOrMoreLink(CharSingleZeroOrMoreLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeCharSingleOptionalLink(CharSingleOptionalLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeStringOneLink(StringOneLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeStringValueLink(StringValueLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  default Link mergeTrailingLink(TrailingLink that) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

}