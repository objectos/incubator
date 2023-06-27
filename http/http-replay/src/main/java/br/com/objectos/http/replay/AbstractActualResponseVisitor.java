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
package br.com.objectos.http.replay;

import br.com.objectos.http.parser.ResponseHeader;
import br.com.objectos.http.parser.SimpleResponseVisitor;
import br.com.objectos.http.parser.Version;
import objectos.http.Status;

public abstract class AbstractActualResponseVisitor extends SimpleResponseVisitor {

  ReplayResult result;

  protected AbstractActualResponseVisitor() {}

  public final ReplayResult getResult() {
    return result;
  }

  public final void reset() {
    result = ReplayResult.NO_RESULT;
  }

  @Override
  public final void visitResponseStatusLine(Version version, Status status, String reason) {
    if (!keepGoing()) {
      visitResponseStatusLineImpl(version, status, reason);
    }
  }

  @Override
  protected final void defaultResponseHeaderAction(ResponseHeader header) {
    if (!keepGoing()) {
      defaultResponseHeaderActionImpl(header);
    }
  }

  protected void defaultResponseHeaderActionImpl(ResponseHeader header) {}

  protected final void fail() {
    result = ReplayResult.FAILED;
  }

  protected final void success() {
    result = ReplayResult.SUCCESS;
  }

  protected void visitResponseStatusLineImpl(Version version, Status status, String reason) {}

  private boolean keepGoing() {
    return result != ReplayResult.NO_RESULT;
  }

}
