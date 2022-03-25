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
package br.com.objectos.logger;

/**
 * A listener for storage log events; can intercept logs after they have been
 * written to the storage or after they have been read from the storage.
 *
 * @since 2
 */
public interface LogListener {

  /**
   * Called on a logged event.
   *
   * @param log
   *        the logged event
   */
  void onLog(Log log);

}
