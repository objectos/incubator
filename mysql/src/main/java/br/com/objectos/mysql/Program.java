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

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;

enum Program {

  MYSQL,

  MYSQL_CONFIG_EDITOR,

  MYSQLBINLOG,

  MYSQLDUMP;

  private final String linux;

  private Program() {
    linux = name().toLowerCase();
  }

  final RegularFile getRegularFile(Directory directory) throws IOException {
    return directory.getRegularFile(linux);
  }

  final ResolvedPath resolve(Directory directory) {
    return directory.resolve(linux);
  }

}
