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
import br.com.objectos.fs.DirectoryContentsVisitor;
import br.com.objectos.fs.RegularFile;
import java.io.IOException;
import org.testng.Assert;

class RegularFileCount implements DirectoryContentsVisitor {

  int count;

  @Override
  public final void visitDirectory(Directory directory) throws IOException {
    Assert.fail();
  }

  @Override
  public final void visitRegularFile(RegularFile file) throws IOException {
    count++;
  }

}