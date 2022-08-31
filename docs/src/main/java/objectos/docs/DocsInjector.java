/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
import objectos.asciidoc.Document;
import objectos.util.UnmodifiableList;

abstract class DocsInjector {

  abstract AbstractFragment $bottomBar();

  abstract Document $document();

  abstract String $elink(String target);

  abstract String $href();

  abstract String $href(String key);

  abstract String $ilink(String target);

  abstract boolean $isCurrentKey(String key);

  abstract boolean $isNext();

  abstract NextBanner $nextBanner();

  abstract String $nextKey();

  abstract String $prevKey();

  abstract DocumentRecord $record(String key);

  abstract TableOfContents $tableOfContents();

  abstract DocumentTitle $title();

  abstract AbstractFragment $topBar();

  abstract UnmodifiableList<String> $trail();

  abstract String $trailTitle(String key);

  abstract Version $version();

}