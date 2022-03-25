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

final class TestingMoreLogging {

  /*
  
  @startmindmap
  
  *_ Test cases
  
  **:**Test case 03**
  ----
  BootstrapLogger use-case.
  ----
  # create BootstrapLogger
  # log trace 0
  # log warn 1
  # bootstrap
  # log trace 1
  # log trace 2
  # log trace 3
  # log warn 0
  # log warn 2
  # log warn 3
  # verify result contents;
  *** TRACE
  *** WARN
  *** BootstrapLogger
  
  **:**Test case 02**
  ----
  LogThrowable use-case.
  ----
  # log ex1: no cause, no suppressed
  # log ex2: with ex1 as cause
  # log ex3: with ex2 as suppressed
  # verify result contents;
  *** ERROR
  *** ReadJob
  *** StorageLogger
  *** StorageWatcher
  *** WriteJob

  **:**Test case 01**
  ----
  Log use-case.
  ----
  # log debug 0
  # log debug 1
  # log debug 2
  # log debug 3
  # log info 0
  # log info 1
  # log info 2
  # log info 3
  # verify result contents;
  *** DEBUG
  *** INFO
  *** ReadJob
  *** StorageLogger
  *** StorageWatcher
  *** WriteJob
  
  @endmindmap
  
   */

}