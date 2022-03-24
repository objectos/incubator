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
package br.com.objectos.fs.testing;

import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.runtime.ShutdownHook;
import br.com.objectos.core.runtime.ShutdownHookTask;
import br.com.objectos.core.throwable.Throwables;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.JavaIoTmpdir;
import br.com.objectos.random.testing.Next;
import java.io.IOException;

/**
 * A class that creates and manages temporary directories suitable for testing
 * environments. The temporary directories created by this class are
 * automatically removed during the JVM shutdown process.
 *
 * <p>
 * While this class is thread-safe, it was not designed with concurrency in
 * mind. In other words, concurrent invocations of the {@link #create()} method
 * might suffer from contention.
 */
public final class TmpDir implements ShutdownHookTask {

  static final TmpDir INSTANCE = createInstance();

  private final MutableList<Directory> directories = MutableList.create();

  private TmpDir() {}

  /**
   * Creates and returns a randomly named directory that is automatically
   * removed during the JVM shutdown process.
   *
   * <p>
   * The returned directory will be a direct child of the directory returned by
   * the {@link JavaIoTmpdir} class.
   *
   * @return a randomly named temporary directory
   *
   * @throws IOException
   *         if an I/O error occurs
   */
  public static Directory create() throws IOException {
    return INSTANCE.nextImpl();
  }

  private static TmpDir createInstance() {
    TmpDir instance;
    instance = new TmpDir();

    ShutdownHook.register(instance);

    return instance;
  }

  @Override
  public final void executeShutdownHookTask() throws Exception {
    Throwable rethrow;
    rethrow = null;

    for (int i = 0, size = directories.size(); i < size; i++) {
      Directory directory;
      directory = directories.get(i);

      try {
        directory.deleteContents();
      } catch (Exception e) {
        rethrow = Throwables.addSuppressed(rethrow, e);
      }

      try {
        directory.delete();
      } catch (Exception e) {
        rethrow = Throwables.addSuppressed(rethrow, e);
      }
    }

    if (rethrow != null) {
      throw (Exception) rethrow;
    }
  }

  final Directory nextImpl() throws IOException {
    Directory tempDirectory;
    tempDirectory = JavaIoTmpdir.get();

    String suffix;
    suffix = Next.string(10);

    Directory result;
    result = tempDirectory.createDirectory("fs-testing-" + suffix);

    synchronized (this) {
      directories.add(result);
    }

    return result;
  }

}