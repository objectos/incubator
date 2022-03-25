package br.com.objectos.css.type;

import br.com.objectos.code.annotations.Generated;
import java.util.Locale;

@Generated("br.com.objectos.css.boot.CssBoot")
public enum LengthUnit {

  CH,

  CM,

  EM,

  EX,

  IN,

  MM,

  PC,

  PT,

  PX,

  Q,

  REM,

  VH,

  VMAX,

  VMIN,

  VW;

  private static final LengthUnit[] ARRAY = LengthUnit.values();

  private final String name;

  private LengthUnit() {
    this.name = name().toLowerCase(Locale.US);
  }

  public static LengthUnit getByCode(int code) {
    return ARRAY[code];
  }

  public static int size() {
    return ARRAY.length;
  }

  public final int getCode() {
    return ordinal();
  }

  public final String getName() {
    return name;
  }

}