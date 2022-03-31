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

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.Util;
import br.com.objectos.be.processor.testing.img.ImgMarker;
import br.com.objectos.code.model.element.ProcessingPackage;
import java.io.IOException;
import org.testng.annotations.Test;

public class ProcessedResourceImageTest extends AbstractBeProcessorTest {

  @Test
  public void generateInterface() throws IOException {
    ProcessingPackage testingImg;
    testingImg = testingEnv.getProcessingPackage(ImgMarker.class);

    ResourceName jpgResourceName;
    jpgResourceName = new ResourceName(testingImg, "5x2.jpg");

    ProcessedResourceImage jpgResource;
    jpgResource = (ProcessedResourceImage) jpgResourceName.process();

    Util.assertHasLines(
        jpgResource.generateInterface().toString(),
        "package br.com.objectos.be.processor.testing.img;",
        "",
        "import br.com.objectos.be.meta.MetaBeImage;",
        "import br.com.objectos.be.meta.MetaBeImageDimensions;",
        "import br.com.objectos.be.resource.ImageResource;",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.http.media.ImageType;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeResourcesProcessor\")",
        "@MetaBeImage(imageType = ImageType.JPEG, resourceName = \"5x2.jpg\")",
        "@MetaBeImageDimensions(width = 5, height = 2)",
        "public interface Image5x2Jpg extends ImageResource {}"
    );
  }

}