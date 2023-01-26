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
package objectos.docs;

import br.com.objectos.html.tmpl.AbstractFragment;

final class LeftBar {

  private final LeftBarNext next = new LeftBarNext(this);

  private final LeftBarV0003 v0003 = new LeftBarV0003(this);

  private final LeftBarV0002 v0002 = new LeftBarV0002(this);

  private final LeftBarV0001 v0001 = new LeftBarV0001(this);

  private final DocsInjector injector;

  public LeftBar(DocsInjector injector) { this.injector = injector; }

  public final AbstractFragment get(String key, Version version) {
    return switch (version) {
      case NEXT -> next.get(key);

      case V0_3_0 -> v0003;

      case V0_2_0 -> v0002;

      case V0_1_0 -> v0001;
    };
  }

  final String $elink(String target) { return injector.$elink(target); }

  final boolean $isCurrentKey(String key) { return injector.$isCurrentKey(key); }

  final DocumentRecord $record(String key) { return injector.$record(key); }

  final Version $version() { return injector.$version(); }

}