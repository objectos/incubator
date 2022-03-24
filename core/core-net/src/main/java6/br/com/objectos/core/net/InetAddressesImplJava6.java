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
package br.com.objectos.core.net;

import br.com.objectos.latest.Singleton;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

final class InetAddressesImplJava6 extends InetAddresses.Impl {

  @Singleton.Field
  static final InetAddresses.Impl INSTANCE = new InetAddressesImplJava6();

  private volatile InetAddress loopbackAddress;

  private InetAddressesImplJava6() {}

  @Override
  final InetAddress getLoopbackAddress() {
    if (loopbackAddress == null) {
      loopbackAddress = getLoopbackAddress0();
    }

    return loopbackAddress;
  }

  private InetAddress getLoopbackAddress0() {
    try {
      Enumeration<NetworkInterface> interfaces;
      interfaces = NetworkInterface.getNetworkInterfaces();

      while (interfaces.hasMoreElements()) {
        NetworkInterface i;
        i = interfaces.nextElement();

        if (!i.isLoopback()) {
          continue;
        }

        Enumeration<InetAddress> addresses;
        addresses = i.getInetAddresses();

        while (addresses.hasMoreElements()) {
          InetAddress address;
          address = addresses.nextElement();

          if (address.isLoopbackAddress()) {
            return address;
          }
        }
      }
    } catch (SocketException e) {
    }

    try {
      byte[] ipv4;
      ipv4 = new byte[] {0x7f, 0x00, 0x00, 0x01};

      return InetAddress.getByAddress("localhost", ipv4);
    } catch (UnknownHostException e) {
      AssertionError error;
      error = new AssertionError("Unexpected exception");

      error.initCause(e);

      throw error;
    }
  }

}
