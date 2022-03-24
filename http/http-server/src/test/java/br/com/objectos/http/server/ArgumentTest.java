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
package br.com.objectos.http.server;

import static org.testng.Assert.assertEquals;

import br.com.objectos.http.path.Argument;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArgumentTest {

  @Test
  public void intArgument() {
    Argument res = Argument.intArg(123);
    assertEquals(res.getInt(), 123);
  }

  @Test(expectedExceptions = UnsupportedOperationException.class)
  public void intArgument_string() {
    Argument res = Argument.intArg(123);
    res.getString();
    Assert.fail();
  }

  @Test
  public void stringArgument() {
    Argument res = Argument.stringArg("abc");
    assertEquals(res.getString(), "abc");
  }

  @Test(expectedExceptions = UnsupportedOperationException.class)
  public void stringArgument_int() {
    Argument res = Argument.stringArg("abc");
    res.getInt();
    Assert.fail();
  }

}