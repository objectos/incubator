/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.fs.watch;

import br.com.objectos.core.service.Services;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;

/**
 * Provides {@code static} methods for configurating and creating a file system
 * watch service.
 *
 * <p>
 * For example, a crude service that uploads to a remote location any file
 * created in a directory could be implemented like this:
 *
 * <pre>
 *     Watch.Listener listener = new SimpleWatchListener() {
 *       final Uploaded uploader = createUploader();
 *
 *       &#64;Override
 *       public final void onRegularFileCreated(RegularFile file) {
 *         // uploader must handle any exception
 *         uploader.upload(file);
 *       }
 *     };
 *
 *     Watch.Service service = Watch.createService(
 *         Watch.watchDirectory(
 *             LocalFs.getDirectory("/srv/upload"),
 *             listener,
 *             Watch.CREATED
 *         )
 *     );
 *
 *     Services.start(service);
 * </pre>
 *
 * @since 2
 */
public final class Watch {

  /**
   * The {@link Event#CREATED} constant (provided for convenience).
   */
  public static final Event CREATED = Event.CREATED;

  /**
   * The {@link Event#DELETED} constant (provided for convenience).
   */
  public static final Event DELETED = Event.DELETED;

  /**
   * The {@link Event#MODIFIED} constant (provided for convenience).
   */
  public static final Event MODIFIED = Event.MODIFIED;

  private static final Factory FACTORY = new Factory();

  private Watch() {}

  /**
   * Creates and returns a new watch service with the specified options.
   *
   * <p>
   * Once created, watch service instance cannot be modified. Therefore, all
   * registrations must be supplied at creation time.
   *
   * @param options
   *        the options to use for creating the watch servcie
   *
   * @return a newly created watch service with the specified options
   *
   * @throws IOException
   *         if an I/O error occurs
   */
  public static Service createService(Option... options) throws IOException {
    return FACTORY.create(options);
  }

  /**
   * Returns a new configuration option to watch the specified directory with
   * the specified listener for the specified event.
   *
   * @param directory
   *        the directory to watch for events
   * @param listener
   *        the listener instance that will act upon the watched events
   * @param event
   *        the event to watch for
   *
   * @return a new configuration option to use with the
   *         {@link #createService(Option...)} service factory method
   */
  public static Option watchDirectory(
      final Directory directory,
      final Listener listener,
      final Event event) {
    return new Option() {
      @Override
      public final void acceptWatchServiceBuilder(Watch.ServiceBuilder builder) {
        builder.watchDirectory(directory, listener, event);
      }
    };
  }

  /**
   * The events available for watching.
   */
  public enum Event {

    /**
     * A directory entry is created.
     */
    CREATED,

    /**
     * A directory entry is deleted.
     */
    DELETED,

    /**
     * A directory entry is modified.
     */
    MODIFIED;

    /**
     * Returns {@code true} if this is the {@link #CREATED} value.
     *
     * @return {@code true} if this is the {@link #CREATED} value.
     */
    public final boolean isCreated() {
      return this == CREATED;
    }

    /**
     * Returns {@code true} if this is the {@link #DELETED} value.
     *
     * @return {@code true} if this is the {@link #DELETED} value.
     */
    public final boolean isDeleted() {
      return this == DELETED;
    }

    /**
     * Returns {@code true} if this is the {@link #MODIFIED} value.
     *
     * @return {@code true} if this is the {@link #MODIFIED} value.
     */
    public final boolean isModified() {
      return this == MODIFIED;
    }

  }

  /**
   * A listener for previously registered file system events.
   */
  public interface Listener {

    /**
     * Called when a directory is created.
     *
     * @param directory
     *        the directory which has been created
     */
    void onDirectoryCreated(Directory directory);

    /**
     * Called when a directory is modified. A directory is considered to be
     * <i>modified</i> when any children of the directory are created,
     * deleted or have any of their attributes altered.
     *
     * @param directory
     *        the directory which has been modified
     */
    void onDirectoryModified(Directory directory);

    /**
     * Called when a file system object of a directory is deleted.
     *
     * @param notFound
     *        an object representing the pathname of the file system object
     *        which has been deleted
     */
    void onNotFoundDeleted(ResolvedPath notFound);

    /**
     * Called when a regular file is created.
     *
     * @param file
     *        the file which has been created
     */
    void onRegularFileCreated(RegularFile file);

    /**
     * Called when a regular file is modified.
     *
     * @param file
     *        the file which has been modified
     */
    void onRegularFileModified(RegularFile file);

  }

  /**
   * Represents an option for configuring a watch service.
   */
  public interface Option {

    /**
     * Configures the specified watch service builder.
     *
     * @param builder
     *        a watch service builder
     *
     * @throws IOException
     *         if an I/O error occurs
     */
    void acceptWatchServiceBuilder(Watch.ServiceBuilder builder) throws IOException;

  }

  /**
   * The watch service interface, allows for starting and stopping the watch
   * service.
   *
   * <p>
   * Once created, this instance cannot be changed. Configuration is done at
   * creation time.
   *
   * <p>
   * Instances of this interface can be used with the {@link Services} class for
   * automatically registering with a JVM shutdown hook at start time.
   *
   * @see Services#start(br.com.objectos.core.service.Service...)
   */
  public interface Service extends br.com.objectos.core.service.Service {

    /**
     * Starts this watch service.
     *
     * @throws IOException
     *         if an I/O error occurs
     */
    @Override
    void startService() throws IOException;

    /**
     * Stops this watch service.
     *
     * @throws IOException
     *         if an I/O error occurs
     */
    @Override
    void stopService() throws IOException;

  }

  /**
   * A façade for configuring a watch service builder.
   */
  public interface ServiceBuilder {

    /**
     * Instructs this watch service to watch the specified directory with the
     * specified listener for the specified event.
     *
     * @param directory
     *        the directory to watch for events
     * @param listener
     *        the listener instance that will act upon the watched events
     * @param event
     *        the event to watch for
     */
    void watchDirectory(Directory directory, Listener listener, Event event);

  }

}
