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
import objectos.docs.internal.ArticleTemplate2;
import objectos.docs.internal.DocsCss;
import objectos.docs.internal.Step3Generate;
import objectos.docs.internal.VersionsTemplate2;
import objectos.html.HtmlTemplate;
import objectos.shared.JavaRenderer;
import objectos.shared.SharedTemplate;
import objectos.shared.XmlRenderer;

public final class Docs extends Step3Generate {

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
    ArticleTemplate2.initArticleTemplate();
    VersionsTemplate2.initVersionsTemplate();
    DocsCss.init();
    XmlRenderer.init();
    JavaRenderer.init();
  }

  public static final String INDEX = "docs/latest/index";

  public static final String OVERVIEW = "docs/latest/intro/overview";

  public static final String LATEST = "0.6.6";

  public Docs() {}

  public static void main(String[] args) throws IOException {
    out.println("Running...");

    var docs = new Docs();

    docs.parseArgs(args);

    docs.execute();
  }

  public final void execute() throws IOException {
    executeVersions();

    executeScan();

    executeGenerate();
  }

}