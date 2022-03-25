/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

class LinkerDriver extends LinkerDriverDef {

  private Linker linker;

  @Override
  void givenLinkerImpl() {
    linker = new Linker();
  }

  @Override
  void whenTrailingBlockImpl(TrailingBlock block) {
    block.acceptLinker(linker);
  }

  @Override
  void thenLinkerToStringImpl(String[] expected) {
    String[] split = linker.toString().split("\n");
    List<String> toStringList = Arrays.asList(split);
    assertEquals(toStringList, Arrays.asList(expected));
  }

}