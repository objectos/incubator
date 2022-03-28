/*
 * Copyright (C) 2022-2022 Objectos Software LTDA.
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
package br.com.objectos.docs;

import br.com.objectos.be.site.SitePage;
import br.com.objectos.css.framework.reset.Reset;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.http.media.ImageType;

final class Index extends SitePage {

  private TopNavbar topNavbar;

  protected void bd() {
    main(
      p("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vel sem vel metus faucibus porttitor vel quis velit. Pellentesque consequat semper nisl, eu viverra metus blandit vitae. Quisque est magna, vulputate ut tempor quis, hendrerit sed sem. Etiam mattis, mauris eget vestibulum egestas, ligula metus venenatis nibh, pharetra lacinia neque nibh ut neque. Quisque turpis diam, convallis ut purus ultricies, mollis ultrices nibh. Aenean egestas sem sollicitudin dictum posuere. Maecenas urna felis, tincidunt sit amet bibendum quis, molestie ut massa. Morbi nec lobortis nibh. Praesent sit amet eleifend nunc, et ultricies sem."),

      p("Vivamus accumsan eu nulla at sollicitudin. Quisque accumsan erat vitae porttitor accumsan. Mauris at diam volutpat est feugiat suscipit nec sit amet lorem. Aliquam pulvinar porta convallis. In sit amet orci erat. Phasellus semper finibus placerat. Integer ultrices lectus augue, vitae placerat erat efficitur id. Sed nec ante id elit suscipit volutpat at ut enim. Quisque blandit commodo hendrerit. Sed ornare facilisis mauris, id aliquam ipsum placerat quis. Nullam interdum in lorem sed posuere. Donec nec nulla tellus. Phasellus et faucibus odio. Phasellus lacinia, nunc non vestibulum eleifend, massa ipsum suscipit arcu, eget mattis ante mauris in nisi. Aliquam et luctus orci. Quisque sodales malesuada sodales."),

      p("Cras scelerisque ut turpis ultricies mollis. Vestibulum bibendum sapien ac blandit commodo. Mauris gravida orci sed nisl porttitor sodales. Donec feugiat tincidunt aliquam. Aenean eros nisl, dignissim eu feugiat non, imperdiet non orci. Aliquam quam erat, porta ut elit sit amet, tincidunt pellentesque dui. Phasellus diam neque, tristique sit amet ante in, volutpat dapibus nisi. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Ut consectetur, mi at imperdiet elementum, ligula ipsum interdum enim, a pharetra tellus tellus at risus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nulla dignissim elit id neque cursus cursus."),

      p("Curabitur sed dictum sem. In id ultrices est. Sed bibendum augue sit amet libero fringilla malesuada. Ut ut felis eu nisi ultricies dignissim at venenatis lectus. Duis id nulla dolor. Duis vel massa ut orci sollicitudin mattis. Donec eu est vitae ex rhoncus consectetur vitae in metus. Sed eu erat dolor. Etiam a purus sed nibh vulputate vehicula at non orci. Morbi nec purus sem."),

      p("Integer faucibus felis libero, a interdum metus volutpat eget. In ut tempor orci. In ultrices malesuada aliquam. Duis facilisis, ligula a faucibus condimentum, tortor ligula vestibulum augue, quis euismod justo nunc at dui. Pellentesque ante turpis, dignissim a faucibus vitae, elementum ut elit. Integer ut bibendum velit. Nam risus lorem, scelerisque quis erat vel, consequat rutrum metus. Proin quis maximus elit. Cras malesuada libero odio, et varius sapien condimentum id. Integer libero justo, tempus sagittis suscipit quis, scelerisque id lectus. Sed ac justo a neque scelerisque placerat ac non dui. Donec pulvinar quis purus eu porttitor."),

      p("Vivamus vehicula diam libero, quis egestas nisi congue id. Cras at felis sed dolor pellentesque scelerisque ut laoreet nulla. Cras efficitur magna eget ipsum facilisis, hendrerit mattis quam accumsan. Nunc ac quam sed diam aliquet venenatis. Proin lacinia purus nec aliquet placerat. Curabitur sem massa, molestie ut gravida ut, molestie finibus lectus. Vestibulum ullamcorper ipsum lacus. Cras eu erat viverra, ornare quam ac, semper quam. Praesent lobortis tincidunt sem. In non quam justo. Aenean commodo augue porta elit ultricies porttitor. Sed efficitur nibh dui, ac tincidunt metus convallis ac. Fusce nec facilisis ante, ut iaculis nibh."),

      p("Cras aliquet finibus lobortis. Praesent molestie, leo in euismod iaculis, nulla ligula luctus mauris, ut elementum lorem tellus in eros. Vestibulum et tortor risus. Vivamus dolor nunc, aliquam eu ante quis, consectetur imperdiet purus. Sed commodo nulla at accumsan convallis. Morbi fringilla turpis in massa commodo imperdiet sit amet sed tortor. Nam at magna pharetra, ultrices nunc nec, venenatis justo. Aliquam ornare porttitor cursus. Praesent lacinia orci est, et hendrerit nulla maximus non. Sed eu porttitor lorem. Donec nisl erat, facilisis et venenatis sed, aliquet eget eros. Pellentesque lorem diam, rhoncus in nisi id, venenatis pulvinar ipsum. Quisque viverra est nec justo ullamcorper ornare. Sed vitae accumsan nunc, vel porttitor tortor. Proin orci neque, interdum ac convallis sit amet, molestie nec tellus."),

      p("Maecenas pellentesque elementum consequat. Etiam mattis mi ac gravida cursus. Pellentesque vulputate feugiat mi a luctus. Vestibulum pretium justo quis ligula vestibulum, vitae viverra magna dignissim. Nunc interdum lacinia lorem, eget maximus ipsum ornare id. Nam egestas ac ligula maximus viverra. Aliquam eu purus tellus. Aenean imperdiet elit a blandit egestas. Phasellus finibus consequat ante quis commodo. Vivamus finibus eros non justo sagittis congue. Nullam dignissim, dui non dictum elementum, nunc ex mollis eros, vel semper risus turpis ut lectus. Maecenas mollis velit vitae eros tristique, ac finibus urna bibendum. Aenean consectetur iaculis dolor quis varius. Integer maximus leo turpis, vehicula gravida ligula rutrum nec. Donec dolor justo, vulputate aliquam dictum ac, aliquet quis nulla."),

      p("Cras eget eleifend quam. Pellentesque sit amet semper est. Nunc leo libero, venenatis vitae erat quis, convallis accumsan quam. Maecenas luctus, justo non interdum feugiat, tellus nisi aliquet tellus, ut condimentum ex velit sed diam. Etiam vitae finibus tortor, at vestibulum tortor. Integer dignissim tristique euismod. Quisque fringilla tortor nulla, id hendrerit enim eleifend sed. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ultrices elit sit amet purus congue, ac tempus dolor gravida. Donec finibus diam ante, a vestibulum augue tincidunt eget. Phasellus metus augue, finibus at hendrerit id, ornare et lectus."),

      p("Duis aliquet feugiat diam non vulputate. Curabitur commodo ut orci sit amet iaculis. Vivamus fringilla sem dolor, nec accumsan eros scelerisque quis. Donec risus enim, sollicitudin nec tempus ut, consectetur quis lacus. Quisque dictum justo sed imperdiet feugiat. Donec placerat tellus urna, quis interdum ligula sollicitudin sed. Nullam maximus purus blandit urna maximus volutpat. Donec at justo laoreet, semper ligula ac, commodo orci. Donec eu aliquet ante. Pellentesque quis sagittis ante, ac vehicula augue. Integer finibus erat risus, vitae porta ante ultrices at. Nullam libero dolor, ornare vitae dolor id, lobortis faucibus erat. Praesent id mauris sed ante porta aliquet non nec eros. Donec malesuada, ipsum sed tempor pellentesque, velit arcu varius nibh, non commodo eros odio quis purus."),

      p("Pellentesque faucibus augue turpis, sed ornare sem sollicitudin eu. Cras semper varius nibh, molestie accumsan odio auctor ac. Nam nisi risus, tristique at augue ut, convallis porttitor libero. Etiam eu felis eget urna lacinia rutrum a in dui. Phasellus pretium et magna eget ornare. Nunc non venenatis urna. Etiam congue metus suscipit est ornare, at vehicula tellus scelerisque. Fusce dapibus dapibus euismod. Fusce dictum fringilla elementum. Nunc nec quam tincidunt, vulputate mi ac, convallis lorem. Quisque ac aliquet elit. Aliquam ullamcorper, quam ullamcorper rhoncus ullamcorper, orci ligula vestibulum lectus, ultrices volutpat magna odio non sapien. In quis lectus eget sem volutpat luctus. Quisque sit amet varius nulla."),

      p("Nam rutrum auctor dui ut volutpat. Cras et pulvinar eros. Pellentesque ac laoreet eros. Curabitur in pellentesque leo, vitae aliquet tellus. Vestibulum mollis urna nisl. Fusce in sem ut tellus dapibus ornare non pharetra est. Proin ullamcorper mattis magna, eu varius magna ullamcorper quis. In hac habitasse platea dictumst. Cras felis mauris, elementum et dui eget, faucibus hendrerit lorem. Aenean felis mauris, sagittis a volutpat ac, scelerisque id est. Duis egestas volutpat turpis quis placerat. Nulla pellentesque neque at tincidunt imperdiet."),

      p("Duis dapibus, dolor vitae ornare pharetra, ligula libero finibus arcu, eu iaculis metus purus vitae nibh. Ut ut est ante. Ut non commodo tellus. Morbi non risus aliquam, cursus est ut, vehicula magna. Nam at lobortis elit. Vivamus finibus odio tellus, vel mattis eros feugiat ac. Praesent cursus enim sem, quis elementum velit pharetra ac. Nullam a leo cursus, consectetur mauris id, fermentum libero. Cras a ultricies erat, in rhoncus odio. Sed sit amet dolor auctor, viverra turpis id, dapibus arcu. Ut sit amet consectetur arcu. Aenean non quam sodales, ornare lorem id, aliquam lectus."),

      p("Maecenas at cursus metus. Morbi pellentesque, urna a aliquam vehicula, urna mi convallis sapien, ac posuere augue erat a nunc. Quisque a nisi vitae lectus porta convallis vitae at enim. Curabitur at dui cursus sapien pharetra porttitor non sed tortor. Nam commodo ipsum ac ligula accumsan auctor ac at orci. Nulla maximus condimentum velit, eget accumsan nulla lacinia eget. Duis in viverra ipsum. Mauris et feugiat neque. Vivamus vel nisi nisl. Donec tincidunt libero urna, quis bibendum sem ornare consequat. Suspendisse bibendum nulla sagittis dolor fermentum pellentesque. Morbi ultrices risus vitae elit varius, in eleifend arcu vulputate."),

      p("Nullam ac lacus consectetur, vestibulum nibh in, ullamcorper tortor. Phasellus eu magna id orci rhoncus tristique. Donec dapibus dictum volutpat. Sed semper quam et euismod mattis. Quisque velit libero, vulputate in turpis at, sodales dignissim erat. Nullam vitae diam est. Sed consectetur ullamcorper placerat.")
    );
  }

  @Override
  protected final void definition() {
    topNavbar = getInstance(TopNavbar.class);

    topNavbar.setTitle("Home");

    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      body(
        f(topNavbar),

        f(this::bd)
      )
    );
  }

  protected void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type(ImageType.ICON.qualifiedName()), href("/favicon.ico"));

    StyleSheet styleSheet;
    styleSheet = styleSheet();

    String css;
    css = styleSheet.printMinified();

    style(raw(css));
  }

  protected StyleSheet styleSheet() {
    return new AbstractStyleSheet() {
      @Override
      protected final void definition() {
        install(new Reset());

        install(topNavbar.css);

        style(
          body,

          height(pct(100)),
          overflowY(hidden)
        );

        style(
          main,

          padding(Spacing.V0, Spacing.V04)
        );

        style(
          p,

          marginBottom(Spacing.V05)
        );
      }
    };
  }

}