/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.specgen.spec;

import static br.com.objectos.code.java.Java._var;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.css.specgen.Types;
import java.util.Set;
import java.util.TreeSet;
import javax.lang.model.SourceVersion;

public class KeywordSet {

  private final Set<String> values;

  private KeywordSet(Builder builder) {
    values = builder.values();
  }

  public static Builder builder() {
    return new Builder();
  }

  public final void acceptPropertyDefinitionMethod(MethodCode.Builder builder) {
    for (String value : values) {
      builder.addStatement(
        _var(Types.KeywordName, identifier(value), invoke("keyword", l(value)))
      );
    }
  }

  public final boolean contains(String... strings) {
    for (String s : strings) {
      if (!values.contains(s)) {
        return false;
      }
    }

    return true;
  }

  public final int size() {
    return values.size();
  }

  private Identifier identifier(String value) {
    String candidate = JavaNames.toValidMethodName(value);

    if (SourceVersion.isKeyword(candidate)) {
      candidate = candidate + "Kw";
    }

    return id(candidate);
  }

  public static class Builder {

    private boolean keyword;

    private final StringBuilder sb = new StringBuilder();

    private final Set<String> values = new TreeSet<>();

    private Builder() {}

    public final void add(String keyword) {
      values.add(keyword);
    }

    public final void addGlobalKeywords() {
      values.add("inherit");
      values.add("initial");
      values.add("unset");
    }

    public final KeywordSet build() {
      return new KeywordSet(this);
    }

    public final void parse(String name, String formal) {
      keyword = true;

      char[] chars = formal.toCharArray();
      for (char c : chars) {

        if (Character.isAlphabetic(c)) {
          if (keyword) {
            sb.append(c);
          }
          continue;
        }

        switch (c) {
          case '<':
          case '(':
            keyword = false;
            break;
          case '>':
          case ')':
            keyword = true;
            sb.setLength(0);
            break;
          case '-':
            if (keyword) {
              sb.append(c);
            }
            break;
          default:
            addKeyword(name);
            break;
        }

      }

      addKeyword(name);
    }

    final Set<String> values() {
      return values;
    }

    private void addKeyword(String name) {
      if (keyword) {
        addKeyword0(name);
      }
    }

    private void addKeyword0(String name) {
      if (sb.length() > 0) {
        String keyword = sb.toString();
        values.add(keyword);
      }
      sb.setLength(0);
    }

  }

}
