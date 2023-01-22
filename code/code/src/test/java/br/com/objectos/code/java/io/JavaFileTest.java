/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCode (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.code.java.io;

import static br.com.objectos.code.java.declaration.ClassCode._class;
import static br.com.objectos.code.java.declaration.EnumCode._enum;
import static br.com.objectos.code.java.declaration.ExtendsOne._extends;
import static br.com.objectos.code.java.declaration.InterfaceCode._interface;
import static br.com.objectos.code.java.declaration.Modifiers._public;
import static br.com.objectos.code.java.expression.Expressions.id;
import static br.com.objectos.code.java.io.JavaFile.javaFile;
import static br.com.objectos.code.java.type.NamedTypeParameter.typeParam;
import static br.com.objectos.code.java.type.NamedTypes.t;
import static br.com.objectos.tools.Tools.compilationUnit;
import static br.com.objectos.tools.Tools.javac;
import static br.com.objectos.tools.Tools.processor;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.declaration.PackageNameFake;
import br.com.objectos.code.util.AbstractCodeJavaTest;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.fs.testing.TmpDir;
import br.com.objectos.tools.Compilation;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaFileTest extends AbstractCodeJavaTest {

  private Directory tempDir;

  @Test
  public void className() {
    assertEquals(
        javaFile(TESTING_CODE, _class(id("A"))).className(),
        TESTING_CODE.nestedClass("A")
    );
    assertEquals(
        javaFile(TESTING_OTHER, _enum(id("B"))).className(),
        TESTING_OTHER.nestedClass("B")
    );
    assertEquals(
        javaFile(TESTING_OTHER, _interface(id("C"))).className(),
        TESTING_OTHER.nestedClass("C")
    );
  }

  @Test(
      description = ""
          + "verify that the type parameters of class "
          + "correctly generate import declarations"
  )
  public void classWithTypeParametersThatGenerateImport() {
    testToString(
        _class(
            id("Subject"),
            typeParam("I", t(InputStream.class), t(Closeable.class))
        ).toJavaFile(TESTING_CODE),
        "package testing.code;",
        "",
        "import java.io.Closeable;",
        "import java.io.InputStream;",
        "",
        "class Subject<I extends InputStream & Closeable> {}"
    );
  }

  @Test(description = ""
      + "javaFile shorthand should honor _class() shorthand")
  public void javaFileWithClass() {
    testToString(
        javaFile(
            TESTING_CODE,
            _class(id("Subject"))
        ),
        "package testing.code;",
        "",
        "class Subject {}"
    );
    testToString(
        javaFile(
            TESTING_CODE,
            _class(id("Subject"), _extends(TESTING_CODE.nestedClass("Super")))
        ),
        "package testing.code;",
        "",
        "class Subject extends Super {}"
    );
  }

  @BeforeMethod
  public void setUpTempDir() throws IOException {
    tempDir = TmpDir.create();
  }

  @Test(description = ""
      + "it should return the simpleName of the file, ie, "
      + "the filename without the .java extension")
  public void simpleName() {
    assertEquals(javaFile(TESTING_CODE, _class(id("A"))).simpleName(), "A");
    assertEquals(javaFile(TESTING_OTHER, _enum(id("B"))).simpleName(), "B");
    assertEquals(javaFile(TESTING_OTHER, _interface(id("C"))).simpleName(), "C");
  }

  @Test(description = ""
      + "JavaFile built with an unnamed package should not render the package statement.")
  public void unnamedPackage() {
    testToString(
        JavaFile.builder().addType(ClassCode.builder().build()).build(),
        "class Unnamed {}"
    );
  }

  @Test(description = ""
      + "it should create the directory (package) structure if needed."
      + "it should write itself to a new file (or overwrite it if it exists).")
  public void writeToDirectory() throws IOException {
    ClassCode clazzCode = _class(id("WriteToDirectory"));

    PackageName a = PackageName.named("code.a");
    PackageName b = PackageName.named("code.a.b");
    PackageName c = PackageName.named("code.a.b.c");

    javaFile(a, clazzCode).writeTo(tempDir);
    javaFile(b, clazzCode).writeTo(tempDir);
    javaFile(c, clazzCode).writeTo(tempDir);

    ResolvedPath codeA;
    codeA = tempDir.resolve("code", "a");

    codeA.createParents();

    Directory aDir = codeA.toDirectory();

    testToString(
        Read.string(aDir.getRegularFile("WriteToDirectory.java"), Charsets.utf8()),
        "package code.a;",
        "",
        "class WriteToDirectory {}"
    );

    Directory bDir = aDir.getDirectory("b");
    testToString(
        Read.string(bDir.getRegularFile("WriteToDirectory.java"), Charsets.utf8()),
        "package code.a.b;",
        "",
        "class WriteToDirectory {}"
    );

    Directory cDir = bDir.getDirectory("c");
    testToString(
        Read.string(cDir.getRegularFile("WriteToDirectory.java"), Charsets.utf8()),
        "package code.a.b.c;",
        "",
        "class WriteToDirectory {}"
    );

    javaFile(c, _class(_public(), id("WriteToDirectory"))).writeTo(tempDir);

    testToString(
        Read.string(cDir.getRegularFile("WriteToDirectory.java"), Charsets.utf8()),
        "package code.a.b.c;",
        "",
        "public class WriteToDirectory {}"
    );
  }

  @Test(description = ""
      + "it should write its contents to a javax.ann.proc.Filer")
  public void writeToFiler() {
    Compilation compilation = javac(
        processor(new WriteToProcessor()),
        compilationUnit(
            "package testing.code;",
            "class Dummy {}"
        )
    );
    assertTrue(compilation.wasSuccessful());
    testToString(
        compilation.getJavaFile("testing.code.Subject"),
        "package testing.code;",
        "",
        "class Subject {}"
    );
  }

  private class WriteToProcessor extends AbstractProcessor {
    @Override
    public final Set<String> getSupportedAnnotationTypes() {
      return Collections.singleton("*");
    }

    @Override
    public final boolean process(
        Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      if (roundEnv.processingOver()) {
        ClassCode code = _class(id("Subject"));
        JavaFile file = code.toJavaFile(PackageNameFake.TESTING_CODE);
        write(file);
      }
      return false;
    }

    private void write(JavaFile file) {
      try {
        file.writeTo(processingEnv.getFiler());
      } catch (IOException e) {
        processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage());
      }
    }
  }

}