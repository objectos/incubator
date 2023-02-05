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
package br.com.objectos.code.testing.annotation.processing;

import br.com.objectos.core.io.Resource;
import java.io.IOException;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileManager.Location;
import javax.tools.JavaFileObject;

public final class ResourcesFiler implements Filer {

  private static final Filer INSTANCE = new ResourcesFiler();

  private ResourcesFiler() {}

  public static Filer getInstance() {
    return INSTANCE;
  }

  @Override
  public JavaFileObject createClassFile(
      CharSequence name, Element... originatingElements)
      throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public FileObject createResource(
      Location location, CharSequence pkg, CharSequence relativeName,
      Element... originatingElements)
      throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public JavaFileObject createSourceFile(
      CharSequence name, Element... originatingElements)
      throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public FileObject getResource(
      Location location, CharSequence pkg, CharSequence relativeName)
      throws IOException {
    String packageName = pkg.toString();
    String resourceName = packageName.replace('.', '/') + "/" + relativeName;
    Resource resource = Resource.getResource(resourceName);
    return new ResourceFileObject(resource);
  }

}