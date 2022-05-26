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

# `Checks`

If you are writing a method or constructor that is intended to be invoked by code other
than your own, it is better to make no assumptions about:

- the validity of arguments received;
- whether an argument will not be null when nulls are not allowed; or
- the correct state of an object before a method can be invoked.

Put in other words, it is better to detect programming errors sooner rather than later;
therefore, depending on your requirements, it is safer to always assume the arguments
can be invalid.

## _Fail-fast_ checks

The `Checks` class provides static methods for implementing _fail-fast_ checks intended
to verify if a method or a constructor was invoked:

- with valid arguments; or
- on an object having the correct state.

## `Checks.checkArgument`

The `Checks` class provides a series of `checkArgument` methods for validating
the arguments passed to a method or constructor. The following check for an `int` argument:

```java
public void repeat(int count) {
  if (count < 0) {
    throw new IllegalArgumentException("count must be >= 0");
  }

  // method implementation
}
```

Can be written as:

```java
public void repeat(int count) {
  Checks.checkArgument(count >= 0, "count must be >= 0");

  // method implementation
}
```

Note the test expression: `count >= 0`.

The test expression must always be: ___"what I want to be true"___.

### Message concatenating

The `Checks` class provides an overload that allows for concatenating two distinct
message parts into a single message. The concatenating happens in the same order the parts
are specified.

For example the following check:

```java
public void serveChildPath(Path path) {
  Checks.checkArgument(
    !path.isAbsolute,
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
final class LangChecks extends DocsPage {
  @Override
  protected void configure() {
    titleText = "Checks";
  }
}