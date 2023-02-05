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
package br.com.objectos.mojo.proper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import br.com.objectos.core.io.Resource;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.testing.TmpDir;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import objectos.lang.Check;
import org.apache.maven.Maven;
import org.apache.maven.artifact.InvalidRepositoryException;
import org.apache.maven.bridge.MavenRepositorySystem;
import org.apache.maven.cli.MavenCli;
import org.apache.maven.cli.event.ExecutionEventLogger;
import org.apache.maven.cli.logging.Slf4jLoggerManager;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequestPopulationException;
import org.apache.maven.execution.MavenExecutionRequestPopulator;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.SettingsUtils;
import org.apache.maven.settings.building.DefaultSettingsBuildingRequest;
import org.apache.maven.settings.building.SettingsBuilder;
import org.apache.maven.settings.building.SettingsBuildingException;
import org.apache.maven.settings.building.SettingsBuildingRequest;
import org.apache.maven.settings.building.SettingsBuildingResult;
import org.codehaus.plexus.ContainerConfiguration;
import org.codehaus.plexus.DefaultContainerConfiguration;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.classworlds.ClassWorld;
import org.codehaus.plexus.classworlds.realm.NoSuchRealmException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.util.Os;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MavenProperTest {

  private Directory workingDirectory;

  @AfterMethod(alwaysRun = true)
  public void cleanUp() throws IOException {
    if (workingDirectory != null) {
      workingDirectory.deleteContents();
      workingDirectory.delete();
    }
  }

  @Test(
      enabled = false,
      description = ""
          + "extracted from MavenCli debug."
          + "left for future reference: eg. settings extraction"
  )
  public void execute()
      throws IOException,
      PlexusContainerException,
      ComponentLookupException,
      NoSuchRealmException,
      MavenExecutionRequestPopulationException, SettingsBuildingException {

    PlexusContainer container = getContainer();

    RegularFile pom = project("mojo-invoker-it-02");

    DefaultMavenExecutionRequest request = new DefaultMavenExecutionRequest();
    request.setGoals(Arrays.asList("compile"));
    request.setOffline(true);
    request.setPom(pom.toFile());
    request.setMultiModuleProjectDirectory(workingDirectory.toFile());
    request.setExecutionListener(new ExecutionEventLogger());

    systemProperties(request);

    File globalSettingsFile = new File("settings.xml");
    File userSettingsFile = new File(System.getProperty("user.home"), ".m2/settings.xml");

    request.setGlobalSettingsFile(globalSettingsFile);
    request.setUserSettingsFile(userSettingsFile);

    SettingsBuildingRequest settingsRequest = new DefaultSettingsBuildingRequest();
    settingsRequest.setGlobalSettingsFile(globalSettingsFile);
    settingsRequest.setUserSettingsFile(userSettingsFile);

    SettingsBuilder settingsBuilder = container.lookup(SettingsBuilder.class);
    SettingsBuildingResult settingsResult = settingsBuilder.build(settingsRequest);

    populateFromSettings(request, settingsResult.getEffectiveSettings());

    MavenExecutionRequestPopulator populator
        = container.lookup(MavenExecutionRequestPopulator.class);
    populator.populateDefaults(request);

    Maven maven = container.lookup(Maven.class);

    MavenExecutionResult result = maven.execute(request);
    assertNotNull(result);

    if (result.hasExceptions()) {
      List<Throwable> exceptions = result.getExceptions();
      for (Throwable e : exceptions) {
        e.printStackTrace();
      }
      Assert.fail();
    }

    Directory target = workingDirectory.getDirectory("target");
    assertTrue(target.exists());

    Directory classes = target.getDirectory("classes");
    assertTrue(classes.exists());
    assertTrue(classes.getDirectory("testing").getRegularFile("Main.class").exists());

  }

  @Test(enabled = false)
  public void mavenCli() throws IOException {
    project("mojo-invoker-it-02");

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    ClassWorld classWorld = new ClassWorld("plexus.core", classLoader);

    System.setProperty("maven.multiModuleProjectDirectory", workingDirectory.toString());

    MavenCli cli = new MavenCli(classWorld);
    int result = cli.doMain(
      new String[] {"compile"},
      workingDirectory.toString(),
      System.out,
      System.err
    );
    assertEquals(result, 0);

    Directory target = workingDirectory.getDirectory("target");
    assertTrue(target.exists());

    Directory classes = target.getDirectory("classes");
    assertTrue(classes.exists());
    assertTrue(classes.getDirectory("testing").getRegularFile("Main.class").exists());
  }

  @BeforeMethod
  public void setUp() throws IOException {
    workingDirectory = TmpDir.create();
  }

  final void mirrors(DefaultMavenExecutionRequest request, Settings settings) {
    // <mirrors>
    // <mirror>
    // <id>nexus</id>
    // <mirrorOf>*</mirrorOf>
    // <url>http://repository.sonatype.org/content/groups/public</url>
    // </mirror>
    // </mirrors>

    for (Mirror mirror : settings.getMirrors()) {
      mirror = mirror.clone();
      request.addMirror(mirror);
    }
  }

  final void profiles(DefaultMavenExecutionRequest request, Settings settings) {
    request.setActiveProfiles(settings.getActiveProfiles());

    for (org.apache.maven.settings.Profile rawProfile : settings.getProfiles()) {
      request.addProfile(SettingsUtils.convertFromSettingsProfile(rawProfile));

      if (settings.getActiveProfiles().contains(rawProfile.getId())) {
        List<Repository> remoteRepositories = rawProfile.getRepositories();
        for (Repository remoteRepository : remoteRepositories) {
          try {
            request.addRemoteRepository(
              MavenRepositorySystem.buildArtifactRepository(remoteRepository));
          } catch (InvalidRepositoryException e) {
            // do nothing for now
          }
        }

        List<Repository> pluginRepositories = rawProfile.getPluginRepositories();
        for (Repository pluginRepository : pluginRepositories) {
          try {
            request.addPluginArtifactRepository(
              MavenRepositorySystem.buildArtifactRepository(pluginRepository));
          } catch (InvalidRepositoryException e) {
            // do nothing for now
          }
        }
      }
    }
  }

  final void proxies(DefaultMavenExecutionRequest request, Settings settings) {
    // <proxies>
    // <proxy>
    // <active>true</active>
    // <protocol>http</protocol>
    // <host>proxy.somewhere.com</host>
    // <port>8080</port>
    // <username>proxyuser</username>
    // <password>somepassword</password>
    // <nonProxyHosts>www.google.com|*.somewhere.com</nonProxyHosts>
    // </proxy>
    // </proxies>

    for (Proxy proxy : settings.getProxies()) {
      if (!proxy.isActive()) {
        continue;
      }

      proxy = proxy.clone();
      request.addProxy(proxy);
    }
  }

  final void servers(DefaultMavenExecutionRequest request, Settings settings) {
    for (Server server : settings.getServers()) {
      server = server.clone();
      request.addServer(server);
    }
  }

  final void systemProperties(DefaultMavenExecutionRequest request) {
    Properties systemProperties = new Properties();
    boolean caseSensitive = !Os.isFamily(Os.FAMILY_WINDOWS);
    for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
      String key
          = "env." + (caseSensitive ? entry.getKey() : entry.getKey().toUpperCase(Locale.ENGLISH));
      systemProperties.setProperty(key, entry.getValue());
    }
    systemProperties.putAll(System.getProperties());
    request.setSystemProperties(systemProperties);
  }

  private ClassWorld classWorld() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    return new ClassWorld("plexus.core", classLoader);
  }

  private PlexusContainer getContainer()
      throws PlexusContainerException, NoSuchRealmException {

    ClassWorld classWorld = classWorld();

    ContainerConfiguration configuration = new DefaultContainerConfiguration()
        .setClassWorld(classWorld)
        .setClassPathScanning(PlexusConstants.SCANNING_INDEX)
        .setAutoWiring(true)
        .setJSR250Lifecycle(true)
        .setName("maven");

    DefaultPlexusContainer container = new DefaultPlexusContainer(configuration);

    // use TCCL for lookups (?)
    // container.setLookupRealm(null);
    // Thread.currentThread().setContextClassLoader(container.getContainerRealm());

    container.setLoggerManager(new Slf4jLoggerManager());

    return container;
  }

  private void populateFromSettings(DefaultMavenExecutionRequest request, Settings settings) {
    request.setOffline(settings.isOffline());
    // request.setInteractiveMode(settings.isInteractiveMode());
    // request.setPluginGroups(settings.getPluginGroups());
    // request.setLocalRepositoryPath(settings.getLocalRepository());

    // servers(request, settings);
    // proxies(request, settings);
    // mirrors(request, settings);
    // profiles(request, settings);
  }

  private RegularFile project(String basedir) {
    Check.notNull(basedir, "basedir == null");

    String resourceName;
    resourceName = "TEST-INF/" + basedir + "/pom.xml";

    try {
      Resource resource;
      resource = Resource.getResource(resourceName);

      RegularFile file;
      file = LocalFs.getRegularFile(resource);

      Directory baseDirectory;
      baseDirectory = file.getParent();

      baseDirectory.copyTo(workingDirectory);

      return workingDirectory.getRegularFile("pom.xml");
    } catch (Exception e) {
      Assert.fail(e.getMessage());
      return null;
    }
  }

}
