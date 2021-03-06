package br.com.objectos.css.function;

import br.com.objectos.code.annotations.Generated;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

@Generated("br.com.objectos.css.boot.CssBoot")
public enum StandardFunctionName implements FunctionName {

  ROTATE("rotate", "rotate"),

  ROTATEX("rotateX", "rotateX"),

  ROTATEY("rotateY", "rotateY"),

  ROTATEZ("rotateZ", "rotateZ");

  private static final StandardFunctionName[] ARRAY = StandardFunctionName.values();

  private static final UnmodifiableMap<String, StandardFunctionName> MAP = buildMap();

  private final String javaName;

  private final String name;

  private StandardFunctionName(String javaName, String name) {
    this.javaName = javaName;
    this.name = name;
  }

  public static StandardFunctionName getByCode(int code) {
    return ARRAY[code];
  }

  public static StandardFunctionName getByName(String name) {
    return MAP.get(name);
  }

  private static UnmodifiableMap<String, StandardFunctionName> buildMap() {
    GrowableMap<String, StandardFunctionName> m = new GrowableMap<>();
    m.put("rotate", ROTATE);
    m.put("rotateX", ROTATEX);
    m.put("rotateY", ROTATEY);
    m.put("rotateZ", ROTATEZ);
    return m.toUnmodifiableMap();
  }

  public static int size() {
    return ARRAY.length;
  }

  @Override
  public final int getCode() {
    return ordinal();
  }

  public final String getJavaName() {
    return javaName;
  }

  @Override
  public final String getName() {
    return name;
  }

}