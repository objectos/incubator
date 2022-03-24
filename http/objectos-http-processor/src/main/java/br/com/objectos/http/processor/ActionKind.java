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
package br.com.objectos.http.processor;

import br.com.objectos.comuns.io.File;
import br.com.objectos.html.writer.HtmlWritable;
import br.com.objectos.http.server.FileResponse;
import br.com.objectos.http.server.HtmlResponse;
import br.com.objectos.http.server.Response;
import br.com.objectos.http.server.StatusCode;
import br.com.objectos.http.server.TextResponse;
import br.com.objectos.way.code.model.element.ExecutableElementQuery;
import br.com.objectos.way.code.model.type.TypeMirrorQuery;
import br.com.objectos.way.util.ImmutableMap;
import com.squareup.javapoet.CodeBlock;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

enum ActionKind {

  RESPONSE {
    @Override
    public CodeBlock.Builder returnStatement(CodeBlock.Builder body, String name, String params) {
      return body.addStatement("return $L($L)", name, params);
    }
  },

  FILE {
    @Override
    public CodeBlock.Builder returnStatement(CodeBlock.Builder body, String name, String params) {
      return body.addStatement("return new $T($L($L))", FileResponse.class, name, params);
    }
  },

  HTML_WRITABLE {
    @Override
    public CodeBlock.Builder returnStatement(CodeBlock.Builder body, String name, String params) {
      return body
          .addStatement("return new $T($L($L).toString())", HtmlResponse.class, name, params);
    }
  },

  STATUS_CODE {
    @Override
    public CodeBlock.Builder returnStatement(CodeBlock.Builder body, String name, String params) {
      return body.addStatement("return $L($L).response()", name, params);
    }
  },

  TEXT_PLAIN {
    @Override
    public CodeBlock.Builder returnStatement(CodeBlock.Builder body, String name, String params) {
      return body.addStatement("return new $T($L($L))", TextResponse.class, name, params);
    }
  };

  private static final Map<String, ActionKind> QNAME_MAP =
      ImmutableMap.<String, ActionKind> builder()
          .put(Response.class.getName(), RESPONSE)
          .put(File.class.getName(), FILE)
          .put(StatusCode.class.getName(), STATUS_CODE)
          .put(String.class.getName(), TEXT_PLAIN)
          .build();

  private static final Map<Class<?>, ActionKind> INSTANCEOF_MAP =
      ImmutableMap.<Class<?>, ActionKind> builder()
          .put(HtmlWritable.class, HTML_WRITABLE)
          .build();

  public static ActionKind of(ExecutableElementQuery method) {
    TypeMirrorQuery returnType = method.returnType();
    String qualifiedName = returnType.qualifiedName();

    if (QNAME_MAP.containsKey(qualifiedName)) {
      return QNAME_MAP.get(qualifiedName);
    }

    Set<Entry<Class<?>, ActionKind>> instanceOfEntrySet = INSTANCEOF_MAP.entrySet();
    for (Entry<Class<?>, ActionKind> entry : instanceOfEntrySet) {
      Class<?> type = entry.getKey();
      if (returnType.isSubType(type)) {
        return entry.getValue();
      }
    }

    return null;
  }

  public abstract CodeBlock.Builder returnStatement(CodeBlock.Builder body, String name,
      String params);

}