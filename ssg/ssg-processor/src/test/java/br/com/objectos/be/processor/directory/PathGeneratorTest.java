/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor.directory;

import static br.com.objectos.code.java.Java._package;

import br.com.objectos.be.processor.Util;
import br.com.objectos.be.processor.testing.iter01.Index01Html;
import br.com.objectos.be.processor.testing.iter01.Styles01Css;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.http.media.ImageType;
import org.testng.annotations.Test;

public class PathGeneratorTest extends AbstractBeProcessorDirectoryTest {

  @Test
  public void addCssInstance() {
    PackageName iter01;
    iter01 = PackageName.of(Styles01Css.class);

    NamedClass className;
    className = iter01.nestedClass("BeDirectory");

    PathGenerator generator;
    generator = new PathGenerator(className);

    ProcessedResourceType css;
    css = getProcessedResourceType(Styles01Css.class);

    generator.addCssInstance(css);

    JavaFile generated;
    generated = generator.generate();

    Util.assertHasLines(
        generated.toString(),
        "package br.com.objectos.be.processor.testing.iter01;",
        "",
        "import br.com.objectos.be.resource.AbstractCssResource;",
        "import br.com.objectos.be.resource.AbstractPath;",
        "import br.com.objectos.be.resource.ResolvedUrl;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeDirectoryProcessor\")",
        "public class BeDirectory extends AbstractPath {",
        "",
        "  public BeDirectory(ResolvedUrl resolvedUrl) {",
        "    super(resolvedUrl);",
        "  }",
        "",
        "  public final Styles01Css styles01Css() {",
        "    return new CssImpl(get(\"styles01.css\"));",
        "  }",
        "",
        "  private static class CssImpl extends AbstractCssResource implements Styles01Css {",
        "",
        "    CssImpl(String src) {",
        "      super(src);",
        "    }",
        "",
        "  }",
        "",
        "}"
    );
  }

  @Test
  public void addHtmlInstance() {
    PackageName iter01;
    iter01 = PackageName.of(Index01Html.class);

    NamedClass className;
    className = iter01.nestedClass("BeDirectory");

    PathGenerator generator;
    generator = new PathGenerator(className);

    ProcessedResourceType html;
    html = getProcessedResourceType(Index01Html.class);

    generator.addHtmlInstance(html);

    JavaFile generated;
    generated = generator.generate();

    Util.assertHasLines(
        generated.toString(),
        "package br.com.objectos.be.processor.testing.iter01;",
        "",
        "import br.com.objectos.be.resource.AbstractHtmlResource;",
        "import br.com.objectos.be.resource.AbstractPath;",
        "import br.com.objectos.be.resource.ResolvedUrl;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeDirectoryProcessor\")",
        "public class BeDirectory extends AbstractPath {",
        "",
        "  public BeDirectory(ResolvedUrl resolvedUrl) {",
        "    super(resolvedUrl);",
        "  }",
        "",
        "  public final Index01Html index01Html() {",
        "    return new HtmlImpl(get(\"index01.html\"));",
        "  }",
        "",
        "  private static class HtmlImpl extends AbstractHtmlResource implements Index01Html {",
        "",
        "    HtmlImpl(String src) {",
        "      super(src);",
        "    }",
        "",
        "  }",
        "",
        "}"
    );
  }

  @Test
  public void addImageInstance() {
    PackageName testingImg;
    testingImg = _package("testing.img");

    NamedClass className;
    className = testingImg.nestedClass("ImgDirectory");

    PathGenerator generator;
    generator = new PathGenerator(className);

    ProcessedResourceImage image;
    image = new ThisImageBuilder(testingImg).build();

    generator.addImageInstance(image);

    JavaFile generated;
    generated = generator.generate();

    Util.assertHasLines(
        generated.toString(),
        "package testing.img;",
        "",
        "import br.com.objectos.be.resource.AbstractImageResource;",
        "import br.com.objectos.be.resource.AbstractPath;",
        "import br.com.objectos.be.resource.ResolvedUrl;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeDirectoryProcessor\")",
        "public class ImgDirectory extends AbstractPath {",
        "",
        "  public ImgDirectory(ResolvedUrl resolvedUrl) {",
        "    super(resolvedUrl);",
        "  }",
        "",
        "  public final ImageSvg imageSvg() {",
        "    return new ImageImpl(get(\"image.svg\"));",
        "  }",
        "",
        "  private static class ImageImpl extends AbstractImageResource implements ImageSvg {",
        "",
        "    ImageImpl(String src) {",
        "      super(src);",
        "    }",
        "",
        "    ImageImpl(String src, int width, int height) {",
        "      super(src, width, height);",
        "    }",
        "",
        "  }",
        "",
        "}"
    );
  }

  private class ThisImageBuilder extends ProcessedResourceImage.Builder {
    final PackageName packageName;

    ThisImageBuilder(PackageName packageName) {
      this.packageName = packageName;
    }

    @Override
    final NamedClass className() {
      return packageName.nestedClass("ImageSvg");
    }

    @Override
    final ImageDimensions dimensions() {
      return ImageDimensions.none();
    }

    @Override
    final ImageType imageType() {
      return null;
    }

    @Override
    final String resourceName() {
      return "image.svg";
    }
  }

}
