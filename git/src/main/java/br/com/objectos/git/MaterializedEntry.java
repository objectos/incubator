/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.git;

/**
 * A Git tree entry resulting from a <em>materialize entry</em> operation.
 *
 * @since 3
 */
public interface MaterializedEntry {

  /**
   * Returns {@code true} if this entry denotes a tree object; {@code false}
   * otherwise.
   *
   * @return {@code true} if this entry denotes a tree object; {@code false}
   *         otherwise
   */
  boolean isTree();

}