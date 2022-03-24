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

import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProcessingAnnotationValueTypeArrayTest extends AbstractProcessingAnnotationValueTest {

  @Test
  public void getDeclaredOrDefaultValue() {
    assertEquals(
        getDeclaredOrDefaultValue(defaults()),
        ImmutableList.of()
    );

    assertEquals(
        getDeclaredOrDefaultValue(noDefaults()),
        ImmutableList.of(cn(Void.class))
    );

    assertEquals(
        getDeclaredOrDefaultValue(withDefaults()),
        ImmutableList.of(cn(Integer.class), cn(Long.class))
    );
  }

  @Test
  public void getDeclaredValue() {
    try {
      getDeclaredValue(defaults());
      Assert.fail();
    } catch (NoDeclaredValueException expected) {
      assertTrue(expected.getMessage().contains("value"));
    }

    assertEquals(
        getDeclaredValue(noDefaults()),
        ImmutableList.of(cn(Void.class))
    );

    assertEquals(
        getDeclaredValue(withDefaults()),
        ImmutableList.of(cn(Integer.class), cn(Long.class))
    );
  }

  @Test
  public void getDefaultValue() {
    assertEquals(
        getDefaultValue(defaults()),
        ImmutableList.of()
    );

    try {
      getDefaultValue(noDefaults());
      Assert.fail();
    } catch (NoDefaultValueException expected) {
      assertTrue(expected.getMessage().contains("value"));
    }

    assertEquals(
        getDefaultValue(withDefaults()),
        ImmutableList.of()
    );
  }

  private NamedClass cn(Class<?> type) {
    return NamedClass.of(type);
  }

  private ProcessingAnnotation defaults() {
    return getDirectlyPresentAnnotation(Defaults.class, WithDefaults.class);
  }

  private ImmutableList<NamedType> getDeclaredOrDefaultValue(ProcessingAnnotation annotation) {
    ProcessingAnnotationValue value;
    value = getDeclaredOrDefaultValue(annotation, "value");

    return tn(value.getTypeArray());
  }

  private ImmutableList<NamedType> getDeclaredValue(ProcessingAnnotation annotation) {
    ProcessingAnnotationValue value;
    value = getDeclaredValue(annotation, "value");

    return tn(value.getTypeArray());
  }

  private ImmutableList<NamedType> getDefaultValue(ProcessingAnnotation annotation) {
    ProcessingAnnotationValue value;
    value = getDefaultValue(annotation, "value");

    return tn(value.getTypeArray());
  }

  private ProcessingAnnotation noDefaults() {
    return getDirectlyPresentAnnotation(Subject.class, NoDefaults.class);
  }

  private ImmutableList<NamedType> tn(List<PTypeMirror> iter) {
    MutableList<NamedType> result;
    result = MutableList.create();

    for (int i = 0; i < iter.size(); i++) {
      PTypeMirror modelType = iter.get(i);

      NamedType name = modelType.getName();

      result.add(name);
    }

    return result.toImmutableList();
  }

  private ProcessingAnnotation withDefaults() {
    return getDirectlyPresentAnnotation(Subject.class, WithDefaults.class);
  }

  @WithDefaults
  private static class Defaults {}

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  private @interface NoDefaults {
    Class<?>[] value();
  }

  @NoDefaults({Void.class})
  @WithDefaults({Integer.class, Long.class})
  private static class Subject {}

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  private @interface WithDefaults {
    Class<?>[] value() default {};
  }

}