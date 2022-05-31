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
package br.com.objectos.css.boot.keyword;

import static br.com.objectos.code.java.Java._class;
import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._implements;
import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java._private;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java._super;
import static br.com.objectos.code.java.Java.constructor;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.core.set.Sets;
import br.com.objectos.css.boot.CssBoot;
import br.com.objectos.css.boot.property.ParameterType;
import br.com.objectos.css.boot.spec.Ids;
import br.com.objectos.css.boot.type.PrimitiveType;
import br.com.objectos.css.boot.type.Value;
import br.com.objectos.css.boot.type.ValueType;
import java.util.Set;
import javax.lang.model.SourceVersion;
import objectos.lang.Check;
import objectos.lang.ToString;

public class KeywordName implements Comparable<KeywordName>, ParameterType, Value {

  public final NamedClass className;

  public final Identifier fieldName;
  private final Set<NamedClass> interfaces = Sets.newTreeSet();

  private final String name;

  private KeywordName(String name, String fieldName) {
    this.name = name;
    className = KeywordNames.className(JavaNames.toValidClassName(name) + "Keyword");
    this.fieldName = id(fieldName);
  }

  public static KeywordName of(String value) {
    Check.notNull(value, "value == null");

    char firstChar = value.charAt(0);
    String fieldNameCandidate = Character.isUpperCase(firstChar)
        ? JavaNames.toValidClassName(value)
        : JavaNames.toValidMethodName(value);
    if (SourceVersion.isKeyword(fieldNameCandidate)) {
      fieldNameCandidate = fieldNameCandidate + "Kw";
    }
    return new KeywordName(value, fieldNameCandidate);
  }

  public static KeywordName withKwSuffix(String value) {
    Check.notNull(value, "value == null");
    return new KeywordName(value, JavaNames.toValidMethodName(value) + "Kw");
  }

  public final void acceptPritiveType(PrimitiveType type) {
    interfaces.add(type.typeClassName());
  }

  @Override
  public final void acceptValueType(ValueType type) {
    interfaces.add(type.className);
  }

  @Override
  public final int compareTo(KeywordName o) {
    return name.compareTo(o.name);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof KeywordName)) {
      return false;
    }
    KeywordName that = (KeywordName) obj;
    return name.equals(that.name);
  }

  @Override
  public final int hashCode() {
    return name.hashCode();
  }

  @Override
  public final NamedArray toNamedArray() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final NamedType toNamedType() {
    return className;
  }

  @Override
  public final String toString() {
    return ToString.toString(this, "", name);
  }

  final ClassCode generate(int code) {
    return _class(
        CssBoot.GENERATED,
        _public(), _final(), className,
        _extends(KeywordNames._StandardKeyword),
        _implements(interfaces),

        field(
            _static(), _final(), className,
            init(Ids.INSTANCE, _new(className))
        ),

        constructor(
            _private(),
            _super(l(code), l(fieldName.name()), l(name))
        )
    );
  }

  final MethodInvocation invokePut(Identifier map) {
    return invoke(map, "put", l(name), fieldName);
  }

}
