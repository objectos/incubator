/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.processor.common;

import static org.testng.Assert.assertEquals;

import br.com.objectos.way.util.UnmodifiableList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.Test;

public class RoutePartIteratorTest {

  @Test
  public void hwAddressShouldGiveOneVariable() {
    List<RoutePart> res = toList("/:hwAddress");
    assertEquals(res.toString(), "[:hwAddress]");
  }

  @Test
  public void hwAddressTrailingShouldGiveOneVariable() {
    List<RoutePart> res = toList("/:hwAddress/");
    assertEquals(res.toString(), "[:hwAddress]");
  }

  @Test
  public void hwAddressStage3ShouldGiveOneVariableAndOneFixed() {
    List<RoutePart> res = toList("/:hwAddress/stage3.tar.bz2");
    assertEquals(res.toString(), "[:hwAddress, stage3.tar.bz2]");
  }

  private List<RoutePart> toList(String path) {
    Iterator<RoutePart> iterator = new RoutePartIterator(path);
    return UnmodifiableList.ofAll(iterator);
  }

}