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

import java.util.Locale;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

public final class ConfigurationGroup {

  private final Group group;
  private final ImmutableList<? extends Option> options;

  private ConfigurationGroup(Group group, ImmutableList<? extends Option> options) {
    this.group = group;
    this.options = options;
  }

  public static ConfigurationGroup client(ClientOption... options) {
    return new ConfigurationGroup(
        Group.CLIENT,
        ImmutableList.copyOf(options)
    );
  }

  public static ConfigurationGroup mysql(ShellOption... options) {
    return new ConfigurationGroup(
        Group.MYSQL,
        ImmutableList.copyOf(options)
    );
  }

  public static ConfigurationGroup mysqlbinlog(BinlogOption... options) {
    return new ConfigurationGroup(
        Group.MYSQLBINLOG,
        ImmutableList.copyOf(options)
    );
  }

  public static ConfigurationGroup mysqld(ServerOption... options) {
    return new ConfigurationGroup(
        Group.MYSQLD,
        ImmutableList.copyOf(options)
    );
  }

  public static ConfigurationGroup mysqldump(DumpOption... options) {
    return new ConfigurationGroup(
        Group.MYSQLDUMP,
        ImmutableList.copyOf(options)
    );
  }

  public final void addTo(MutableList<String> lines) {
    if (options.isEmpty()) {
      return;
    }

    lines.add(group.toString());

    for (int i = 0; i < options.size(); i++) {
      Option o;
      o = options.get(i);

      o.acceptConfigurationFile(lines);
    }
  }

  public enum Group {

    CLIENT,

    MYSQL,

    MYSQLBINLOG,

    MYSQLD,

    MYSQLDUMP;

    @Override
    public final String toString() {
      return '[' + name().toLowerCase(Locale.US) + ']';
    }

  }

}