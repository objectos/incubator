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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import objectos.lang.Check;
import objectos.util.UnmodifiableList;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.settings.Mirror;
import org.codehaus.plexus.util.Os;

final class BuildRequest implements Request {

  private final List<String> goals = new ArrayList<>();

  private File localRepository;

  private final List<Mirror> mirrors = new ArrayList<>();

  private File multiModuleProjectDirectory;

  private File pom;

  private Properties systemProperties;

  @Override
  public final void acceptMavenExecutionRequest(Object execution) {
    if (execution instanceof MavenExecutionRequest) {
      MavenExecutionRequest request;
      request = (MavenExecutionRequest) execution;

      acceptMavenExecutionRequest0(request);
    }
  }

  @Override
  public final void addMirror(Mirror mirror) {
    Check.notNull(mirror, "mirror == null");

    mirrors.add(mirror);
  }

  @Override
  public final void setGoals(UnmodifiableList<String> goals) {
    Check.notNull(goals, "goals == null");

    this.goals.clear();

    for (int i = 0; i < goals.size(); i++) {
      String g;
      g = goals.get(i);

      this.goals.add(g);
    }
  }

  @Override
  public final void setLocalRepository(Directory directory) {
    Check.notNull(directory, "directory == null");

    localRepository = directory.toFile();
  }

  @Override
  public final void setMultiModuleProjectDirectory(Directory directory) {
    Check.notNull(directory, "directory == null");

    multiModuleProjectDirectory = directory.toFile();
  }

  @Override
  public final void setPom(RegularFile file) {
    Check.notNull(file, "file == null");

    pom = file.toFile();
  }

  @Override
  public final void setSystemProperties(Properties properties) {
    Check.notNull(properties, "properties == null");

    systemProperties = properties;
  }

  private void acceptMavenExecutionRequest0(MavenExecutionRequest request) {
    request.setGoals(goals);

    for (int i = 0; i < mirrors.size(); i++) {
      Mirror mirror;
      mirror = mirrors.get(i);

      request.addMirror(mirror);
    }

    if (localRepository != null) {
      request.setLocalRepositoryPath(localRepository);
    }

    if (multiModuleProjectDirectory != null) {
      request.setMultiModuleProjectDirectory(multiModuleProjectDirectory);
    }

    if (pom != null) {
      request.setPom(pom);
    }

    Properties p;
    p = new Properties();

    if (systemProperties != null) {
      p.putAll(systemProperties);
    }

    boolean caseSensitive;
    caseSensitive = !Os.isFamily(Os.FAMILY_WINDOWS);

    for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
      String key;
      key = entry.getKey();

      key = caseSensitive ? key : key.toUpperCase(Locale.ENGLISH);

      key = "env." + key;

      p.setProperty(key, entry.getValue());
    }

    p.putAll(System.getProperties());

    request.setSystemProperties(p);
  }

}
