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
package br.com.objectos.fs.watch;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.fs.watch.Watch.Listener;

/**
 * A convenience implementation of the {@link Listener Watch.Listener}
 * interface where all methods perform no actions.
 *
 * @since 2
 */
public class SimpleWatchListener implements Watch.Listener {

  @Override
  public void onDirectoryCreated(Directory directory) {}

  @Override
  public void onDirectoryModified(Directory directory) {}

  @Override
  public void onNotFoundDeleted(ResolvedPath notFound) {}

  @Override
  public void onRegularFileCreated(RegularFile file) {}

  @Override
  public void onRegularFileModified(RegularFile file) {}

}