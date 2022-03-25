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

import static br.com.objectos.code.java.Java._package;
import static org.testng.Assert.assertEquals;

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.Util;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.statement.Statement;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.http.media.ImageType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProcessedResourceImageTest extends AbstractBeProcessorTest {

  private NamedClass className;
  private ImageDimensions dimensions;
  private ImageType imageType;
  private String resourceName;

  @Test
  public void generateDirectoryConfigureStatement() {
    imageType = ImageType.JPEG;
    resourceName = "5x2.jpg";

    ProcessedResourceImage image;
    image = build();

    Statement statement;
    statement = image.generateDirectoryConfigureStatement();

    assertEquals(
        statement.toString(),
        "addResource(br.com.objectos.http.media.ImageType.JPEG, \"5x2.jpg\")"
    );
  }

  @Test
  public void generateDirectoryMethod() {
    PackageName imgPackage;
    imgPackage = _package("testing.img");

    className = imgPackage.nestedClass("Image5x2Jpg");
    dimensions = ImageDimensions.dim(5, 2);
    resourceName = "5x2.jpg";

    NamedClass implClassName;
    implClassName = imgPackage.nestedClass("ImgDirectory").nestedClass("ImageImpl");

    ProcessedResourceImage image;
    image = build();

    MethodCode method;
    method = image.generatePathMethod(implClassName);

    Util.assertHasLines(
        method.toString(),
        "public final testing.img.Image5x2Jpg image5x2Jpg() {",
        "  return new testing.img.ImgDirectory.ImageImpl(get(\"5x2.jpg\"), 5, 2);",
        "}"
    );

    dimensions = ImageDimensions.none();

    image = build();

    method = image.generatePathMethod(implClassName);

    Util.assertHasLines(
        method.toString(),
        "public final testing.img.Image5x2Jpg image5x2Jpg() {",
        "  return new testing.img.ImgDirectory.ImageImpl(get(\"5x2.jpg\"));",
        "}"
    );
  }

  @BeforeMethod
  public void reset() {
    imageType = null;
    dimensions = null;
    className = null;
    resourceName = null;
  }

  private ProcessedResourceImage build() {
    return new ProcessedResourceImage.Builder() {
      @Override
      final NamedClass className() {
        return className;
      }

      @Override
      final ImageDimensions dimensions() {
        return dimensions;
      }

      @Override
      final ImageType imageType() {
        return imageType;
      }

      @Override
      final String resourceName() {
        return resourceName;
      }
    }.build();
  }

}
