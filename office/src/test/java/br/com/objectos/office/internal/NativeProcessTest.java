/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.office.internal;

import br.com.objectos.office.AbstractOfficeTest;
import br.com.objectos.office.ServerStartException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NativeProcessTest extends AbstractOfficeTest {

  @Test(enabled = false)
  public void start() {
    NativeProcess process;
    process = null;

    try {
      process = NativeProcess.create();

      process.start();
    } catch (IOException e) {
      Assert.fail("IOException", e);
    } catch (ServerStartException e) {
      Assert.fail("ServerStartException", e);
    } finally {
      destroy(process);
    }
  }

}
