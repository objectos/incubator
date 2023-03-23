/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.code.method;

import java.util.Iterator;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;
import objectos.code.ParameterizedTypeName;
import objectos.code.TypeVariableName;

public class MethodTypeParam03 extends JavaTemplate {
  static final ClassTypeName CHARSEQ
      = ClassTypeName.of(CharSequence.class);

  static final ClassTypeName ITERATOR
      = ClassTypeName.of(Iterator.class);

  static final TypeVariableName E
      = TypeVariableName.of("E");

  static final ParameterizedTypeName ITERATOR_E
      = ParameterizedTypeName.of(ITERATOR, E);

  public static void main(String[] args) {
    System.out.println(new MethodTypeParam03());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("TypeParameterBounds"),

      method(
        ABSTRACT,
        typeParameter("E", CHARSEQ),
        name("ex01")
      ),

      method(
        ABSTRACT,
        typeParameter("ITER", ITERATOR_E),
        typeParameter("E"),
        name("ex02")
      ),

      method(
        ABSTRACT,
        typeParameter("T", E),
        typeParameter("E"),
        name("ex03")
      )
    );
  }
}
