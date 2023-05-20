/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.internal;

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.border.Border;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderTop;
import br.com.objectos.css.framework.border.Rounded;
import br.com.objectos.css.framework.flexbox.FlexWrap;
import br.com.objectos.css.framework.flexbox.JustifyContent;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.spacing.MarginBottom;
import br.com.objectos.css.framework.spacing.MarginRight;
import br.com.objectos.css.framework.spacing.MarginTop;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.Padding;
import br.com.objectos.css.framework.spacing.PaddingBottom;
import br.com.objectos.css.framework.spacing.PaddingX;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.LetterSpacing;
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.select.ClassSelector;
import java.io.IOException;
import java.io.UncheckedIOException;
import objectos.asciidoc.pseudom.Node;
import objectos.asciidoc.pseudom.Node.ContainerNode;
import objectos.asciidoc.pseudom.Node.Header;
import objectos.asciidoc.pseudom.Node.Paragraph;
import objectos.asciidoc.pseudom.Node.Text;
import objectos.asciidoc.pseudom.Node.Title;

public final class VersionsTemplate2 extends DocsTemplate2 {

  static final ClassSelector DATE = Css.randomDot(3);

  static final ClassSelector TITLE = Css.randomDot(3);

  VersionsTemplate2(DocsInjector injector) {
    super(injector);
  }

  public static void initVersionsTemplate() {}

  @Override
  final void main0() {
    main(
      BorderColor.slate200,
      BorderTop.v1,
      PaddingBottom.v64,
      PaddingX.v04,
      PaddingX.md.v06,

      header(
        MarginX.auto,
        MaxWidth.screenXl,

        f(this::render)
      ),

      section(
        MarginX.auto,
        MaxWidth.screenXl,

        h2(
          FontSize.xLarge2,
          LetterSpacing.tight,
          MarginTop.v14,

          t("v0.x series")
        ),

        p(t("Our current release stream.")),

        div(
          Display.lg.flex,
          FlexWrap.lg.wrap,
          MarginTop.v10,

          f(this::v0x)
        )
      )
    );
  }

  private void render() {
    try (var document = injector.$document2()) {
      var nodes = document.nodes();

      var iter = nodes.iterator();

      if (!iter.hasNext()) {
        return;
      }

      var first = iter.next();

      if (first instanceof Header header) {
        renderContainer(header);
      } else {
        renderNode(first);
      }

      div(
        FontSize.xLarge,
        MarginBottom.v10,
        MarginTop.v02,
        TextColor.gray800,

        f(() -> {
          while (iter.hasNext()) {
            renderNode(iter.next());
          }
        })
      );
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void renderContainer(ContainerNode container) {
    for (var node : container.nodes()) {
      renderNode(node);
    }
  }

  private void renderNode(Node node) {
    if (node instanceof Paragraph paragraph) {
      p(f(() -> renderContainer(paragraph)));
    } else if (node instanceof Text text) {
      t(text.value());
    } else if (node instanceof Title title) {
      int level = title.level();

      switch (level) {
        case 0 -> h1(
          FontSize.xLarge4,
          FontSize.md.xLarge5,
          LetterSpacing.tight,
          MarginTop.v14,

          f(() -> renderContainer(title))
        );

        default -> throw new UnsupportedOperationException("level=" + level);
      }
    } else {
      throw new UnsupportedOperationException(
        "Implement me :: type=" + node.getClass().getSimpleName()
      );
    }
  }

  private void v0x() {
    var versions = injector.$versions();

    for (var version : versions) {
      a(
        BackgroundColor.hover.gray100,
        Border.v1,
        BorderColor.gray300,
        Display.block,
        MarginRight.lg.v04,
        MarginTop.v04,
        Padding.v03,
        Padding.lg.v08,
        Rounded.medium,

        pathTo("/" + version.directory + "/index.html"),

        div(
          Display.flex,
          Display.lg.block,
          JustifyContent.between,
          TextAlign.lg.center,

          h3(
            MarginBottom.lg.v06,

            t("Version "), t(version.name)
          ),

          dl(
            Display.md.flex,
            Display.lg.block,

            dt(
              MarginRight.md.v02,
              MarginRight.lg.v0,

              t("Release date")
            ),
            dl(version.releaseDateString())
          )
        )
      );
    }
  }

}