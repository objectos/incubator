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

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import java.util.List;
import java.util.Properties;
import objectos.util.ImmutableList;
import org.apache.maven.settings.Mirror;

final class FakeRequest implements Request {

  private ImmutableList<String> goals;

  private RegularFile pom;

  @Override
  public void acceptMavenExecutionRequest(Object execution) {}

  @Override
  public void addMirror(Mirror mirror) {}

  @Override
  public void setGoals(ImmutableList<String> goals) {
    this.goals = goals;
  }

  @Override
  public void setLocalRepository(Directory directory) {}

  @Override
  public void setMultiModuleProjectDirectory(Directory directory) {}

  @Override
  public void setPom(RegularFile file) {
    pom = file;
  }

  @Override
  public void setSystemProperties(Properties properties) {}

  final List<String> getGoals() {
    return goals;
  }

  final String getPomAbsolutePath() {
    return pom.toString();
  }

}
