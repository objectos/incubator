/*
 * Copyright (C) 2014-2020 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCode project.
 *
 * ObjectosCode is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosCode is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosCode.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.code.www;

import br.com.objectos.be.site.Module;

public class RootModule extends Module{

  @Override
  protected final void configure() {
    addTemplate(new Root());
  }

}
