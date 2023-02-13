package br.com.objectos.css.keyword;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.type.AppearanceValue;

@Generated("br.com.objectos.css.boot.CssBoot")
public final class ButtonKeyword extends StandardKeyword implements AppearanceValue {
  static final ButtonKeyword INSTANCE = new ButtonKeyword();

  private ButtonKeyword() {
    super(33, "buttonKw", "button");
  }
}
