/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.server.nio;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChannelBuilderTest {

  @DataProvider
  public Object[][] channelProvider() {
    return new Object[][] {
        { ChannelBuilder.localhost(8080), "http://localhost:8080" },
        { ChannelBuilder.hostname("rio.objectos.com.br", 8080), "http://rio.objectos.com.br:8080" },
        { ChannelBuilder.hostname("rio.objectos.com.br", 80), "http://rio.objectos.com.br" }
    };
  }

  @Test(dataProvider = "channelProvider")
  public void toString_test(ChannelBuilder channel, String expected) {
    assertEquals(channel.toString(), expected);
  }

}