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

import br.com.objectos.core.io.InputStreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/**
 * Based on IcoImageParser from Apache's common-imaging.
 */
class IcoImageDimensions {

  public static ImageDimensions of(InputStreamSource source) throws IOException {
    try (InputStream is = source.openInputStream()) {
      FileHeader fileHeader = readFileHeader(is);

      if (fileHeader.iconCount == 1) {
        return readIconInfo(is);
      }
    }

    throw new IOException("Could not get image dimensions: " + source.toString());
  }

  private static int read2Bytes(
      final String name,
      final InputStream is,
      final String exception) throws IOException {

    return read2Bytes(name, is, exception, ByteOrder.LITTLE_ENDIAN);

  }

  private static int read2Bytes(
      final String name,
      final InputStream is,
      final String exception,
      final ByteOrder byteOrder) throws IOException {

    final int byte0 = is.read();
    final int byte1 = is.read();
    if ((byte0 | byte1) < 0) {
      throw new IOException(exception);
    }

    final int result;
    if (byteOrder == ByteOrder.BIG_ENDIAN) {
      result = (byte0 << 8) | byte1;
    } else {
      result = (byte1 << 8) | byte0;
    }

    return result;

  }

  private static byte readByte(final String name, final InputStream is, final String exception)
      throws IOException {
    final int result = is.read();
    if ((result < 0)) {
      throw new IOException(exception);
    }
    return (byte) (0xff & result);
  }

  private static FileHeader readFileHeader(InputStream is) throws IOException {
    final int reserved = read2Bytes("Reserved", is, "Not a Valid ICO File");
    final int iconType = read2Bytes("IconType", is, "Not a Valid ICO File");
    final int iconCount = read2Bytes("IconCount", is, "Not a Valid ICO File");

    if (reserved != 0) {
      throw new IOException("Not a Valid ICO File: reserved is " + reserved);
    }
    if (iconType != 1 && iconType != 2) {
      throw new IOException("Not a Valid ICO File: icon type is " + iconType);
    }

    return new FileHeader(reserved, iconType, iconCount);
  }

  private static ImageDimensions readIconInfo(final InputStream is) throws IOException {
    // Width (1 byte), Width of Icon (1 to 255)
    final byte width = readByte("Width", is, "Not a Valid ICO File");
    // Height (1 byte), Height of Icon (1 to 255)
    final byte height = readByte("Height", is, "Not a Valid ICO File");
    return ImageDimensions.dim(width, height);
  }

  private static class FileHeader {
    public final int iconCount; // IconCount (2 bytes), number of icons in
                                // this file.

    FileHeader(final int reserved, final int iconType, final int iconCount) {
      this.iconCount = iconCount;
    }
  }

}
