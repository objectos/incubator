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
package br.com.objectos.be.processor.testing.iter02;

import br.com.objectos.be.resource.AbstractHtmlResource;
import br.com.objectos.be.resource.AbstractPath;
import br.com.objectos.be.resource.ResolvedUrl;

public class Iter02Path extends AbstractPath {

  public Iter02Path(ResolvedUrl resolvedUrl) {
    super(resolvedUrl);
  }
  
  public final Index02Html index02Html() {
    return new HtmlImpl(get("index02.html"));
  }

  private static class HtmlImpl extends AbstractHtmlResource implements Index02Html {

    HtmlImpl(String src) {
      super(src);
    }

  }

}
