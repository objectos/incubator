/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.mojo;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import java.io.IOException;
import java.util.List;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.model.Build;
import org.apache.maven.project.MavenProject;

final class BuildResult implements Result {

  private final BuildLog log;

  private final MavenExecutionResult result;

  BuildResult(MavenExecutionResult result, BuildLog log) {
    this.result = result;
    this.log = log;
  }

  @Override
  public final Directory getDirectory() throws IOException {
    String directory;
    directory = build().getDirectory();

    return LocalFs.getDirectory(directory);
  }

  @Override
  public final Log getLog() {
    return log;
  }

  @Override
  public final Directory getOutputDirectory() throws IOException {
    String outputDirectory;
    outputDirectory = build().getOutputDirectory();

    return LocalFs.getDirectory(outputDirectory);
  }

  @Override
  public final boolean isFailed() {
    return result.hasExceptions();
  }

  @Override
  public final boolean isSuccess() {
    return !isFailed();
  }

  @Override
  public final void throwExceptionIfFailed() {
    if (result.hasExceptions()) {
      List<Throwable> exceptions = result.getExceptions();
      throw new RuntimeException(exceptions.get(0));
    }
  }

  private Build build() {
    return project().getBuild();
  }

  private MavenProject project() {
    return result.getProject();
  }

}
