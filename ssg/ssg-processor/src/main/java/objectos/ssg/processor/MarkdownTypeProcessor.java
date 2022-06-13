/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg.processor;

import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.Processor;
import objectos.ssg.Markdown;
import objectos.util.ImmutableSet;

@Services(Processor.class)
public final class MarkdownTypeProcessor extends AbstractProcessingRoundProcessor {

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(Markdown.class);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    ImmutableSet<ProcessingType> types;
    types = round.getAnnotatedTypes();

    for (ProcessingType type : types) {
      process0(round, type);
    }

    return round.allowOtherProcessors();
  }

  private void process0(ProcessingRound round, ProcessingType type) {
    try {
      String canonicalName;
      canonicalName = type.getCanonicalName();

      String resourceName;
      resourceName = canonicalName.replace('.', '/');

      resourceName = resourceName + ".md";

      String contents;
      contents = type.getDocComment();

      round.writeResource(resourceName, contents);
    } catch (IOException e) {
      round.printMessageError(e);
    }
  }

}
