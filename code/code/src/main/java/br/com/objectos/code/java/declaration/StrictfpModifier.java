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
package br.com.objectos.code.java.declaration;

public final class StrictfpModifier extends AbstractModifierImpl
    implements
    ClassModifier,
    EnumModifier,
    MethodModifier,
    InterfaceModifier {

  static final StrictfpModifier INSTANCE = new StrictfpModifier();

  private StrictfpModifier() {
    super("strictfp");
  }

  @Override
  public final void acceptClassCodeBuilder(ClassCode.Builder builder) {
    builder.addModifier(this);
  }

  @Override
  public final void acceptEnumCodeBuilder(EnumCode.Builder builder) {
    builder.addModifier(this);
  }

  @Override
  public final void acceptInterfaceCodeBuilder(InterfaceCode.Builder builder) {
    builder.addModifier(this);
  }

  @Override
  public final void acceptMethodCodeBuilder(MethodCode.Builder builder) {
    builder.uncheckedAddModifier(this);
  }

}