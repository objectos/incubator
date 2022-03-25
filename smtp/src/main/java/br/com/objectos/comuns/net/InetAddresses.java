/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.comuns.net;

import br.com.objectos.latest.Singleton;
import java.net.InetAddress;

public final class InetAddresses {

  private InetAddresses() {}

  public static InetAddress getLoopbackAddress() {
    return InetAddressesImplSingleton.INSTANCE.getLoopbackAddress();
  }

  @Singleton
  abstract static class Impl {

    abstract InetAddress getLoopbackAddress();

  }

}
