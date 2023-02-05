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
package br.com.objectos.logger;

import objectos.lang.Level;

final class MoreLogging {

  /*

  @startuml

  ' config

  hide empty members
  ' left to right direction
  skinparam genericDisplay old
  ' skinparam monochrome true
  skinparam shadowing false
  ' skinparam style strictuml

  class BootstrapLogger {
    + StorageLogger\lbootstrap(\lStorageLogger l)
  }

  interface Log {
  }

  class MoreLogging {
    + {static} BootstrapLogger\lcreateBootstrapLogger()
    + {static} Storage\lcreateStorage(\lDirectory directory)
    + {static} Storage\lopenStorage(\lDirectory directory)
  }

  interface ProcessingContext {
    + void submit(IoTask task)
  }

  interface Processor {
    + Job\lprocess(\lProcessingContext c,\lLog l)
  }

  abstract class Storage {
    + StorageLogger\lcreateLogger(\lIoExecutor ioWorker,\lWorker worker,\lProcessor... processors)
  }

  ' rels

  MoreLogging -[hidden]d- BootstrapLogger

  BootstrapLogger -[hidden]d- Storage

  @enduml

   */

  static final String LINE_SEPARATOR = System.lineSeparator();

  private static final Level[] LEVELS = Level.values();

  private MoreLogging() {}

  public static Layout createStandardLayout() {
    return new StandardLayout();
  }

  static Level parseLevel(byte index) {
    return LEVELS[index];
  }

  static byte[] toMediumByteArray(int maxValue) {
    byte[] result;
    result = new byte[3];

    result[0] = (byte) (maxValue >>> 16);
    result[1] = (byte) (maxValue >>> 8);
    result[2] = (byte) (maxValue >>> 0);

    return result;
  }

  static int toMediumInt(byte b0, byte b1, byte b2) {
    int result = 0;

    result += b0 & 0xFF;

    result = result << 8;

    result += b1 & 0xFF;

    result = result << 8;

    result += b2 & 0xFF;

    return result;
  }

}
