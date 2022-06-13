package br.com.objectos.css.function;

import br.com.objectos.code.annotations.Generated;
import objectos.util.ImmutableMap;
import objectos.util.MutableMap;

@Generated("br.com.objectos.css.boot.CssBoot")
public enum StandardFunctionName implements FunctionName {

  ROTATE("rotate", "rotate"),

  ROTATEX("rotateX", "rotateX"),

  ROTATEY("rotateY", "rotateY"),

  ROTATEZ("rotateZ", "rotateZ");

  private static final StandardFunctionName[] ARRAY = StandardFunctionName.values();

  private static final ImmutableMap<String, StandardFunctionName> MAP = buildMap();

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

  private static ImmutableMap<String, StandardFunctionName> buildMap() {
    MutableMap<String, StandardFunctionName> m = MutableMap.create();
    m.put("rotate", ROTATE);
    m.put("rotateX", ROTATEX);
    m.put("rotateY", ROTATEY);
    m.put("rotateZ", ROTATEZ);
    return m.toImmutableMap();
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