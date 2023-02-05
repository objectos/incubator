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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.repository.Repository;
import org.eclipse.aether.transport.wagon.WagonProvider;

public class Site {

  private String id;
  @SuppressWarnings("unused")
  private String name;
  private String url;

  public Site() {}

  Site(String id, String name, String url) {
    this.id = id;
    this.name = name;
    this.url = url;
  }

  public final Wagon getWagon(WagonProvider provider) throws MojoExecutionException {
    try {
      Repository repository = new Repository(id, url);
      Wagon wagon = provider.lookup(repository.getProtocol());
      wagon.connect(repository);
      return wagon;
    } catch (Exception e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

}
