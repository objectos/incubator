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

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# What is Objectos?

Objectos is a suite of open-source Java libraries. In this first public release
it provides a type-safe logging API for Java applications.
It does not provide an implementation for this API yet.

One can safely say Objectos is under construction.

In this document, we will present you some introductory information about the
Objectos suite of Java libraries. We hope it will encourage you to:

- adopt Objectos in your current Java project;
- consider using Objectos in your next Java project; or
- bookmark this project and return in the future.

## Open-source

Objectos is open-source software licensed under the
[Apache License, version 2.0](https://www.apache.org/licenses/LICENSE-2.0").

## Built from scratch

All of the Objectos' Java libraries are built from scratch.
While we believe it to be a positive thing, we understand if this fact
discourages you. The reasons why we think it is a positive thing are:

- we do not depend on a third-party for addresing vulnerabilities.
  Objectos' libraries have zero third-party dependencies;
- greater control on thread creation, object instantiation and
  performance.

## CPU and memory cost-concious

We do not claim to know how to write performant software.

With that said, we believe that applications should be able to run in appropriately
sized servers. US dollar or Euro based prices for cloud computing can
be costly to small and medium sized businesses in countries with a
currency like the Brazilian Real.

## Built for current JDKs

OpenJDK has been offering both memory and performance improvements with every release.
We should embrace those gains.

## Documentation is a first-class citizen

We do not claim to have good nor great documentation.

We have learned the hard way that a project does not exist without proper
documentation. Therefore, we will work to the best of our abilities to:

- provide documentation for all of our publicly available libraries. This may include:
    - tutorials
    - topic guides
    - how-to guides
    - Javadocs
- keep the documentation up-to-date.

## We eat our own dog food

This documentation site is a static website created with Objectos CSS, Objectos HTML
and Objectos SSG.
Please note that these libraries are not publicly released yet.
But you can find the source code in our
[incubator git repository](https://github.com/objectos/incubator).

*/
//@formatter:on
@Markdown
final class Overview extends DocsPage {
  @Override
  protected final void configure() {
    nextPage = Installation.class;

    titleText = "What is Objectos?";
  }
}