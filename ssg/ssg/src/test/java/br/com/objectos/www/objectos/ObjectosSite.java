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
package br.com.objectos.www.objectos;

import br.com.objectos.be.site.AbstractSite;
import br.com.objectos.core.list.ImmutableList;

public final class ObjectosSite extends AbstractSite {

  @Override
  protected final void configure() {
    addDirectory(new RootDirectory());

    configureNavbar();
  }

  private void configureNavbar() {
    ImmutableList<NavbarPage> pages;
    pages = getInstancesByType(NavbarPage.class);

    Navbar navbar;
    navbar = getInstance(Navbar.class);

    navbar.setPages(pages);
  }

}