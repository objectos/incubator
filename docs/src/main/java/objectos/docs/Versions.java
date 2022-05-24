/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs;

import br.com.objectos.core.list.MutableList;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.StyleSheet;
import objectos.docs.next.Next;
import objectos.docs.style.Colors;
import objectos.docs.style.Spacing;
import objectos.docs.ui.DocsPage;
import objectos.docs.ui.Pages;
import objectos.docs.v0001.V0001;
import objectos.ssg.SiteStyleSheet;

final class Versions extends DocsPage implements Pages.IgnoreMe {

  private static final ClassSelector DATE = Css.randomDot(3);

  private static final ClassSelector TITLE = Css.randomDot(3);

  private final StyleSheet css = new SiteStyleSheet() {
    @Override
    protected final void definition() {
      style(
        h1,

        margin(Spacing.V16, zero())
      );

      style(
        table, sp(), td,

        paddingTop(Spacing.V02),
        verticalAlign(baseline)
      );

      style(
        thead, sp(), th,

        borderBottom(px(1), solid, Colors.GRAY2)
      );

      style(
        DATE,

        textAlign(center),
        width(Spacing.V28)
      );

      style(
        TITLE,

        textAlign(left)
      );
    }
  };

  @Override
  protected final void body0() {
    article(
      header(
        h1("Versions")
      ),

      p("Choose the version of the Objectos documentation from the list below."),

      table(
        thead(
          tr(
            th(
              TITLE,

              t("Version")
            ),

            th(
              DATE,

              t("Release date")
            )
          )
        ),

        tbody(
          tr(
            td(
              TITLE,

              a(href(Next.INDEX), t("0.2.0-SNAPSHOT"))
            ),

            td(
              DATE,

              t("unreleased")
            )
          ),

          tr(
            td(
              TITLE,

              a(href(V0001.INDEX), t("0.1.0"))
            ),

            td(
              DATE,

              t("2022-05-16")
            )
          )
        )
      )
    );
  }

  @Override
  protected final MutableList<StyleSheet> styleSheets() {
    MutableList<StyleSheet> sheets;
    sheets = super.styleSheets();

    sheets.add(css);

    return sheets;
  }

}