/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.specgen.mdn;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import br.com.objectos.css.specgen.spec.Property;
import br.com.objectos.css.specgen.spec.Spec;
import br.com.objectos.css.specgen.spec.Spec.Builder;
import br.com.objectos.css.specgen.spec.ValueType;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import objectos.util.UnmodifiableList;
import objectos.util.GrowableList;

public class Mdn {

  private final BaseUrl baseUrl;
  private final String resourcesDir;

  private Mdn(String baseUrl, String resourcesDir) {
    this.baseUrl = new LoggingBaseUrl(baseUrl);
    this.resourcesDir = resourcesDir;
  }

  public static Spec load() throws IOException {
    Builder b = Spec.builder();

    Resource properties = Resource.getResource("mdn-properties.txt");

    UnmodifiableList<String> lines = Read.lines(properties, Charsets.utf8());

    for (String propertyLine : lines) {
      if (isNotEmpty(propertyLine)) {
        b.addProperty(loadProperty(propertyLine));
      }
    }

    return b.build();
  }

  public static void main(String[] args) {
    String baseUrl = args[0];
    String resourcesDir = args[1];

    System.out.println("Will run with options:");
    System.out.println("  baseUrl=" + baseUrl);
    System.out.println("  resourcesDir=" + resourcesDir);

    try {
      Mdn mdn = new Mdn(baseUrl, resourcesDir);
      mdn.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static boolean isNotEmpty(String s) {
    return !s.isEmpty();
  }

  private static Property loadProperty(String line) throws IOException {
    String[] parts = line.split(":");
    String propertyName = parts[0];
    return new Property(
        propertyName,
        parts[1],
        loadValueTypes(propertyName)
    );
  }

  private static UnmodifiableList<ValueType> loadValueTypes(String property) throws IOException {
    GrowableList<ValueType> types = new GrowableList<>();

    Resource resource = Resource.getResource("mdn/" + property);

    UnmodifiableList<String> lines = Read.lines(resource, Charsets.utf8());

    for (String line : lines) {
      if (isNotEmpty(line)) {
        String[] parts = line.split(":");

        types.add(new ValueType(parts[0], parts[1]));
      }
    }

    return types.toUnmodifiableList();
  }

  private void execute() throws IOException {
    MdnCrawler crawler = new MdnCrawler(baseUrl);
    Spec spec = crawler.crawl();

    Path resourcesPath = Paths.get(resourcesDir);

    Path mdnPath = resourcesPath.resolve("mdn");
    Files.createDirectory(mdnPath);

    Path mdnPropertiesPath = resourcesPath.resolve("mdn-properties.txt");

    try (BufferedWriter mdnPropertiesWriter = Files.newBufferedWriter(
        mdnPropertiesPath,
        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
      for (Property property : spec.properties()) {
        mdnPropertiesWriter.write(property.write());
        mdnPropertiesWriter.newLine();
        writeProperty(mdnPath, property);
      }
    }

  }

  private void writeProperty(Path mdnPath, Property property) throws IOException {
    Path propertyPath = mdnPath.resolve(property.name());
    try (BufferedWriter writer = Files.newBufferedWriter(
        propertyPath,
        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
      for (ValueType type : property.valueTypes()) {
        writer.write(type.write());
        writer.newLine();
      }
    }
  }

  private static class LoggingBaseUrl extends BaseUrl {

    public LoggingBaseUrl(String value) {
      super(value);
    }

    @Override
    final void log(String url) {
      System.out.println("fetch: " + url);
    }

  }

}
