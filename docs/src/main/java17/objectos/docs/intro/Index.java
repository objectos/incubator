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
package objectos.docs.intro;

import br.com.objectos.be.annotations.Markdown;
import objectos.docs.ui.ArticlePage;

//@formatter:off
/**

# Get started with Objectos

*/
//@formatter:on
@Markdown
final class Index extends ArticlePage {
  @Override
  protected final void register() {
    nextPage = Overview.class;

    titleText = "Get started with Objectos";
  }
}