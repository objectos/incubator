/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package objectos.asciidoc;

final class Code {

  static final int DOCUMENT_START = -1;
  static final int DOCUMENT_END = -2;

  static final int HEADING_START = -3;
  static final int HEADING_END = -4;

  static final int PREAMBLE_START = -5;
  static final int PREAMBLE_END = -6;

  static final int PARAGRAPH_START = -7;
  static final int PARAGRAPH_END = -8;

  static final int TOKENS = -9;

  static final int SECTION_START = -10;
  static final int SECTION_END = -11;

  static final int ATTR_POSITIONAL = -12;

  static final int LISTING_BLOCK_START = -13;
  static final int LISTING_BLOCK_END = -14;

  static final int VERBATIM = -15;

  private Code() {}

}