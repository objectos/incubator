/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
import static org.testng.Assert.assertTrue;

import br.com.objectos.http.server.RequestedPart;
import br.com.objectos.http.server.RequestedPath;
import java.util.Arrays;
import java.util.List;
import objectos.util.UnmodifiableList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SlugTest {

  @Test(description = ""
      + "A catch all slug must match:"
      + "- root (empty)"
      + "- files"
      + "- files in sub directory")
  public void catchAll() {
    Slug catchAll = Slug.catchAll();
    assertTrue(catchAll.matchesEmpty());

    RequestedPath file = new RequestedPath("/dir/file");
    file.next();
    assertEquals(
        resolve(catchAll, file, true),
        Arrays.asList(Argument.stringArg("file"))
    );
  }

  @Test(dataProvider = "fixedProvider")
  public void fixed(String spec, String value, boolean expected) {
    Slug part = Slug.fixed(spec);
    accept(part, value, expected);
  }

  @DataProvider
  public Object[][] fixedProvider() {
    return new Object[][] {
        {"abc", "abc", true},
        {"abc", "ab", false},
        {"abc", "abcx", false},
        {"abc", "123", false}
    };
  }

  @Test(dataProvider = "intPartProvider")
  public void intPart(String value, boolean expected) {
    Slug part = Slug.intParam();
    accept(part, value, expected);
  }

  @DataProvider
  public Object[][] intPartProvider() {
    return new Object[][] {
        {"123", true},
        {"123x", false},
        {"x123", false},
        {"abc", false}
    };
  }

  @Test(dataProvider = "stringPartProvider")
  public void stringPart(String value, boolean expected) {
    Slug part = Slug.stringParam();
    accept(part, value, expected);
  }

  @DataProvider
  public Object[][] stringPartProvider() {
    return new Object[][] {
        {"123", true},
        {"123x", true},
        {"x123", true},
        {"abc", true}
    };
  }

  @Test(dataProvider = "toStringProvider")
  public void toString_(Slug part, String toString) {
    assertEquals(part.toString(), toString);
  }

  @DataProvider
  public Object[][] toStringProvider() {
    return new Object[][] {
        {Slug.fixed("abc"), "abc"},
        {Slug.intParam(), "[int]"},
        {Slug.stringParam(), "[string]"}
    };
  }

  private void accept(Slug part, String value, boolean expected) {
    UnmodifiableList<Slug> expectedList = UnmodifiableList.of(part);
    RouteParser parser = new RouteParser(expectedList, null, null);
    RequestedPart requestedPart = new RequestedPath(value).next();
    assertEquals(part.matches(parser, requestedPart), expected);
  }

  private List<Argument> resolve(Slug part, RequestedPath path, boolean expected) {
    UnmodifiableList<Slug> expectedList = UnmodifiableList.of(part);
    RouteParser parser = new RouteParser(expectedList, null, null);
    assertEquals(part.matches(parser, path.next()), expected);
    return parser.argumentList();
  }

}