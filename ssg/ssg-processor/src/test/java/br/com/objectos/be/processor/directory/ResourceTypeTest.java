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
package br.com.objectos.be.processor.directory;

import static org.testng.Assert.assertEquals;

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.testing.iter00.IndexHtml;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.model.element.ProcessingType;
import org.testng.annotations.Test;

public class ResourceTypeTest extends AbstractBeProcessorTest {

  @Test
  public void process() {
    ProcessingType indexHtmlProcessingType;
    indexHtmlProcessingType = testingEnv.getProcessingType(IndexHtml.class);

    ResourceType indexHtmlResourceType;
    indexHtmlResourceType = new ResourceType(indexHtmlProcessingType);

    ProcessedResourceType indexHtml;
    indexHtml = (ProcessedResourceType) indexHtmlResourceType.process();

    assertEquals(indexHtml.className, indexHtmlProcessingType.getName());
    assertEquals(indexHtml.resourceName, "index.html");

    BeAnnotatedType index = indexHtml.beAnnotatedType;

    assertEquals(
        index.className,
        PackageName.of(IndexHtml.class).nestedClass("Index")
    );
  }

}
