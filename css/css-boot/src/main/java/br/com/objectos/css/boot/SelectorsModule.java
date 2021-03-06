/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.boot;

import br.com.objectos.css.boot.spec.CssSpec;
import br.com.objectos.html.element.StandardElementName;

class SelectorsModule extends CssSpec {

  @Override
  protected final void definition() {
    for (int code = 0; code < StandardElementName.size(); code++) {
      StandardElementName elementName;
      elementName = StandardElementName.getByCode(code);

      elementName(elementName.getName());
    }

    pseudoClasses(
        "active",
        "any-link",
        "blank",
        "checked",
        "current",
        "default",
        "defined",
        "disabled",
        "drop",
        "empty",
        "enabled",
        "first",
        "first-child",
        "first-of-type",
        "fullscreen",
        "future",
        "focus",
        "focus-visible",
        "focus-within",
        "host",
        "hover",
        "indeterminate",
        "in-range",
        "invalid",
        "last-child",
        "last-of-type",
        "left",
        "link",
        "local-link",
        "only-child",
        "only-of-type",
        "optional",
        "out-of-range",
        "past",
        "placeholder-shown",
        "read-only",
        "read-write",
        "required",
        "right",
        "root",
        "scope",
        "target",
        "target-within",
        "user-invalid",
        "valid",
        "visited",

        "-moz-focusring"
    );

    pseudoElements(
        "after",
        "backdrop",
        "before",
        "cue",
        "first-letter",
        "first-line",
        "grammar-error",
        "marker",
        "placeholder",
        "selection",
        "spelling-error",

        "-moz-focus-inner",
        "-webkit-inner-spin-button",
        "-webkit-outer-spin-button",
        "-webkit-search-decoration",
        "-webkit-file-upload-button"
    );
  }

}
