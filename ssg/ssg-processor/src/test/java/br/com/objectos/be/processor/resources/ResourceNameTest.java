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
package br.com.objectos.be.processor.resources;

import static org.testng.Assert.assertEquals;

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.testing.img.ImgMarker;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.http.media.ImageType;
import java.io.IOException;
import org.testng.annotations.Test;

public class ResourceNameTest extends AbstractBeProcessorTest {

  @Test
  public void process() throws IOException {
    ProcessingPackage testingImg;
    testingImg = testingEnv.getProcessingPackage(ImgMarker.class);

    ResourceName jpgResourceName = new ResourceName(testingImg, "5x2.jpg");
    ProcessedResourceImage jpgResource;
    jpgResource = (ProcessedResourceImage) jpgResourceName.process();
    assertEquals(jpgResource.className, testingImg.toNamedPackage().nestedClass("Image5x2Jpg"));
    assertEquals(jpgResource.resourceName, "5x2.jpg");
    assertEquals(jpgResource.imageType, ImageType.JPEG);
    assertEquals(jpgResource.dimensions, ImageDimensions.dim(5, 2));
  }

}
