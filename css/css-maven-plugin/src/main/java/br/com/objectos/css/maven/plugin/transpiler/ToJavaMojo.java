/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.maven.plugin.transpiler;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.css.processor.ToJavaFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import objectos.util.UnmodifiableList;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(
    name = "to-java",
    defaultPhase = LifecyclePhase.GENERATE_SOURCES
)
public class ToJavaMojo extends AbstractMojo {

  private class ThisCssFile extends ToJavaFile.CssFile {

    private final Path file;
    private final Path sourceDirectory;

    ThisCssFile(Path sourceDirectory, Path file) {
      this.sourceDirectory = sourceDirectory;
      this.file = file;
    }

    @Override
    protected final NamedClass className() {
      String fileName = file.getFileName().toString();
      Path packageAbsolutePath = file.getParent();
      Path packageAsPath = sourceDirectory.relativize(packageAbsolutePath);
      PackageName packageName
          = PackageName.named(UnmodifiableList.copyOf(packageAsPath).join("."));
      String simpleName = fileName.substring(0, fileName.length() - ".css".length());
      return packageName.nestedClass(simpleName);
    }

    @Override
    protected final InputStream openStream() throws IOException {
      return Files.newInputStream(file);
    }

  }

  private class ThisFileVisitor extends SimpleFileVisitor<Path> {

    private final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.css");
    private final Path sourceDirectoryPath;

    public ThisFileVisitor(Path sourceDirectoryPath) {
      this.sourceDirectoryPath = sourceDirectoryPath;
    }

    @Override
    public final FileVisitResult visitFile(
        Path file, BasicFileAttributes attrs)
        throws IOException {

      if (pathMatcher.matches(file)) {
        executeFile(file);
      }

      return FileVisitResult.CONTINUE;
    }

    private void executeFile(Path file) throws IOException {
      ThisCssFile cssFile;
      cssFile = new ThisCssFile(sourceDirectoryPath, file);

      JavaFile javaFile;
      javaFile = toJavaFile.generate(cssFile);

      javaFile.writeTo(sourceDirectoryPath);
    }

  }

  @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true, readonly = true)
  private File sourceDirectory;

  private final ToJavaFile toJavaFile = ToJavaFile.generatedBy(getClass());

  @Override
  public final void execute() throws MojoExecutionException, MojoFailureException {
    try {
      Path sourceDirectoryPath = sourceDirectory.toPath();
      Files.walkFileTree(sourceDirectoryPath, new ThisFileVisitor(sourceDirectoryPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
