/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.parser.select;

import static br.com.objectos.css.Css.ACTIVE;
import static br.com.objectos.css.Css.AFTER;
import static br.com.objectos.css.Css.BEFORE;
import static br.com.objectos.css.Css.EMPTY;
import static br.com.objectos.css.Css.HOVER;
import static br.com.objectos.css.Css.VISITED;
import static br.com.objectos.css.Css.cn;
import static br.com.objectos.css.Css.id;
import static br.com.objectos.css.Css.or;
import static br.com.objectos.css.parser.select.SelectorParser.parse;
import static br.com.objectos.css.select.Combinator.ADJACENT_SIBLING;
import static br.com.objectos.css.select.Combinator.CHILD;
import static br.com.objectos.css.select.Combinator.DESCENDANT;
import static br.com.objectos.css.select.Combinator.GENERAL_SIBLING;
import static br.com.objectos.css.select.SelectorFactory.attr;
import static br.com.objectos.css.select.SelectorFactory.contains;
import static br.com.objectos.css.select.SelectorFactory.endsWith;
import static br.com.objectos.css.select.SelectorFactory.eq;
import static br.com.objectos.css.select.SelectorFactory.lang;
import static br.com.objectos.css.select.SelectorFactory.sel;
import static br.com.objectos.css.select.SelectorFactory.startsWith;
import static br.com.objectos.css.select.SelectorFactory.wsList;
import static org.testng.Assert.assertEquals;

import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.TypeSelector;
import br.com.objectos.css.select.TypeSelectors;
import br.com.objectos.css.select.UniversalSelector;
import org.testng.annotations.Test;

public class SelectorParserTest {

  @Test
  public void attribute() {
    assertEquals(parse("[foo]"), attr("foo"));
  }

  @Test
  public void attributeSelectorCompound() {
    assertEquals(parse("a[foo]"), sel(tag("a"), attr("foo")));
    assertEquals(parse("#a[foo]"), sel(id("a"), attr("foo")));
    assertEquals(parse(".a[foo]"), sel(className("a"), attr("foo")));
    assertEquals(parse("[foo][bar]"), sel(attr("foo"), attr("bar")));
  }

  @Test
  public void attributeValue_quoted() {
    assertEquals(parse("[foo='bar']"), attr("foo", eq("bar")));
    assertEquals(parse("[foo=\"bar\"]"), attr("foo", eq("bar")));
  }

  @Test
  public void attributeValue_unquoted() {
    assertEquals(parse("[foo=bar]"), attr("foo", eq("bar")));
    assertEquals(parse("[foo~=bar]"), attr("foo", wsList("bar")));
    assertEquals(parse("[foo^=bar]"), attr("foo", startsWith("bar")));
    assertEquals(parse("[foo$=bar]"), attr("foo", endsWith("bar")));
    assertEquals(parse("[foo*=bar]"), attr("foo", contains("bar")));
    assertEquals(parse("[foo|=bar]"), attr("foo", lang("bar")));
  }

  @Test
  public void attributeValueSelectorCompound() {
    assertEquals(parse("a[foo=bar]"), sel(tag("a"), attr("foo", eq("bar"))));
    assertEquals(parse("#a[foo^=bar]"), sel(id("a"), attr("foo", startsWith("bar"))));
    assertEquals(parse(".a[foo$='bar']"), sel(className("a"), attr("foo", endsWith("bar"))));
    assertEquals(parse("[foo][bar=b]"), sel(attr("foo"), attr("bar", eq("b"))));
    assertEquals(parse("[foo=f][bar=b]"), sel(attr("foo", eq("f")), attr("bar", eq("b"))));
  }

  @Test
  public void classSelectorCompound() {
    assertEquals(parse("a.foo"), sel(tag("a"), cn("foo")));
    assertEquals(parse("#div0.active"), sel(id("div0"), cn("active")));
    assertEquals(parse(".first.second"), sel(cn("first"), cn("second")));
  }

  @Test
  public void complexSelector_descendant() {
    assertEquals(
        parse("div p"),
        sel(tag("div"), DESCENDANT, tag("p"))
    );
    assertEquals(
        parse("div#div0 p"),
        sel(tag("div"), id("div0"), DESCENDANT, tag("p"))
    );
    assertEquals(
        parse("div#div0 p.active"),
        sel(tag("div"), id("div0"), DESCENDANT, tag("p"), cn("active"))
    );
    assertEquals(
        parse("#div0 .active"),
        sel(id("div0"), DESCENDANT, className("active"))
    );
    assertEquals(
        parse("#div0 p.active"),
        sel(id("div0"), DESCENDANT, tag("p"), cn("active"))
    );
    assertEquals(
        parse("#div0 #x.active"),
        sel(id("div0"), DESCENDANT, id("x"), cn("active"))
    );
  }

  @Test
  public void complexSelector_descendant_recursive() {
    assertEquals(
        parse("div p a"),
        sel(tag("div"), DESCENDANT, tag("p"), DESCENDANT, tag("a"))
    );
    assertEquals(
        parse("div#div0 p a"),
        sel(tag("div"), id("div0"), DESCENDANT, tag("p"), DESCENDANT, tag("a"))
    );
    assertEquals(
        parse("div#div0 p.active a"),
        sel(tag("div"), id("div0"), DESCENDANT, tag("p"), cn("active"), DESCENDANT, tag("a"))
    );
  }

  @Test
  public void complexSelector_others() {
    assertEquals(parse("div > p"), sel(tag("div"), CHILD, tag("p")));
    assertEquals(parse("div ~ p"), sel(tag("div"), GENERAL_SIBLING, tag("p")));
    assertEquals(parse("div + p"), sel(tag("div"), ADJACENT_SIBLING, tag("p")));
  }

  @Test
  public void compoundSelector() {
    assertEquals(parse("a#foo.bar"), sel(tag("a"), id("foo"), cn("bar")));
    assertEquals(parse("a#foo.bar.more"), sel(tag("a"), id("foo"), cn("bar"), cn("more")));
    assertEquals(parse("a.foo.bar"), sel(tag("a"), cn("foo"), cn("bar")));
    assertEquals(parse("a.foo.bar.more"), sel(tag("a"), cn("foo"), cn("bar"), cn("more")));

    assertEquals(parse("a#foo[bar]"), sel(tag("a"), id("foo"), attr("bar")));
    assertEquals(parse("a#foo[bar][more]"), sel(tag("a"), id("foo"), attr("bar"), attr("more")));
    assertEquals(parse("a#foo.bar[more]"), sel(tag("a"), id("foo"), cn("bar"), attr("more")));
    assertEquals(parse("a.foo[bar]"), sel(tag("a"), cn("foo"), attr("bar")));
    assertEquals(parse("a[foo][bar]"), sel(tag("a"), attr("foo"), attr("bar")));
  }

  @Test
  public void compoundSelector_attributeValue() {
    assertEquals(parse("a#foo[x=y]"), sel(tag("a"), id("foo"), attr("x", eq("y"))));
    assertEquals(
        parse("a#id.foo[x=y]"),
        sel(tag("a"), id("id"), cn("foo"), attr("x", eq("y")))
    );
    assertEquals(parse("a.foo[x=y]"), sel(tag("a"), cn("foo"), attr("x", eq("y"))));
    assertEquals(parse("a[foo][x=y]"), sel(tag("a"), attr("foo"), attr("x", eq("y"))));
    assertEquals(
        parse("a[foo^=bar][x=y]"),
        sel(tag("a"), attr("foo", startsWith("bar")), attr("x", eq("y")))
    );
    assertEquals(parse("a#id[foo^=bar][x=y]"),
        sel(tag("a"), id("id"), attr("foo", startsWith("bar")), attr("x", eq("y")))
    );
  }

  @Test
  public void compoundSelector_pseudoClass() {
    assertEquals(parse("a#foo:active"), sel(tag("a"), id("foo"), ACTIVE));
    assertEquals(parse("a#id.foo:active"), sel(tag("a"), id("id"), cn("foo"), ACTIVE));
    assertEquals(parse("a.foo:active"), sel(tag("a"), cn("foo"), ACTIVE));
    assertEquals(parse("a[foo]:active"), sel(tag("a"), attr("foo"), ACTIVE));
    assertEquals(parse("a:active:hover"), sel(tag("a"), ACTIVE, HOVER));
    assertEquals(parse("a#id:active:hover"), sel(tag("a"), id("id"), ACTIVE, HOVER));
  }

  @Test
  public void compoundSelector_pseudoElement() {
    assertEquals(parse("a#foo::after"), sel(tag("a"), id("foo"), AFTER));
    assertEquals(parse("a#id.foo::after"), sel(tag("a"), id("id"), cn("foo"), AFTER));
    assertEquals(parse("a.foo::after"), sel(tag("a"), cn("foo"), AFTER));
    assertEquals(parse("a[foo]::after"), sel(tag("a"), attr("foo"), AFTER));
    assertEquals(parse("a:active::after"), sel(tag("a"), ACTIVE, AFTER));
    assertEquals(parse("a#id:active::after"), sel(tag("a"), id("id"), ACTIVE, AFTER));
  }

  @Test
  public void idSelectorCompound() {
    assertEquals(parse("a#foo"), sel(tag("a"), id("foo")));
  }

  @Test
  public void list() {
    assertEquals(parse("a, p"), Css.list(tag("a"), tag("p")));
    assertEquals(parse("a, p, div"), Css.list(tag("a"), tag("p"), tag("div")));
  }

  @Test
  public void pseudoClass() {
    assertEquals(parse(":active"), ACTIVE);
    assertEquals(parse(":visited"), VISITED);
    assertEquals(parse(":hover"), HOVER);
  }

  @Test
  public void pseudoClassSelectorCompound() {
    assertEquals(parse("a:active"), sel(tag("a"), ACTIVE));
    assertEquals(parse("#a:empty"), sel(id("a"), EMPTY));
    assertEquals(parse(".a:active"), sel(className("a"), ACTIVE));
    assertEquals(parse("[foo]:empty"), sel(attr("foo"), EMPTY));
    assertEquals(parse("[foo=f]:active"), sel(attr("foo", eq("f")), ACTIVE));
    assertEquals(parse(":hover:active"), sel(HOVER, ACTIVE));
  }

  @Test
  public void pseudoElement() {
    assertEquals(parse("::before"), BEFORE);
    assertEquals(parse("::after"), AFTER);
  }

  @Test
  public void pseudoElementSelectorCompound() {
    assertEquals(parse("a::after"), sel(tag("a"), AFTER));
    assertEquals(parse("#a::before"), sel(id("a"), BEFORE));
    assertEquals(parse(".a::after"), sel(className("a"), AFTER));
    assertEquals(parse("[foo]::before"), sel(attr("foo"), BEFORE));
    assertEquals(parse("[foo=f]::after"), sel(attr("foo", eq("f")), AFTER));
    assertEquals(parse(":hover::after"), sel(HOVER, AFTER));
  }

  @Test
  public void singles() {
    assertEquals(parse("#div0"), id("div0"));
    assertEquals(parse(".active"), cn("active"));
    assertEquals(parse("ul"), TypeSelectors.ul);
  }

  @Test
  public void universalSelector() {
    assertEquals(parse("*"), UniversalSelector.getInstance());
    assertEquals(parse("*,::before"),
        sel(UniversalSelector.getInstance(), or(), BEFORE)
    );
  }

  private ClassSelector className(String name) {
    return Css.cn(name);
  }

  private TypeSelector tag(String name) {
    return TypeSelectors.getByName(name);
  }

}
