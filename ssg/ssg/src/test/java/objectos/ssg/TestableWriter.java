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
package objectos.ssg;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

final class TestableWriter implements SiteWriter {

  private final Map<String, StringContents> contentMap = new TreeMap<>();

  public final ImmutableList<String> pathList() {
    Set<String> keys;
    keys = contentMap.keySet();

    return ImmutableList.copyOf(keys);
  }

  public final void testString(String path, MediaType mediaType, String contents) {
    StringContents sc;
    sc = contentMap.get(path);

    assertNotNull(sc, path + " not found");

    assertEquals(sc.mediaType, mediaType);

    assertEquals(sc.contents, contents);
  }

  @Override
  public final void writeStringArtifact(
      String path, MediaType mediaType, String contents) throws IOException {
    StringContents c;
    c = new StringContents(mediaType, contents);

    contentMap.put(path, c);
  }

  private record StringContents(MediaType mediaType, String contents) {}

}