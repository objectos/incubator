/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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

import static org.testng.Assert.assertSame;

import org.testng.annotations.Test;

public class SimpleMediaTypeVisitorTest {

  @Test
  public void simpleTest() {
    ThisVisitor visitor = new ThisVisitor();
    test(ApplicationType.JAVASCRIPT, visitor);
    test(ImageType.BMP, visitor);
    test(TextType.PLAIN, visitor);
  }
  
  private void test(MediaType type, MediaTypeVisitor<MediaType, Void> visitor) {
    MediaType res = type.acceptMediaTypeVisitor(visitor, null);
    assertSame(res, type);
  }

  private static class ThisVisitor extends SimpleMediaTypeVisitor<MediaType, Void> {
    @Override
    protected final MediaType defaultAction(MediaType type, Void p) {
      return type;
    }
  }
  
}
