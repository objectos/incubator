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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class PackageNameTest {

  @Test
  public void append() {
    PackageName res = brComObjectos().append(c('c', 'o', 'd', 'e'));
    assertEquals(res.toString(), "br.com.objectos.code");
  }

  @Test
  public void of() {
    PackageName res = brComObjectos();
    assertEquals(res.toString(), "br.com.objectos");
  }

  @Test
  public void of_null() {
    PackageName res = PackageName.of(null);
    assertEquals(res.toString(), "");
  }

  @Test
  public void toQualifiedName() {
    QualifiedName res
        = brComObjectos().append(c('c', 'o', 'd', 'e')).toQualifiedName(c('J', 'd', 't'));
    assertEquals(res.toString(), "br.com.objectos.code.Jdt");
  }

  private PackageName brComObjectos() {
    return PackageName.of(cc(c('b', 'r'), c('c', 'o', 'm'), c('o', 'b', 'j', 'e', 'c', 't', 'o', 's')));
  }

  private char[][] cc(char[]... args) {
    return args;
  }

  private char[] c(char... args) {
    return args;
  }

}