/*
 * Copyright (C) 2021-2023 Objectos Software LTDA.
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
package br.com.objectos.logger;

/**
 * Returns a string representation for a {@link Log} instance.
 *
 * @since 2
 */
public interface Layout {

  /**
   * Returns a formatted string representation of the specified log instance.
   *
   * @param log
   *        the log instance to format
   *
   * @return a formatted string representation of the specified log instance
   */
  String formatLog(Log log);

}