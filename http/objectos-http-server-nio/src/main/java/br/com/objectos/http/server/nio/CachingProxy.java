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

import static br.com.objectos.collections.Collections.map;
import static br.com.objectos.collections.Collections.toUnmodifiableList;

import br.com.objectos.collections.UnmodifiableList;
import br.com.objectos.http.server.Code500InternalServerErrorException;
import br.com.objectos.http.server.FileResponse;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.Response;
import br.com.objectos.http.server.StatusCode;
import br.com.objectos.http.server.nio.CachingProxy.ResponseBuilder;
import br.com.objectos.io.Directory;
import br.com.objectos.io.FsObject;
import br.com.objectos.io.FsObjectVisitor;
import br.com.objectos.io.Io;
import br.com.objectos.io.NotFound;
import br.com.objectos.io.RegularFile;
import br.com.objectos.io.Url;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class CachingProxy implements FsObjectVisitor<ResponseBuilder, String> {

  private final Directory cache;
  private final UnmodifiableList<String> specList;

  CachingProxy(Directory cache, UnmodifiableList<String> specList) {
    this.cache = cache;
    this.specList = specList;
  }

  public Response get(String path) throws HttpException {
    try {
      FsObject query = cache.resolve(path);

      ResponseBuilder builder = query.acceptFsObjectVisitor(this, path);

      return builder.get();
    } catch (IOException e) {
      throw new Code500InternalServerErrorException(e);
    }
  }

  public UnmodifiableList<Url> urlList(String path) {
    Iterable<Url> map
        = map(specList, spec -> Url.of(spec + path).timeoutAfter(3, TimeUnit.MINUTES));

    return toUnmodifiableList(map);
  }

  @Override
  public ResponseBuilder visitDirectory(Directory directory, String p) {
    return this::notFound;
  }

  @Override
  public ResponseBuilder visitNotFound(NotFound notFound, String p) {
    try (InputStream in = openStream(p)) {
      if (in == null) {
        return this::notFound;
      }

      RegularFile file;
      file = notFound.createRegularFile();

      Io.writeInputStream(file, in);

      return visitRegularFile(file, p);
    } catch (IOException e) {
      return () -> {
        throw new Code500InternalServerErrorException(e);
      };
    }
  }

  @Override
  public ResponseBuilder visitRegularFile(RegularFile file, String p) {
    return () -> new FileResponse(file);
  }

  private Response notFound() {
    return StatusCode.NOT_FOUND.response();
  }

  private InputStream openStream(String path) {
    for (String spec : specList) {
      try {
        return Url.of(spec + path).timeoutAfter(3, TimeUnit.MINUTES).openInputStream();
      } catch (IOException e) {
        // next
      }
    }
    return null;
  }

  interface ResponseBuilder {
    Response get() throws HttpException;
  }

}