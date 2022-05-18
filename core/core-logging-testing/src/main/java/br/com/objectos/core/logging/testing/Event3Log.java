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
import objectos.lang.Event;
import objectos.lang.Event3;
import objectos.lang.ToString;

/**
 * An {@link Event3} log instance.
 *
 * @param <T1>
 *        the type of the first log value
 * @param <T2>
 *        the type of the second log value
 * @param <T3>
 *        the type of the third log value
 */
public final class Event3Log<T1, T2, T3> extends Log {

  final Event3<T1, T2, T3> event;

  final T1 value1;

  final T2 value2;

  final T3 value3;

  /**
   * Creates a new log instance.
   *
   * @param event
   *        the event instance
   * @param value1
   *        the first value associated with the event
   * @param value2
   *        the second value associated with the event
   * @param value3
   *        the third value associated with the event
   */
  public Event3Log(Event3<T1, T2, T3> event, T1 value1, T2 value2, T3 value3) {
    super(event);

    this.event = event;
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
      sb, depth, this,
      "event", event,
      "value1", value1,
      "value2", value2,
      "value3", value3
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <X1, X2, X3> boolean isEvent3(
      Event3<X1, X2, X3> event, X1 value1, X2 value2, X3 value3) {
    return Equals.objects(this.event, event)
        && Equals.objects(this.value1, value1)
        && Equals.objects(this.value2, value2)
        && Equals.objects(this.value3, value3);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void printStackTrace() {
    printStackTrace(value1);

    printStackTrace(value2);

    printStackTrace(value3);
  }

  @Override
  final boolean hasEvent(Event event) {
    return Equals.objects(this.event, event);
  }

  @Override
  final void print() {
    print0(event);

    printValue(Logging.format(value1));

    printValue(Logging.format(value2));

    printValue(Logging.format(value3));

    printReturn();

    printStackTrace(value1);

    printStackTrace(value2);

    printStackTrace(value3);
  }

}