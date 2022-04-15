/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.maven.plugin;

import br.com.objectos.http.server.MutableHttpServer;
import br.com.objectos.http.server.jetty.JettyHttpServerBuilder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import objectos.ssg.Site;
import objectos.ssg.stage.DevelopmentStage;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

@Mojo(name = "serve", requiresDependencyResolution = ResolutionScope.TEST)
public class ServeMojo extends AbstractBeMojo {

  @Parameter(defaultValue = "true", readonly = true)
  private boolean joinExecution;

  private final Log logger = getLog();

  @Parameter(defaultValue = "${project.build.outputDirectory}", required = true, readonly = true)
  private File outputDirectory;

  private ClassRealm parent;

  private Thread pluginThread;

  private MutableHttpServer server;

  private URLClassLoader siteClassLoader;

  private URL[] urls;

  @Override
  public final void execute() throws MojoExecutionException, MojoFailureException {
    pluginThread = Thread.currentThread();

    parent = parent();

    setUp(parent);

    try {
      URI uri;
      uri = outputDirectory.toURI();

      URL url;
      url = uri.toURL();

      urls = new URL[] {url};
    } catch (MalformedURLException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }

    server = new JettyHttpServerBuilder().port(8080).buildMutable();

    try {
      reload();
    } catch (IOException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }

    Reloader reloader;
    reloader = new Reloader();

    reloader.start();

    server.start();

    if (joinExecution) {
      try {
        server.join();
      } catch (InterruptedException e) {
        throw new MojoExecutionException(e.getMessage(), e);
      }
    }
  }

  private URLClassLoader newClassLoader() {
    return new URLClassLoader(urls, parent);
  }

  @SuppressWarnings("unchecked")
  private Site newSite() {
    Site result;
    result = null;

    ClassLoader ccl;
    ccl = pluginThread.getContextClassLoader();

    pluginThread.setContextClassLoader(siteClassLoader);

    try {
      String siteCanonicalName;
      siteCanonicalName = getSiteCanonicalName();

      Class<? extends Site> siteClazz;
      siteClazz = (Class<? extends Site>) siteClassLoader.loadClass(siteCanonicalName);

      Constructor<? extends Site> constructor;
      constructor = siteClazz.getConstructor();

      result = constructor.newInstance();
    } catch (ClassNotFoundException e) {
      logger.warn("ClassNotFoundException", e);
    } catch (IllegalAccessException e) {
      logger.warn("IllegalAccessException", e);
    } catch (InvocationTargetException e) {
      logger.warn("InvocationTargetException", e);
    } catch (InstantiationException e) {
      logger.warn("InstantiationException", e);
    } catch (NoSuchMethodException e) {
      logger.warn("NoSuchMethodException", e);
    } catch (SecurityException e) {
      logger.warn("SecurityException", e);
    } finally {
      pluginThread.setContextClassLoader(ccl);
    }

    return result;
  }

  private void reload() throws IOException {
    logger.info("Reload");

    siteClassLoader = newClassLoader();

    DevelopmentStage stage;
    stage = new DevelopmentStage();

    Site site;
    site = newSite();

    if (site != null) {
      stage.addSite(site);

      server.reconfigure(stage);

      logger.info("Reload success: " + new Date());
    }
  }

  private class Reloader extends Thread {

    Reloader() {
      super("reloader");

      setDaemon(true);
    }

    @Override
    public final void run() {
      try {
        reload();

        System.out.println("Press <enter> to reload");

        InputStream in;
        in = System.in;

        while (true) {
          int c;
          c = in.read();

          if (c < 0) {
            Thread.sleep(500);

            continue;
          }

          if (c == '\n') {
            reload();
          }
        }
      } catch (IOException e) {
        logger.warn(e);
      } catch (InterruptedException e) {
        return;
      }
    }

  }

}
