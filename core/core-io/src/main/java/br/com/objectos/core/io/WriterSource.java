/*
 * Copyright (C) 2021-2023 Objectos Software LTDA.
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
package br.com.objectos.core.io;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * An object that can provide {@link Writer} instances.
 *
 * @since 2
 *
 * @see Write
 */
public interface WriterSource {

  /**
   * Returns a new {@link Writer} with the specified charset for writing to this
   * source.
   *
   * @param charset
   *        the charset to use for encoding
   *
   * @return a new {@link Writer} with the specified charset
   *
   * @throws IOException
   *         if an I/O error occurs
   */
  Writer openWriter(Charset charset) throws IOException;

}