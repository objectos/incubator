package br.com.objectos.css.select;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.annotations.Ignore;
import objectos.util.MutableMap;
import objectos.util.UnmodifiableMap;

@Generated("br.com.objectos.css.boot.select.TypeSelectorsGen")
public final class TypeSelectors {

  public static final TypeSelector a = new TypeSelector(0, "a");

  public static final TypeSelector abbr = new TypeSelector(1, "abbr");

  public static final TypeSelector article = new TypeSelector(2, "article");

  public static final TypeSelector b = new TypeSelector(3, "b");

  public static final TypeSelector blockquote = new TypeSelector(4, "blockquote");

  public static final TypeSelector body = new TypeSelector(5, "body");

  public static final TypeSelector br = new TypeSelector(6, "br");

  public static final TypeSelector button = new TypeSelector(7, "button");

  public static final TypeSelector clipPath = new TypeSelector(8, "clipPath");

  public static final TypeSelector code = new TypeSelector(9, "code");

  public static final TypeSelector dd = new TypeSelector(10, "dd");

  public static final TypeSelector defs = new TypeSelector(11, "defs");

  public static final TypeSelector details = new TypeSelector(12, "details");

  public static final TypeSelector div = new TypeSelector(13, "div");

  public static final TypeSelector dl = new TypeSelector(14, "dl");

  public static final TypeSelector dt = new TypeSelector(15, "dt");

  public static final TypeSelector em = new TypeSelector(16, "em");

  public static final TypeSelector fieldset = new TypeSelector(17, "fieldset");

  public static final TypeSelector figure = new TypeSelector(18, "figure");

  public static final TypeSelector footer = new TypeSelector(19, "footer");

  public static final TypeSelector form = new TypeSelector(20, "form");

  public static final TypeSelector g = new TypeSelector(21, "g");

  public static final TypeSelector h1 = new TypeSelector(22, "h1");

  public static final TypeSelector h2 = new TypeSelector(23, "h2");

  public static final TypeSelector h3 = new TypeSelector(24, "h3");

  public static final TypeSelector h4 = new TypeSelector(25, "h4");

  public static final TypeSelector h5 = new TypeSelector(26, "h5");

  public static final TypeSelector h6 = new TypeSelector(27, "h6");

  public static final TypeSelector head = new TypeSelector(28, "head");

  public static final TypeSelector header = new TypeSelector(29, "header");

  public static final TypeSelector hgroup = new TypeSelector(30, "hgroup");

  public static final TypeSelector hr = new TypeSelector(31, "hr");

  public static final TypeSelector html = new TypeSelector(32, "html");

  public static final TypeSelector img = new TypeSelector(33, "img");

  public static final TypeSelector input = new TypeSelector(34, "input");

  public static final TypeSelector kbd = new TypeSelector(35, "kbd");

  public static final TypeSelector label = new TypeSelector(36, "label");

  public static final TypeSelector legend = new TypeSelector(37, "legend");

  public static final TypeSelector li = new TypeSelector(38, "li");

  public static final TypeSelector link = new TypeSelector(39, "link");

  public static final TypeSelector main = new TypeSelector(40, "main");

  public static final TypeSelector meta = new TypeSelector(41, "meta");

  public static final TypeSelector nav = new TypeSelector(42, "nav");

  public static final TypeSelector ol = new TypeSelector(43, "ol");

  public static final TypeSelector optgroup = new TypeSelector(44, "optgroup");

  public static final TypeSelector option = new TypeSelector(45, "option");

  public static final TypeSelector p = new TypeSelector(46, "p");

  public static final TypeSelector path = new TypeSelector(47, "path");

  public static final TypeSelector pre = new TypeSelector(48, "pre");

  public static final TypeSelector progress = new TypeSelector(49, "progress");

  public static final TypeSelector samp = new TypeSelector(50, "samp");

  public static final TypeSelector script = new TypeSelector(51, "script");

  public static final TypeSelector section = new TypeSelector(52, "section");

  public static final TypeSelector select = new TypeSelector(53, "select");

  public static final TypeSelector small = new TypeSelector(54, "small");

  public static final TypeSelector span = new TypeSelector(55, "span");

  public static final TypeSelector strong = new TypeSelector(56, "strong");

  public static final TypeSelector style = new TypeSelector(57, "style");

  public static final TypeSelector sub = new TypeSelector(58, "sub");

  public static final TypeSelector summary = new TypeSelector(59, "summary");

  public static final TypeSelector sup = new TypeSelector(60, "sup");

  public static final TypeSelector svg = new TypeSelector(61, "svg");

  public static final TypeSelector table = new TypeSelector(62, "table");

  public static final TypeSelector tbody = new TypeSelector(63, "tbody");

  public static final TypeSelector td = new TypeSelector(64, "td");

  public static final TypeSelector template = new TypeSelector(65, "template");

  public static final TypeSelector textarea = new TypeSelector(66, "textarea");

  public static final TypeSelector th = new TypeSelector(67, "th");

  public static final TypeSelector thead = new TypeSelector(68, "thead");

  public static final TypeSelector title = new TypeSelector(69, "title");

  public static final TypeSelector tr = new TypeSelector(70, "tr");

  public static final TypeSelector ul = new TypeSelector(71, "ul");

  private static final TypeSelector[] ARRAY = new TypeSelector[] {a, abbr, article, b, blockquote, body, br, button, clipPath, code, dd, defs, details, div, dl, dt, em, fieldset, figure, footer, form, g, h1, h2, h3, h4, h5, h6, head, header, hgroup, hr, html, img, input, kbd, label, legend, li, link, main, meta, nav, ol, optgroup, option, p, path, pre, progress, samp, script, section, select, small, span, strong, style, sub, summary, sup, svg, table, tbody, td, template, textarea, th, thead, title, tr, ul};

  private static final UnmodifiableMap<String, TypeSelector> MAP = buildMap();

  private TypeSelectors() {}

  @Ignore
  public static TypeSelector getByCode(int code) {
    return ARRAY[code];
  }

  @Ignore
  public static TypeSelector getByName(String name) {
    return MAP.get(name);
  }

  private static UnmodifiableMap<String, TypeSelector> buildMap() {
    MutableMap<String, TypeSelector> m = new MutableMap<>();
    m.put("a", a);
    m.put("abbr", abbr);
    m.put("article", article);
    m.put("b", b);
    m.put("blockquote", blockquote);
    m.put("body", body);
    m.put("br", br);
    m.put("button", button);
    m.put("clipPath", clipPath);
    m.put("code", code);
    m.put("dd", dd);
    m.put("defs", defs);
    m.put("details", details);
    m.put("div", div);
    m.put("dl", dl);
    m.put("dt", dt);
    m.put("em", em);
    m.put("fieldset", fieldset);
    m.put("figure", figure);
    m.put("footer", footer);
    m.put("form", form);
    m.put("g", g);
    m.put("h1", h1);
    m.put("h2", h2);
    m.put("h3", h3);
    m.put("h4", h4);
    m.put("h5", h5);
    m.put("h6", h6);
    m.put("head", head);
    m.put("header", header);
    m.put("hgroup", hgroup);
    m.put("hr", hr);
    m.put("html", html);
    m.put("img", img);
    m.put("input", input);
    m.put("kbd", kbd);
    m.put("label", label);
    m.put("legend", legend);
    m.put("li", li);
    m.put("link", link);
    m.put("main", main);
    m.put("meta", meta);
    m.put("nav", nav);
    m.put("ol", ol);
    m.put("optgroup", optgroup);
    m.put("option", option);
    m.put("p", p);
    m.put("path", path);
    m.put("pre", pre);
    m.put("progress", progress);
    m.put("samp", samp);
    m.put("script", script);
    m.put("section", section);
    m.put("select", select);
    m.put("small", small);
    m.put("span", span);
    m.put("strong", strong);
    m.put("style", style);
    m.put("sub", sub);
    m.put("summary", summary);
    m.put("sup", sup);
    m.put("svg", svg);
    m.put("table", table);
    m.put("tbody", tbody);
    m.put("td", td);
    m.put("template", template);
    m.put("textarea", textarea);
    m.put("th", th);
    m.put("thead", thead);
    m.put("title", title);
    m.put("tr", tr);
    m.put("ul", ul);
    return m.toUnmodifiableMap();
  }

}