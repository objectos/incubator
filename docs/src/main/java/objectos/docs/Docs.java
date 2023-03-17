/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs;

import static java.lang.System.out;

import br.com.objectos.css.Css;
import java.io.IOException;
import java.nio.file.Path;
import objectos.docs.internal.ArticleTemplate;
import objectos.docs.internal.DocsCss;
import objectos.docs.internal.Step2Generate;
import objectos.docs.internal.VersionsTemplate;
import objectos.html.HtmlTemplate;
import objectos.shared.JavaRenderer;
import objectos.shared.SharedTemplate;
import objectos.shared.XmlRenderer;

public final class Docs extends Step2Generate {

  public interface BottomBar {
    HtmlTemplate toFragment();
  }

  public interface TopBar {
    String javaScript();

    HtmlTemplate toFragment();
  }

  public static final long SEED = 84321674516L;

  static {
    Css.randomSeed(SEED);

    SharedTemplate.init();
    ArticleTemplate.initArticleTemplate();
    VersionsTemplate.initVersionsTemplate();
    DocsCss.init();
    XmlRenderer.init();
    JavaRenderer.init();
  }

  public static final String INDEX = "docs/0.5.1/index";

  public static final String OVERVIEW = "docs/0.5.1/intro/overview";

  public static final String LATEST = "0.5.1";

  public Docs() {}

  public static void main(String[] args) throws IOException {
    out.println("Running...");

    var docs = new Docs();

    docs.parseArgs(args);

    docs.executeScan();

    docs.executeGenerate();
  }

  public final void execute(Path sourceDirectory, Path targetDirectory) throws IOException {
    sourceDirectory(sourceDirectory);

    targetDirectory(targetDirectory);

    executeScan();

    executeGenerate();
  }

}