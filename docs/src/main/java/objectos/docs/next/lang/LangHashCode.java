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

# The `HashCode` class

The `HashCode` class helps implementing the `hashCode()` method.

It should be noted that this class makes no claims about the performance
of the hash code computation.

With that said, let's see how to use it.

## Object references only

If you are computing the hash code of object reference only, i.e., it
does not include computing the hash code of primitive values, you just
invoke the `of` method:

```java
class Edition {
  Book book;
  Publisher publisher;
  LocalDate date;

  @Override
  public int hashCode() {
    return HashCode.of(book, publisher, date);
  }
```

The use of `HashCode.compute` here is analogous to using the JDK `Objects.hash` method.

The difference is that `HashCode.compute`, in this example, does not allocate an array
for computing the hash code.

## Primitive values only

If the hash code you are computing are of primitive values only you must compute
each value individually and then aggregate them like so:

```java
class Primitives {
  boolean a;
  int b;
  double c;

  @Override
  public int hashCode() {
    return HashCode.of(
      HashCode.of(a),
      HashCode.of(b),
      HashCode.of(c)
    );
  }
}
```

This prevents:

- the unnecessary boxing of any of the primitive values; and
- a variable arguments array allocation.

## Object references and primitives

If the hash code you are computing has a mix of object references and primitive values
you must compute each value individually and then aggregate them:

```java
class ShoppingCartItem {
  ShoppingCart cart;
  Product product;
  int quantity;

  @Override
  public int hashCode() {
    return HashCode.of(
      HashCode.of(cart),
      HashCode.of(product),
      HashCode.of(quantity)
    );
  }
}
```

Like the 'primitive values only' case, this prevents:

- the unnecessary boxing of any of the primitive values; and
- a variable arguments array allocation.

It additionally prevents:

- a `NullPointerException` if any of the object references are `null`.

## Single object reference or single primitive

When computing the hash code of either a single object reference or a single primitive
you can do so by invoking the `of` method directly.

For example, a single object reference:

```java
class Category {
  String name;

  @Override
  public int hashCode() {
    return HashCode.of(name);
  }
}
```

This prevents a possible `NullPointerException` that might occur when doing:

```java
@Override
public int hashCode() {
  return name.hashCode();
}
```

In the case of a single primitive value:

```java
class Id {
  long value;

  @Override
  public int hashCode() {
    return HashCode.of(value);
  }
}
```

Which is equivalent to:

```java
@Override
public int hashCode() {
  return Long.hashCode(value);
}
```

## Using `start` and `update`

The class exposes the methods it uses internally for computing the hash code.

For example, you can use them for computing the hash code of an array or a collection of elements:

```java
@Override
public int hashCode() {
  var result = HashCode.start();

  for (Object e : elements) {
    result = HashCode.update(result, e);
  }

  return result;
}
```

You can also use for primitive values. The difference is that you should compute
the hash code of an individual element before updating the partial result:

```java
@Override
public int hashCode() {
  var result = HashCode.start();

  for (double d : values) {
    var hc = HashCode.of(d);
    result = HashCode.update(result, hc);
  }

  return result;
}
```

*/
//@formatter:on
@Markdown
final class LangHashCode extends DocsPage {
  @Override
  protected void configure() {
    titleText = "The HashCode class";
  }
}