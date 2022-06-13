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
package objectos.docs.v0002.lang;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# The `Check` class

The `Check` class provides static methods for verifying if a method or a constructor was invoked:

- with valid arguments;
- without a null argument when nulls are not allowed; or
- on an object having the correct state.

All of the checks provided by the class are _runtime_ checks. In other words:

- checking occurs during the execution of the program; and
- failed checks result in a `RuntimeException` subclass being thrown.

## `Check.argument`

The `Check.argument` method is for informing the programmer that a method or a constructor
was invoked with an illegal or invalid argument.

In this case the check will cause the invoking method or constructor to complete abruptly
by throwing an `IllegalArgumentException`.

The following code checks the validity of an `int` argument:

```java
public void repeat(int count) {
  if (count < 0) {
    throw new IllegalArgumentException("count must be >= 0");
  }

  // method implementation
}
```

Using the `Check.argument` it becomes:

```java
public void repeat(int count) {
  Check.argument(count >= 0, "count must be >= 0");

  // method implementation
}
```

Note the test expression: `count >= 0`.

The test expression must always be: ___"what I want to be true"___.

## `Check.notNull`

The `Check.notNull` method is for informing the programmer that a method
(or a constructor) was incorrectly invoked with a `null` argument.

In this case the check will cause the invoking method to complete abruptly
by throwing a `NullPointerException`.

### Safeguarding methods

In the example below the check is used to prevent adding a `null` product to a
hypothetical shopping cart:

```java
public void add(Product product) {
  Check.notNull(product, "product == null");

  // product is guaranteed to be != null
  // it can now be safely added to the cart
}
```

Which is equivalent to the following plain Java code:

```java
public void add(Product product) {
  if (product == null) {
    throw new NullPointerException("product == null");
  }

  // product is guaranteed to be != null
  // it can now be safely added to the cart
}
```

### Safeguarding field assignments

You can use the check to prevent a field to be assigned a `null` value. This is
particularly useful in constructors like so:

```java
public class Category {
  private final String name;

  public Category(String name) {
    this.name = Check.notNull(name, "please provide a name");
  }
}
```

Which is _semantically_ equivalent to the plain Java code:

```java
public class Category {
  private final String name;

  public Category(String name) {
    if (name == null) {
      throw new NullPointerException("please provide a name");
    }

    this.name = name;
  }
}
```

You can also use it in methods:

```java
public void setName(String name) {
  this.name = Check.notNull(name, "name == null");
}
```

## `Check.state`

The `Check.state` method is for informing the programmer that a method was invoked
at an illegal or inappropriate time.

In this case the check will cause the invoking method to complete abruptly
by throwing an `IllegalStateException`.

In the following code you wish to inform the programmer that a hypothetical service has already
been started:

```java
public void start() {
  if (state != State.NEW) {
    throw new IllegalStateException("Cannot call start(): already started!");
  }
}
```

Becomes:

```java
public void start() {
  Check.state(state == State.NEW, "Cannot call start(): already started!");
}
```

Note that, for the checks to pass, the condition must evaluate to `true`.

## Message concatenating

The `Check` class provides overloads for concatenating two or more distinct
message parts into a single message. The concatenating happens in the same order the parts
are specified.

For example the following check:

```java
public void serveChildPath(Path path) {
  Check.argument(
    !path.isAbsolute(),
    "Expected a relative path. But found: ", path
  );
}
```

So if the method is invoked like so:

```java
serveChildPath(Path.of("/var/secrets"));
```

Would fail with the following message:

```
Expected a relative path. But found: /var/secrets
```

As the string representation of both message parts are concatenated into a single string.

*/
//@formatter:on
@Markdown
final class LangCheck extends DocsPage {
  @Override
  protected void configure() {
    titleText = "The Check class";
  }
}