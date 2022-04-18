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
package objectos.ssg.mojo;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import objectos.ssg.Site;
import objectos.ssg.stage.ProductionStage;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

@Mojo(name = "site", requiresDependencyResolution = ResolutionScope.TEST, requiresReports = true)
public class SiteMojo extends AbstractSiteMojo {

  @Parameter(defaultValue = "", required = false)
  private String baseHref;

  @Parameter(defaultValue = "${project.build.outputDirectory}", required = true, readonly = true)
  private File build;

  @Parameter(defaultValue = "${project.reporting.outputDirectory}")
  private File siteOutputDirectory;

  @Override
  public final void execute() throws MojoExecutionException, MojoFailureException {
    ClassRealm classRealm = parent();

    setUp(classRealm);

    try {
      classRealm.addURL(build.toURI().toURL());
    } catch (MalformedURLException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }

    siteOutputDirectory.mkdirs();

    try {
      Directory target;
      target = LocalFs.getDirectory(siteOutputDirectory);

      ProductionStage stage;
      stage = new ProductionStage(
        baseHref == null ? "" : baseHref,

        target
      );

      Site site;
      site = getSiteInstance(classRealm);

      site.generate(stage);
    } catch (IOException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

  private Site getSiteInstance(ClassRealm classRealm) throws MojoExecutionException {
    try {
      String canonicalName;
      canonicalName = getSiteCanonicalName();

      Class<?> siteClass;
      siteClass = classRealm.loadClass(canonicalName);

      Constructor<?> constructor;
      constructor = siteClass.getDeclaredConstructor();

      return (Site) constructor.newInstance();
    } catch (ClassNotFoundException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (InstantiationException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (IllegalAccessException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (NoSuchMethodException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (SecurityException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (IllegalArgumentException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (InvocationTargetException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

}
