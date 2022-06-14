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

import java.io.IOException;
import java.io.InputStream;
import objectos.util.ImmutableList;
import objectos.util.MutableList;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFormatException;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

class StandardPackageName extends PackageName {

  private final String name;
  private final ImmutableList<String> nameList;

  public StandardPackageName(ImmutableList<String> nameList) {
    this.nameList = nameList;
    name = nameList.join(".");
  }

  @Override
  public PackageName append(char[] name) {
    if (name == null) {
      return this;
    }

    MutableList<String> list;
    list = new MutableList<>();

    list.addAll(nameList);

    list.add(new String(name));

    ImmutableList<String> result;
    result = list.toImmutableList();

    return new StandardPackageName(result);
  }

  @SuppressWarnings("deprecation")
  @Override
  public boolean isPackage() {
    if ("java".equals(name)) {
      return true;
    }

    Package maybePackage = Package.getPackage(name);

    return maybePackage != null;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public char[][] toCharArray() {
    char[][] res = new char[nameList.size()][];
    for (int i = 0; i < res.length; i++) {
      res[i] = nameList.get(i).toCharArray();
    }
    return res;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  NameEnvironmentAnswer findType(String simpleName) {
    try {
      String filename = nameList.join("/", "/", "/" + simpleName + ".class");
      InputStream in = getClass().getResourceAsStream(filename);
      return in == null
          ? null
          : answer(in, filename);
    } catch (ClassFormatException e) {
      return null;
    } catch (IOException e) {
      return null;
    }
  }

  private NameEnvironmentAnswer answer(InputStream in, String filename)
      throws ClassFormatException, IOException {
    ClassFileReader reader = ClassFileReader.read(in, filename);
    return new NameEnvironmentAnswer(reader, null);
  }

}