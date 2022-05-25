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
package br.com.objectos.git;

import br.com.objectos.concurrent.CpuTask;
import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.set.MutableSet;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import objectos.lang.NoteSink;

abstract class GitInjector {

  GitInjector() {}

  abstract <E> ArrayDeque<E> getArrayDeque();

  abstract int getBufferSize();

  abstract ByteArrayWriter getByteArrayWriter();

  abstract ByteBuffer getByteBuffer();

  abstract CharBuffer getCharBuffer();

  abstract CharsetDecoder getDecoder(Charset charset);

  abstract Deflater getDeflater();

  abstract CharsetEncoder getEncoder(Charset charset);

  abstract FilterNonExisting getFilterNonExisting();

  abstract IdentificationBuilder getIdentificationBuilder();

  abstract Inflater getInflater();

  abstract IntStack getIntStack();

  abstract IoWorker getIoWorker();

  abstract NoteSink getLogger();

  abstract MessageDigest getMessageDigest(String algoName);

  abstract <E> MutableList<E> getMutableList();

  abstract <E> MutableSet<E> getMutableSet();

  abstract ObjectReader getObjectReader();

  abstract OpenPackFile getOpenPackFile();

  abstract ReadBlob getReadBlob();

  abstract ResolveRef getResolveRef();

  abstract StringBuilder getStringBuilder();

  abstract UpdateRef getUpdateRef();

  abstract void putArrayDeque(ArrayDeque<?> deque);

  abstract ByteArrayWriter putByteArrayWriter(ByteArrayWriter writer);

  abstract ByteBuffer putByteBuffer(ByteBuffer buffer);

  abstract CharBuffer putCharBuffer(CharBuffer buffer);

  abstract CharsetDecoder putDecoder(CharsetDecoder decoder);

  abstract Deflater putDeflater(Deflater deflater);

  abstract CharsetEncoder putEncoder(CharsetEncoder encoder);

  abstract FilterNonExisting putFilterNonExisting(FilterNonExisting task);

  abstract Inflater putInflater(Inflater inflater);

  abstract IntStack putIntStack(IntStack stack);

  abstract MessageDigest putMessageDigest(MessageDigest digest);

  abstract <E> MutableList<E> putMutableList(MutableList<E> list);

  abstract <E> MutableSet<E> putMutableSet(MutableSet<E> set);

  abstract ObjectReader putObjectReader(ObjectReader reader);

  abstract ResolveRef putResolveRef(ResolveRef resolveRef);

  abstract StringBuilder putStringBuilder(StringBuilder sb);

  abstract boolean tryLock(CpuTask job);

  abstract void unlock(CpuTask job);

}