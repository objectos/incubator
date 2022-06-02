/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.next.lang;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# The `ToString` facility

Objectos Lang offers a facility for generating a human-readable `toString()` output.
It is composed of the following classes and interfaces:

- `ToString`
- `ToStringObject`
- `FormatToString`

For example, the following `Category` class:

```java
class Category implements ToStringObject {
  String name = "Default";

  @Override
  public void formatToString(StringBuilder toString, int level) {
    FormatToString.of(
      toString, level,

      this,
      "name", name
    );
  }

  @Override
  public String toString() {
    return ToString.of(this);
  }
}
```

Would return the following value for the `toString()` method:

```
Category [
  name = Default
]
```

## Human-readable

As mentioned, this particular `toString()` output is meant to be human-readable
as opposed to machine-readable.
In particular, it is meant to be readable during:

- testing
- debugging

In "live" programming sessions or when searching through a log file.

## Nesting

The `ToString` facility supports nesting. For example the `Product` class
that uses the `Category` type from the last example:

```java
class Product implements ToStringObject {
  Category category = new Category("Audio");
  String name = "Headphone";

  @Override
  public void formatToString(StringBuilder toString, int level) {
    FormatToString.of(
      toString, level,

      this,
      "category", category,
      "name", name
    );
  }

  @Override
  public String toString() {
    return ToString.of(this);
  }
}
```

Would return the following value for the `toString()` method:

```
Product [
  category = Category [
    name = Audio
  ]
  name = Headphone
]
```

## Using a custom type name

In our examples so far we have been using the `this` reference as the source of the type name.
If you wish to use a custom type name you can do so by specifying a `String` value as argument
instead of the `this` reference.

For example, suppose our `Category` example was:

```java
class CategoryImpl implements Category, ToStringObject {
  String name = "Default";

  @Override
  public void formatToString(StringBuilder toString, int level) {
    FormatToString.of(
      toString, level,

      this,
      "name", name
    );
  }

  @Override
  public String toString() {
    return ToString.of(this);
  }
}
```

Would generate:

```
CategoryImpl [
  name = Default
]
```

If you wish `Category` to be printed instead, you would change the `formatToString` method
implementation to:

```java
@Override
public void formatToString(StringBuilder toString, int level) {
  FormatToString.of(
    toString, level,

    "Category",
    "name", name
  );
}
```

Which would generate:

```
Category [
  name = Default
]
```

## Primitive values



## `ToString.of`

*/
//@formatter:on
@Markdown
final class LangToString extends DocsPage {
  @Override
  protected void configure() {
    titleText = "The ToString class";
  }
}