/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.server;

public class Code400BadRequestException extends HttpException {

  private static final long serialVersionUID = 1L;

  private Code400BadRequestException(String message) {
    super(message);
  }

  public static Code400BadRequestException invalidMethod(String name) {
    return new Code400BadRequestException("Invalid Method: " + name);
  }

  public static Code400BadRequestException invalidProtocol(String name) {
    return new Code400BadRequestException("Invalid protocol: " + name);
  }

}