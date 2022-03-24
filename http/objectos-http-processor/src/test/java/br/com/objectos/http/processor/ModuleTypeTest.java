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

import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ModuleTypeTest extends AbstractTest {

  @DataProvider
  public Object[][] constructorProvider() {
    return new Object[][] {
        { "DirectoryModule", list("HttpDirectoryModule.constructor0.poet") }
    };
  }

  @DataProvider
  public Object[][] typeProvider() {
    return new Object[][] {
        { "MirrorModule", "HttpMirrorModule" }
    };
  }

  @Test(dataProvider = "constructorProvider")
  public void constructorList(String typeName, List<String> expected) {
    ModuleTypeArtifact moduleType = moduleType(typeName);
    ASSERT.that(moduleType.constructorList()).hasToStringEqualTo(expected);
  }

  @Test(enabled = false, dataProvider = "typeProvider")
  public void configureMethodSpec(String typeName, String expected) {
    ModuleTypeArtifact moduleType = moduleType(typeName);
    ASSERT.that(moduleType.configureMethodSpec()).hasToStringEqualTo(expected + ".configure.poet");
  }

  @Test(enabled = false, dataProvider = "typeProvider")
  public void typeSpec(String typeName, String expected) {
    ModuleTypeArtifact moduleType = moduleType(typeName);
    ASSERT.that(moduleType.typeSpec()).hasToStringEqualTo(expected + ".type.poet");
  }

}