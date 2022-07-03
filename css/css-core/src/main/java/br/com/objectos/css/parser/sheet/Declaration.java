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
package br.com.objectos.css.parser.sheet;

import br.com.objectos.css.parser.IsNonTerminal;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.sheet.MultiDeclarationElement;
import br.com.objectos.css.sheet.StyleSheetDsl;
import java.util.Arrays;
import java.util.List;

abstract class Declaration implements IsNonTerminal, br.com.objectos.css.sheet.Declaration {

  private enum ThisMultiDeclarationElement implements MultiDeclarationElement {
    INSTANCE;
  }

  final StandardPropertyName propertyName;

  Declaration(Identifier identifier) {
    propertyName = identifier.getPropertyName();
  }

  public static Declaration get1(Identifier identifier, ThisValue value) {
    return new Declaration(identifier) {
      @Override
      public final void create(StyleSheetDsl dsl) {
        dsl.addDeclaration(propertyName, value);
      }
    };
  }

  public static Declaration get2(Identifier identifier, ThisValue value1, ThisValue value2) {
    return new Declaration(identifier) {
      @Override
      public final void create(StyleSheetDsl dsl) {
        dsl.addDeclaration(propertyName, value1, value2);
      }
    };
  }

  public static Declaration get3(Identifier identifier, ThisValue value1, ThisValue value2,
      ThisValue value3) {
    return new Declaration(identifier) {
      @Override
      public final void create(StyleSheetDsl dsl) {
        dsl.addDeclaration(propertyName, value1, value2, value3);
      }
    };
  }

  public static Declaration get4(
      Identifier identifier, ThisValue value1, ThisValue value2, ThisValue value3,
      ThisValue value4) {
    return new Declaration(identifier) {
      @Override
      public final void create(StyleSheetDsl dsl) {
        dsl.addDeclaration(propertyName, value1, value2, value3, value4);
      }
    };
  }

  public static Declaration getMulti(Identifier identifier, ThisValue first,
      List<CommaValue> rest) {
    return new Declaration(identifier) {
      @Override
      public final void create(StyleSheetDsl dsl) {
        doValue(dsl, first);

        for (int i = 0; i < rest.size(); i++) {
          CommaValue restValue;
          restValue = rest.get(i);

          ThisValue nextValue;
          nextValue = restValue.value;

          doValue(dsl, nextValue);
        }

        MultiDeclarationElement[] elements;
        elements = new MultiDeclarationElement[rest.size() + 1];

        Arrays.fill(elements, ThisMultiDeclarationElement.INSTANCE);

        dsl.addDeclaration(propertyName, elements);
      }

      private void doValue(StyleSheetDsl dsl, ThisValue value) {
        dsl.addDeclaration(propertyName, value);
      }
    };
  }

  public abstract void create(StyleSheetDsl dsl);

  public final String toMethodName() {
    return propertyName.getJavaName();
  }

}