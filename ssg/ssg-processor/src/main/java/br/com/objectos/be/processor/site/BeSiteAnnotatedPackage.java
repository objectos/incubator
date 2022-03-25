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
package br.com.objectos.be.processor.site;

import br.com.objectos.be.annotations.BeSite;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.processing.type.ErrorTypeException;
import br.com.objectos.core.list.ImmutableList;
import java.io.IOException;

public class BeSiteAnnotatedPackage {

  private final ProcessingAnnotation annotation;
  private final JavaFileConsumer javaFileConsumer;

  private final SiteGenerator siteGenerator;

  private BeSiteAnnotatedPackage(JavaFileConsumer javaFileConsumer,
                                 ProcessingAnnotation annotation,
                                 SiteGenerator siteGenerator) {
    this.javaFileConsumer = javaFileConsumer;
    this.annotation = annotation;
    this.siteGenerator = siteGenerator;
  }

  public static void process(
      JavaFileConsumer javaFileConsumer, ProcessingPackage annotatedPackage)
      throws ErrorTypeException, IOException {
    ProcessingAnnotation beSiteAnnotation;
    beSiteAnnotation = annotatedPackage.getDirectlyPresentAnnotation(BeSite.class);

    PackageName namedPackage;
    namedPackage = annotatedPackage.toNamedPackage();

    SiteGenerator siteGenerator;
    siteGenerator = SiteGenerator.build(namedPackage);

    BeSiteAnnotatedPackage beSitePackage;
    beSitePackage = new BeSiteAnnotatedPackage(javaFileConsumer, beSiteAnnotation, siteGenerator);

    beSitePackage.process();
  }

  private void process() throws ErrorTypeException, IOException {
    ImmutableList<AtDirectory> directories;
    directories = AtDirectory.fromBeSiteAnnotation(annotation);

    for (int i = 0; i < directories.size(); i++) {
      AtDirectory directory;
      directory = directories.get(i);

      siteGenerator.addAtDirectory(directory);
    }

    siteGenerator.acceptJavaFileConsumer(javaFileConsumer);
  }

}
