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
package br.com.objectos.css.maven.plugin.framework;

import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.config.framework.Configuration;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

@Mojo(
    name = "framework",
    defaultPhase = LifecyclePhase.GENERATE_SOURCES
)
public class FrameworkMojo extends AbstractMojo {

  private class ThisAdapter implements ConfigurationAdapter {

    private final Directory dir;

    ThisAdapter(Directory dir) {
      this.dir = dir;
    }

    @Override
    public final void writeJavaFile(JavaFile file) {
      try {
        file.writeTo(dir.toPath());
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

  }

  public static final AnnotationCode GENERATED = annotation(
    Generated.class,
    l(FrameworkMojo.class.getCanonicalName())
  );

  @Parameter(required = true, readonly = true)
  private String configurationClass;

  @Parameter(defaultValue = "${plugin}")
  private PluginDescriptor pluginDescriptor;

  @Parameter(defaultValue = "${project.artifacts}")
  private Set<Artifact> projectArtifacts;

  @Parameter(defaultValue = "${project.build.sourceDirectory}", required = true, readonly = true)
  private File sourceDirectory;

  @Override
  public final void execute() throws MojoExecutionException, MojoFailureException {
    try {

      ClassRealm pluginClassRealm = pluginDescriptor.getClassRealm();

      for (Artifact artifact : pluginDescriptor.getArtifacts()) {
        URL url = artifact.getFile().toURI().toURL();
        pluginClassRealm.addURL(url);
      }

      for (Artifact artifact : projectArtifacts) {
        URL url = artifact.getFile().toURI().toURL();
        pluginClassRealm.addURL(url);
      }

      PluginConfigurationDsl dsl = new PluginConfigurationDsl();

      Configuration configuration = newConfiguration();
      configuration.acceptConfigurationDsl(dsl);

      Directory dir;
      dir = LocalFs.getDirectory(sourceDirectory);

      ConfigurationAdapter adapter = new ThisAdapter(dir);
      dsl.execute(adapter);

    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    }
  }

  private Configuration newConfiguration()
      throws ClassNotFoundException,
      IllegalAccessException,
      InvocationTargetException,
      InstantiationException,
      NoSuchMethodException,
      SecurityException {
    Class<?> classInstance;
    classInstance = Class.forName(configurationClass);

    Constructor<?> constructor;
    constructor = classInstance.getConstructor();

    Object instance;
    instance = constructor.newInstance();

    return (Configuration) instance;
  }

}
