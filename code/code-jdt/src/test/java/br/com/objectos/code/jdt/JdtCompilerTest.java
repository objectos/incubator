/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.jdt;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import objectos.util.ImmutableList;
import objectos.util.ImmutableSet;
import objectos.util.MutableList;
import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JdtCompilerTest {

  @Test
  public void compile() {
    JdtCompiler compiler = JdtCompiler.builder()
        .withSourceVersion(SourceVersion.latestSupported())
        .withCompilerRequestor(acceptResult())
        .build();
    compiler.compile(FakeCompilationUnit.HelloWorld);
  }

  @Test
  public void compile_apt() {
    JdtCompiler compiler = JdtCompiler.builder()
        .withSourceVersion(SourceVersion.latestSupported())
        .withCompilerRequestor(acceptResult())
        .withAnnotationProcessor(new EnumConstantProcessor())
        .build();
    compiler.compile(FakeCompilationUnit.TypeMarker);
  }

  @Test
  public void compile_isSubType() {
    IsSubTypeProcessor processor = new IsSubTypeProcessor();
    JdtCompiler compiler = JdtCompiler.builder()
        .withSourceVersion(SourceVersion.latestSupported())
        .withCompilerRequestor(acceptResult())
        .withAnnotationProcessor(processor)
        .build();
    compiler.compile(FakeCompilationUnit.IsSubType);
  }

  @Test(enabled = false)
  public void compile_jdtEnumEnclosedElementsBug() {
    EnumConstantProcessor processor = new EnumConstantProcessor();
    JdtCompiler compiler = JdtCompiler.builder()
        .withSourceVersion(SourceVersion.latestSupported())
        .withCompilerRequestor(acceptResult())
        .withAnnotationProcessor(processor)
        .build();
    compiler.compile(FakeCompilationUnit.TypeMarker);
    List<String> res = processor.enumConstantList();
    assertEquals(res.size(), 2);
    assertEquals(res, Arrays.asList("HTTP", "HTTPS"));
  }

  @Test
  public void methodsShouldBeListedInDeclarationOrder() {
    class MethodCollectorProcessor extends AbstractProcessingRoundProcessor {
      private ImmutableList<ProcessingMethod> methods;

      public final ImmutableList<String> getAbstractMethodNames() {
        MutableList<String> result;
        result = MutableList.create();

        for (int i = 0; i < methods.size(); i++) {
          ProcessingMethod method;
          method = methods.get(i);

          if (!method.isAbstract()) {
            continue;
          }

          String name;
          name = method.getName();

          result.add(name);
        }

        return result.toImmutableList();
      }

      @Override
      public final Set<String> getSupportedAnnotationTypes() {
        return supportedAnnotationTypes(Marker.class);
      }

      @Override
      protected final boolean process(ProcessingRound round) {
        ImmutableSet<ProcessingType> types = round.getAnnotatedTypes();
        for (ProcessingType type : types) {
          methods = type.getDeclaredMethods();
        }
        return round.claimTheseAnnotations();
      }
    }

    MethodCollectorProcessor processor;
    processor = new MethodCollectorProcessor();

    JdtCompiler compiler = JdtCompiler.builder()
        .withSourceVersion(SourceVersion.latestSupported())
        .withCompilerRequestor(acceptResult())
        .withAnnotationProcessor(processor)
        .build();

    compiler.compile(FakeCompilationUnit.MethodOrder);

    assertEquals(
      processor.getAbstractMethodNames(),
      ImmutableList.of("m6", "m5", "m4", "m3", "m2", "m1", "m0")
    );
  }

  private ICompilerRequestor acceptResult() {
    return new ICompilerRequestor() {
      @Override
      public final void acceptResult(CompilationResult result) {
        CategorizedProblem[] problems = result.getProblems();
        if (problems != null) {
          for (CategorizedProblem problem : problems) {
            System.out.println(problem);
          }
          Assert.fail("Compilation failed.");
        }
      }
    };
  }

}