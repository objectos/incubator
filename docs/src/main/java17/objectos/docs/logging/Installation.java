/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.logging;

import objectos.docs.intro.IntroDir;
import objectos.docs.ui.ArticlePage;

final class Installation extends ArticlePage {

  @Override
  protected final void contents() {
    h1("Installation");

    p(
      t("Import the Objectos BOM POM by following "),
      a(IntroDir.INSTALLATION, "this document"),
      t("."));

    p(
      t("Next, add the Objectos Logging dependency to your project's "),
      code("pom.xml"),
      t(":"));

    codeXml(
      """
      <dependencies>
          <dependency>
              <groupId>br.com.objectos</groupId>
              <artifactId>logging</artifactId>
          </dependency>
      </dependencies>""");
  }

}