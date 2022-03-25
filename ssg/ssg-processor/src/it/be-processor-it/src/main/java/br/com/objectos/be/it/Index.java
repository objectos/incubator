/*
 * Copyright (C) 2011-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosBe project.
 *
 * ObjectosBe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosBe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosBe.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.be.it;

import br.com.objectos.be.annotations.Be;
import br.com.objectos.html.tmpl.AbstractTemplate;

@Be
public class Index extends AbstractTemplate {

  @Override
  protected final void definition() {
    doctype();
    html(
        f(this::head0),
        f(this::body0)
    );
  }

  private void head0() {
    head(
        meta(charset("utf-8")),
        title("objectos::be processor test")
    );
  }

  private void body0() {
    body(
        t("Hello world!")
    );
  }
  
}