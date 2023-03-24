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
package objectos.docs.internal;

import java.time.LocalDate;
import objectos.docs.internal.Navigation.NavigationOption;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

public class Version {

  sealed interface VersionOption {
    void accept(Builder builder);
  }

  private static class Builder {

    String name;

    String directory;

    LocalDate releaseDate;

    Status status;

    GrowableMap<String, Navigation> navigationMap = new GrowableMap<>();

    public final Version build() {
      if (directory == null) {
        directory = name;
      }

      return new Version(
        name, directory, releaseDate, status,
        navigationMap.toUnmodifiableMap()
      );
    }

  }

  private record NameOption(String value) implements VersionOption {
    @Override
    public void accept(Builder builder) {
      builder.name = value;
    }
  }

  private record ReleaseDateOption(LocalDate value) implements VersionOption {
    @Override
    public void accept(Builder builder) {
      builder.releaseDate = value;
    }
  }

  private record StatusOption(Status value) implements VersionOption {
    @Override
    public void accept(Builder builder) {
      builder.status = value;
    }
  }

  private record NavigationValue(Navigation value) implements VersionOption {
    @Override
    public void accept(Builder builder) {
      var name = value.name;

      builder.navigationMap.put(name, value);
    }
  }

  private record DirectoryOption(String value) implements VersionOption {
    @Override
    public void accept(Builder builder) {
      builder.directory = value;
    }
  }

  final String name;

  final String directory;

  final LocalDate releaseDate;

  final Status status;

  final UnmodifiableMap<String, Navigation> navigationMap;

  Version(String name,
          String directory,
          LocalDate releaseDate,
          Status status,
          UnmodifiableMap<String, Navigation> navigationMap) {
    this.name = name;
    this.directory = directory;
    this.releaseDate = releaseDate;
    this.status = status;
    this.navigationMap = navigationMap;
  }

  public static Version create(VersionOption... options) {
    var builder = new Builder();

    for (var option : options) {
      option.accept(builder);
    }

    return builder.build();
  }

  public static VersionOption directory(String name) {
    return new DirectoryOption(name);
  }

  public static NavigationOption link(String iref) {
    return Navigation.link(iref);
  }

  public static NavigationOption link(String iref, NavigationOption... options) {
    return Navigation.link(iref, options);
  }

  public static NavigationOption link(String iref, String title) {
    return Navigation.link(iref, title);
  }

  public static VersionOption name(String string) {
    return new NameOption(string);
  }

  public static VersionOption navigation(NavigationOption... options) {
    return new NavigationValue(
      Navigation.create("default", options)
    );
  }

  public static VersionOption navigation(String name, NavigationOption... options) {
    return new NavigationValue(
      Navigation.create(name, options)
    );
  }

  public static VersionOption releaseDate(int year, int month, int day) {
    return new ReleaseDateOption(
      LocalDate.of(year, month, day)
    );
  }

  public static NavigationOption section(String name, NavigationOption... options) {
    return Navigation.section(name, options);
  }

  public static VersionOption status(Status value) {
    return new StatusOption(value);
  }

  public final Navigation getNavigation(String key) {
    var navigation = navigationMap.get("default");

    if (navigationMap.size() > 1) {
      int offset = directory.length() + 2;

      int slash = key.indexOf('/', offset);

      if (slash > 0) {
        var name = key.substring(offset, slash);

        navigation = navigationMap.getOrDefault(name, navigation);
      }
    }

    return navigation;
  }

  public final String leftBarLink(String iref) {
    return "/" + directory + iref + ".html";
  }

  @Override
  public final String toString() {
    return "Version[" + name + "]";
  }

  final String releaseDateString() {
    return releaseDate == null
        ? "unreleased"
        : releaseDate.toString();
  }

}