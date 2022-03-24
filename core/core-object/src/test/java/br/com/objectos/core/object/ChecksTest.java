/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ChecksTest {

  @Test
  public void checkArgument() {
    try {
      Checks.checkArgument(true, "should not happen");
    } catch (IllegalArgumentException e) {
      Assert.fail();
    }

    try {
      Checks.checkArgument(false, "failed");

      Assert.fail();
    } catch (IllegalArgumentException expected) {
      assertEquals(expected.getMessage(), "failed");
    }

    try {
      Checks.checkArgument(false, "number 1 ", "letter A");

      Assert.fail();
    } catch (IllegalArgumentException expected) {
      assertEquals(expected.getMessage(), "number 1 letter A");
    }
  }

  @Test
  public void checkNotNull() {
    Object notNull;
    notNull = Boolean.TRUE;

    try {
      Checks.checkNotNull(notNull, "Should not happen");
    } catch (NullPointerException e) {
      Assert.fail();
    }

    try {
      Checks.checkNotNull(null, "foo is null");

      Assert.fail();
    } catch (NullPointerException expected) {
      assertEquals(expected.getMessage(), "foo is null");
    }

    try {
      Checks.checkNotNull(null, "foo ", "is null");

      Assert.fail();
    } catch (NullPointerException expected) {
      assertEquals(expected.getMessage(), "foo is null");
    }

    try {
      Checks.checkNotNull(null, "elements[", 10, "] == null");

      Assert.fail();
    } catch (NullPointerException expected) {
      assertEquals(expected.getMessage(), "elements[10] == null");
    }
  }

  @Test
  public void checkState() {
    try {
      Checks.checkState(true, "Should not happen");
    } catch (IllegalStateException e) {
      Assert.fail();
    }

    try {
      Checks.checkState(false, "state not valid");

      Assert.fail();
    } catch (IllegalStateException expected) {
      assertEquals(expected.getMessage(), "state not valid");
    }

    try {
      Checks.checkState(false, "number 1 ", "letter A");

      Assert.fail();
    } catch (IllegalStateException expected) {
      assertEquals(expected.getMessage(), "number 1 letter A");
    }
  }

}