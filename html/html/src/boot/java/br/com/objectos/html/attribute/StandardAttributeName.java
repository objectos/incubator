package br.com.objectos.html.attribute;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.core.map.ImmutableMap;
import br.com.objectos.html.spi.tmpl.Marker;
import br.com.objectos.html.spi.tmpl.Renderer;
import br.com.objectos.html.spi.type.AValue;
import br.com.objectos.html.spi.type.BlockquoteValue;
import br.com.objectos.html.spi.type.BodyValue;
import br.com.objectos.html.spi.type.ButtonValue;
import br.com.objectos.html.spi.type.ClipPathValue;
import br.com.objectos.html.spi.type.DefsValue;
import br.com.objectos.html.spi.type.DetailsValue;
import br.com.objectos.html.spi.type.FormValue;
import br.com.objectos.html.spi.type.GValue;
import br.com.objectos.html.spi.type.ImgValue;
import br.com.objectos.html.spi.type.InputValue;
import br.com.objectos.html.spi.type.LabelValue;
import br.com.objectos.html.spi.type.LinkValue;
import br.com.objectos.html.spi.type.MetaValue;
import br.com.objectos.html.spi.type.OlValue;
import br.com.objectos.html.spi.type.OptionValue;
import br.com.objectos.html.spi.type.PathValue;
import br.com.objectos.html.spi.type.ScriptValue;
import br.com.objectos.html.spi.type.SelectValue;
import br.com.objectos.html.spi.type.StyleValue;
import br.com.objectos.html.spi.type.SvgValue;
import br.com.objectos.html.spi.type.TextareaValue;
import br.com.objectos.html.spi.type.Value;

@Generated("br.com.objectos.html.boot.HtmlBoot")
public abstract class StandardAttributeName implements AttributeName, Value {

  public static final Accesskey ACCESSKEY = new Accesskey();

  public static final Action ACTION = new Action();

  public static final AlignmentBaseline ALIGNMENTBASELINE = new AlignmentBaseline();

  public static final Alt ALT = new Alt();

  public static final Async ASYNC = new Async();

  public static final Autocomplete AUTOCOMPLETE = new Autocomplete();

  public static final Autofocus AUTOFOCUS = new Autofocus();

  public static final BaselineShift BASELINESHIFT = new BaselineShift();

  public static final Charset CHARSET = new Charset();

  public static final Cite CITE = new Cite();

  public static final Class CLASS = new Class();

  public static final ClipPath CLIPPATH = new ClipPath();

  public static final ClipRule CLIPRULE = new ClipRule();

  public static final Color COLOR = new Color();

  public static final ColorInterpolation COLORINTERPOLATION = new ColorInterpolation();

  public static final ColorInterpolationFilters COLORINTERPOLATIONFILTERS = new ColorInterpolationFilters();

  public static final Cols COLS = new Cols();

  public static final Content CONTENT = new Content();

  public static final Contenteditable CONTENTEDITABLE = new Contenteditable();

  public static final Crossorigin CROSSORIGIN = new Crossorigin();

  public static final Cursor CURSOR = new Cursor();

  public static final D D = new D();

  public static final Defer DEFER = new Defer();

  public static final Dir DIR = new Dir();

  public static final Direction DIRECTION = new Direction();

  public static final Dirname DIRNAME = new Dirname();

  public static final Disabled DISABLED = new Disabled();

  public static final Display DISPLAY = new Display();

  public static final DominantBaseline DOMINANTBASELINE = new DominantBaseline();

  public static final Draggable DRAGGABLE = new Draggable();

  public static final Enctype ENCTYPE = new Enctype();

  public static final Fill FILL = new Fill();

  public static final FillOpacity FILLOPACITY = new FillOpacity();

  public static final FillRule FILLRULE = new FillRule();

  public static final Filter FILTER = new Filter();

  public static final FloodColor FLOODCOLOR = new FloodColor();

  public static final FloodOpacity FLOODOPACITY = new FloodOpacity();

  public static final FontFamily FONTFAMILY = new FontFamily();

  public static final FontSize FONTSIZE = new FontSize();

  public static final FontSizeAdjust FONTSIZEADJUST = new FontSizeAdjust();

  public static final FontStretch FONTSTRETCH = new FontStretch();

  public static final FontStyle FONTSTYLE = new FontStyle();

  public static final FontVariant FONTVARIANT = new FontVariant();

  public static final FontWeight FONTWEIGHT = new FontWeight();

  public static final For FOR = new For();

  public static final Form FORM = new Form();

  public static final GlyphOrientationHorizontal GLYPHORIENTATIONHORIZONTAL = new GlyphOrientationHorizontal();

  public static final GlyphOrientationVertical GLYPHORIENTATIONVERTICAL = new GlyphOrientationVertical();

  public static final Height HEIGHT = new Height();

  public static final Hidden HIDDEN = new Hidden();

  public static final Href HREF = new Href();

  public static final HttpEquiv HTTPEQUIV = new HttpEquiv();

  public static final Id ID = new Id();

  public static final ImageRendering IMAGERENDERING = new ImageRendering();

  public static final Integrity INTEGRITY = new Integrity();

  public static final Label LABEL = new Label();

  public static final Lang LANG = new Lang();

  public static final LetterSpacing LETTERSPACING = new LetterSpacing();

  public static final LightingColor LIGHTINGCOLOR = new LightingColor();

  public static final MarkerEnd MARKEREND = new MarkerEnd();

  public static final MarkerMid MARKERMID = new MarkerMid();

  public static final MarkerStart MARKERSTART = new MarkerStart();

  public static final Mask MASK = new Mask();

  public static final MaskType MASKTYPE = new MaskType();

  public static final Maxlength MAXLENGTH = new Maxlength();

  public static final Media MEDIA = new Media();

  public static final Method METHOD = new Method();

  public static final Minlength MINLENGTH = new Minlength();

  public static final Multiple MULTIPLE = new Multiple();

  public static final Name NAME = new Name();

  public static final Nomodule NOMODULE = new Nomodule();

  public static final Onafterprint ONAFTERPRINT = new Onafterprint();

  public static final Onbeforeprint ONBEFOREPRINT = new Onbeforeprint();

  public static final Onbeforeunload ONBEFOREUNLOAD = new Onbeforeunload();

  public static final Onhashchange ONHASHCHANGE = new Onhashchange();

  public static final Onlanguagechange ONLANGUAGECHANGE = new Onlanguagechange();

  public static final Onmessage ONMESSAGE = new Onmessage();

  public static final Onoffline ONOFFLINE = new Onoffline();

  public static final Ononline ONONLINE = new Ononline();

  public static final Onpagehide ONPAGEHIDE = new Onpagehide();

  public static final Onpageshow ONPAGESHOW = new Onpageshow();

  public static final Onpopstate ONPOPSTATE = new Onpopstate();

  public static final Onrejectionhandled ONREJECTIONHANDLED = new Onrejectionhandled();

  public static final Onstorage ONSTORAGE = new Onstorage();

  public static final Onunhandledrejection ONUNHANDLEDREJECTION = new Onunhandledrejection();

  public static final Onunload ONUNLOAD = new Onunload();

  public static final Opacity OPACITY = new Opacity();

  public static final Open OPEN = new Open();

  public static final Overflow OVERFLOW = new Overflow();

  public static final PaintOrder PAINTORDER = new PaintOrder();

  public static final Placeholder PLACEHOLDER = new Placeholder();

  public static final PointerEvents POINTEREVENTS = new PointerEvents();

  public static final Readonly READONLY = new Readonly();

  public static final Referrerpolicy REFERRERPOLICY = new Referrerpolicy();

  public static final Rel REL = new Rel();

  public static final Required REQUIRED = new Required();

  public static final Rev REV = new Rev();

  public static final Reversed REVERSED = new Reversed();

  public static final Rows ROWS = new Rows();

  public static final Selected SELECTED = new Selected();

  public static final ShapeRendering SHAPERENDERING = new ShapeRendering();

  public static final Size SIZE = new Size();

  public static final Sizes SIZES = new Sizes();

  public static final Spellcheck SPELLCHECK = new Spellcheck();

  public static final Src SRC = new Src();

  public static final Srcset SRCSET = new Srcset();

  public static final Start START = new Start();

  public static final StopColor STOPCOLOR = new StopColor();

  public static final StopOpacity STOPOPACITY = new StopOpacity();

  public static final Stroke STROKE = new Stroke();

  public static final StrokeDasharray STROKEDASHARRAY = new StrokeDasharray();

  public static final StrokeDashoffset STROKEDASHOFFSET = new StrokeDashoffset();

  public static final StrokeLinecap STROKELINECAP = new StrokeLinecap();

  public static final StrokeLinejoin STROKELINEJOIN = new StrokeLinejoin();

  public static final StrokeMiterlimit STROKEMITERLIMIT = new StrokeMiterlimit();

  public static final StrokeOpacity STROKEOPACITY = new StrokeOpacity();

  public static final StrokeWidth STROKEWIDTH = new StrokeWidth();

  public static final Style STYLE = new Style();

  public static final Tabindex TABINDEX = new Tabindex();

  public static final Target TARGET = new Target();

  public static final TextAnchor TEXTANCHOR = new TextAnchor();

  public static final TextDecoration TEXTDECORATION = new TextDecoration();

  public static final TextOverflow TEXTOVERFLOW = new TextOverflow();

  public static final TextRendering TEXTRENDERING = new TextRendering();

  public static final Title TITLE = new Title();

  public static final Transform TRANSFORM = new Transform();

  public static final TransformOrigin TRANSFORMORIGIN = new TransformOrigin();

  public static final Translate TRANSLATE = new Translate();

  public static final Type TYPE = new Type();

  public static final UnicodeBidi UNICODEBIDI = new UnicodeBidi();

  public static final Value VALUE = new Value();

  public static final VectorEffect VECTOREFFECT = new VectorEffect();

  public static final ViewBox VIEWBOX = new ViewBox();

  public static final Visibility VISIBILITY = new Visibility();

  public static final WhiteSpace WHITESPACE = new WhiteSpace();

  public static final Width WIDTH = new Width();

  public static final WordSpacing WORDSPACING = new WordSpacing();

  public static final Wrap WRAP = new Wrap();

  public static final WritingMode WRITINGMODE = new WritingMode();

  public static final Xmlns XMLNS = new Xmlns();

  private static final StandardAttributeName[] ARRAY = new StandardAttributeName[] {ACCESSKEY, ACTION, ALIGNMENTBASELINE, ALT, ASYNC, AUTOCOMPLETE, AUTOFOCUS, BASELINESHIFT, CHARSET, CITE, CLASS, CLIPPATH, CLIPRULE, COLOR, COLORINTERPOLATION, COLORINTERPOLATIONFILTERS, COLS, CONTENT, CONTENTEDITABLE, CROSSORIGIN, CURSOR, D, DEFER, DIR, DIRECTION, DIRNAME, DISABLED, DISPLAY, DOMINANTBASELINE, DRAGGABLE, ENCTYPE, FILL, FILLOPACITY, FILLRULE, FILTER, FLOODCOLOR, FLOODOPACITY, FONTFAMILY, FONTSIZE, FONTSIZEADJUST, FONTSTRETCH, FONTSTYLE, FONTVARIANT, FONTWEIGHT, FOR, FORM, GLYPHORIENTATIONHORIZONTAL, GLYPHORIENTATIONVERTICAL, HEIGHT, HIDDEN, HREF, HTTPEQUIV, ID, IMAGERENDERING, INTEGRITY, LABEL, LANG, LETTERSPACING, LIGHTINGCOLOR, MARKEREND, MARKERMID, MARKERSTART, MASK, MASKTYPE, MAXLENGTH, MEDIA, METHOD, MINLENGTH, MULTIPLE, NAME, NOMODULE, ONAFTERPRINT, ONBEFOREPRINT, ONBEFOREUNLOAD, ONHASHCHANGE, ONLANGUAGECHANGE, ONMESSAGE, ONOFFLINE, ONONLINE, ONPAGEHIDE, ONPAGESHOW, ONPOPSTATE, ONREJECTIONHANDLED, ONSTORAGE, ONUNHANDLEDREJECTION, ONUNLOAD, OPACITY, OPEN, OVERFLOW, PAINTORDER, PLACEHOLDER, POINTEREVENTS, READONLY, REFERRERPOLICY, REL, REQUIRED, REV, REVERSED, ROWS, SELECTED, SHAPERENDERING, SIZE, SIZES, SPELLCHECK, SRC, SRCSET, START, STOPCOLOR, STOPOPACITY, STROKE, STROKEDASHARRAY, STROKEDASHOFFSET, STROKELINECAP, STROKELINEJOIN, STROKEMITERLIMIT, STROKEOPACITY, STROKEWIDTH, STYLE, TABINDEX, TARGET, TEXTANCHOR, TEXTDECORATION, TEXTOVERFLOW, TEXTRENDERING, TITLE, TRANSFORM, TRANSFORMORIGIN, TRANSLATE, TYPE, UNICODEBIDI, VALUE, VECTOREFFECT, VIEWBOX, VISIBILITY, WHITESPACE, WIDTH, WORDSPACING, WRAP, WRITINGMODE, XMLNS};

  private static final ImmutableMap<String, StandardAttributeName> MAP = new NamesBuilder().put("accesskey", ACCESSKEY).put("action", ACTION).put("alignment-baseline", ALIGNMENTBASELINE).put("alt", ALT).put("async", ASYNC).put("autocomplete", AUTOCOMPLETE).put("autofocus", AUTOFOCUS).put("baseline-shift", BASELINESHIFT).put("charset", CHARSET).put("cite", CITE).put("class", CLASS).put("clip-path", CLIPPATH).put("clip-rule", CLIPRULE).put("color", COLOR).put("color-interpolation", COLORINTERPOLATION).put("color-interpolation-filters", COLORINTERPOLATIONFILTERS).put("cols", COLS).put("content", CONTENT).put("contenteditable", CONTENTEDITABLE).put("crossorigin", CROSSORIGIN).put("cursor", CURSOR).put("d", D).put("defer", DEFER).put("dir", DIR).put("direction", DIRECTION).put("dirname", DIRNAME).put("disabled", DISABLED).put("display", DISPLAY).put("dominant-baseline", DOMINANTBASELINE).put("draggable", DRAGGABLE).put("enctype", ENCTYPE).put("fill", FILL).put("fill-opacity", FILLOPACITY).put("fill-rule", FILLRULE).put("filter", FILTER).put("flood-color", FLOODCOLOR).put("flood-opacity", FLOODOPACITY).put("font-family", FONTFAMILY).put("font-size", FONTSIZE).put("font-size-adjust", FONTSIZEADJUST).put("font-stretch", FONTSTRETCH).put("font-style", FONTSTYLE).put("font-variant", FONTVARIANT).put("font-weight", FONTWEIGHT).put("for", FOR).put("form", FORM).put("glyph-orientation-horizontal", GLYPHORIENTATIONHORIZONTAL).put("glyph-orientation-vertical", GLYPHORIENTATIONVERTICAL).put("height", HEIGHT).put("hidden", HIDDEN).put("href", HREF).put("http-equiv", HTTPEQUIV).put("id", ID).put("image-rendering", IMAGERENDERING).put("integrity", INTEGRITY).put("label", LABEL).put("lang", LANG).put("letter-spacing", LETTERSPACING).put("lighting-color", LIGHTINGCOLOR).put("marker-end", MARKEREND).put("marker-mid", MARKERMID).put("marker-start", MARKERSTART).put("mask", MASK).put("mask-type", MASKTYPE).put("maxlength", MAXLENGTH).put("media", MEDIA).put("method", METHOD).put("minlength", MINLENGTH).put("multiple", MULTIPLE).put("name", NAME).put("nomodule", NOMODULE).put("onafterprint", ONAFTERPRINT).put("onbeforeprint", ONBEFOREPRINT).put("onbeforeunload", ONBEFOREUNLOAD).put("onhashchange", ONHASHCHANGE).put("onlanguagechange", ONLANGUAGECHANGE).put("onmessage", ONMESSAGE).put("onoffline", ONOFFLINE).put("ononline", ONONLINE).put("onpagehide", ONPAGEHIDE).put("onpageshow", ONPAGESHOW).put("onpopstate", ONPOPSTATE).put("onrejectionhandled", ONREJECTIONHANDLED).put("onstorage", ONSTORAGE).put("onunhandledrejection", ONUNHANDLEDREJECTION).put("onunload", ONUNLOAD).put("opacity", OPACITY).put("open", OPEN).put("overflow", OVERFLOW).put("paint-order", PAINTORDER).put("placeholder", PLACEHOLDER).put("pointer-events", POINTEREVENTS).put("readonly", READONLY).put("referrerpolicy", REFERRERPOLICY).put("rel", REL).put("required", REQUIRED).put("rev", REV).put("reversed", REVERSED).put("rows", ROWS).put("selected", SELECTED).put("shape-rendering", SHAPERENDERING).put("size", SIZE).put("sizes", SIZES).put("spellcheck", SPELLCHECK).put("src", SRC).put("srcset", SRCSET).put("start", START).put("stop-color", STOPCOLOR).put("stop-opacity", STOPOPACITY).put("stroke", STROKE).put("stroke-dasharray", STROKEDASHARRAY).put("stroke-dashoffset", STROKEDASHOFFSET).put("stroke-linecap", STROKELINECAP).put("stroke-linejoin", STROKELINEJOIN).put("stroke-miterlimit", STROKEMITERLIMIT).put("stroke-opacity", STROKEOPACITY).put("stroke-width", STROKEWIDTH).put("style", STYLE).put("tabindex", TABINDEX).put("target", TARGET).put("text-anchor", TEXTANCHOR).put("text-decoration", TEXTDECORATION).put("text-overflow", TEXTOVERFLOW).put("text-rendering", TEXTRENDERING).put("title", TITLE).put("transform", TRANSFORM).put("transform-origin", TRANSFORMORIGIN).put("translate", TRANSLATE).put("type", TYPE).put("unicode-bidi", UNICODEBIDI).put("value", VALUE).put("vector-effect", VECTOREFFECT).put("viewBox", VIEWBOX).put("visibility", VISIBILITY).put("white-space", WHITESPACE).put("width", WIDTH).put("word-spacing", WORDSPACING).put("wrap", WRAP).put("writing-mode", WRITINGMODE).put("xmlns", XMLNS).build();

  private final int code;

  private final AttributeKind kind;

  private final String name;

  StandardAttributeName(int code, AttributeKind kind, String name) {
    this.code = code;
    this.kind = kind;
    this.name = name;
  }

  public static StandardAttributeName getByCode(int code) {
    return ARRAY[code];
  }

  public static StandardAttributeName getByName(String name) {
    return MAP.get(name);
  }

  public static int size() {
    return ARRAY.length;
  }

  @Override
  public final int getCode() {
    return code;
  }

  @Override
  public final AttributeKind getKind() {
    return kind;
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final void mark(Marker marker) {
    marker.markAttribute();
  }

  @Override
  public final void render(Renderer renderer) {}

  public static class Accesskey extends StandardAttributeName implements GlobalAttributeName {

    private Accesskey() {
      super(0, AttributeKind.STRING, "accesskey");
    }

  }

  public static class Action extends StandardAttributeName implements FormValue {

    private Action() {
      super(1, AttributeKind.STRING, "action");
    }

  }

  public static class AlignmentBaseline extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private AlignmentBaseline() {
      super(2, AttributeKind.STRING, "alignment-baseline");
    }

  }

  public static class Alt extends StandardAttributeName implements ImgValue {

    private Alt() {
      super(3, AttributeKind.STRING, "alt");
    }

  }

  public static class Async extends StandardAttributeName implements ScriptValue {

    private Async() {
      super(4, AttributeKind.BOOLEAN, "async");
    }

  }

  public static class Autocomplete extends StandardAttributeName implements SelectValue, TextareaValue {

    private Autocomplete() {
      super(5, AttributeKind.STRING, "autocomplete");
    }

  }

  public static class Autofocus extends StandardAttributeName implements InputValue {

    private Autofocus() {
      super(6, AttributeKind.BOOLEAN, "autofocus");
    }

  }

  public static class BaselineShift extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private BaselineShift() {
      super(7, AttributeKind.STRING, "baseline-shift");
    }

  }

  public static class Charset extends StandardAttributeName implements MetaValue {

    private Charset() {
      super(8, AttributeKind.STRING, "charset");
    }

  }

  public static class Cite extends StandardAttributeName implements BlockquoteValue {

    private Cite() {
      super(9, AttributeKind.STRING, "cite");
    }

  }

  public static class Class extends StandardAttributeName implements GlobalAttributeName {

    private Class() {
      super(10, AttributeKind.STRING, "class");
    }

  }

  public static class ClipPath extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private ClipPath() {
      super(11, AttributeKind.STRING, "clip-path");
    }

  }

  public static class ClipRule extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private ClipRule() {
      super(12, AttributeKind.STRING, "clip-rule");
    }

  }

  public static class Color extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Color() {
      super(13, AttributeKind.STRING, "color");
    }

  }

  public static class ColorInterpolation extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private ColorInterpolation() {
      super(14, AttributeKind.STRING, "color-interpolation");
    }

  }

  public static class ColorInterpolationFilters extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private ColorInterpolationFilters() {
      super(15, AttributeKind.STRING, "color-interpolation-filters");
    }

  }

  public static class Cols extends StandardAttributeName implements TextareaValue {

    private Cols() {
      super(16, AttributeKind.STRING, "cols");
    }

  }

  public static class Content extends StandardAttributeName implements MetaValue {

    private Content() {
      super(17, AttributeKind.STRING, "content");
    }

  }

  public static class Contenteditable extends StandardAttributeName implements GlobalAttributeName {

    private Contenteditable() {
      super(18, AttributeKind.STRING, "contenteditable");
    }

  }

  public static class Crossorigin extends StandardAttributeName implements LinkValue, ScriptValue {

    private Crossorigin() {
      super(19, AttributeKind.STRING, "crossorigin");
    }

  }

  public static class Cursor extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Cursor() {
      super(20, AttributeKind.STRING, "cursor");
    }

  }

  public static class D extends StandardAttributeName implements ClipPathValue, PathValue {

    private D() {
      super(21, AttributeKind.STRING, "d");
    }

  }

  public static class Defer extends StandardAttributeName implements ScriptValue {

    private Defer() {
      super(22, AttributeKind.BOOLEAN, "defer");
    }

  }

  public static class Dir extends StandardAttributeName implements GlobalAttributeName {

    private Dir() {
      super(23, AttributeKind.STRING, "dir");
    }

  }

  public static class Direction extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Direction() {
      super(24, AttributeKind.STRING, "direction");
    }

  }

  public static class Dirname extends StandardAttributeName implements TextareaValue {

    private Dirname() {
      super(25, AttributeKind.STRING, "dirname");
    }

  }

  public static class Disabled extends StandardAttributeName implements OptionValue, SelectValue, TextareaValue {

    private Disabled() {
      super(26, AttributeKind.BOOLEAN, "disabled");
    }

  }

  public static class Display extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Display() {
      super(27, AttributeKind.STRING, "display");
    }

  }

  public static class DominantBaseline extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private DominantBaseline() {
      super(28, AttributeKind.STRING, "dominant-baseline");
    }

  }

  public static class Draggable extends StandardAttributeName implements GlobalAttributeName {

    private Draggable() {
      super(29, AttributeKind.STRING, "draggable");
    }

  }

  public static class Enctype extends StandardAttributeName implements FormValue {

    private Enctype() {
      super(30, AttributeKind.STRING, "enctype");
    }

  }

  public static class Fill extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Fill() {
      super(31, AttributeKind.STRING, "fill");
    }

  }

  public static class FillOpacity extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FillOpacity() {
      super(32, AttributeKind.STRING, "fill-opacity");
    }

  }

  public static class FillRule extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FillRule() {
      super(33, AttributeKind.STRING, "fill-rule");
    }

  }

  public static class Filter extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Filter() {
      super(34, AttributeKind.STRING, "filter");
    }

  }

  public static class FloodColor extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FloodColor() {
      super(35, AttributeKind.STRING, "flood-color");
    }

  }

  public static class FloodOpacity extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FloodOpacity() {
      super(36, AttributeKind.STRING, "flood-opacity");
    }

  }

  public static class FontFamily extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FontFamily() {
      super(37, AttributeKind.STRING, "font-family");
    }

  }

  public static class FontSize extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FontSize() {
      super(38, AttributeKind.STRING, "font-size");
    }

  }

  public static class FontSizeAdjust extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FontSizeAdjust() {
      super(39, AttributeKind.STRING, "font-size-adjust");
    }

  }

  public static class FontStretch extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FontStretch() {
      super(40, AttributeKind.STRING, "font-stretch");
    }

  }

  public static class FontStyle extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FontStyle() {
      super(41, AttributeKind.STRING, "font-style");
    }

  }

  public static class FontVariant extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FontVariant() {
      super(42, AttributeKind.STRING, "font-variant");
    }

  }

  public static class FontWeight extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private FontWeight() {
      super(43, AttributeKind.STRING, "font-weight");
    }

  }

  public static class For extends StandardAttributeName implements LabelValue {

    private For() {
      super(44, AttributeKind.STRING, "for");
    }

  }

  public static class Form extends StandardAttributeName implements SelectValue, TextareaValue {

    private Form() {
      super(45, AttributeKind.STRING, "form");
    }

  }

  public static class GlyphOrientationHorizontal extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private GlyphOrientationHorizontal() {
      super(46, AttributeKind.STRING, "glyph-orientation-horizontal");
    }

  }

  public static class GlyphOrientationVertical extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private GlyphOrientationVertical() {
      super(47, AttributeKind.STRING, "glyph-orientation-vertical");
    }

  }

  public static class Height extends StandardAttributeName implements ImgValue, SvgValue {

    private Height() {
      super(48, AttributeKind.STRING, "height");
    }

  }

  public static class Hidden extends StandardAttributeName implements GlobalAttributeName {

    private Hidden() {
      super(49, AttributeKind.BOOLEAN, "hidden");
    }

  }

  public static class Href extends StandardAttributeName implements AValue, LinkValue {

    private Href() {
      super(50, AttributeKind.STRING, "href");
    }

  }

  public static class HttpEquiv extends StandardAttributeName implements MetaValue {

    private HttpEquiv() {
      super(51, AttributeKind.STRING, "http-equiv");
    }

  }

  public static class Id extends StandardAttributeName implements GlobalAttributeName {

    private Id() {
      super(52, AttributeKind.STRING, "id");
    }

  }

  public static class ImageRendering extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private ImageRendering() {
      super(53, AttributeKind.STRING, "image-rendering");
    }

  }

  public static class Integrity extends StandardAttributeName implements ScriptValue {

    private Integrity() {
      super(54, AttributeKind.STRING, "integrity");
    }

  }

  public static class Label extends StandardAttributeName implements OptionValue {

    private Label() {
      super(55, AttributeKind.STRING, "label");
    }

  }

  public static class Lang extends StandardAttributeName implements GlobalAttributeName {

    private Lang() {
      super(56, AttributeKind.STRING, "lang");
    }

  }

  public static class LetterSpacing extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private LetterSpacing() {
      super(57, AttributeKind.STRING, "letter-spacing");
    }

  }

  public static class LightingColor extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private LightingColor() {
      super(58, AttributeKind.STRING, "lighting-color");
    }

  }

  public static class MarkerEnd extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private MarkerEnd() {
      super(59, AttributeKind.STRING, "marker-end");
    }

  }

  public static class MarkerMid extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private MarkerMid() {
      super(60, AttributeKind.STRING, "marker-mid");
    }

  }

  public static class MarkerStart extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private MarkerStart() {
      super(61, AttributeKind.STRING, "marker-start");
    }

  }

  public static class Mask extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Mask() {
      super(62, AttributeKind.STRING, "mask");
    }

  }

  public static class MaskType extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private MaskType() {
      super(63, AttributeKind.STRING, "mask-type");
    }

  }

  public static class Maxlength extends StandardAttributeName implements TextareaValue {

    private Maxlength() {
      super(64, AttributeKind.STRING, "maxlength");
    }

  }

  public static class Media extends StandardAttributeName implements LinkValue {

    private Media() {
      super(65, AttributeKind.STRING, "media");
    }

  }

  public static class Method extends StandardAttributeName implements FormValue {

    private Method() {
      super(66, AttributeKind.STRING, "method");
    }

  }

  public static class Minlength extends StandardAttributeName implements TextareaValue {

    private Minlength() {
      super(67, AttributeKind.STRING, "minlength");
    }

  }

  public static class Multiple extends StandardAttributeName implements SelectValue {

    private Multiple() {
      super(68, AttributeKind.BOOLEAN, "multiple");
    }

  }

  public static class Name extends StandardAttributeName implements InputValue, MetaValue, SelectValue, TextareaValue {

    private Name() {
      super(69, AttributeKind.STRING, "name");
    }

  }

  public static class Nomodule extends StandardAttributeName implements ScriptValue {

    private Nomodule() {
      super(70, AttributeKind.BOOLEAN, "nomodule");
    }

  }

  public static class Onafterprint extends StandardAttributeName implements BodyValue {

    private Onafterprint() {
      super(71, AttributeKind.STRING, "onafterprint");
    }

  }

  public static class Onbeforeprint extends StandardAttributeName implements BodyValue {

    private Onbeforeprint() {
      super(72, AttributeKind.STRING, "onbeforeprint");
    }

  }

  public static class Onbeforeunload extends StandardAttributeName implements BodyValue {

    private Onbeforeunload() {
      super(73, AttributeKind.STRING, "onbeforeunload");
    }

  }

  public static class Onhashchange extends StandardAttributeName implements BodyValue {

    private Onhashchange() {
      super(74, AttributeKind.STRING, "onhashchange");
    }

  }

  public static class Onlanguagechange extends StandardAttributeName implements BodyValue {

    private Onlanguagechange() {
      super(75, AttributeKind.STRING, "onlanguagechange");
    }

  }

  public static class Onmessage extends StandardAttributeName implements BodyValue {

    private Onmessage() {
      super(76, AttributeKind.STRING, "onmessage");
    }

  }

  public static class Onoffline extends StandardAttributeName implements BodyValue {

    private Onoffline() {
      super(77, AttributeKind.STRING, "onoffline");
    }

  }

  public static class Ononline extends StandardAttributeName implements BodyValue {

    private Ononline() {
      super(78, AttributeKind.STRING, "ononline");
    }

  }

  public static class Onpagehide extends StandardAttributeName implements BodyValue {

    private Onpagehide() {
      super(79, AttributeKind.STRING, "onpagehide");
    }

  }

  public static class Onpageshow extends StandardAttributeName implements BodyValue {

    private Onpageshow() {
      super(80, AttributeKind.STRING, "onpageshow");
    }

  }

  public static class Onpopstate extends StandardAttributeName implements BodyValue {

    private Onpopstate() {
      super(81, AttributeKind.STRING, "onpopstate");
    }

  }

  public static class Onrejectionhandled extends StandardAttributeName implements BodyValue {

    private Onrejectionhandled() {
      super(82, AttributeKind.STRING, "onrejectionhandled");
    }

  }

  public static class Onstorage extends StandardAttributeName implements BodyValue {

    private Onstorage() {
      super(83, AttributeKind.STRING, "onstorage");
    }

  }

  public static class Onunhandledrejection extends StandardAttributeName implements BodyValue {

    private Onunhandledrejection() {
      super(84, AttributeKind.STRING, "onunhandledrejection");
    }

  }

  public static class Onunload extends StandardAttributeName implements BodyValue {

    private Onunload() {
      super(85, AttributeKind.STRING, "onunload");
    }

  }

  public static class Opacity extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Opacity() {
      super(86, AttributeKind.STRING, "opacity");
    }

  }

  public static class Open extends StandardAttributeName implements DetailsValue {

    private Open() {
      super(87, AttributeKind.BOOLEAN, "open");
    }

  }

  public static class Overflow extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Overflow() {
      super(88, AttributeKind.STRING, "overflow");
    }

  }

  public static class PaintOrder extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private PaintOrder() {
      super(89, AttributeKind.STRING, "paint-order");
    }

  }

  public static class Placeholder extends StandardAttributeName implements InputValue, TextareaValue {

    private Placeholder() {
      super(90, AttributeKind.STRING, "placeholder");
    }

  }

  public static class PointerEvents extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private PointerEvents() {
      super(91, AttributeKind.STRING, "pointer-events");
    }

  }

  public static class Readonly extends StandardAttributeName implements InputValue, TextareaValue {

    private Readonly() {
      super(92, AttributeKind.BOOLEAN, "readonly");
    }

  }

  public static class Referrerpolicy extends StandardAttributeName implements LinkValue, ScriptValue {

    private Referrerpolicy() {
      super(93, AttributeKind.STRING, "referrerpolicy");
    }

  }

  public static class Rel extends StandardAttributeName implements LinkValue {

    private Rel() {
      super(94, AttributeKind.STRING, "rel");
    }

  }

  public static class Required extends StandardAttributeName implements InputValue, SelectValue, TextareaValue {

    private Required() {
      super(95, AttributeKind.BOOLEAN, "required");
    }

  }

  public static class Rev extends StandardAttributeName implements LinkValue {

    private Rev() {
      super(96, AttributeKind.STRING, "rev");
    }

  }

  public static class Reversed extends StandardAttributeName implements OlValue {

    private Reversed() {
      super(97, AttributeKind.BOOLEAN, "reversed");
    }

  }

  public static class Rows extends StandardAttributeName implements TextareaValue {

    private Rows() {
      super(98, AttributeKind.STRING, "rows");
    }

  }

  public static class Selected extends StandardAttributeName implements OptionValue {

    private Selected() {
      super(99, AttributeKind.BOOLEAN, "selected");
    }

  }

  public static class ShapeRendering extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private ShapeRendering() {
      super(100, AttributeKind.STRING, "shape-rendering");
    }

  }

  public static class Size extends StandardAttributeName implements SelectValue {

    private Size() {
      super(101, AttributeKind.STRING, "size");
    }

  }

  public static class Sizes extends StandardAttributeName implements LinkValue {

    private Sizes() {
      super(102, AttributeKind.STRING, "sizes");
    }

  }

  public static class Spellcheck extends StandardAttributeName implements GlobalAttributeName {

    private Spellcheck() {
      super(103, AttributeKind.STRING, "spellcheck");
    }

  }

  public static class Src extends StandardAttributeName implements ImgValue, ScriptValue {

    private Src() {
      super(104, AttributeKind.STRING, "src");
    }

  }

  public static class Srcset extends StandardAttributeName implements ImgValue {

    private Srcset() {
      super(105, AttributeKind.STRING, "srcset");
    }

  }

  public static class Start extends StandardAttributeName implements OlValue {

    private Start() {
      super(106, AttributeKind.STRING, "start");
    }

  }

  public static class StopColor extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StopColor() {
      super(107, AttributeKind.STRING, "stop-color");
    }

  }

  public static class StopOpacity extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StopOpacity() {
      super(108, AttributeKind.STRING, "stop-opacity");
    }

  }

  public static class Stroke extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Stroke() {
      super(109, AttributeKind.STRING, "stroke");
    }

  }

  public static class StrokeDasharray extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StrokeDasharray() {
      super(110, AttributeKind.STRING, "stroke-dasharray");
    }

  }

  public static class StrokeDashoffset extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StrokeDashoffset() {
      super(111, AttributeKind.STRING, "stroke-dashoffset");
    }

  }

  public static class StrokeLinecap extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StrokeLinecap() {
      super(112, AttributeKind.STRING, "stroke-linecap");
    }

  }

  public static class StrokeLinejoin extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StrokeLinejoin() {
      super(113, AttributeKind.STRING, "stroke-linejoin");
    }

  }

  public static class StrokeMiterlimit extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StrokeMiterlimit() {
      super(114, AttributeKind.STRING, "stroke-miterlimit");
    }

  }

  public static class StrokeOpacity extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StrokeOpacity() {
      super(115, AttributeKind.STRING, "stroke-opacity");
    }

  }

  public static class StrokeWidth extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private StrokeWidth() {
      super(116, AttributeKind.STRING, "stroke-width");
    }

  }

  public static class Style extends StandardAttributeName implements GlobalAttributeName {

    private Style() {
      super(117, AttributeKind.STRING, "style");
    }

  }

  public static class Tabindex extends StandardAttributeName implements GlobalAttributeName {

    private Tabindex() {
      super(118, AttributeKind.STRING, "tabindex");
    }

  }

  public static class Target extends StandardAttributeName implements AValue {

    private Target() {
      super(119, AttributeKind.STRING, "target");
    }

  }

  public static class TextAnchor extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private TextAnchor() {
      super(120, AttributeKind.STRING, "text-anchor");
    }

  }

  public static class TextDecoration extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private TextDecoration() {
      super(121, AttributeKind.STRING, "text-decoration");
    }

  }

  public static class TextOverflow extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private TextOverflow() {
      super(122, AttributeKind.STRING, "text-overflow");
    }

  }

  public static class TextRendering extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private TextRendering() {
      super(123, AttributeKind.STRING, "text-rendering");
    }

  }

  public static class Title extends StandardAttributeName implements GlobalAttributeName {

    private Title() {
      super(124, AttributeKind.STRING, "title");
    }

  }

  public static class Transform extends StandardAttributeName implements ClipPathValue, GValue, PathValue, SvgValue {

    private Transform() {
      super(125, AttributeKind.STRING, "transform");
    }

  }

  public static class TransformOrigin extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private TransformOrigin() {
      super(126, AttributeKind.STRING, "transform-origin");
    }

  }

  public static class Translate extends StandardAttributeName implements GlobalAttributeName {

    private Translate() {
      super(127, AttributeKind.STRING, "translate");
    }

  }

  public static class Type extends StandardAttributeName implements ButtonValue, InputValue, LinkValue, OlValue, ScriptValue, StyleValue {

    private Type() {
      super(128, AttributeKind.STRING, "type");
    }

  }

  public static class UnicodeBidi extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private UnicodeBidi() {
      super(129, AttributeKind.STRING, "unicode-bidi");
    }

  }

  public static class Value extends StandardAttributeName implements InputValue, OptionValue {

    private Value() {
      super(130, AttributeKind.STRING, "value");
    }

  }

  public static class VectorEffect extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private VectorEffect() {
      super(131, AttributeKind.STRING, "vector-effect");
    }

  }

  public static class ViewBox extends StandardAttributeName implements SvgValue {

    private ViewBox() {
      super(132, AttributeKind.STRING, "viewBox");
    }

  }

  public static class Visibility extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private Visibility() {
      super(133, AttributeKind.STRING, "visibility");
    }

  }

  public static class WhiteSpace extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private WhiteSpace() {
      super(134, AttributeKind.STRING, "white-space");
    }

  }

  public static class Width extends StandardAttributeName implements ImgValue, SvgValue {

    private Width() {
      super(135, AttributeKind.STRING, "width");
    }

  }

  public static class WordSpacing extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private WordSpacing() {
      super(136, AttributeKind.STRING, "word-spacing");
    }

  }

  public static class Wrap extends StandardAttributeName implements TextareaValue {

    private Wrap() {
      super(137, AttributeKind.STRING, "wrap");
    }

  }

  public static class WritingMode extends StandardAttributeName implements ClipPathValue, DefsValue, GValue, PathValue, SvgValue {

    private WritingMode() {
      super(138, AttributeKind.STRING, "writing-mode");
    }

  }

  public static class Xmlns extends StandardAttributeName implements SvgValue {

    private Xmlns() {
      super(139, AttributeKind.STRING, "xmlns");
    }

  }

}