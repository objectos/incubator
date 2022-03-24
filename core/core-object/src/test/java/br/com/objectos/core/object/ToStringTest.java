/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * This file is part of the Objectos Core :: Object (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.core.object;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class ToStringTest {

  @Test(description = ""
      + "Single level"
  )
  public void testCase01() {
    // 0
    test(
        new TSObject0(),
        "TSObject0[]"
    );
    test(
        new TSObject0("X"),
        "X[]"
    );

    // 1
    test(
        new TSObject1(
            "A", "1"
        ),
        "TSObject1[",
        "  A=1",
        "]"
    );
    test(
        new TSObject1("X",
            "A", "1"
        ),
        "X[",
        "  A=1",
        "]"
    );

    // 2
    test(
        new TSObject2(
            "A", "1",
            "B", "2"
        ),
        "TSObject2[",
        "  A=1",
        "  B=2",
        "]"
    );
    test(
        new TSObject2("X",
            "A", "1",
            "B", "2"
        ),
        "X[",
        "  A=1",
        "  B=2",
        "]"
    );

    // 3
    test(
        new TSObject3(
            "A", "1",
            "B", "2",
            "C", "3"
        ),
        "TSObject3[",
        "  A=1",
        "  B=2",
        "  C=3",
        "]"
    );
    test(
        new TSObject3("X",
            "A", "1",
            "B", "2",
            "C", "3"
        ),
        "X[",
        "  A=1",
        "  B=2",
        "  C=3",
        "]"
    );

    // 4
    test(
        new TSObject4(
            "A", "1",
            "B", "2",
            "C", "3",
            "D", "4"
        ),
        "TSObject4[",
        "  A=1",
        "  B=2",
        "  C=3",
        "  D=4",
        "]"
    );
    test(
        new TSObject4("X",
            "A", "1",
            "B", "2",
            "C", "3",
            "D", "4"
        ),
        "X[",
        "  A=1",
        "  B=2",
        "  C=3",
        "  D=4",
        "]"
    );

    // 5
    test(
        new TSObject5(
            "A", "1",
            "B", "2",
            "C", "3",
            "D", "4",
            "E", "5"
        ),
        "TSObject5[",
        "  A=1",
        "  B=2",
        "  C=3",
        "  D=4",
        "  E=5",
        "]"
    );
    test(
        new TSObject5("X",
            "A", "1",
            "B", "2",
            "C", "3",
            "D", "4",
            "E", "5"
        ),
        "X[",
        "  A=1",
        "  B=2",
        "  C=3",
        "  D=4",
        "  E=5",
        "]"
    );

    // 6
    test(
        new TSObject6(
            "A", "1",
            "B", "2",
            "C", "3",
            "D", "4",
            "E", "5",
            "F", "6"
        ),
        "TSObject6[",
        "  A=1",
        "  B=2",
        "  C=3",
        "  D=4",
        "  E=5",
        "  F=6",
        "]"
    );
    test(
        new TSObject6("X",
            "A", "1",
            "B", "2",
            "C", "3",
            "D", "4",
            "E", "5",
            "F", "6"
        ),
        "X[",
        "  A=1",
        "  B=2",
        "  C=3",
        "  D=4",
        "  E=5",
        "  F=6",
        "]"
    );
  }

  @Test(description = ""
      + "Simple nesting"
  )
  public void testCase02() {
    TSObject0 ts0;
    ts0 = new TSObject0();

    TSObject1 ts1;
    ts1 = new TSObject1(
        "ts0", ts0
    );

    test(
        ts1,
        "TSObject1[",
        "  ts0=TSObject0[]",
        "]"
    );

    TSObject2 ts2;
    ts2 = new TSObject2(
        "ts0", ts0,
        "ts1", ts1
    );

    test(
        ts2,
        "TSObject2[",
        "  ts0=TSObject0[]",
        "  ts1=TSObject1[",
        "    ts0=TSObject0[]",
        "  ]",
        "]"
    );

    TSObject3 ts3;
    ts3 = new TSObject3(
        "ts0", ts0,
        "ts1", ts1,
        "ts2", ts2
    );

    test(
        ts3,
        "TSObject3[",
        "  ts0=TSObject0[]",
        "  ts1=TSObject1[",
        "    ts0=TSObject0[]",
        "  ]",
        "  ts2=TSObject2[",
        "    ts0=TSObject0[]",
        "    ts1=TSObject1[",
        "      ts0=TSObject0[]",
        "    ]",
        "  ]",
        "]"
    );

    TSObject4 ts4;
    ts4 = new TSObject4(
        "ts0", ts0,
        "ts1", ts1,
        "ts2", ts2,
        "ts3", ts3
    );

    test(
        ts4,
        "TSObject4[",
        "  ts0=TSObject0[]",
        "  ts1=TSObject1[",
        "    ts0=TSObject0[]",
        "  ]",
        "  ts2=TSObject2[",
        "    ts0=TSObject0[]",
        "    ts1=TSObject1[",
        "      ts0=TSObject0[]",
        "    ]",
        "  ]",
        "  ts3=TSObject3[",
        "    ts0=TSObject0[]",
        "    ts1=TSObject1[",
        "      ts0=TSObject0[]",
        "    ]",
        "    ts2=TSObject2[",
        "      ts0=TSObject0[]",
        "      ts1=TSObject1[",
        "        ts0=TSObject0[]",
        "      ]",
        "    ]",
        "  ]",
        "]"
    );
  }

  @Test
  public void toStringTest() {
    final int a = 1;
    final int b = 2;
    final int c = 3;
    final int d = 4;
    final int e = 5;

    assertEquals(
        ToString.toString(this),
        "ToStringTest[]"
    );

    assertEquals(
        ToString.toString(
            this,
            "A", ToString.toString(a)
        ),
        lines(
            "ToStringTest[",
            "  A=1",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "A", ToString.toString(a),
            "B", ToString.toString(b)
        ),
        lines(
            "ToStringTest[",
            "  A=1",
            "  B=2",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "A", ToString.toString(a),
            "B", ToString.toString(b),
            "C", ToString.toString(c)
        ),
        lines(
            "ToStringTest[",
            "  A=1",
            "  B=2",
            "  C=3",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "A", ToString.toString(a),
            "B", ToString.toString(b),
            "C", ToString.toString(c),
            "D", ToString.toString(d)
        ),
        lines(
            "ToStringTest[",
            "  A=1",
            "  B=2",
            "  C=3",
            "  D=4",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "A", ToString.toString(a),
            "B", ToString.toString(b),
            "C", ToString.toString(c),
            "D", ToString.toString(d),
            "E", ToString.toString(e)
        ),
        lines(
            "ToStringTest[",
            "  A=1",
            "  B=2",
            "  C=3",
            "  D=4",
            "  E=5",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "A", null,
            "B", null,
            "C", null,
            "D", null,
            "E", ToString.toString(e)
        ),
        lines(
            "ToStringTest[",
            "  A=null",
            "  B=null",
            "  C=null",
            "  D=null",
            "  E=5",
            "]"
        )
    );

    assertEquals(
        ToString.toString("Foo"),
        "Foo[]"
    );

    assertEquals(
        ToString.toString(
            "Foo",
            "A", ToString.toString(a)
        ),
        lines(
            "Foo[",
            "  A=1",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            "Foo",
            "A", ToString.toString(a),
            "B", ToString.toString(b)
        ),
        lines(
            "Foo[",
            "  A=1",
            "  B=2",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            "Foo",
            "A", ToString.toString(a),
            "B", ToString.toString(b),
            "C", ToString.toString(c)
        ),
        lines(
            "Foo[",
            "  A=1",
            "  B=2",
            "  C=3",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            "Foo",
            "A", ToString.toString(a),
            "B", ToString.toString(b),
            "C", ToString.toString(c),
            "D", ToString.toString(d)
        ),
        lines(
            "Foo[",
            "  A=1",
            "  B=2",
            "  C=3",
            "  D=4",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            "Foo",
            "A", ToString.toString(a),
            "B", ToString.toString(b),
            "C", ToString.toString(c),
            "D", ToString.toString(d),
            "E", ToString.toString(e)
        ),
        lines(
            "Foo[",
            "  A=1",
            "  B=2",
            "  C=3",
            "  D=4",
            "  E=5",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "", ToString.toString(a),
            "", ToString.toString(b),
            "", ToString.toString(c),
            "", ToString.toString(d),
            "", ToString.toString(e)
        ),
        lines(
            "ToStringTest[",
            "  1",
            "  2",
            "  3",
            "  4",
            "  5",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "object", new SimpleToStringObject(123)
        ),
        lines(
            "ToStringTest[",
            "  object=SimpleToStringObject[",
            "    value=123",
            "  ]",
            "]"
        )
    );

    assertEquals(
        ToString.toString(
            this,
            "depth1", new SimpleToStringObject(
                new SimpleToStringObject("XPTO")
            )
        ),
        lines(
            "ToStringTest[",
            "  depth1=SimpleToStringObject[",
            "    value=SimpleToStringObject[",
            "      value=XPTO",
            "    ]",
            "  ]",
            "]"
        )
    );

    assertEquals(
        new SimpleToStringList(0).toString(),
        "SimpleToStringList[]"
    );

    assertEquals(
        new SimpleToStringList(1).toString(),
        lines(
            "SimpleToStringList[",
            "  0=x",
            "]"
        )
    );

    assertEquals(
        new SimpleToStringList(2).toString(),
        lines(
            "SimpleToStringList[",
            "  0=x",
            "  1=xx",
            "]"
        )
    );

    assertEquals(
        new SimpleToStringList(7).toString(),
        lines(
            "SimpleToStringList[",
            "  0=x",
            "  1=xx",
            "  2=xxx",
            "  3=xxxx",
            "  4=xxxxx",
            "  5=xxxxxx",
            "  6=xxxxxxx",
            "]"
        )
    );

    SimpleToStringMap isMap;
    isMap = new SimpleToStringMap();

    assertEquals(
        isMap.toString(),
        "SimpleToStringMap[]"
    );

    isMap.put("first", Integer.valueOf(1));

    assertEquals(
        isMap.toString(),
        lines(
            "SimpleToStringMap[",
            "  first=1",
            "]"
        )
    );

    isMap.put("second", Integer.valueOf(2));

    assertEquals(
        isMap.toString(),
        lines(
            "SimpleToStringMap[",
            "  first=1",
            "  second=2",
            "]"
        )
    );
  }

  private String lines(String... lines) {
    switch (lines.length) {
      case 0:
        return "";
      case 1:
        return lines[0];
      default:
        StringBuilder sb;
        sb = new StringBuilder();

        sb.append(lines[0]);

        for (int i = 1; i < lines.length; i++) {
          sb.append('\n');

          sb.append(lines[i]);
        }

        return sb.toString();
    }
  }

  private void test(Object o, String... lines) {
    assertEquals(o.toString(), lines(lines));
  }

}
