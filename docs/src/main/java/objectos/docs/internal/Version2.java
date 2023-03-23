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
import objectos.util.GrowableList;
import objectos.util.UnmodifiableList;

public class Version2 {

  sealed interface VersionOption {
    void accept(Builder builder);
  }

  private static class Builder {

    String name;

    String directory;

    LocalDate releaseDate;

    Status status;

    GrowableList<Navigation> navigationList = new GrowableList<>();

    public final Version2 build() {
      if (directory == null) {
        directory = name;
      }

      return new Version2(
        name, directory, releaseDate, status,
        navigationList.toUnmodifiableList()
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
      builder.navigationList.add(value);
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

  final UnmodifiableList<Navigation> navigationList;

  Version2(String name,
           String directory,
           LocalDate releaseDate,
           Status status,
           UnmodifiableList<Navigation> navigationList) {
    this.name = name;
    this.directory = directory;
    this.releaseDate = releaseDate;
    this.status = status;
    this.navigationList = navigationList;
  }

  public static Version2 create(VersionOption... options) {
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

}