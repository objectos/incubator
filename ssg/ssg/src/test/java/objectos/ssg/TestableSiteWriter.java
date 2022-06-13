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

import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import objectos.util.ImmutableList;

final class TestableSiteWriter implements SiteWriter {

  private final Map<String, Contents> contentMap = new TreeMap<>();

  public final ImmutableList<String> pathList() {
    Set<String> keys;
    keys = contentMap.keySet();

    return ImmutableList.copyOf(keys);
  }

  public final void testBytes(
      String path, MediaType mediaType, Resource resource) throws IOException {
    Contents sc;
    sc = contentMap.get(path);

    assertNotNull(sc, path + " not found");

    assertEquals(sc.mediaType, mediaType);

    assertEquals(sc.bytes, Read.byteArray(resource));
  }

  public final void testString(String path, MediaType mediaType, String contents) {
    Contents sc;
    sc = contentMap.get(path);

    assertNotNull(sc, path + " not found");

    assertEquals(sc.mediaType, mediaType);

    assertEquals(sc.string, contents);
  }

  @Override
  public final void writeBytes(
      String path, MediaType mediaType, byte[] contents) throws IOException {
    Contents c;
    c = new Contents(mediaType, null, contents);

    contentMap.put(path, c);
  }

  @Override
  public final void writeString(
      String path, MediaType mediaType, String contents) throws IOException {
    Contents c;
    c = new Contents(mediaType, contents, null);

    contentMap.put(path, c);
  }

  private record Contents(MediaType mediaType, String string, byte[] bytes) {}

}