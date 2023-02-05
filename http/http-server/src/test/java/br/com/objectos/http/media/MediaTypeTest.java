/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.media;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class MediaTypeTest {

  @Test
  public void application() {
    assertEquals(resolve("DUMMY."), ApplicationType.OCTET_STREAM);
    assertEquals(resolve("dummy.xpto"), ApplicationType.OCTET_STREAM);
    assertEquals(resolve("dontknow"), ApplicationType.OCTET_STREAM);
  }

  @Test
  public void image() {
    assertEquals(resolve("image.bmp"), ImageType.BMP);
    assertEquals(resolve("image.gif"), ImageType.GIF);
    assertEquals(resolve("image.jpeg"), ImageType.JPEG);
    assertEquals(resolve("image.jpg"), ImageType.JPEG);
    assertEquals(resolve("image.png"), ImageType.PNG);
    assertEquals(resolve("image.svg"), ImageType.SVG);
    assertEquals(resolve("favicon.ico"), ImageType.ICON);
  }

  @Test
  public void text() {
    assertEquals(resolve("dummy.txt"), TextType.PLAIN);
    assertEquals(resolve("DUMMY.TXT"), TextType.PLAIN);
    assertEquals(resolve("dummy.html"), TextType.HTML);
    assertEquals(resolve("DUMMY.HTML"), TextType.HTML);
  }

  private MediaType resolve(String f) {
    return MediaTypes.ofFileName(f);
  }

}