package br.com.objectos.css.keyword;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.type.WhiteSpaceValue;

@Generated("br.com.objectos.css.boot.CssBoot")
public final class BreakSpacesKeyword extends StandardKeyword implements WhiteSpaceValue {
  static final BreakSpacesKeyword INSTANCE = new BreakSpacesKeyword();

  private BreakSpacesKeyword() {
    super(32, "breakSpaces", "break-spaces");
  }
}
