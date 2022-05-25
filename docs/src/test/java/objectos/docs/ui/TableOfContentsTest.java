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
package objectos.docs.ui;

import static org.testng.Assert.assertEquals;

import objectos.docs.DocsSiteTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TableOfContentsTest extends DocsSiteTest {

  private TableOfContents toc;

  @BeforeMethod
  public void _beforeMethod() {
    toc = getObject(TableOfContents.class);
  }

  @Test
  public void v0001() {
    toc.set("v0001");

    assertEquals(
      toc.toString(),

      """
      <ul>\
      <li><a href="/0.1/index.html">Home</a></li>\
      <li><a href="/0.1/intro/index.html">Get started with Objectos</a>\
      <ul><li><a href="/0.1/intro/overview.html">What is Objectos?</a></li>\
      <li><a href="/0.1/intro/install.html">Installation</a></li>\
      </ul></li><li><a href="/0.1/logging/index.html">Objectos Logging</a><ul><li><a href="/0.1/logging/getting-started/index.html">Getting started</a><ul><li><a href="/0.1/logging/getting-started/about-logging.html">About logging</a></li><li><a href="/0.1/logging/getting-started/objectos-logging.html">Logging with Objectos Logging</a></li><li><a href="/0.1/logging/getting-started/installing.html">Installing Objectos Logging</a></li><li><a href="/0.1/logging/getting-started/quick-start.html">Quick start</a></li></ul></li><li><a href="/0.1/logging/logging-guide/index.html">Logging guide</a><ul><li><a href="/0.1/logging/logging-guide/events.html">Creating events</a></li><li><a href="/0.1/logging/logging-guide/logger.html">Using the Logger interface</a></li></ul></li><li><a href="/0.1/logging/no-op-logger/index.html">The NoOpLogger</a><ul></ul></li></ul></li><li><a href="/0.1/relnotes/index.html">Release notes</a><ul><li><a href="/0.1/relnotes/0.1.0.html">Objectos 0.1.0 release notes</a></li></ul></li></ul>"""
    );
  }

}