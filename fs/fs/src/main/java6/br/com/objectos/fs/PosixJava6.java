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

@Bridge
class PosixJava6 extends PosixJavaAny {

  @Override
  final PosixFileModeOption ownerExecutable0() {
    return OwnerExecutableJava6.INSTANCE;
  }

  @Override
  final PosixFileModeOption ownerReadable0() {
    return OwnerReadableJava6.INSTANCE;
  }

  @Override
  final PosixFileModeOption ownerWritable0() {
    return OwnerWritableJava6.INSTANCE;
  }

}
