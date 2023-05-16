/*
 * Copyright 2023 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
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