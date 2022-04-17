/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.ui;

import br.com.objectos.core.list.ImmutableList;
import objectos.ssg.SiteComponent;

class NoOpContext implements SiteComponent.Context {

  @Override
  public String getHref(Class<?> key) {
    return null;
  }

  @Override
  public <T> T getObject(Class<? extends T> key) {
    return null;
  }

  @Override
  public <T> ImmutableList<T> getObjectsByType(Class<? extends T> key) {
    return null;
  }

}