/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.code;

import java.util.LinkedHashMap;
import objectos.code.JavaTemplate;

public class Tutorial extends JavaTemplate {
  public static void main(String[] args) {
    System.out.println(new Tutorial());
  }
  @Override
  protected final void definition() {
    // @formatter:off
    _package("com.example");

    autoImports();

    at(t("com.example.annotations", "Subject"), s("Objectos Code"));
    _public(); _final(); _class("Tutorial"); body(
      include(this::constants),

      _private(), _final(), _int(), id("value"),

      _public(), constructor(_int(), id("value")), block(
        _this(), n("value"), gets(), n("value")
      ),

      _public(), _int(), method("get"), block(
        _return(), n("value")
      )
    );
    // @formatter:on
  }

  private void constants() {
    var constants = new LinkedHashMap<String, Integer>();

    constants.put("ONE", 1);
    constants.put("TWO", 2);
    constants.put("THREE", 3);

    for (var entry : constants.entrySet()) {
      var name = entry.getKey();
      var value = entry.getValue();

      // @formatter:off
      _public(); _static(); _final(); _int(); id(name); i(value.intValue());
      // @formatter:on
    }
  }
}