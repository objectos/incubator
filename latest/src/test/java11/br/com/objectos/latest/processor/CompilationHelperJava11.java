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
package br.com.objectos.latest.processor;

import java.lang.module.Configuration;
import java.lang.module.ModuleReference;
import java.lang.module.ResolvedModule;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.tools.JavaCompiler.CompilationTask;

class CompilationHelperJava11 implements CompilationHelper {

  private static volatile List<ThisModule> modules;

  private static final String PATH_SEPARATOR = System.getProperty("path.separator");

  CompilationHelperJava11() {}

  @Override
  public final void configureModulesIfPossible(CompilationTask task) {
    List<ThisModule> modules;
    modules = getModules();

    if (modules.isEmpty()) {
      return;
    }

    List<String> names;
    names = new ArrayList<>();

    for (int i = 0; i < modules.size(); i++) {
      ThisModule module;
      module = modules.get(i);

      names.add(module.name);
    }

    task.addModules(names);
  }

  @Override
  public final Iterable<String> getOptions() {
    List<ThisModule> modules;
    modules = getModules();

    if (modules.isEmpty()) {
      return Collections.emptySet();
    }

    StringBuilder paths;
    paths = new StringBuilder();

    ThisModule first;
    first = modules.get(0);

    first.appendPaths(paths);

    for (int i = 1; i < modules.size(); i++) {
      paths.append(PATH_SEPARATOR);

      ThisModule next;
      next = modules.get(i);

      next.appendPaths(paths);
    }

    String modulePath;
    modulePath = paths.toString();

    return List.of("--module-path", modulePath);
  }

  private List<ThisModule> getModules() {
    if (modules == null) {
      modules = getModules0();
    }

    return modules;
  }

  private List<ThisModule> getModules0() {
    List<ThisModule> result;
    result = new ArrayList<>();

    ModuleLayer boot;
    boot = ModuleLayer.boot();

    Configuration bootConfig;
    bootConfig = boot.configuration();

    Set<Module> bootModules = boot.modules();

    for (Module bootModule : bootModules) {
      String name = bootModule.getName();

      if (name.startsWith("java.")) {
        continue;
      }

      if (name.startsWith("jdk.")) {
        continue;
      }

      Optional<ResolvedModule> maybeResolved;
      maybeResolved = bootConfig.findModule(name);

      ResolvedModule resolved;
      resolved = maybeResolved.orElseThrow();

      ModuleReference resolvedReference;
      resolvedReference = resolved.reference();

      Optional<URI> maybeLocation;
      maybeLocation = resolvedReference.location();

      URI location;
      location = maybeLocation.orElseThrow();

      Path path = Path.of(location);

      ThisModule module;
      module = new ThisModule(name, path.toString());

      result.add(module);
    }

    return result;
  }

  private static class ThisModule {

    final String name;
    final String path;

    ThisModule(String name, String path) {
      this.name = name;
      this.path = path;
    }

    public final void appendPaths(StringBuilder paths) {
      paths.append(path);
    }

    @Override
    public final String toString() {
      return "Module{" + name + '@' + path + "}";
    }

  }

}
