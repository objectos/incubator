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
package br.com.objectos.http.server.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLConnection;

class UrlHelper {

  private final URL url;

  private UrlHelper(URL url) {
    this.url = url;
  }

  public static UrlHelper of(String spec) {
    try {
      URL url = new URL(spec);
      return new UrlHelper(url);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public UrlHelper connect() {
    try {
      URLConnection connection = url.openConnection();
      connection.connect();
      return this;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public String get() {
    StringBuilder out = new StringBuilder();
    try (BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()))) {
      String l = null;
      while ((l = r.readLine()) != null) {
        out.append(l);
      }
      return out.toString();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public UrlHelper sleep(int millis) {
    try {
      Thread.sleep(millis);
      return this;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}