/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.office.writer;

import br.com.objectos.core.io.Resource;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import java.io.IOException;

class WriterResources {

  public static final RegularFile HELLO = getRegularFile("TEST-INF/writer/hello.doc");

  private WriterResources() {}

  private static RegularFile getRegularFile(String resourceName) {
    try {
      Resource resource;
      resource = Resource.getResource(resourceName);

      return LocalFs.getRegularFile(resource);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}