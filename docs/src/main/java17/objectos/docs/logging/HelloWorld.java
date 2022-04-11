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
package objectos.docs.logging;

import br.com.objectos.logging.Event1;
import br.com.objectos.logging.Events;
import br.com.objectos.logging.NoopLogger;

public class HelloWorld {
  public static void main(String[] args) {
    var say = Events.info(HelloWorld.class, "SAY", String.class);

    var logger = new NoopLogger() {
      public <T> void log(Event1<T> event, T arg) {
        if (event == say) {
          // the cast to string is not necessary.
          // It is here just to show it is a safe cast
          // since `say` is parameterized Event1<String>
          System.out.println((String) arg);
        }
      }
    };

    logger.log(say, "Hello world!");
  }
}