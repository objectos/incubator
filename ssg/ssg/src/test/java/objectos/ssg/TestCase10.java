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
package objectos.ssg;

/**
 * [#40] write interception points
 */
final class TestCase10 extends Site {

  String result;

  @Override
  protected final void configure() {
    addObject(new ValueHolder());

    addDirectory("1", new SiteDirectory() {
      @Override
      protected final void configure() {
        addPage("index.html", new Page() {});
      }

      @Override
      protected void writeStart() {
        ValueHolder vh;
        vh = getObject(ValueHolder.class);

        vh.value = "TestCase10:1";
      }
    });

    addDirectory("2", new SiteDirectory() {
      @Override
      protected final void configure() {
        addPage("index.html", new Page() {});
      }

      @Override
      protected void writeStart() {
        ValueHolder vh;
        vh = getObject(ValueHolder.class);

        vh.value = "TestCase10:2";
      }
    });
  }

  abstract static class Page extends SitePage {
    @Override
    protected final void definition() {
      ValueHolder vh;
      vh = getObject(ValueHolder.class);

      html(
        body(vh.value)
      );
    }
  }

  class ValueHolder {

    String value;

  }

}