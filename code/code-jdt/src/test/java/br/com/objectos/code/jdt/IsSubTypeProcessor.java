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

import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.code.processing.type.PTypeMirror;
import java.util.Set;
import objectos.util.ImmutableList;
import objectos.util.ImmutableSet;

class IsSubTypeProcessor extends AbstractProcessingRoundProcessor {

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(Marker.class);
  }

  @Override
  protected boolean process(ProcessingRound round) {
    ImmutableSet<ProcessingType> types = round.getAnnotatedTypes();
    for (ProcessingType type : types) {
      isSubType(type, "stringValue", CharSequence.class, true);
    }
    return round.claimTheseAnnotations();
  }

  private void isSubType(
      ProcessingType type, String methodName, Class<?> superType, boolean expected) {
    ImmutableList<ProcessingMethod> methods;
    methods = type.getDeclaredMethods();

    ProcessingMethod first;
    first = methods.get(0);

    PTypeMirror returnType;
    returnType = first.getReturnType();

    boolean result;
    result = returnType.isInstanceOf(superType);

    assertEquals(result, expected);
  }

}