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
package br.com.objectos.mysql;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import objectos.lang.Checks;

public final class Mysql56 {

  private Mysql56() {}

  public static Process install(
      Directory scriptsDirectory,
      ConfigurationFile configurationFile,
      ServerOption option1,
      ServerOption option2) throws IOException {
    Checks.checkNotNull(scriptsDirectory, "scriptsDirectory == null");
    Checks.checkNotNull(configurationFile, "configurationFile == null");
    Checks.checkNotNull(option1, "option1 == null");
    Checks.checkNotNull(option2, "option2 == null");

    Set<String> keys;
    keys = new HashSet<String>();

    option1.addKeyTo(keys);

    option2.addKeyTo(keys);

    Checks.checkArgument(keys.contains("basedir"), "--basedir option was not set");

    Checks.checkArgument(keys.contains("datadir"), "--datadir option was not set");

    RegularFile scriptFile;

    try {
      scriptFile = scriptsDirectory.getRegularFile("mysql_install_db");
    } catch (IOException e) {
      throw new IOException("Failed to access required mysql_install_db script", e);
    }

    ProcessBuilder builder;
    builder = new ProcessBuilder();

    List<String> command;
    command = builder.command();

    command.add(scriptFile.getPath());

    configurationFile.acceptProcessBuilder(builder);

    option1.acceptProcessBuilder(builder);

    option2.acceptProcessBuilder(builder);

    return builder.start();
  }

}
