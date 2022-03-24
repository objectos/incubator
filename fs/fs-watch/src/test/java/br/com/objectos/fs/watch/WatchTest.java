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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.core.io.Write;
import br.com.objectos.core.service.Service;
import br.com.objectos.core.service.Services;
import br.com.objectos.core.set.Sets;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.PathName;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.random.testing.Next;
import java.io.IOException;
import java.util.Set;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WatchTest implements Watch.Listener {

  private final Set<PathName> createdObjects = Sets.newHashSet();

  private final Set<PathName> deletedObjects = Sets.newHashSet();

  private final Set<PathName> modifiedObjects = Sets.newHashSet();

  private Service watchService;

  private Directory watchServiceCreated;

  @BeforeClass
  public void _beforeClass() throws Exception {
    watchServiceCreated = TmpDir.create();

    watchService = Watch.createService(
        Watch.watchDirectory(
            watchServiceCreated,

            this,

            Watch.CREATED
        )
    );

    Services.start(
        watchService
    );
  }

  @BeforeMethod
  public void _beforeMethod() {
    createdObjects.clear();

    deletedObjects.clear();

    modifiedObjects.clear();
  }

  @Override
  public final void onDirectoryCreated(Directory directory) {
    createdObjects.add(directory);

    doNotify();
  }

  @Override
  public final void onDirectoryModified(Directory directory) {
    modifiedObjects.add(directory);

    doNotify();
  }

  @Override
  public final void onNotFoundDeleted(ResolvedPath notFound) {
    deletedObjects.add(notFound);

    doNotify();
  }

  @Override
  public final void onRegularFileCreated(RegularFile file) {
    createdObjects.add(file);

    doNotify();
  }

  @Override
  public final void onRegularFileModified(RegularFile file) {
    modifiedObjects.add(file);

    doNotify();
  }

  /*
  
  @startmindmap

  *_ Test cases
  
  **:**Test case 01**
  ----
  WatchDirectory use-case:
  - single directory
  - single event (reg. file created)
  ----
  # watch directory (reg. file created)
  # create file
  # check listener notified
  # change file
  # check listener not notified
  # delete file
  # check listener not notified;
  *** WatchService
  
  @endmindmap

  */

  @Test
  public void testCase01() throws InterruptedException, IOException {
    assertEquals(createdObjects.size(), 0);

    assertEquals(modifiedObjects.size(), 0);

    RegularFile subjectFile;
    subjectFile = watchServiceCreated.createRegularFile("subjectFile");

    doWait();

    assertEquals(createdObjects.size(), 1);

    assertEquals(modifiedObjects.size(), 0);

    assertTrue(createdObjects.contains(subjectFile));

    byte[] randomBytes;
    randomBytes = Next.bytes(1 << 16);

    Write.byteArray(subjectFile, randomBytes);

    Thread.sleep(10);

    assertEquals(modifiedObjects.size(), 0);
  }

  private void doNotify() {
    try {
      Thread.sleep(10);

      synchronized (this) {
        notifyAll();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void doWait() throws InterruptedException {
    synchronized (this) {
      wait();
    }
  }

}