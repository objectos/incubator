/*
 * Copyright (C) 2021-2023 Objectos Software LTDA.
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
package br.com.objectos.fs;

import br.com.objectos.latest.Concrete.Bridge;
import br.com.objectos.latest.Concrete.Constructor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import objectos.lang.Check;

@Bridge
abstract class ObjectJava6 extends ObjectJavaAny {

  private final File delegate;

  private URI uri;

  @Constructor
  ObjectJava6(File delegate) {
    this.delegate = delegate;
  }

  @Override
  public final <R, P> R acceptPathNameVisitor(
      PathNameVisitor<R, P> visitor, P p) throws IOException {
    Check.notNull(visitor, "visitor == null");

    if (!delegate.exists()) {
      return visitor.visitNotFound(this, p);
    }

    if (delegate.isFile()) {
      return visitor.visitRegularFile(this, p);
    }

    if (delegate.isDirectory()) {
      return visitor.visitDirectory(this, p);
    }

    throw new IOException("Not a directory nor a regular file");
  }

  @Override
  public final Directory createDirectory() throws IOException {
    if (delegate.mkdir()) {
      return this;
    }

    if (delegate.exists()) {
      throw new FoundException(getPath());
    }

    throw new IOException(getPath());
  }

  @Override
  public final ResolvedPath createParents() throws IOException {
    File parent;
    parent = delegate.getParentFile();

    if (parent == null) {
      return this;
    }

    if (parent.exists()) {
      return this;
    }

    if (!parent.mkdirs()) {
      throw new IOException("createParents operation failed");
    }

    return this;
  }

  @Override
  public final RegularFile createRegularFile(
      RegularFileCreateOption... options) throws IOException {
    Check.notNull(options, "options == null");

    checkRegularFileDoesNotExist(delegate);

    boolean created;
    created = delegate.createNewFile();

    if (!created) {
      throw new IOException("Failed to create new file " + delegate);
    }

    for (RegularFileCreateOption option : options) {
      option.createRegularFileSetValue(delegate);
    }

    return this;
  }

  @Override
  public final void delete() throws IOException {
    if (!delegate.delete()) {
      throw new IOException("delete operation failed");
    }
  }

  @Override
  public final void deleteContents() throws IOException {
    File[] files;
    files = delegate.listFiles();

    if (files == null) {
      return;
    }

    for (File file : files) {
      deleteContents0(file);

      file.delete();
    }
  }

  @Override
  public final boolean exists() {
    return delegate.exists();
  }

  @Override
  public final long getLastModifiedMillis() {
    return delegate.lastModified();
  }

  @Override
  public final String getName() {
    return delegate.getName();
  }

  @Override
  public final boolean is(RegularFileIsOption option) throws IOException {
    Check.notNull(option, "option == null");

    return option.is(delegate);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final InputStream openInputStream() throws IOException {
    return new FileInputStream(delegate);
  }

  @Override
  public final OutputStream openOutputStream() throws IOException {
    return newFileOutputStream();
  }

  @Override
  public final FileChannel openReadAndWriteChannel() throws IOException {
    delegate.createNewFile();

    return ComunsIoJava6.openReadAndWriteChannel(delegate);
  }

  @SuppressWarnings("resource")
  @Override
  public final FileChannel openReadChannel() throws IOException {
    FileInputStream stream;
    stream = new FileInputStream(delegate);

    return stream.getChannel();
  }

  @Override
  public final Reader openReader(Charset charset) throws IOException {
    InputStream stream;
    stream = openInputStream();

    InputStreamReader reader;
    reader = new InputStreamReader(stream, charset);

    return new BufferedReader(reader);
  }

  @Override
  public final FileChannel openWriteChannel() throws IOException {
    FileOutputStream stream;
    stream = newFileOutputStream();

    return stream.getChannel();
  }

  @Override
  public final Writer openWriter(Charset charset) throws IOException {
    OutputStream stream;
    stream = openOutputStream();

    OutputStreamWriter writer;
    writer = new OutputStreamWriter(stream, charset);

    return new BufferedWriter(writer);
  }

  @Override
  public final long size() throws IOException {
    long length;
    length = delegate.length();

    if (length != 0L) {
      return length;
    }

    if (delegate.isFile()) {
      return length;
    }

    String message;
    message = delegate.toString();

    throw new NotRegularFileException(message);
  }

  @Override
  public final File toFile() {
    return delegate;
  }

  @Override
  public final URI toUri() {
    if (uri == null) {
      URI source;
      source = delegate.toURI();

      uri = ComunsIoJava6.toUri(source);
    }

    return uri;
  }

  @Override
  public final void visitContents(DirectoryContentsVisitor visitor) throws IOException {
    Check.notNull(visitor, "visitor == null");

    File[] files;
    files = delegate.listFiles();

    if (files == null) {
      return;
    }

    for (File f : files) {
      ObjectJavaAny o;
      o = newObject(f);

      o.acceptPathNameVisitor(DirectoryContentsVisitorAdapter.INSTANCE, visitor);
    }
  }

  @Override
  final boolean equals0(ObjectJavaAny that) {
    return that instanceof ObjectJava6 && equalsImpl((ObjectJava6) that);
  }

  @Override
  final File getDelegate() {
    return delegate;
  }

  @Override
  final Directory getParent0() throws NotFoundException {
    File parentFile;
    parentFile = delegate.getParentFile();

    if (parentFile == null) {
      throw new NotFoundException("No parent directory for " + delegate);
    }

    return newObject(parentFile);
  }

  @Override
  final ObjectJavaAny resolve0(String firstName, String[] more) {
    Check.notNull(firstName, "firstName == null");

    StringBuilder b;
    b = new StringBuilder(firstName);

    for (int i = 0; i < more.length; i++) {
      b.append(File.separatorChar);

      String part;
      part = more[i];

      b.append(
          Check.notNull(part, "more[", i, "] == null")
      );
    }

    String pathName;
    pathName = b.toString();

    if (pathName.isEmpty()) {
      return this;
    }

    File file;
    file = new File(pathName);

    Check.argument(!file.isAbsolute(), "pathName is absolute");

    URI uri;
    uri = toUri();

    URI resolved;
    resolved = uri.resolve(pathName);

    resolved = resolved.normalize();

    File candidate;
    candidate = new File(resolved);

    String candidatePath;
    candidatePath = candidate.getAbsolutePath();

    String parentPath;
    parentPath = delegate.getAbsolutePath();

    boolean isDescendant;
    isDescendant = candidatePath.startsWith(parentPath + File.separatorChar);

    Check.argument(isDescendant, "pathName is not a descendant");

    return newObject(candidate);
  }

  @Override
  final ObjectJavaAny resolveChild0(String value, String variableName) {
    Check.notNull(value, variableName, " == null");

    Check.argument(!value.equals(""), variableName, " is empty");

    File path;
    path = new File(value);

    Check.argument(!path.isAbsolute(), variableName, " is absolute");

    File candidate;
    candidate = new File(delegate, value);

    File parentFile;
    parentFile = candidate.getParentFile();

    Check.argument(
        delegate.equals(parentFile),
        variableName, " does not resolve to a child of this directory"
    );

    return newObject(candidate);
  }

  private void checkRegularFileDoesNotExist(File file) throws FoundException {
    if (file.exists()) {
      String message;
      message = file.toString();

      throw new FoundException(message);
    }
  }

  private void deleteContents0(File file) {
    File[] files;
    files = file.listFiles();

    if (files == null) {
      return;
    }

    for (File f : files) {
      if (f.isDirectory()) {
        deleteContents0(f);
      }

      f.delete();
    }
  }

  private boolean equalsImpl(ObjectJava6 that) {
    File thisFile;
    thisFile = delegate;

    File thatFile;
    thatFile = that.delegate;

    URI thisUri;
    thisUri = thisFile.toURI().normalize();

    URI thatUri;
    thatUri = thatFile.toURI().normalize();

    return thisUri.equals(thatUri);
  }

  private FileOutputStream newFileOutputStream() throws IOException, FileNotFoundException {
    delegate.createNewFile();

    return new FileOutputStream(delegate, true);
  }

  private ObjectJavaAny newObject(File file) {
    return new ObjectImpl(file);
  }

}