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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.codehaus.plexus.util.Os;

final class MvnRequest {

  final List<String> goals = new ArrayList<>();

  final Properties systemProperties = new Properties();

  private File basedir;

  final void acceptMavenExecutionRequest(DefaultMavenExecutionRequest request) {
    request.setGoals(goals);

    if (basedir != null) {
      File pom;
      pom = new File(basedir, "pom.xml");

      request.setPom(pom);
    }

    boolean caseSensitive;
    caseSensitive = !Os.isFamily(Os.FAMILY_WINDOWS);

    for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
      String key;
      key = entry.getKey();

      key = caseSensitive ? key : key.toUpperCase(Locale.ENGLISH);

      key = "env." + key;

      systemProperties.setProperty(key, entry.getValue());
    }

    systemProperties.putAll(System.getProperties());

    request.setSystemProperties(systemProperties);
  }

  final void addGoal(String id) {
    goals.add(id);
  }

  final void setBasedir(Directory directory) {
    basedir = directory.toFile();
  }

}
