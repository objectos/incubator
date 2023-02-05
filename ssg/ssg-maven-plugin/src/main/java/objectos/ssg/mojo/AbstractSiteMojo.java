/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

abstract class AbstractSiteMojo extends AbstractMojo {

  @Parameter(defaultValue = "${mojo}", readonly = true)
  private MojoExecution mojoExecution;

  @Parameter(defaultValue = "${plugin}", readonly = true)
  private PluginDescriptor pluginDescriptor;

  @Parameter(defaultValue = "${project.artifacts}", readonly = true)
  private Set<Artifact> projectArtifacts;

  @Parameter(required = true)
  private String siteClass;

  @Parameter(readonly = true)
  private Map<String, String> systemProperties;

  final String getSiteCanonicalName() {
    return siteClass;
  }

  final MojoExecutionException mojoExecutionException(String format, Object... args) {
    return new MojoExecutionException(String.format(format, args));
  }

  final ClassRealm parent() {
    return mojoExecution.getMojoDescriptor().getRealm();
  }

  final void setUp(ClassRealm pluginClassRealm) throws MojoExecutionException {
    if (systemProperties != null) {
      Set<String> keys;
      keys = systemProperties.keySet();

      for (String key : keys) {
        String value;
        value = systemProperties.get(key);

        if (value == null) {
          continue;
        }

        System.setProperty(key, value);
      }
    }

    try {
      List<Artifact> pluginArtifacts;
      pluginArtifacts = pluginDescriptor.getArtifacts();

      for (int i = 0; i < pluginArtifacts.size(); i++) {
        Artifact artifact;
        artifact = pluginArtifacts.get(i);

        addArtifact(pluginClassRealm, artifact);
      }

      for (Artifact artifact : projectArtifacts) {
        addArtifact(pluginClassRealm, artifact);
      }
    } catch (MalformedURLException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

  private void addArtifact(ClassRealm realm, Artifact artifact) throws MalformedURLException {
    File file;
    file = artifact.getFile();

    URI uri;
    uri = file.toURI();

    URL url;
    url = uri.toURL();

    realm.addURL(url);
  }

}
