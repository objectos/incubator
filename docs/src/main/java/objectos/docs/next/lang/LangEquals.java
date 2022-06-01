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

# The `Equals` class

The `Equals` is a utility class for implementing `Object.equals(Object)` methods.
It offers methods for testing pairs of objects for equality in a null-safe manner.

## Single attribute

```java
class Category {
  String name;

  @Override
  public boolean equals(Object o) {
    return o == this || o instanceof Category that
      && Equals.objects(name, that.name);
  }
}
```

## Two or more attributes

When testing two attributes for equality:

```java
class Product {
  String name;
  Brand brand;

  @Override
  public boolean equals(Object o) {
    return o == this || o instanceof Product that
      && Equals.objects(
        name, that.name,
        brand, that.brand
      );
  }
}
```

When testing three attributes:

```java
class Edition {
  Book book;
  Publisher publisher;
  LocalDate date;

  @Override
  public boolean equals(Object o) {
    return o == this || o instanceof Edition that
      && Equals.objects(
        book, that.book,
        publisher, that.publisher,
        date, that.date
      );
  }
}
```

## Primitives

The methods provided by the `Equals` class are suited for object references.
You should avoid using it when testing primitive values for equality.
You should use the `==` operator instead:

```java
class ShoppingCartItem {
  ShoppingCart cart;
  Product product;
  int quantity;

  @Override
  public boolean equals(Object o) {
    return o == this || o instanceof ShoppingCartItem that
      && quantity == that.quantity
      && Equals.objects(
        cart, that.cart,
        product, that.product
      );
  }
}
```

*/
//@formatter:on
@Markdown
final class LangEquals extends DocsPage {
  @Override
  protected void configure() {
    titleText = "The Equals class";
  }
}