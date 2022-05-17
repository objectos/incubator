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
package br.com.objectos.code.jdt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import objectos.lang.Checks;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

public class CompilationUnits {

  private CompilationUnits() {}

  public static ResourceCompilationUnitBuilder resourceBuilder() {
    return new ResourceBuilder();
  }

  private static class ResourceBuilder
      implements
      ResourceCompilationUnitBuilder,
      ResourceCompilationUnitBuilder.ResourceName,
      ResourceCompilationUnitBuilder.PackageName,
      ResourceCompilationUnitBuilder.MainTypeName {

    private String mainTypeName;
    private String packageName;
    private String resourceName;

    @Override
    public ICompilationUnit build() {
      return new InMemoryCompilationUnit(
          resourceName,
          packageName,
          mainTypeName,
          contents());
    }

    @Override
    public MainTypeName mainTypeName(String mainTypeName) {
      this.mainTypeName = Checks.checkNotNull(mainTypeName, "mainTypeName == null");
      return this;
    }

    @Override
    public PackageName packageName(String packageName) {
      this.packageName = Checks.checkNotNull(packageName, "packageName == null");
      return this;
    }

    @Override
    public ResourceName resourceName(String resourceName) {
      this.resourceName = Checks.checkNotNull(resourceName, "resourceName == null");
      return this;
    }

    private String contents() {
      InputStream in;
      in = getClass().getResourceAsStream(resourceName);

      InputStreamReader inReader;
      inReader = new InputStreamReader(in);

      BufferedReader reader;
      reader = new BufferedReader(inReader);

      try {
        StringBuilder contents = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
          contents.append((char) c);
        }
        return contents.toString();
      } catch (IOException e) {
        return "";
      } finally {
        try {
          reader.close();
        } catch (IOException e) {
        }
      }
    }

  }

}