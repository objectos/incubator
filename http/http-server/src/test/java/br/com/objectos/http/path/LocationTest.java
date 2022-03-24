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
package br.com.objectos.http.path;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LocationTest {

  @DataProvider
  public Object[][] toStringProvider() {
    return new Object[][] {
        {dataString(), "/data/[string]"},
        {postInt(), "/post/[int]"}
    };
  }

  @Test(dataProvider = "toStringProvider")
  public void toString_(Location spec, String toString) {
    assertEquals(spec.toString(), toString);
  }

  @Test(description = ""
      + "A location with no slugs should match the root /")
  public void root() {
    Location loc = Location.builder().build();
    assertSame(loc, Location.root());
    assertEquals(loc.toString(), "/");
  }

  @Test(description = ""
      + "it should accept:"
      + "- letters (any unicode?)"
      + "- digits"
      + "- slash")
  public void parse() {
    assertEquals(
        Location.parse("/"),
        Location.builder().build()
    );
    assertEquals(
        Location.parse("/abc"),
        Location.builder().fixed("abc").build()
    );
    assertEquals(
        Location.parse("/abc/123"),
        Location.builder().fixed("abc").fixed("123").build()
    );
  }

  @Test(description = ""
      + "it should throw an exception when:"
      + "- it does not start with a forward slash"
      + "- contains control (new line, tab, etc...)"
      + "- is empty")
  public void parseError() {
    itShouldFail("relative");
    itShouldFail("\n");
    itShouldFail("/abc/\n123");
    itShouldFail("/abc/x\nx");
    itShouldFail("");
  }

  private void itShouldFail(String string) {
    try {
      Location.parse(string);
      Assert.fail("Expected to fail: " + string);
    } catch (IllegalArgumentException expected) {
      assertEquals(expected.getMessage(), string + " is not a valid location");
    }
  }

  private Location dataString() {
    return Location.builder()
        .fixed("data")
        .stringParam()
        .build();
  }

  private Location postInt() {
    return Location.builder()
        .fixed("post")
        .intParam()
        .build();
  }

}