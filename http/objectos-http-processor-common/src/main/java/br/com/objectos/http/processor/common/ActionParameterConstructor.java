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

import br.com.objectos.http.server.CatchAll;
import br.com.objectos.way.code.model.element.VariableElementQuery;

public abstract class ActionParameterConstructor {

  protected ActionParameterConstructor() {
  }

  public static ActionParameterConstructor ofParameter(IndexGenerator index, VariableElementQuery parameter) {
    return new OfParameter(index, parameter);
  }

  static IndexGenerator newIndexGenerator() {
    return new IndexGenerator();
  }

  protected abstract void compilationError(String message);

  protected abstract String qualifiedName();

  protected abstract int index();

  protected abstract String name();

  protected abstract boolean catchAll();

  static class IndexGenerator {

    private int index;

    private IndexGenerator() {
    }

    int next() {
      return index++;
    }

  }

  private static class OfParameter extends ActionParameterConstructor {

    private final IndexGenerator index;
    private final VariableElementQuery delegate;

    private OfParameter(IndexGenerator index, VariableElementQuery delegate) {
      this.index = index;
      this.delegate = delegate;
    }

    @Override
    protected void compilationError(String message) {
      delegate.compilationError(message);
    }

    @Override
    protected String qualifiedName() {
      return delegate.asType().qualifiedName();
    }

    @Override
    protected int index() {
      return index.next();
    }

    @Override
    protected String name() {
      return delegate.name();
    }

    @Override
    protected boolean catchAll() {
      return delegate.isAnnotatedWith(CatchAll.class);
    }

  }

}