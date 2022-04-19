/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.ui;

import br.com.objectos.core.list.ImmutableList;
import objectos.ssg.Site;

class NoOpContext implements Site.Context {

  @Override
  public <T> T getObject(Class<? extends T> key) {
    return null;
  }

  @Override
  public <T> ImmutableList<T> getObjectsByType(Class<? extends T> key) {
    return null;
  }

}