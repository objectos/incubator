/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.internal;

import java.nio.file.Path;
import java.util.function.Predicate;

final class DocsInternal extends Step3Generate {

  static final Predicate<Path> ALWAYS_TRUE = (p) -> true;

  Predicate<Path> fileFilter = ALWAYS_TRUE;

  @Override
  final boolean acceptRegularFile(Path file) {
    return fileFilter.test(file);
  }

  @Override
  final void acceptVersion(Version version) {
    super.acceptVersion(version);
  }

}