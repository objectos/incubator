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

import static br.com.objectos.way.code.testing.CodeAssertion.assertThat;

import br.com.objectos.way.code.testing.Compilation;
import br.com.objectos.way.code.testing.CompilationScript;
import org.testng.annotations.Test;

public class ModuleProcessorTest {

  @Test(enabled = false)
  public void directoryModule() {
    test("DirectoryModule");
  }

  @Test(enabled = false)
  public void mirrorModule() {
    test("MirrorModule");
  }

  @Test(enabled = false)
  public void responseModule() {
    test("ResponseModule");
  }

  private void test(String simpleName) {
    Compilation compilation = new CompilationScript()
        .addProcessor(new ModuleProcessor())
        .addCompilationUnit("/code/http/" + simpleName + ".java")
        .compile();
    assertThat(compilation)
        .generatedJavaFile("br.com.objectos.testing.way.Http" + simpleName)
        .equalToResource("/code/http/Http" + simpleName + ".java");
  }

}