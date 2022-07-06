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

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import objectos.docs.ui.PageSwitcher;
import objectos.ssg.SiteFragment;
import objectos.util.GrowableList;
import objectos.util.UnmodifiableList;
import org.asciidoctor.ast.Document;

final class Pages {

  private final Map<String, Document> documents = new HashMap<>();

  private final Map<String, String> hrefs = new HashMap<>();

  private final Map<String, String> nextKeys = new HashMap<>();

  private final Map<String, String> prevKeys = new HashMap<>();

  private final Map<String, UnmodifiableList<String>> trails = new HashMap<>();

  private final GrowableList<String> trailBuilder = new GrowableList<>();

  private final GrowableList<String> partsBuilder = new GrowableList<>();

  final SiteFragment prevNext = new PageSwitcher();

  private String current;

  private String slug;

  private String baseHref;

  public Pages() {}

  public final void current(String key) {
    current = key;
  }

  public final Document document() {
    return document(current);
  }

  public final Document document(String key) {
    return get0(documents, key);
  }

  public final String href() {
    return href(current);
  }

  public final String href(String key) {
    return get0(hrefs, key);
  }

  public final String nextKey() {
    return nextKey(current);
  }

  public final String nextKey(String key) {
    return nextKeys.get(key);
  }

  public final String prevKey() {
    return prevKey(current);
  }

  public final String prevKey(String key) {
    return prevKeys.get(key);
  }

  public final void put(String key) {
    var href = baseHref + "/" + slug + "/" + key + ".html";

    hrefs.put(key, href);

    if (current != null) {
      prevKeys.put(key, current);

      nextKeys.put(current, key);
    }

    current = key;

    var parts = key.split("/");

    if (parts.length == 1) {
      trails.put(key, UnmodifiableList.of(key));

      return;
    }

    partsBuilder.clear();
    trailBuilder.clear();

    trailBuilder.add("index");

    var lastIndex = parts.length - 1;

    for (int i = 0; i < lastIndex; i++) {
      var part = parts[i];

      partsBuilder.add(part);

      var joined = partsBuilder.join("/", "", "/index");

      trailBuilder.add(joined);
    }

    var last = parts[lastIndex];

    if (!last.equals("index")) {
      partsBuilder.add(last);

      var joined = partsBuilder.join("/");

      trailBuilder.add(joined);
    }

    trails.put(key, trailBuilder.toUnmodifiableList());
  }

  public final void reset(String baseHref, String slug) {
    this.baseHref = baseHref;

    this.slug = slug;

    current = null;

    documents.clear();

    hrefs.clear();

    trails.clear();

    trailBuilder.clear();

    partsBuilder.clear();
  }

  public final void set(String key, Document document) {
    documents.put(key, document);
  }

  public final String templateName() {
    var document = document(current);

    return (String) document.getAttribute("template", "ArticlePage");
  }

  public final String title(String key) {
    var doc = document(key);

    return doc.getDoctitle();
  }

  public final UnmodifiableList<String> trail() {
    return trail(current);
  }

  public final UnmodifiableList<String> trail(String key) {
    return get0(trails, key);
  }

  public final void unset() {
    current = null;
  }

  private <T> T get0(Map<String, T> map, String key) {
    T value = map.get(key);

    if (value == null) {
      throw new NoSuchElementException(key);
    }

    return value;
  }

}