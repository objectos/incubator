/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.office.internal;

import br.com.objectos.core.list.Lists;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.JavaIoTmpdir;
import br.com.objectos.office.ServerStartException;
import com.sun.star.bridge.XUnoUrlResolver;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.connection.ConnectionSetupException;
import com.sun.star.connection.NoConnectException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import objectos.lang.Checks;
import objectos.lang.RandomString;

public class NativeProcess {

  private static final int MAX_TRIES = 200;

  private static final RandomString RANDOM = new RandomString();

  private static final long SLEEP_TIME = 300;

  private final ConnectString connectString;
  private Desktop desktop;
  private Process process;

  private final ProcessBuilder processBuilder;
  private final Directory workingDirectory;

  private NativeProcess(String executablePath,
                        ConnectString connectString,
                        Directory workingDirectory) {
    List<String> command;
    command = Lists.newArrayList();

    command.add(executablePath);
    command.add("--headless");
    command.add("--nolockcheck");
    command.add("--norestore");

    command.add(connectString.toAcceptOption());

    ProcessBuilder builder;
    builder = new ProcessBuilder(command);

    builder.directory(workingDirectory.toFile());

    this.processBuilder = builder;
    this.connectString = connectString;
    this.workingDirectory = workingDirectory;
  }

  public static NativeProcess create() throws IOException {
    String executablePath;
    executablePath = getDefaultExecutablePath();

    ConnectString connectString;
    connectString = getDefaultConnectString();

    Directory workingDirectory;
    workingDirectory = getDefaultWorkingDirectory();

    return new NativeProcess(executablePath, connectString, workingDirectory);
  }

  private static ConnectString getDefaultConnectString() {
    return PipeConnectString.getRandom();
  }

  private static String getDefaultExecutablePath() {
    return System.getProperty("objectos.office.executablePath", "soffice");
  }

  private static Directory getDefaultWorkingDirectory() throws IOException {
    Directory systemTempDirectory = JavaIoTmpdir.get();

    return systemTempDirectory.createDirectory(RANDOM.nextString(12));
  }

  public final Desktop getDesktop() {
    restartIfNecessary();
    return desktop;
  }

  public final Directory getWorkingDirectory() {
    return workingDirectory;
  }

  public final void restartIfNecessary() {
    checkStarted();

    try {
      int exitValue;
      exitValue = process.exitValue();

      throw new UnsupportedOperationException("Implement me: exitValue=" + exitValue);
    } catch (IllegalThreadStateException e) {
      // still running, no problem
      return;
    }
  }

  public final void start() throws ServerStartException {
    Checks.checkState(process == null, "process already started");

    synchronized (processBuilder) {
      try {
        start0();
      } catch (InterruptedException e) {
        Thread.interrupted();
        stop();
        throw new ServerStartException("Got interrupted while waiting to start process", e);
      } catch (ServerStartException e) {
        stop();
        throw e;
      }
    }
  }

  public final void stop() {
    if (desktop != null) {
      desktop.terminate();
    }

    if (process != null) {
      process.destroy();
    }

    try {
      workingDirectory.deleteContents();
    } catch (IOException e) {
      // noop
    }

    try {
      workingDirectory.delete();
    } catch (IOException e) {
      // noop
    }
  }

  private void checkStarted() {
    if (process == null) {
      throw new IllegalStateException("process was not started");
    }
  }

  private void start0() throws ServerStartException, InterruptedException {
    try {
      process = processBuilder.start();
    } catch (IOException e) {
      throw new ServerStartException("Could not start native process", e);
    }

    Map<String, Object> env;
    env = null;

    XComponentContext context;

    try {
      context = Bootstrap.createInitialComponentContext(env);
    } catch (Exception e) {
      throw new ServerStartException("Could not create initial component context", e);
    }

    XMultiComponentFactory serviceManager;
    serviceManager = context.getServiceManager();

    String urlResolverName;
    urlResolverName = "com.sun.star.bridge.UnoUrlResolver";

    Object _urlResolver;

    try {
      _urlResolver = serviceManager.createInstanceWithContext(urlResolverName, context);
    } catch (Exception e) {
      throw new ServerStartException("Could not create UnoUrlResolver instance", e);
    }

    XUnoUrlResolver urlResolver;
    urlResolver = UnoRuntime.queryInterface(XUnoUrlResolver.class, _urlResolver);

    String componentContextName;
    componentContextName = "StarOffice.ComponentContext";

    String componentContextUrl;
    componentContextUrl = connectString.getUnoUrl(componentContextName);

    Object _componentContext;
    _componentContext = null;

    int failedCount;
    failedCount = 0;

    while (failedCount < MAX_TRIES) {
      try {
        _componentContext = urlResolver.resolve(componentContextUrl);
        break;
      } catch (NoConnectException e) {
        failedCount++;
        Thread.sleep(SLEEP_TIME);
      } catch (IllegalArgumentException e) {
        throw new ServerStartException("Could not acquire StarOffice.ComponentContext instance",
          e);
      } catch (ConnectionSetupException e) {
        throw new ServerStartException("Could not acquire StarOffice.ComponentContext instance",
          e);
      }
    }

    if (_componentContext == null) {
      throw new ServerStartException("Could not acquire StarOffice.ComponentContext instance");
    }

    XComponentContext componentContext;
    componentContext = UnoRuntime.queryInterface(XComponentContext.class, _componentContext);

    try {
      desktop = Desktop.create(componentContext);
    } catch (com.sun.star.uno.Exception e) {
      throw new ServerStartException("Could create Desktop instance", e);
    }
  }

}