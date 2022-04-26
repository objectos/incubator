/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.next.logging;

import objectos.docs.ui.DocsPage;
import objectos.ssg.Markdown;

//@formatter:off
/**

# Logging with Objectos Logging

In this section we will give you an overview of how Objectos Logging approaches
logging.

## Log events

As we have seen in the previous section, logging is about emitting messages
describing events taking place during a program execution. Therefore, a central
part of logging with Objectos Logging is defining the events you want to log.

## Debugging

## Monitoring

 */
//@formatter:on
@Markdown
final class GettingStartedObjectosLogging extends DocsPage {
  @Override
  protected final void configure() {
    titleText = "Logging with Objectos Logging";
  }
}
