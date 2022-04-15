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
package objectos.ssg.processor;

import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.core.system.LineSeparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MarkdownBeAnnotatedTypeMethodTest {

  private MarkdownMethod method;

  @BeforeClass
  public void _beforeClass() {
    method = new MarkdownMethod(null);
  }

  @Test
  public void blockquote() {
    Util.assertHasLines(
      generate(
        "a",
        "",
        "> hi"
      ),

      "void unnamed() {",
      "  p(",
      "      t(\"a\")",
      "  );",
      "  blockquote(",
      "      p(",
      "          t(\"hi\")",
      "      )",
      "  );",
      "}"
    );
  }

  @Test
  public void em() {
    Util.assertHasLines(
      generate(
        "first _second_ third"
      ),

      "void unnamed() {",
      "  p(",
      "      t(\"first \"),",
      "      em(",
      "          t(\"second\")",
      "      ),",
      "      t(\" third\")",
      "  );",
      "}"
    );
  }

  @Test
  public void generate0() {
    Util.assertHasLines(
      generate(
        "[a link](https://example.com)"
      ),

      "void unnamed() {",
      "  p(",
      "      a(",
      "          href(\"https://example.com\"),",
      "          t(\"a link\")",
      "      )",
      "  );",
      "}"
    );

    Util.assertHasLines(
      generate(
        "- Java16",
        "- Java11",
        "- Java8",
        "- Java7",
        "- Java6"
      ),

      "void unnamed() {",
      "  ul(",
      "      li(",
      "          t(\"Java16\")",
      "      ),",
      "      li(",
      "          t(\"Java11\")",
      "      ),",
      "      li(",
      "          t(\"Java8\")",
      "      ),",
      "      li(",
      "          t(\"Java7\")",
      "      ),",
      "      li(",
      "          t(\"Java6\")",
      "      )",
      "  );",
      "}"
    );

    Util.assertHasLines(
      generate(
        "```",
        "mvn --help",
        "```",
        "",
        "the command `mvn` is used..."
      ),

      "void unnamed() {",
      "  pre(",
      "      code(",
      "          t(\"mvn --help\\n\")",
      "      )",
      "  );",
      "  p(",
      "      t(\"the command \"),",
      "      code(\"mvn\"),",
      "      t(\" is used...\")",
      "  );",
      "}"
    );

    Util.assertHasLines(
      generate(
        "- versions",
        "    - Java16",
        "    - Java11",
        "- vendors",
        "    - OpenJDK",
        "    - Oracle"
      ),

      "void unnamed() {",
      "  ul(",
      "      li(",
      "          t(\"versions\"), ul(",
      "              li(",
      "                  t(\"Java16\")",
      "              ),",
      "              li(",
      "                  t(\"Java11\")",
      "              )",
      "          )",
      "      ),",
      "      li(",
      "          t(\"vendors\"), ul(",
      "              li(",
      "                  t(\"OpenJDK\")",
      "              ),",
      "              li(",
      "                  t(\"Oracle\")",
      "              )",
      "          )",
      "      )",
      "  );",
      "}"
    );
  }

  @Test
  public void h() {
    Util.assertHasLines(
      generate(
        "# Heading 1"
      ),

      "void unnamed() {",
      "  h1(",
      "      t(\"Heading 1\")",
      "  );",
      "}"
    );

    Util.assertHasLines(
      generate(
        "# Heading 1{#custom-id}"
      ),

      "void unnamed() {",
      "  h1(",
      "      id(\"custom-id\"),",
      "      t(\"Heading 1\")",
      "  );",
      "}"
    );
  }

  @Test
  public void img() {
    Util.assertHasLines(
      generate(
        "![alt text](file.jpg)",
        "![alt text](file.jpg \"A title\")"
      ),

      "void unnamed() {",
      "  p(",
      "      img(",
      "          src(\"file.jpg\"),",
      "          alt(\"alt text\")",
      "      ), t(\"\\n\"),",
      "      img(",
      "          src(\"file.jpg\"),",
      "          title(\"A title\"),",
      "          alt(\"alt text\")",
      "      )",
      "  );",
      "}"
    );
  }

  @Test
  public void link() {
    Util.assertHasLines(
      generate(
        "[normal](https://example.com)",
        "^^1^^"
      ),

      "void unnamed() {",
      "  p(",
      "      a(",
      "          href(\"https://example.com\"),",
      "          t(\"normal\")",
      "      ), t(\"\\n\"),",
      "      a(",
      "          id(\"fn1-ret\"), href(\"#fn1\"), sup(\"[1]\")",
      "      )",
      "  );",
      "}"
    );
  }

  @Test
  public void ol() {
    Util.assertHasLines(
      generate(
        "1. Introduction",
        "    1. Motivation",
        "    1. Caveats",
        "1. Part 1",
        "1. Part 2"
      ),

      "void unnamed() {",
      "  ol(",
      "      li(",
      "          t(\"Introduction\"), ol(",
      "              li(",
      "                  t(\"Motivation\")",
      "              ),",
      "              li(",
      "                  t(\"Caveats\")",
      "              )",
      "          )",
      "      ),",
      "      li(",
      "          t(\"Part 1\")",
      "      ),",
      "      li(",
      "          t(\"Part 2\")",
      "      )",
      "  );",
      "}"
    );
  }

  @Test
  public void preCode() {
    Util.assertHasLines(
      generate(
        "```xml",
        "<foo></foo>",
        "```"
      ),

      "void unnamed() {",
      "  pre(",
      "      code(",
      "          _class(\"language-xml\"),",
      "          t(\"<foo></foo>\\n\")",
      "      )",
      "  );",
      "}"
    );
  }

  @Test
  public void self() {
    Util.assertHasLines(
      generate(
        "```self",
        "m0",
        "```"
      ),

      "void unnamed() {",
      "  m0();",
      "}"
    );
    Util.assertHasLines(
      generate(
        "```self",
        "m0",
        "m1",
        "```"
      ),

      "void unnamed() {",
      "  m0();",
      "  m1();",
      "}"
    );
  }

  @Test
  public void strong() {
    Util.assertHasLines(
      generate(
        "first __second__ third"
      ),

      "void unnamed() {",
      "  p(",
      "      t(\"first \"),",
      "      strong(",
      "          t(\"second\")",
      "      ),",
      "      t(\" third\")",
      "  );",
      "}"
    );
  }

  private String generate(String... lines) {
    method.methodCode = MethodCode.builder();

    MethodCode result;
    result = method.generate0(
      LineSeparator.join(lines)
    );

    return result.toString();
  }

}