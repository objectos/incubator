/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.server.nio;

import static org.testng.Assert.assertEquals;

import br.com.objectos.http.server.SocketWriter;
import br.com.objectos.io.Io;
import br.com.objectos.io.Resource;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WritableByteChannelSocketWriterTest {

  @Test(dataProvider = "writeByteSourceProvider")
  public void writeByteSource(String filename, int size) throws IOException {
    WritableByteChannel channel = new ByteArrayWritableByteChannel();
    ByteBuffer buffer = ByteBuffer.allocate(size);
    SocketWriter writer = WritableByteChannelSocketWriter.of(channel, buffer);

    Resource file = Resource.getResource(filename);
    writer.write(file);
    writer.flush();
    assertEquals(channel.toString(), Io.readStringUtf8(file));
  }

  @DataProvider
  public Object[][] writeByteSourceProvider() {
    return new Object[][] {
        {"htdocs/hello-world.txt", 100},
        {"htdocs/hello-world.txt", 5}
    };
  }

  @Test(dataProvider = "writeStringProvider")
  public void writeString(String text, int size) throws IOException {
    WritableByteChannel channel = new ByteArrayWritableByteChannel();
    ByteBuffer buffer = ByteBuffer.allocate(size);
    SocketWriter writer = WritableByteChannelSocketWriter.of(channel, buffer);
    writer.writeString(text);
    writer.flush();
    assertEquals(channel.toString(), text);
  }

  @DataProvider
  public Object[][] writeStringProvider() {
    return new Object[][] {
        {"abc", 4,},
        {"abc", 3,},
        {"abc", 2,},
        {"abc", 1,}
    };
  }

}