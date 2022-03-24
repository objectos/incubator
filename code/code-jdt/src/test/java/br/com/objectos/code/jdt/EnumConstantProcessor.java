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

import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.core.list.Lists;
import java.util.List;
import java.util.Set;

class EnumConstantProcessor extends AbstractProcessingRoundProcessor {

  private final List<String> enumConstantList = Lists.newArrayList();

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(Marker.class);
  }

  @Override
  protected boolean process(ProcessingRound round) {
    // ProcessingType type = ProcessingType.adapt(processingEnv,
    // aTypeElement);
    // type.methods()
    // .map(MethodElementQuery::returnType)
    // .map(TypeMirrorQuery::asTypeElement)
    // .filter(Optional::isPresent)
    // .map(Optional::get)
    // .filter(ProcessingType::isEnum)
    // .flatMap(ProcessingType::fields)
    // .filter(VariableElementQuery::isEnumConstant)
    // .map(VariableElementQuery::name)
    // .forEach(enumConstantList::add);
    throw new UnsupportedOperationException("Implement me");
  }

  List<String> enumConstantList() {
    return enumConstantList;
  }

}