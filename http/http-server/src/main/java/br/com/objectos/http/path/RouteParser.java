/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.http.path;

import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.RequestProto;
import br.com.objectos.http.server.RequestedPart;
import br.com.objectos.http.server.Resolution;
import java.util.ArrayList;
import java.util.List;
import objectos.util.ImmutableList;

public class RouteParser {

  private final HttpAction action;
  private final List<Argument> argumentList = new ArrayList<>();
  private final ImmutableList<Slug> expectedList;

  private int index;
  private final RequestProto request;

  public RouteParser(ImmutableList<Slug> expectedList,
                     HttpAction action,
                     RequestProto request) {
    this.expectedList = expectedList;
    this.action = action;
    this.request = request;
  }

  public final boolean add(Argument argument) {
    return argumentList.add(argument);
  }

  public final List<Argument> argumentList() {
    return argumentList;
  }

  public final boolean matches(RequestedPart part) {
    int currentIndex = index == expectedList.size() ? index - 1 : index++;
    Slug expected = expectedList.get(currentIndex);
    return matches0(expected, part);
  }

  public final boolean matchesEmpty() {
    return expectedList.isEmpty() ? true : matchesEmpty0();
  }

  public final Resolution resolve() {
    return Resolution.resolved(
        action,
        new Request(argumentList, request));
  }

  public final boolean wasLastPart() {
    return index == expectedList.size();
  }

  private boolean matches0(Slug expected, RequestedPart requested) {
    return expected.matches(this, requested);
  }

  private boolean matchesEmpty0() {
    int currentIndex = index++;
    return currentIndex >= expectedList.size()
        ? false
        : expectedList.get(currentIndex).matchesEmpty();
  }

}