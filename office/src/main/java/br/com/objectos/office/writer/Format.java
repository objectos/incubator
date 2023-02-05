/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.office.writer;

import java.util.UUID;

public enum Format {

  // https://wiki.openoffice.org/wiki/Framework/Article/Filter/FilterList_OOo_3_0

  DOC("MS Word 97"),

  DOCX("MS Word 2007 XML"),

  ODT("writer8"),

  PDF("writer_pdf_Export"),

  RTF("Rich Text Format"),

  TXT("Text");

  public final String filterName;

  private final String extension;

  private Format(String filterName) {
    this.filterName = filterName;

    extension = name().toLowerCase();
  }

  public final String nextFileName() {
    UUID uuid;
    uuid = UUID.randomUUID();

    return uuid.toString() + '.' + extension;
  }

}