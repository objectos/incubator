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
package br.com.objectos.code.processor;

import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.java.type.NamedClassOrParameterized;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.code.processing.type.PTypeMirror;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.processing.Processor;
import objectos.util.UnmodifiableSet;
import objectos.util.Lists;
import objectos.util.MutableList;

@Services(Processor.class)
public class ServicesProcessor extends AbstractProcessingRoundProcessor {

  private final Map<String, MutableList<String>> servicesMap = new TreeMap<>();

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(Services.class);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    UnmodifiableSet<ProcessingType> types = round.getAnnotatedTypes();
    for (ProcessingType type : types) {
      process(type);
    }
    if (round.isOver()) {
      write(round);
    }
    return round.claimTheseAnnotations();
  }

  private String getServiceName(ProcessingType type) throws AssertionError {
    ProcessingAnnotation servicesAnnotation;
    servicesAnnotation = type.getDirectlyPresentAnnotation(Services.class);

    ProcessingAnnotationValue value;
    value = servicesAnnotation.getDeclaredValue("value");

    PTypeMirror serviceType;
    serviceType = value.getType();

    if (!serviceType.isDeclaredType()) {
      throw new AssertionError("Expected a ModelClassOrParameterized instance");
    }

    PDeclaredType serviceClass;
    serviceClass = serviceType.toDeclaredType();

    NamedClassOrParameterized serviceClassName;
    serviceClassName = serviceClass.getName();

    return serviceClassName.toString();
  }

  private void process(ProcessingType type) {
    String serviceName;
    serviceName = getServiceName(type);

    MutableList<String> implementationList;
    implementationList = servicesMap.get(serviceName);

    if (implementationList == null) {
      implementationList = new MutableList<>();

      servicesMap.put(serviceName, implementationList);
    }

    implementationList.add(type.getBinaryName());
  }

  private void write(ProcessingRound round) {
    Set<String> services = servicesMap.keySet();

    for (String service : services) {
      write0(round, service, servicesMap.get(service));
    }
  }

  private void write0(
      ProcessingRound round,
      String serviceName,
      MutableList<String> implementations) {
    try {
      String resourceName;
      resourceName = "META-INF/services/" + serviceName;

      implementations.sort(Lists.naturalOrder());

      String contents;
      contents = implementations.join("\n");

      round.writeResource(resourceName, contents);
    } catch (IOException e) {
      round.printMessageError(e);
    }
  }

}