/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.processor;

import static java.lang.String.format;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ActionMethodTest extends AbstractTest {

  @DataProvider
  public Object[][] methodProvider() {
    return new Object[][] {
        { "MirrorModule", "get" },
        { "ResponseModule", "file" },
        { "ResponseModule", "status" },
        { "ResponseModule", "text" }
    };
  }

  @Test(dataProvider = "methodProvider")
  public void actionMethodSpec(String typeName, String methodName) {
    ActionMethod method = actionMethod(typeName, methodName);
    ASSERT.that(method.actionMethodSpec())
        .hasToStringEqualTo(format("Http%s.%s.action.poet", typeName, methodName));
  }

}