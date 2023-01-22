/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCode (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.code.model.element;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.AbstractCodeModelTest;
import br.com.objectos.code.processing.type.ModelTypeMarker;
import br.com.objectos.code.util.AbstractCodeCoreTest;
import br.com.objectos.code.util.Marker1;
import br.com.objectos.code.util.PackageAnnotation;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import java.io.IOException;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;
import javax.tools.StandardLocation;
import objectos.util.UnmodifiableList;
import org.testng.annotations.Test;

public class ProcessingPackageTest extends AbstractCodeModelTest {

  @Test
  public void getDirectlyPresentAnnotations() {
    ProcessingPackage pkg00 = getProcessingPackage(ModelTypeMarker.class);

    UnmodifiableList<ProcessingAnnotation> annotations;
    annotations = pkg00.getDirectlyPresentAnnotations();

    UnmodifiableList<NamedClass> classNames;
    classNames = annotationToClassName(annotations);

    assertEquals(classNames.size(), 2);
    assertTrue(classNames.contains(NamedClass.of(Marker1.class)));
    assertTrue(classNames.contains(NamedClass.of(PackageAnnotation.class)));
  }

  @Test
  public void getResource() throws IOException {
    ProcessingPackage code;
    code = getProcessingPackage(AbstractCodeCoreTest.class);

    ProcessingResource res;
    res = code.getResource(StandardLocation.CLASS_PATH, "get-resource.txt");

    assertEquals(Read.string(res, Charsets.utf8()), "got it!");
  }

  @Test
  public void getSimpleName() {
    ProcessingPackage unnamedPackage;
    unnamedPackage = getProcessingPackage("");

    ProcessingPackage utilPackage;
    utilPackage = getProcessingPackage("br.com.objectos.code.util");

    ProcessingPackage modelTypePackage;
    modelTypePackage = getProcessingPackage(ModelTypeMarker.class);

    assertEquals(unnamedPackage.getSimpleName(), "");

    assertEquals(utilPackage.getSimpleName(), "util");

    assertEquals(modelTypePackage.getSimpleName(), "type");
  }

  @Test
  public void toNamedPackage() {
    ProcessingPackage pkg00;
    pkg00 = getProcessingPackage(ModelTypeMarker.class);

    assertEquals(
        pkg00.toNamedPackage(),
        PackageName.named("br.com.objectos.code.processing.type")
    );
  }

  private ProcessingPackage getProcessingPackage(String name) {
    Elements elements;
    elements = processingEnv.getElementUtils();

    PackageElement packageElement;
    packageElement = elements.getPackageElement(name);

    return ProcessingPackage.adapt(processingEnv, packageElement);
  }

}
