/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.smtp.server;

import br.com.objectos.smtp.mail.ClientName;
import br.com.objectos.smtp.mail.ForwardPath;
import br.com.objectos.smtp.mail.ReversePath;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface Transaction {

  void addForwardPath(ForwardPath forwardPath) throws IOException;

  void commit();

  ProcessingResult processData() throws IOException;

  void rollback();

  void setClientName(ClientName clientName);

  void setReversePath(ReversePath reversePath);

  void writeData(ByteBuffer byteBuffer) throws IOException;

}
