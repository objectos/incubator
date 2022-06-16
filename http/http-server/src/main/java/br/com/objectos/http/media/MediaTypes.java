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

import java.util.Locale;
import objectos.lang.Check;
import objectos.util.UnmodifiableMap;
import objectos.util.MutableMap;

public class MediaTypes {

  private MediaTypes() {}

  public static MediaType ofExtension(String extension) {
    Check.notNull(extension, "extension == null");

    String ext = extension.toLowerCase();

    MediaType mediaType = ExtensionHolder.map.get(ext);

    if (mediaType == null) {
      mediaType = ApplicationType.OCTET_STREAM;
    }

    return mediaType;
  }

  public static MediaType ofFileName(String fileName) {
    Check.notNull(fileName, "fileName == null");

    int lastIndex;
    lastIndex = fileName.lastIndexOf('.');

    if (lastIndex == -1) {
      return ApplicationType.OCTET_STREAM;
    }

    String extension;
    extension = fileName.substring(lastIndex + 1);

    String ext;
    ext = extension.toLowerCase();

    MediaType mediaType = ExtensionHolder.map.get(ext);

    if (mediaType == null) {
      mediaType = ApplicationType.OCTET_STREAM;
    }

    return mediaType;
  }

  static String qualifiedNameImpl(Enum<? extends MediaType> subtype) {
    return qualifiedNameImpl(subtype, subtypeNameImpl(subtype));
  }

  static String qualifiedNameImpl(Enum<? extends MediaType> subtype, String subtypeName) {
    MediaType mediaType = (MediaType) subtype;
    TopLevel topLevel = mediaType.type();
    return topLevel.simpleName() + '/' + subtypeName;
  }

  static String subtypeNameImpl(Enum<? extends MediaType> subType) {
    return subType.name().toLowerCase(Locale.US).replace('_', '-');
  }

  private static class ExtensionHolder {

    static final UnmodifiableMap<String, MediaType> map = buildExtensionMap();

    private static UnmodifiableMap<String, MediaType> buildExtensionMap() {
      MutableMap<String, MediaType> map;
      map = new MutableMap<>();

      // application
      map.put("js", ApplicationType.JAVASCRIPT);
      map.put("json", ApplicationType.JSON);

      // image
      map.put("bmp", ImageType.BMP);
      map.put("gif", ImageType.GIF);
      map.put("ico", ImageType.ICON);
      map.put("jpg", ImageType.JPEG);
      map.put("jpeg", ImageType.JPEG);
      map.put("png", ImageType.PNG);
      map.put("svg", ImageType.SVG);

      // text
      map.put("css", TextType.CSS);
      map.put("html", TextType.HTML);
      map.put("java", TextType.PLAIN);
      map.put("xml", TextType.XML);
      map.put("txt", TextType.PLAIN);

      return map.toUnmodifiableMap();
    }

  }

}
