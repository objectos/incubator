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
package objectos.docs.v0001;

import br.com.objectos.be.annotations.Be;
import br.com.objectos.be.annotations.Markdown;
import objectos.docs.ui.ArticlePage;

@Be
abstract class Introduction extends ArticlePage {

  @Override
  public final String topBarTitle() {
    return "Introduction";
  }

//@formatter:off
  /**

# Documentation for Objectos developers

This is the first public version of the Objectos suite of libraries.
Our goal is to, in time, allow you to build Java web applications.
For now, you can learn more about Objectos by browsing the documentation of
the available components.

   */
  //@formatter:on
  @Markdown
  @Override
  protected abstract void contents();

}