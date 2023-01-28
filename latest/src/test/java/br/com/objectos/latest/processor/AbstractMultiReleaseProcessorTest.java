/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

abstract class AbstractMultiReleaseProcessorTest implements IHookable {

  private static final String LINE_SEPARATOR = System.getProperty("line.separator");

  private JavaCompiler compiler;

  private TestingJavaFileManager fileManager;

  @Override
  public final void run(IHookCallBack callBack, ITestResult result) {
    compiler = ToolProvider.getSystemJavaCompiler();

    fileManager = null;

    try {

      fileManager = new TestingJavaFileManager(compiler);

      callBack.runTestMethod(result);

    } finally {
      if (fileManager != null) {
        try {
          fileManager.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  final JavaFileObject getGeneratedJavaFile(String className) {
    return fileManager.getGeneratedJavaFile(className);
  }

  final boolean isJava(int... versions) {
    SourceVersion latest;
    latest = SourceVersion.latest();

    int latestVersion;
    latestVersion = latest.ordinal();

    for (int version : versions) {
      if (version == latestVersion) {
        return true;
      }
    }

    return false;
  }

  final JavaFileObject javaFileObject(String qualifiedName, String... lines) {
    return new StringJavaFileObject(
        qualifiedName,

        join(lines)
    );
  }

  final void process(Processor processor, JavaFileObject... files) {
    List<JavaFileObject> fileList;
    fileList = Arrays.asList(files);

    CompilationHelper helper;
    helper = getHelper();

    Iterable<String> options;
    options = helper.getOptions();

    CompilationTask task;
    task = compiler.getTask(null, fileManager, null, options, null, fileList);

    helper.configureModulesIfPossible(task);

    Set<Processor> processors;
    processors = Collections.singleton(processor);

    task.setProcessors(processors);

    Boolean result;
    result = task.call();

    assertTrue(result.booleanValue());
  }

  final void testToString(Object o, String... expected) {
    String actual;
    actual = o.toString();

    String joined;
    joined = join(expected);

    assertEquals(actual, joined);
  }

  private CompilationHelper getHelper() {
    try {
      String packageName;
      packageName = getClass().getPackage().getName();

      String implName;
      implName = packageName + '.' + getHelperImplName();

      ClassLoader classLoader;
      classLoader = Thread.currentThread().getContextClassLoader();

      Class<?> helperClass;
      helperClass = classLoader.loadClass(implName);

      Constructor<?> constructor;
      constructor = helperClass.getDeclaredConstructor();

      return (CompilationHelper) constructor.newInstance();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    } catch (SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  private String getHelperImplName() {
    SourceVersion latest;
    latest = SourceVersion.latestSupported();

    int version;
    version = latest.ordinal();

    if (version < 6) {
      throw new UnsupportedOperationException();
    }

    else if (version <= 8) {
      return "CompilationHelperJava" + version;
    }

    else if (version <= 11) {
      return "CompilationHelperJava11";
    }

    else {
      return "CompilationHelperJava17";
    }
  }

  private String join(String... expected) {
    StringBuilder result;
    result = new StringBuilder();

    if (expected.length != 0) {
      String first;
      first = expected[0];

      result.append(first);

      for (int i = 1; i < expected.length; i++) {
        result.append(LINE_SEPARATOR);

        String next;
        next = expected[i];

        result.append(next);
      }
    }

    return result.toString();
  }

}
