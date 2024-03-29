/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
@BeDirectory({
    IndexHtml.class
})
@BeSite(
    directories = {
        @At(path = "/", type = ItDirectory.class),
        @At(path = "/css", type = CssDirectory.class)
    }
)
package br.com.objectos.be.it;

import br.com.objectos.be.annotations.At;
import br.com.objectos.be.annotations.BeDirectory;
import br.com.objectos.be.annotations.BeSite;
import br.com.objectos.be.it.css.CssDirectory;
