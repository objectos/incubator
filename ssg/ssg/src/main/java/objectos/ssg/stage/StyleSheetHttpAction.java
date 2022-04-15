/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg.stage;

import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.http.server.CssResponse;
import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.Response;

class StyleSheetHttpAction implements HttpAction {

  private final String sheet;

  StyleSheetHttpAction(StyleSheet sheet) {
    this.sheet = sheet.printMinified();
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof StyleSheetHttpAction)) {
      return false;
    }
    StyleSheetHttpAction that = (StyleSheetHttpAction) obj;
    return sheet.equals(that.sheet);
  }

  @Override
  public final Response execute(Request request) throws HttpException {
    return new CssResponse(sheet.toString());
  }

  @Override
  public final int hashCode() {
    return sheet.hashCode();
  }

}
