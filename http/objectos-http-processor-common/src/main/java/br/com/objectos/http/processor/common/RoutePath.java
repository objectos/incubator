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

import br.com.objectos.way.util.ImmutableList;
import br.com.objectos.way.util.ImmutableListBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class RoutePath {

  private final List<RoutePart> partList;

  private RoutePath(List<RoutePart> partList) {
    this.partList = partList;
  }

  private RoutePath(Iterator<RoutePart> iterator) {
    partList = ImmutableList.ofAll(iterator);
  }

  public static RoutePath parse(String path) {
    Iterator<RoutePart> iterator = new RoutePartIterator(path);
    return new RoutePath(iterator);
  }

  public void accept(RoutePathVisitor visitor) {
    for (RoutePart part : partList) {
      part.accept(visitor);
    }
  }

  public void acceptConfigureBody(ConfigureMethodSpec method) {
    for (RoutePart part : partList) {
      part.acceptConfigureBody(method);
    }
  }

  public static RoutePath parse(String path, List<ActionParameter> parameterList) throws RoutePartException {
    List<RoutePart> partList = new ArrayList<>();

    Iterator<RoutePart> partIter = new RoutePartIterator(path);
    Iterator<ActionParameter> pmtrIter = parameterList.iterator();

    while (partIter.hasNext()) {
      RoutePart part = partIter.next();
      partList.add(part.consume(pmtrIter));
    }

    return new RoutePath(partList);
  }

  public RoutePath add(RoutePath other) {
    ImmutableListBuilder<RoutePart> add = ImmutableList.builder();
    add.addAll(partList);
    add.addAll(other.partList);
    return new RoutePath(add.build());
  }

  public List<RoutePart> partList() {
    return partList;
  }

  @Override
  public final String toString() {
    return partList.stream()
        .map(RoutePart::toString)
        .collect(Collectors.joining("/", "/", ""));
  }

}