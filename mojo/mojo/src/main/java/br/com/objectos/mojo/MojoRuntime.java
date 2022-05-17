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
package br.com.objectos.mojo;

import br.com.objectos.code.annotations.Ignore;
import br.com.objectos.core.list.Lists;
import br.com.objectos.fs.Directory;
import java.io.Closeable;
import java.io.File;
import java.util.List;
import objectos.lang.Checks;
import org.apache.maven.Maven;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.bridge.MavenRepositorySystem;
import org.apache.maven.building.FileSource;
import org.apache.maven.cli.MavenCli;
import org.apache.maven.cli.event.ExecutionEventLogger;
import org.apache.maven.cli.transfer.BatchModeMavenTransferListener;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequestPopulationException;
import org.apache.maven.execution.MavenExecutionRequestPopulator;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.settings.Mirror;
import org.apache.maven.toolchain.building.DefaultToolchainsBuildingRequest;
import org.apache.maven.toolchain.building.ToolchainsBuilder;
import org.apache.maven.toolchain.building.ToolchainsBuildingException;
import org.apache.maven.toolchain.building.ToolchainsBuildingResult;
import org.apache.maven.toolchain.model.PersistedToolchains;
import org.codehaus.plexus.DefaultContainerConfiguration;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.MutablePlexusContainer;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.classworlds.ClassWorld;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.eclipse.aether.transfer.TransferListener;

// @NotThreadSafe
public class MojoRuntime implements Closeable {

  private final MutablePlexusContainer container;

  private final File localRepository;

  private final BuildLogger logger = new BuildLogger();

  private final List<Mirror> mirrors;

  private MojoRuntime(Builder builder) throws MojoException {
    container = builder.getContainer();

    container.setLoggerManager(logger);

    localRepository = builder.localRepository;

    mirrors = builder.mirrors;
  }

  @Ignore
  public static Builder builder() {
    return new Builder();
  }

  @Ignore
  public static MojoRuntime create() throws MojoException {
    Builder b;
    b = builder();

    return b.build();
  }

  public static MojoRuntime runtime(
      MojoRuntimeElement e1) throws MojoException {
    Checks.checkNotNull(e1, "e1 == null");

    Builder b;
    b = builder();

    e1.acceptMojoRuntimeBuilder(b);

    return b.build();
  }

  public static MojoRuntime runtime(
      MojoRuntimeElement e1,
      MojoRuntimeElement e2) throws MojoException {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");

    Builder b;
    b = builder();

    e1.acceptMojoRuntimeBuilder(b);
    e2.acceptMojoRuntimeBuilder(b);

    return b.build();
  }

  public static MojoRuntime runtime(
      MojoRuntimeElement e1,
      MojoRuntimeElement e2,
      MojoRuntimeElement e3) throws MojoException {
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    Checks.checkNotNull(e3, "e3 == null");

    Builder b;
    b = builder();

    e1.acceptMojoRuntimeBuilder(b);
    e2.acceptMojoRuntimeBuilder(b);
    e3.acceptMojoRuntimeBuilder(b);

    return b.build();
  }

  @Override
  public final void close() {
    container.dispose();
  }

  public final Result execute(Request request) throws MojoException {
    DefaultMavenExecutionRequest execution;
    execution = new DefaultMavenExecutionRequest();

    request.acceptMavenExecutionRequest(execution);

    configureToolchains(execution);

    MavenExecutionRequestPopulator populator;
    populator = lookup(MavenExecutionRequestPopulator.class);

    try {
      populator.populateDefaults(execution);
    } catch (MavenExecutionRequestPopulationException e) {
      throw new MojoException(e);
    }

    if (localRepository != null) {
      File currentLocalRepository;
      currentLocalRepository = execution.getLocalRepositoryPath();

      if (currentLocalRepository == null) {
        execution.setLocalRepositoryPath(localRepository);
      }
    }

    execution.setMirrors(mirrors);

    BuildLogger logger;
    logger = new BuildLogger();

    ExecutionEventLogger executionEventLogger;
    executionEventLogger = new ExecutionEventLogger(logger);

    execution.setExecutionListener(executionEventLogger);

    TransferListener transferListener;
    transferListener = new BatchModeMavenTransferListener(logger);

    execution.setTransferListener(transferListener);

    try {
      return execute0(execution, logger);
    } finally {
      logger.close();
    }
  }

  public final Result mvn(
      Directory basedir,
      MvnOption opt1) throws MojoException {
    Checks.checkNotNull(basedir, "basedir == null");
    Checks.checkNotNull(opt1, "opt1 == null");

    MvnRequest request;
    request = mvn0(basedir);

    opt1.acceptMvnRequest(request);

    return mvn1(request);
  }

  public final Result mvn(
      Directory basedir,
      MvnOption opt1,
      MvnOption opt2) throws MojoException {
    Checks.checkNotNull(basedir, "basedir == null");
    Checks.checkNotNull(opt1, "opt1 == null");
    Checks.checkNotNull(opt2, "opt2 == null");

    MvnRequest request;
    request = mvn0(basedir);

    opt1.acceptMvnRequest(request);
    opt2.acceptMvnRequest(request);

    return mvn1(request);
  }

  public final Result mvn(
      Directory basedir,
      MvnOption opt1,
      MvnOption opt2,
      MvnOption opt3) throws MojoException {
    Checks.checkNotNull(basedir, "basedir == null");
    Checks.checkNotNull(opt1, "opt1 == null");
    Checks.checkNotNull(opt2, "opt2 == null");
    Checks.checkNotNull(opt3, "opt3 == null");

    MvnRequest request;
    request = mvn0(basedir);

    opt1.acceptMvnRequest(request);
    opt2.acceptMvnRequest(request);
    opt3.acceptMvnRequest(request);

    return mvn1(request);
  }

  public final Request newRequest() {
    return new BuildRequest();
  }

  final <T> T lookup(Class<T> role) throws MojoException {
    try {
      return container.lookup(role);
    } catch (ComponentLookupException e) {
      throw new MojoException(e);
    }
  }

  private void configureToolchains(MavenExecutionRequest request) throws MojoException {
    File userToolchainsFile;
    userToolchainsFile = MavenCli.DEFAULT_USER_TOOLCHAINS_FILE;

    if (!userToolchainsFile.isFile()) {
      return;
    }

    request.setUserToolchainsFile(userToolchainsFile);

    DefaultToolchainsBuildingRequest toolchainsRequest;
    toolchainsRequest = new DefaultToolchainsBuildingRequest();

    toolchainsRequest.setUserToolchainsSource(new FileSource(userToolchainsFile));

    ToolchainsBuilder toolchainsBuilder;
    toolchainsBuilder = lookup(ToolchainsBuilder.class);

    ToolchainsBuildingResult toolchainsResult;

    try {
      toolchainsResult = toolchainsBuilder.build(toolchainsRequest);
    } catch (ToolchainsBuildingException e) {
      throw new MojoException(e);
    }

    MavenExecutionRequestPopulator executionRequestPopulator;
    executionRequestPopulator = lookup(MavenExecutionRequestPopulator.class);

    PersistedToolchains effectiveToolchains;
    effectiveToolchains = toolchainsResult.getEffectiveToolchains();

    try {
      executionRequestPopulator.populateFromToolchains(request, effectiveToolchains);
    } catch (MavenExecutionRequestPopulationException e) {
      throw new MojoException(e);
    }
  }

  private BuildResult execute0(MavenExecutionRequest execution, BuildLogger logger)
      throws MojoException {
    MavenExecutionResult result;

    Maven maven;
    maven = lookup(Maven.class);

    result = maven.execute(execution);

    BuildLog log;
    log = logger.build();

    return new BuildResult(result, log);
  }

  private MvnRequest mvn0(Directory basedir) {
    MvnRequest request;
    request = new MvnRequest();

    request.setBasedir(basedir);

    return request;
  }

  private Result mvn1(MvnRequest mvnRequest) throws MojoException {
    DefaultMavenExecutionRequest executionRequest;
    executionRequest = new DefaultMavenExecutionRequest();

    mvnRequest.acceptMavenExecutionRequest(executionRequest);

    MavenExecutionRequestPopulator populator;
    populator = lookup(MavenExecutionRequestPopulator.class);

    try {
      populator.populateDefaults(executionRequest);
    } catch (MavenExecutionRequestPopulationException e) {
      throw new MojoException("Failed to populate default values", e);
    }

    if (localRepository != null) {
      MavenRepositorySystem repositorySystem;
      repositorySystem = lookup(MavenRepositorySystem.class);

      ArtifactRepository repo;

      try {
        repo = repositorySystem.createLocalRepository(executionRequest, localRepository);
      } catch (Exception e) {
        throw new MojoException("Failed to create local repository", e);
      }

      executionRequest.setLocalRepository(repo);
    }

    executionRequest.setMirrors(mirrors);

    ExecutionEventLogger executionEventLogger;
    executionEventLogger = new ExecutionEventLogger(logger);

    executionRequest.setExecutionListener(executionEventLogger);

    TransferListener transferListener;
    transferListener = new BatchModeMavenTransferListener(logger);

    executionRequest.setTransferListener(transferListener);

    MavenExecutionResult result;

    container.setLoggerManager(logger);

    Maven maven;
    maven = lookup(Maven.class);

    result = maven.execute(executionRequest);

    BuildLog log;
    log = logger.build();

    return new BuildResult(result, log);
  }

  public static class Builder {

    private File localRepository;

    private final List<Mirror> mirrors = Lists.newArrayList();

    private Builder() {}

    public final MojoRuntime build() throws MojoException {
      return new MojoRuntime(this);
    }

    final void addMirror(Mirror mirror) {
      mirrors.add(mirror);
    }

    final MutablePlexusContainer getContainer() throws MojoException {
      DefaultContainerConfiguration configuration;
      configuration = new DefaultContainerConfiguration();

      configuration.setAutoWiring(true);

      configuration.setClassPathScanning(PlexusConstants.SCANNING_INDEX);

      Thread currentThread;
      currentThread = Thread.currentThread();

      ClassLoader contextClassLoader;
      contextClassLoader = currentThread.getContextClassLoader();

      ClassWorld classWorld;
      classWorld = new ClassWorld("plexus.core", contextClassLoader);

      configuration.setClassWorld(classWorld);

      configuration.setJSR250Lifecycle(true);

      configuration.setName("maven");

      try {
        return new DefaultPlexusContainer(configuration);
      } catch (PlexusContainerException e) {
        throw new MojoException("Failed to create PlexusContainer instance", e);
      }
    }

    final void setLocalRepository(Directory directory) {
      localRepository = directory.toFile();
    }

  }

}
