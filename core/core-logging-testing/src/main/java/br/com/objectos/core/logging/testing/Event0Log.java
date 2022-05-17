/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.core.logging.testing;

import objectos.lang.Equals;
import objectos.lang.ToString;
import objectos.logging.Event;
import objectos.logging.Event0;

/**
 * An {@link Event0} log instance.
 */
public final class Event0Log extends Log {

  final Event0 event;

  /**
   * Creates a new log instance.
   *
   * @param event
   *        the event instance
   */
  public Event0Log(Event0 event) {
    super(event);

    this.event = event;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, this,
        "event", event
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEvent0(Event0 event) {
    return Equals.objects(this.event, event);
  }

  @Override
  final boolean hasEvent(Event event) {
    return Equals.objects(this.event, event);
  }

  @Override
  final void print() {
    print0(event);

    printReturn();
  }

}