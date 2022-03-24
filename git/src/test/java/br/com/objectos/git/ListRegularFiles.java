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

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.Lists;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.DirectoryContentsVisitor;
import br.com.objectos.fs.RegularFile;
import java.io.File;
import java.io.IOException;

/**
 * A {@link DirectoryContentsVisitor} for recursively listing all of the regular
 * files in a directory.
 */
public final class ListRegularFiles implements DirectoryContentsVisitor {

  private final MutableList<String> leafs = MutableList.create();

  private String prefix = "";

  private ListRegularFiles() {}

  /**
   * Recursively lists the relative pathnames of all (regular) files of the
   * specified directory; the result is both sorted and immutable.
   *
   * @param directory
   *        the directory to list
   *
   * @return sorted immutable list of all files
   *
   * @throws IOException
   *         if an I/O error occurs
   */
  public static ImmutableList<String> of(Directory directory) throws IOException {
    Checks.checkNotNull(directory, "directory == null");

    ListRegularFiles ls;
    ls = new ListRegularFiles();

    directory.visitContents(ls);

    return ls.build();
  }

  @Override
  public final void visitDirectory(Directory directory) throws IOException {
    String previousPrefix;
    previousPrefix = prefix;

    String directoryName;
    directoryName = directory.getName();

    prefix = prefix + directoryName;

    prefix = prefix + File.separatorChar;

    directory.visitContents(this);

    prefix = previousPrefix;
  }

  @Override
  public final void visitRegularFile(RegularFile file) throws IOException {
    String leaf;
    leaf = prefix + file.getName();

    leafs.add(leaf);
  }

  final ImmutableList<String> build() throws IOException {
    leafs.sort(Lists.naturalOrder());

    return leafs.toImmutableList();
  }

}