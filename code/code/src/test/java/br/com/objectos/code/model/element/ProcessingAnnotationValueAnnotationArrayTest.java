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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import objectos.util.ImmutableList;
import objectos.util.MutableList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProcessingAnnotationValueAnnotationArrayTest
    extends
    AbstractProcessingAnnotationValueTest {

  @Test
  public void getDeclaredOrDefaultValue() {
    assertEquals(
        getDeclaredOrDefaultValue(defaults()),
        ImmutableList.of()
    );

    assertEquals(
        getDeclaredOrDefaultValue(noDefaults()),
        ImmutableList.of("A")
    );

    assertEquals(
        getDeclaredOrDefaultValue(withDefaults()),
        ImmutableList.of("1", "2")
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
        ImmutableList.of("A")
    );

    assertEquals(
        getDeclaredValue(withDefaults()),
        ImmutableList.of("1", "2")
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

  private ProcessingAnnotation defaults() {
    return getDirectlyPresentAnnotation(Defaults.class, WithDefaults.class);
  }

  private ImmutableList<String> getDeclaredOrDefaultValue(ProcessingAnnotation annotation) {
    ProcessingAnnotationValue value;
    value = getDeclaredOrDefaultValue(annotation, "value");

    return tn(value.getAnnotationArray());
  }

  private ImmutableList<String> getDeclaredValue(ProcessingAnnotation annotation) {
    ProcessingAnnotationValue value;
    value = getDeclaredValue(annotation, "value");

    return tn(value.getAnnotationArray());
  }

  private ImmutableList<String> getDefaultValue(ProcessingAnnotation annotation) {
    ProcessingAnnotationValue value;
    value = getDefaultValue(annotation, "value");

    return tn(value.getAnnotationArray());
  }

  private ProcessingAnnotation noDefaults() {
    return getDirectlyPresentAnnotation(Subject.class, NoDefaults.class);
  }

  private ImmutableList<String> tn(ImmutableList<ProcessingAnnotation> iter) {
    MutableList<String> result;
    result = MutableList.create();

    for (int i = 0; i < iter.size(); i++) {
      ProcessingAnnotation ann;
      ann = iter.get(i);

      ProcessingAnnotationValue value;
      value = ann.getDeclaredValue("value");

      String s;
      s = value.getString();

      result.add(s);
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
    Value[] value();
  }

  @NoDefaults({@Value("A")})
  @WithDefaults({@Value("1"), @Value("2")})
  private static class Subject {}

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.ANNOTATION_TYPE)
  private @interface Value {
    String value();
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  private @interface WithDefaults {
    Value[] value() default {};
  }

}