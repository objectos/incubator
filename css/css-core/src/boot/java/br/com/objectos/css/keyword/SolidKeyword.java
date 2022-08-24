package br.com.objectos.css.keyword;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.type.LineStyleValue;
import br.com.objectos.css.type.TextDecorationStyleValue;

@Generated("br.com.objectos.css.boot.CssBoot")
public final class SolidKeyword extends StandardKeyword implements LineStyleValue, TextDecorationStyleValue {

  static final SolidKeyword INSTANCE = new SolidKeyword();

  private SolidKeyword() {
    super(221, "solid", "solid");
  }

}