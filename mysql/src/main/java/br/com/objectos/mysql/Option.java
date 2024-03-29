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
package br.com.objectos.mysql;

import java.util.Set;
import objectos.util.GrowableList;

public interface Option {

  void acceptClientJob(AbstractClientJob<?> job);

  void acceptConfigurationFile(GrowableList<String> lines);

  @SuppressWarnings("exports")
  void acceptExecution(Execution execution);

  void acceptProcessBuilder(ProcessBuilder builder);

  void addKeyTo(Set<String> keys);

  boolean is(String key);

}
