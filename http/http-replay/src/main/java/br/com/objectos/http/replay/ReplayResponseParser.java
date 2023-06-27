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
package br.com.objectos.http.replay;

import br.com.objectos.concurrent.IoTask;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.http.parser.Header.ContentType;
import br.com.objectos.http.parser.Header.ContentTypeVisitor;
import br.com.objectos.http.parser.HeaderParser;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import objectos.http.media.ApplicationType;
import objectos.http.media.ImageType;
import objectos.http.media.TextType;

final class ReplayResponseParser implements IoTask {

  private static final char[] CONTENT_HTTP_TRAILER = "HTTP: 200\r\n\r\n".toCharArray();

  private static final char[] CONTENT_TRAILER = "ontent-Type: ".toCharArray();

  private static final char[] LOCATION_TRAILER = "ocation: ".toCharArray();

  private Action action;

  private final ReplayResponseParserAdapter adapter;

  private Charset bodyCharset;

  private final StringBuilder bodyTextBuilder = new StringBuilder();

  private final byte[] byteArray;

  private int byteArrayCount;

  private final ByteBuffer byteBuffer;

  private final CharBuffer charBuffer;

  private int charIndex;

  private ContentType contentType;

  private final ThisContentTypeVisitor contentTypeVisitor = new ThisContentTypeVisitor();

  private Action decodeAction;

  private CharsetDecoder decoder = Charsets.usAscii().newDecoder();

  private final Map<Charset, CharsetDecoder> decoderMap = new HashMap<>(4);

  private GZIPInputStream input;

  private IOException ioException;

  private volatile boolean ioRunning;

  private final IoWorker ioWorker;

  ReplayResponseParser(ReplayResponseParserAdapter adapter,
                       ByteBuffer byteBuffer,
                       CharBuffer charBuffer,
                       IoWorker ioWorker) {
    this.adapter = adapter;

    byteArray = new byte[byteBuffer.limit()];

    this.byteBuffer = byteBuffer;
    this.charBuffer = charBuffer;
    this.ioWorker = ioWorker;
  }

  @Override
  public final void executeIo() {
    try {
      byteArrayCount = input.read(byteArray);
    } catch (IOException e) {
      ioException = e;
    } finally {
      ioRunning = false;
    }
  }

  public final void executeOne() {
    action = execute();
  }

  public final void setInput(GZIPInputStream gzip) {
    input = gzip;

    action = Action.RESET;
  }

  public final boolean shouldExecute() {
    return action != null;
  }

  private Action execute() {
    switch (action) {
      case BODY_TEXT:
        return executeBodyText();
      case BODY_TEXT_LOOP:
        return executeBodyTextLoop();
      case DECODE:
        return executeDecode();
      case IO_WAIT:
        return executeIoWait();
      case PARSE:
        return executeParse();
      case RESET:
        return executeReset();
      default:
        throw new UnsupportedOperationException("Implement me @ " + action.name());
    }
  }

  private Action executeBodyText() {
    int overReadCount;
    overReadCount = nextCharSize();

    int oldPosition;
    oldPosition = byteBuffer.position();

    int newPosition;
    newPosition = oldPosition - overReadCount;

    if (newPosition < 0) {
      throw new UnsupportedOperationException("Implement me @ newPosition=" + newPosition);
    }

    byteBuffer.position(newPosition);

    setDecoder(bodyCharset);

    decodeAction = Action.BODY_TEXT_LOOP;

    bodyTextBuilder.setLength(0);

    if (byteBuffer.hasRemaining()) {
      return Action.DECODE;
    } else {
      return toIo();
    }
  }

  private Action executeBodyTextLoop() {
    if (!charBuffer.hasArray()) {
      throw new UnsupportedOperationException("Implement me");
    }

    char[] array;
    array = charBuffer.array();

    bodyTextBuilder.append(array, 0, charBuffer.position());

    return toIo();
  }

  private Action executeDecode() {
    charBuffer.clear();

    charIndex = 0;

    CoderResult result;
    result = decoder.decode(byteBuffer, charBuffer, false);

    if (!result.isUnderflow()) {
      throw new UnsupportedOperationException("Implement me. result=" + result);
    }

    return decodeAction;
  }

  private Action executeIoEof() {
    switch (decodeAction) {
      case BODY_TEXT_LOOP:
        return executeResultText();
      default:
        throw new UnsupportedOperationException("Implement me @ " + decodeAction);
    }
  }

  private Action executeIoWait() {
    if (ioRunning) {
      return Action.IO_WAIT;
    }

    else if (ioException != null) {
      throw new UnsupportedOperationException("Implement me @ io exception");
    }

    if (byteArrayCount < 0) {
      return executeIoEof();
    }

    if (byteArrayCount == 0) {
      throw new UnsupportedOperationException("Implement me @ no bytes read");
    }

    byteBuffer.clear();

    byteBuffer.put(byteArray, 0, byteArrayCount);

    byteBuffer.flip();

    return Action.DECODE;
  }

  private Action executeParse() {
    char nextChar;
    nextChar = nextChar();

    switch (nextChar) {
      case 'C':
        return executeParseContent();
      case 'L':
        return executeParseLocation();
      default:
        throw new UnsupportedOperationException("Implement me @ next char=" + nextChar);
    }
  }

  private Action executeParseContent() {
    boolean matches;
    matches = matches(CONTENT_TRAILER);

    if (!matches) {
      throw new UnsupportedOperationException("Implement me");
    }

    HeaderParser<ContentType> parser;
    parser = HeaderParser.createContentTypeParser();

    while (parser.shouldConsume()) {
      char c;
      c = nextChar();

      parser.consume(c);
    }

    if (parser.isMalformed()) {
      throw new UnsupportedOperationException("Implement me");
    }

    matches = matches(CONTENT_HTTP_TRAILER);

    if (!matches) {
      throw new UnsupportedOperationException("Implement me");
    }

    contentType = parser.build();

    action = toResult();

    contentType.acceptContentTypeVisitor(contentTypeVisitor);

    return action;
  }

  private Action executeParseLocation() {
    boolean matches;
    matches = matches(LOCATION_TRAILER);

    if (!matches) {
      throw new UnsupportedOperationException("Implement me");
    }

    char[] source;
    source = nextCharArray(nextCharSize());

    if (source.length < 3) {
      throw new UnsupportedOperationException("Implement me");
    }

    matches = true
        && source[source.length - 2] == '\r'
        && source[source.length - 1] == '\n';

    if (!matches) {
      throw new UnsupportedOperationException("Implement me");
    }

    String location;
    location = new String(source, 0, source.length - 2);

    adapter.expectRedirect(location);

    return toResult();
  }

  private Action executeReset() {
    bodyCharset = null;

    bodyTextBuilder.setLength(0);

    byteArrayCount = 0;

    contentType = null;

    decodeAction = Action.PARSE;

    setDecoder(Charsets.usAscii());

    return toIo();
  }

  private Action executeResultText() {
    String text;
    text = bodyTextBuilder.toString();

    adapter.expectTextResponse(contentType, text);

    return toResult();
  }

  private boolean matches(char[] expected) {
    int length;
    length = expected.length;

    char[] source;
    source = nextCharArray(length);

    for (int i = 0; i < length; i++) {
      char thisChar;
      thisChar = source[i];

      char thatChar;
      thatChar = expected[i];

      if (thisChar != thatChar) {
        return false;
      }
    }

    return true;
  }

  private char nextChar() {
    return charBuffer.get(charIndex++);
  }

  private char[] nextCharArray(int length) {
    char[] result;
    result = new char[length];

    charBuffer.get(charIndex, result, 0, length);

    charIndex += length;

    return result;
  }

  private int nextCharSize() {
    return charBuffer.position() - charIndex;
  }

  private void setDecoder(Charset charset) {
    CharsetDecoder newDecoder;
    newDecoder = decoderMap.get(charset);

    if (newDecoder == null) {
      newDecoder = charset.newDecoder();

      decoderMap.put(charset, newDecoder);
    } else {
      newDecoder.reset();
    }

    decoder = newDecoder;
  }

  private Action toIo() {
    byteArrayCount = 0;

    ioRunning = true;

    ioWorker.submit(this);

    return Action.IO_WAIT;
  }

  private Action toResult() {
    // should end, return null
    return null;
  }

  private enum Action {

    BODY_TEXT,

    BODY_TEXT_LOOP,

    DECODE,

    IO_WAIT,

    PARSE,

    RESET;

  }

  private class ThisContentTypeVisitor implements ContentTypeVisitor {
    @Override
    public final void visitApplicationType(ApplicationType type) {
      switch (type) {
        case JSON:
          action = Action.BODY_TEXT;

          bodyCharset = Charsets.utf8();
          break;
        default:
          throw new UnsupportedOperationException("Implement me @ " + type);
      }
    }

    @Override
    public final void visitApplicationType(ApplicationType type, Charset charset) {
      throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public final void visitImageType(ImageType type) {
      throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public final void visitTextType(TextType type) {
      throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public final void visitTextType(TextType type, Charset charset) {
      action = Action.BODY_TEXT;

      bodyCharset = charset;
    }
  }

}