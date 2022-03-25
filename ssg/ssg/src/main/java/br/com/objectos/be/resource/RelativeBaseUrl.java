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
package br.com.objectos.be.resource;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.fs.Directory;
import br.com.objectos.http.path.Location;
import java.io.IOException;

public final class RelativeBaseUrl extends BaseUrl {

  private static final RelativeBaseUrl ROOT = new RelativeBaseUrl(ImmutableList.of());

  private final ImmutableList<String> parts;

  private RelativeBaseUrl(ImmutableList<String> parts) {
    this.parts = parts;
  }

  public static RelativeBaseUrl root() {
    return ROOT;
  }

  @Override
  public final RelativeBaseUrl getChild(String path) {
    Checks.checkNotNull(path, "path == null");

    MutableList<String> builder;
    builder = MutableList.create();

    builder.addAll(parts);

    splitPathAndAddPartsToList(path, builder);

    ImmutableList<String> result;
    result = builder.toImmutableList();

    return new RelativeBaseUrl(result);
  }

  @Override
  public final Directory getDirectory(Directory target) throws IOException {
    Checks.checkNotNull(target, "target == null");

    String path;
    path = toString();

    if (path.isEmpty()) {
      return target;
    }

    return target.resolve(path).toDirectoryCreateIfNotFound();
  }

  @Override
  public final Location getLocation(String filename) {
    Location.Builder b = Location.builder();
    for (int i = 0; i < size(); i++) {
      b.fixed(get(i));
    }
    b.fixed(filename);
    return b.build();
  }

  @Override
  public final ResolvedUrl resolve(BaseUrl from) {
    if (!(from instanceof RelativeBaseUrl)) {
      throw new IllegalArgumentException("from must be a RelativeBaseUrl");
    }
    RelativeBaseUrl relative = (RelativeBaseUrl) from;
    return resolve0(relative);
  }

  @Override
  public final String toString() {
    return toStringBuilder().toString();
  }

  @Override
  final String toString(String filename) {
    return size() == 0 ? filename : toStringBuilder().append('/').append(filename).toString();
  }

  private void addPartToList(StringBuilder buffer, MutableList<String> list) {
    String part;
    part = buffer.toString();

    if (!part.isEmpty()) {
      list.add(part);
    }
  }

  private String get(int index) {
    return parts.get(index);
  }

  private boolean isParent(RelativeBaseUrl from) {
    if (from.size() > size()) {
      return false;
    }

    return isParent0(from);
  }

  private boolean isParent0(RelativeBaseUrl from) {
    for (int i = 0; i < from.size(); i++) {
      if (!get(i).equals(from.get(i))) {
        return false;
      }
    }
    return true;
  }

  private boolean isRoot(RelativeBaseUrl from) {
    return from.size() == 0;
  }

  private ResolvedUrl resolve0(RelativeBaseUrl from) {
    if (equals(from)) {
      return FilenameOnlyResolvedUrl.INSTANCE;
    }

    String prefix;

    if (isRoot(from)) {
      prefix = toString();
    }

    else if (isParent(from)) {
      prefix = resolve1Parent(from);
    }

    else {
      prefix = resolve2(from);
    }

    return new PrefixResolvedUrl(prefix);
  }

  private String resolve1Parent(RelativeBaseUrl parent) {
    StringBuilder res = new StringBuilder();

    int first = parent.size();
    res.append(get(first));

    for (int i = first + 1; i < size(); i++) {
      res.append('/');
      res.append(get(i));
    }

    return res.toString();
  }

  private String resolve2(RelativeBaseUrl from) {
    int root = 0;
    int minSize = Math.min(from.size(), size());

    for (; root < minSize; root++) {
      if (!get(root).equals(from.get(root))) {
        break;
      }
    }

    StringBuilder res = new StringBuilder();
    res.append("..");

    for (int i = from.size() - 1; i > root; i--) {
      res.append('/');
      res.append("..");
    }

    for (int i = root; i < size(); i++) {
      res.append('/');
      res.append(get(i));
    }

    return res.toString();
  }

  private int size() {
    return parts.size();
  }

  private void splitPathAndAddPartsToList(String path, MutableList<String> list) {
    if (path.isEmpty()) {
      return;
    }

    StringBuilder buffer;
    buffer = new StringBuilder();

    char[] chars;
    chars = path.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      char current = chars[i];

      switch (current) {
        case '/':
          addPartToList(buffer, list);
          buffer.setLength(0);
          break;
        default:
          buffer.append(current);
          break;
      }
    }

    addPartToList(buffer, list);
  }

  private StringBuilder toStringBuilder() {
    StringBuilder s = new StringBuilder();

    if (!parts.isEmpty()) {
      s.append(parts.get(0));
      for (int i = 1; i < size(); i++) {
        s.append('/');
        s.append(parts.get(i));
      }
    }

    return s;
  }

}