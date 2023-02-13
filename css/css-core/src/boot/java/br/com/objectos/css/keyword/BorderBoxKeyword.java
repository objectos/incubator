package br.com.objectos.css.keyword;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.type.BoxSizingValue;
import br.com.objectos.css.type.BoxValue;

@Generated("br.com.objectos.css.boot.CssBoot")
public final class BorderBoxKeyword extends StandardKeyword implements BoxSizingValue, BoxValue {
  static final BorderBoxKeyword INSTANCE = new BorderBoxKeyword();

  private BorderBoxKeyword() {
    super(29, "borderBox", "border-box");
  }
}
