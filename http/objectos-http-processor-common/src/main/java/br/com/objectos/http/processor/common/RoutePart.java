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

import java.util.Iterator;

class RoutePart {

  private final String value;
  private final boolean parameter;

  public RoutePart(String value, boolean parameter) {
    this.value = value;
    this.parameter = parameter;
  }

  public void accept(RoutePathVisitor visitor) {
    visitor.visitFixedPart(value);
  }

  public void acceptConfigureBody(ConfigureMethodSpec method) {
    method.fixedPart(value);
  }

  public RoutePart consume(Iterator<ActionParameter> pmtrIter) throws RoutePartException {
    return parameter ? consumeParameter(pmtrIter) : this;
  }

  @Override
  public String toString() {
    return parameter ? ":" + value : value;
  }

  private RoutePart consumeParameter(Iterator<ActionParameter> pmtrIter) throws RoutePartException {
    if (!pmtrIter.hasNext()) {
      throw new RoutePartException("Expected parameter named '%s'", value);
    }

    ActionParameter parameter = pmtrIter.next();
    if (!parameter.hasName(value)) {
      throw new RoutePartException("Expected parameter named '%s' but found '%s'", value, parameter.name());
    }

    return new ParameterPathPart(value, parameter);
  }

  private static class ParameterPathPart extends RoutePart {

    private final ActionParameter actionParameter;

    public ParameterPathPart(String value, ActionParameter actionParameter) {
      super(value, true);
      this.actionParameter = actionParameter;
    }

    @Override
    public void accept(RoutePathVisitor visitor) {
      actionParameter.accept(visitor);
    }

    @Override
    public void acceptConfigureBody(ConfigureMethodSpec method) {
      actionParameter.acceptConfigureBody(method);
    }

  }

}