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
import java.util.Properties;
import objectos.util.ImmutableList;
import org.apache.maven.settings.Mirror;

public interface Request {

  void acceptMavenExecutionRequest(Object execution);

  void addMirror(Mirror mirror);

  void setGoals(ImmutableList<String> goals);

  void setLocalRepository(Directory directory);

  void setMultiModuleProjectDirectory(Directory directory);

  void setPom(RegularFile file);

  void setSystemProperties(Properties properties);

}