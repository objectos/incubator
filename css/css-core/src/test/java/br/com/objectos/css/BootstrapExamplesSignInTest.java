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

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Write;
import br.com.objectos.fs.RegularFile;
import java.io.IOException;
import org.testng.annotations.Test;

public class BootstrapExamplesSignInTest extends AbstractCssCoreTest {

  @Test(enabled = false, groups = "browser")
  public void browser() throws IOException, InterruptedException {
    BootstrapExamplesSignInForm form = new BootstrapExamplesSignInForm();
    BootstrapExamplesSignIn frag = new BootstrapExamplesSignIn(form);

    RegularFile file = directory.createRegularFile("x");

    Write.string(file, Charsets.utf8(), frag.toString());

    Process process = Runtime.getRuntime().exec("firefox --no-remote -P o7 " + file.toString());
    process.waitFor();
  }

}
