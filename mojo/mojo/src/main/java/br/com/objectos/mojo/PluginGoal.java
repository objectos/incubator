/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.mojo;

import objectos.lang.Checks;

public class PluginGoal implements MvnOption {

  private final String prefix;
  private final String goal;

  private PluginGoal(String prefix, String goal) {
    this.prefix = prefix;
    this.goal = goal;
  }

  public static PluginGoal plugin(String prefix, String goal) {
    Checks.checkNotNull(prefix, "prefix == null");
    Checks.checkNotNull(goal, "goal == null");
    return new PluginGoal(prefix, goal);
  }

  @Override
  public final void acceptMvnRequest(MvnRequest request) {
    request.addGoal(command());
  }

  public final String command() {
    return prefix + ':' + goal;
  }

}
