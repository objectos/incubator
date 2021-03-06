/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractCssCoreTest {

  Directory directory;

  @AfterClass
  public final void cleanUp() throws IOException {
    directory.deleteContents();
    directory.delete();
  }

  @BeforeClass
  public final void setUp() throws IOException {
    directory = TmpDir.create();
  }

  // selector

  final ListSelector list(Selector first, Selector second) {
    return new ListSelector(first, second);
  }

  final SimpleSelector selector(String value) {
    return new SimpleSelector(value);
  }

}