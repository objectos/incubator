/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import org.testng.annotations.Test;

public class HashCodeTest {

  @Test
  public void hashCodeTest() {
    Object o1 = "one";
    Object o2 = Long.MAX_VALUE;
    Object o3 = Integer.valueOf(3);
    Object o4 = Arrays.asList(1, 2, 3, 4);
    Object o5 = new File("abc.txt");
    Object o6 = "six";
    Object o7 = "seven";

    assertEquals(
        HashCode.hashCode(o1, o2),
        hc0(
            hc0(
                HashCode.HASH_CODE_START, hc(o1)
            ), hc(o2)
        )
    );

    assertEquals(
        HashCode.hashCode(o1, o2, o3),
        hc0(
            hc0(
                hc0(
                    HashCode.HASH_CODE_START, hc(o1)
                ), hc(o2)),
            hc(o3)
        )
    );

    assertEquals(
        HashCode.hashCode(o1, o2, o3, o4),
        hc0(
            hc0(
                hc0(
                    hc0(
                        HashCode.HASH_CODE_START, hc(o1)
                    ), hc(o2)
                ), hc(o3)
            ), hc(o4)
        )
    );

    assertEquals(
        HashCode.hashCode(o1, o2, o3, o4, o5),
        hc0(
            hc0(
                hc0(
                    hc0(
                        hc0(
                            HashCode.HASH_CODE_START, hc(o1)
                        ), hc(o2)
                    ), hc(o3)
                ), hc(o4)
            ), hc(o5)
        )
    );

    assertEquals(
        HashCode.hashCode(o1, o2, o3, o4, o5, o6),
        hc0(
            hc0(
                hc0(
                    hc0(
                        hc0(
                            hc0(
                                HashCode.HASH_CODE_START, hc(o1)
                            ), hc(o2)
                        ), hc(o3)
                    ), hc(o4)
                ), hc(o5)
            ), hc(o6)
        )
    );

    assertEquals(
        HashCode.hashCode(o1, o2, o3, o4, o5, o6, o7),
        hc0(
            hc0(
                hc0(
                    hc0(
                        hc0(
                            hc0(
                                hc0(
                                    HashCode.HASH_CODE_START, hc(o1)
                                ), hc(o2)
                            ), hc(o3)
                        ), hc(o4)
                    ), hc(o5)
                ), hc(o6)
            ), hc(o7)
        )
    );
  }

  private int hc(Object o) {
    return HashCode.hashCode(o);
  }

  private int hc0(int partial, int hashCode) {
    return HashCode.hashCodeUpdate(partial, hashCode);
  }

}