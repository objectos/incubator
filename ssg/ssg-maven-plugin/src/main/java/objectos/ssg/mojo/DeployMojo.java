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

import java.io.File;
import javax.inject.Inject;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.wagon.ResourceDoesNotExistException;
import org.apache.maven.wagon.TransferFailedException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.authorization.AuthorizationException;
import org.eclipse.aether.transport.wagon.WagonProvider;

@Mojo(
    name = "deploy",
    defaultPhase = LifecyclePhase.SITE_DEPLOY,
    requiresReports = true
)
public class DeployMojo extends AbstractSiteMojo {

  @Parameter(
      property = "siteOutputDirectory",
      defaultValue = "${project.reporting.outputDirectory}"
  )
  private File inputDirectory;

  @Parameter(required = true)
  private Site site;

  private final WagonProvider wagonProvider;

  @Inject
  DeployMojo(WagonProvider wagonProvider) {
    this.wagonProvider = wagonProvider;
  }

  @Override
  public final void execute() throws MojoExecutionException, MojoFailureException {
    Wagon wagon = site.getWagon(wagonProvider);
    try {      
      wagon.putDirectory(inputDirectory, "./");
    } catch (TransferFailedException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (ResourceDoesNotExistException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    } catch (AuthorizationException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

  final Site site() {
    return site;
  }

}