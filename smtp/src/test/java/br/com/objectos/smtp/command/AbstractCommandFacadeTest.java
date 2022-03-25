/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.smtp.command;

import static org.testng.Assert.assertEquals;

import br.com.objectos.smtp.mail.ClientName;
import br.com.objectos.smtp.mail.ForwardPath;
import br.com.objectos.smtp.mail.ReversePath;
import br.com.objectos.smtp.mail.ReversePathFacade;
import br.com.objectos.smtp.mail.SimpleReversePathFacade;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public abstract class AbstractCommandFacadeTest
    implements
    CommandParserAdapter {

  private Charset asciiCharset;
  CommandFacade ascii;

  private int maxTotal;

  @BeforeClass
  public final void _setUp() {
    asciiCharset = Charset.forName("US-ASCII");

    CharsetDecoder asciiDecoder;
    asciiDecoder = asciiCharset.newDecoder();

    CommandParser commandParser;
    commandParser = new CommandParser(this, asciiDecoder);

    ReversePathFacade reversePathFacade;
    reversePathFacade = new SimpleReversePathFacade();

    ascii = new CommandFacade(commandParser, reversePathFacade);
  }

  @Override
  public final void onChannelRead(int totalRead, int byteBufferCapacity) {
    maxTotal = Math.max(maxTotal, totalRead);
  }

  final void asciiRead(String string) {
    final byte[] bytes;
    bytes = string.getBytes(asciiCharset);

    ReadableByteChannel channel;
    channel = new ReadableByteChannel() {
      private boolean closed;
      private boolean read;

      @Override
      public final void close() throws IOException {
        closed = true;
      }

      @Override
      public final boolean isOpen() {
        return !closed;
      }

      @Override
      public final int read(ByteBuffer dst) throws IOException {
        if (read) {
          return 0;
        } else {
          dst.put(bytes);

          read = true;

          return bytes.length;
        }
      }
    };

    try {
      ascii.readCommand(channel);
    } catch (IOException e) {
      Assert.fail("Should never happen", e);
      return;
    }
  }

  final String line(String string) {
    return string + "\r\n";
  }

  final void testAscii(int[] codes, TestingCommandVisitor visitor) {
    assertEquals(ascii.consume0(), codes);
    visitor.test(ascii);
  }

  abstract class TestingCommandVisitor implements CommandVisitor {

    public final void test(CommandFacade service) {
      service.consume1(this);
      testImpl();
    }

    @Override
    public void visitDATA() {
      Assert.fail("Unexpected command");
    }

    @Override
    public void visitEHLO(ClientName client) {
      Assert.fail("Unexpected command");
    }

    @Override
    public void visitMAIL(ReversePath reversePath) {
      Assert.fail("Unexpected command");
    }

    @Override
    public void visitQUIT() {
      Assert.fail("Unexpected command");
    }

    @Override
    public void visitRCPT(ForwardPath forwardPath) {
      Assert.fail("Unexpected command");
    }

    abstract void testImpl();

  }

}
