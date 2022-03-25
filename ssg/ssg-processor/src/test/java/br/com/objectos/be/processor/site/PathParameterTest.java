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
package br.com.objectos.be.processor.site;

import static br.com.objectos.code.java.Java.id;
import static org.testng.Assert.assertEquals;

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.testing.iter02.Iter02Directory;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.core.list.ImmutableList;
import org.testng.annotations.Test;

public class PathParameterTest extends AbstractBeProcessorTest {

  @Test
  public void _iter02() {
    ProcessingType directory;
    directory = testingEnv.getProcessingType(Iter02Directory.class);

    ImmutableList<PathParameter> paths;
    paths = PathParameter.fromDirectory(directory);

    assertEquals(paths.size(), 1);

    PathParameter path0;
    path0 = paths.get(0);

    assertEquals(path0.identifier, id("iter02"));

    assertEquals(
        path0.generateHelperExpression().toString(),
        "helper.iter02(from)"
    );
  }

}
