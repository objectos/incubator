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
package objectos.ssg.processor;

import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.nl;
import static br.com.objectos.code.java.Java.overriding;

import br.com.objectos.code.java.Java;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.MethodCode.OverridingProcessingMethod;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.production.MethodInvocationExpression;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingType;
import java.util.List;
import objectos.lang.Check;
import objectos.util.ByteArrays;
import objectos.util.UnmodifiableList;
import objectos.util.UnmodifiableSet;
import objectos.util.IntArrays;
import objectos.util.MutableList;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.CustomBlock;
import org.commonmark.node.CustomNode;
import org.commonmark.node.Document;
import org.commonmark.node.Emphasis;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Link;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.ListBlock;
import org.commonmark.node.ListItem;
import org.commonmark.node.Node;
import org.commonmark.node.OrderedList;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.StrongEmphasis;
import org.commonmark.node.Text;
import org.commonmark.node.ThematicBreak;
import org.commonmark.node.Visitor;
import org.commonmark.parser.Parser;

class MarkdownMethod implements Visitor {

  private static final byte _A = 0;

  private static final byte _BLOCKQUOTE = 1;

  private static final byte _DOCUMENT = 2;

  private static final byte _EM = 3;

  private static final byte _HEADING = 4;

  private static final byte _IMG = 5;

  private static final byte _LI = 6;

  private static final byte _LI_TIGHT = 7;

  private static final byte _PARAGRAPH = 8;

  private static final byte _STRONG = 9;

  private static final byte _UL = 10;

  private static final byte _UL_TIGHT = 11;

  private static final Parser PARSER = Parser.builder()
      .extensions(
        UnmodifiableList.of(FootnoteExtension.INSTANCE)
      )
      .build();

  MethodCode.Builder methodCode;

  private final MutableList<ArgumentsElement> arguments = new MutableList<>();

  private int[] argumentsStack = new int[2];

  private byte[] elementStack = new byte[2];

  private final ProcessingMethod processingMethod;

  private int stackIndex = -1;

  MarkdownMethod(ProcessingMethod processingMethod) {
    this.processingMethod = processingMethod;
  }

  public final MethodCode generate() {
    methodCode = MethodCode.builder();

    methodCode.addAnnotation(Override.class);

    OverridingProcessingMethod overridingProcessingMethod;
    overridingProcessingMethod = overriding(processingMethod);

    overridingProcessingMethod.acceptMethodCodeBuilder(methodCode);

    methodCode.addModifier(Java.FINAL);

    String comment;
    comment = processingMethod.getDocComment();

    return generate0(comment);
  }

  public final JavaFile generateJavaFile() {
    ProcessingType declaringType;
    declaringType = processingMethod.getDeclaringType();

    NamedClass name;
    name = declaringType.getName();

    PackageName packageName;
    packageName = name.getPackage();

    methodCode = MethodCode.builder();

    methodCode.addAnnotation(Override.class);

    methodCode.addModifier(Modifiers.PROTECTED);

    methodCode.name("definition");

    String comment;
    comment = processingMethod.getDocComment();

    return Java.javaFile(
      packageName,

      Java._class(
        MarkdownMethodProcessor.GENERATED,

        Java.id(declaringType.getSimpleName() + "_" + processingMethod.getName()),

        Java._extends(TypeNames.AbstractFragment),

        generate0(comment)
      )
    );
  }

  @Override
  public final void visit(BlockQuote blockQuote) {
    push(_BLOCKQUOTE);

    arguments.add(Java.nl());

    visitChildren(blockQuote);

    arguments.add(Java.nl());

    addToMethodOrArguments(
      invoke("blockquote", popToArguments())
    );
  }

  @Override
  public final void visit(BulletList bulletList) {
    listBlock("ul", bulletList);
  }

  @Override
  public final void visit(Code code) {
    arguments.add(
      Java.nl()
    );

    arguments.add(
      invoke("code", l(code.getLiteral()))
    );
  }

  @Override
  public final void visit(CustomBlock customBlock) {
    defaultAction(customBlock);
  }

  @Override
  public final void visit(CustomNode customNode) {
    if (customNode instanceof Footnote) {
      Footnote fn;
      fn = (Footnote) customNode;

      Text t;
      t = (Text) fn.getFirstChild();

      String l;
      l = t.getLiteral();

      String id;
      id = "fn" + l;

      push(_A);

      arguments.add(Java.nl());

      arguments.add(
        invoke("id", l(id + "-ret"))
      );

      arguments.add(
        invoke("href", l("#" + id))
      );

      arguments.add(
        invoke("sup", l('[' + l + ']'))
      );

      arguments.add(Java.nl());

      Iterable<? extends ArgumentsElement> anchorArgs;
      anchorArgs = popToArguments();

      arguments.add(Java.nl());

      arguments.add(
        invoke("a", anchorArgs)
      );
    } else {
      defaultAction(customNode);
    }
  }

  @Override
  public final void visit(Document document) {
    Check.state(stackIndex < 0, "stackIndex < 0");

    push(_DOCUMENT);

    visitChildren(document);

    pop();

    Check.state(stackIndex < 0, "stackIndex < 0");
  }

  @Override
  public final void visit(Emphasis emphasis) {
    push(_EM);

    visitChildren(emphasis);

    arguments.add(Java.nl());

    Iterable<? extends ArgumentsElement> args;
    args = popToArguments();

    arguments.add(Java.nl());

    arguments.add(
      invoke("em", args)
    );
  }

  @Override
  public final void visit(FencedCodeBlock fencedCodeBlock) {
    String info;
    info = fencedCodeBlock.getInfo();

    info = info.trim();

    if (info.equals("self")) {
      String literal;
      literal = fencedCodeBlock.getLiteral();

      String[] methodNames;
      methodNames = literal.split("\\n");

      for (String methodName : methodNames) {
        methodName = methodName.trim();

        if (methodName.isEmpty()) {
          continue;
        }

        addToMethodOrArguments(
          invoke(methodName)
        );
      }
    } else {
      List<ArgumentsElement> args;
      args = new MutableList<>();

      if (!info.isEmpty()) {
        args.add(nl());

        args.add(invoke("_class", l("language-" + info)));
      }

      args.add(nl());

      args.add(invoke("t", l(fencedCodeBlock.getLiteral())));

      args.add(nl());

      addToMethodOrArguments(
        invoke("pre",
          nl(),
          invoke("code", args),
          nl()
        )
      );
    }
  }

  @Override
  public final void visit(HardLineBreak hardLineBreak) {
    defaultAction(hardLineBreak);
  }

  @Override
  public final void visit(Heading heading) {
    String methodName;

    switch (heading.getLevel()) {
      case 1:
        methodName = "h1";
        break;
      case 2:
        methodName = "h2";
        break;
      case 3:
        methodName = "h3";
        break;
      case 4:
        methodName = "h4";
        break;
      case 5:
        methodName = "h5";
        break;
      case 6:
        methodName = "h6";
        break;
      default:
        throw new UnsupportedOperationException();
    }

    push(_HEADING);

    visitChildren(heading);

    arguments.add(Java.nl());

    addToMethodOrArguments(
      invoke(methodName, popToArguments())
    );
  }

  @Override
  public final void visit(HtmlBlock htmlBlock) {
    defaultAction(htmlBlock);
  }

  @Override
  public final void visit(HtmlInline htmlInline) {
    defaultAction(htmlInline);
  }

  @Override
  public final void visit(Image image) {
    push(_IMG);

    arguments.add(Java.nl());

    arguments.add(
      invoke("src", l(image.getDestination()))
    );

    String title;
    title = image.getTitle();

    if (title != null) {
      arguments.add(Java.nl());

      arguments.add(
        invoke("title", l(title))
      );
    }

    visitChildren(image);

    arguments.add(Java.nl());

    Iterable<? extends ArgumentsElement> imgArgs;
    imgArgs = popToArguments();

    arguments.add(Java.nl());

    arguments.add(
      invoke("img", imgArgs)
    );
  }

  @Override
  public final void visit(IndentedCodeBlock indentedCodeBlock) {
    defaultAction(indentedCodeBlock);
  }

  @Override
  public final void visit(Link link) {
    push(_A);

    arguments.add(Java.nl());

    arguments.add(
      invoke("href", l(link.getDestination()))
    );

    visitChildren(link);

    arguments.add(Java.nl());

    Iterable<? extends ArgumentsElement> anchorArgs;
    anchorArgs = popToArguments();

    arguments.add(Java.nl());

    arguments.add(
      invoke("a", anchorArgs)
    );
  }

  @Override
  public final void visit(LinkReferenceDefinition linkReferenceDefinition) {
    defaultAction(linkReferenceDefinition);
  }

  @Override
  public final void visit(ListItem listItem) {
    switch (elementStack[stackIndex]) {
      default:
      case _UL:
        push(_LI);

        arguments.add(Java.nl());
        break;
      case _UL_TIGHT:
        push(_LI_TIGHT);
        break;
    }

    visitChildren(listItem);

    arguments.add(Java.nl());

    Iterable<? extends ArgumentsElement> liArgs;
    liArgs = popToArguments();

    arguments.add(Java.nl());

    arguments.add(
      invoke("li", liArgs)
    );
  }

  @Override
  public final void visit(OrderedList orderedList) {
    listBlock("ol", orderedList);
  }

  @Override
  public final void visit(Paragraph paragraph) {
    switch (elementStack[stackIndex]) {
      default:
        push(_PARAGRAPH);

        visitChildren(paragraph);

        arguments.add(Java.nl());

        addToMethodOrArguments(
          invoke("p", popToArguments())
        );
        break;
      case _LI_TIGHT:
        visitChildren(paragraph);
        break;
    }
  }

  @Override
  public final void visit(SoftLineBreak softLineBreak) {
    arguments.add(
      invoke("t", l("\n"))
    );
  }

  @Override
  public final void visit(StrongEmphasis strongEmphasis) {
    push(_STRONG);

    visitChildren(strongEmphasis);

    arguments.add(Java.nl());

    Iterable<? extends ArgumentsElement> args;
    args = popToArguments();

    arguments.add(Java.nl());

    arguments.add(
      invoke("strong", args)
    );
  }

  @Override
  public final void visit(Text t) {
    arguments.add(
      Java.nl()
    );

    String text;
    text = t.getLiteral();

    if (text.isEmpty()) {
      return;
    }

    char[] charArray;
    charArray = text.toCharArray();

    char c;
    c = '\0';

    int index;
    index = charArray.length - 1;

    int closingBrace;
    closingBrace = -1;

    while (index >= 0) {
      c = charArray[index];

      index--;

      if (c == ' ') {
        continue;
      }

      if (c == '}') {
        closingBrace = index + 1;
      }

      break;
    }

    if (closingBrace < 0) {
      String methodName;
      methodName = "t";

      byte top;
      top = elementStack[stackIndex];

      if (top == _IMG) {
        methodName = "alt";
      }

      arguments.add(
        invoke(methodName, l(text))
      );

      return;
    }

    int openingBrace;
    openingBrace = -1;

    char previous;
    previous = c;

    while (index >= 0) {
      c = charArray[index];

      index--;

      if (c == '{' && previous == '#') {
        openingBrace = index + 1;

        break;
      }

      previous = c;
    }

    if (openingBrace < 0) {
      arguments.add(
        invoke("t", l(text))
      );

      return;
    }

    String id;
    id = new String(charArray, openingBrace + 2, closingBrace - openingBrace - 2);

    arguments.add(
      invoke("id", l(id))
    );

    arguments.add(
      Java.nl()
    );

    text = new String(charArray, 0, openingBrace);

    arguments.add(
      invoke("t", l(text))
    );
  }

  @Override
  public final void visit(ThematicBreak thematicBreak) {
    defaultAction(thematicBreak);
  }

  final MethodCode generate0(String comment) {
    comment = comment.trim();

    if (comment.isEmpty()) {
      return methodCode.build();
    }

    Node node;
    node = PARSER.parse(comment);

    node.accept(this);

    return methodCode.build();
  }

  private void addToMethodOrArguments(MethodInvocationExpression e) {
    if (stackIndex > 0) {
      arguments.add(e);
    } else {
      methodCode.addStatement(e);
    }
  }

  private void defaultAction(Node parent) {
    throw new UnsupportedOperationException("Implement me");
  }

  private void listBlock(String tag, ListBlock list) {
    if (list.isTight()) {
      push(_UL_TIGHT);
    } else {
      push(_UL);

      arguments.add(Java.nl());
    }

    visitChildren(list);

    arguments.add(Java.nl());

    Iterable<? extends ArgumentsElement> listArgs;
    listArgs = popToArguments();

    addToMethodOrArguments(
      invoke(tag, listArgs)
    );
  }

  private void pop() {
    stackIndex--;
  }

  private Iterable<? extends ArgumentsElement> popToArguments() {
    int argsInitial;
    argsInitial = argumentsStack[stackIndex];

    int argsCurrent;
    argsCurrent = arguments.size();

    int argsQuantity;
    argsQuantity = argsCurrent - argsInitial;

    stackIndex--;

    if (argsQuantity == 0) {
      return UnmodifiableSet.of();
    }

    MutableList<ArgumentsElement> result;
    result = new MutableList<>();

    for (int i = argsInitial; i < argsCurrent; i++) {
      ArgumentsElement a;
      a = arguments.get(i);

      result.add(a);
    }

    arguments.truncate(argsInitial);

    return result;
  }

  private void push(byte type) {
    stackIndex++;

    argumentsStack = IntArrays.copyIfNecessary(argumentsStack, stackIndex);

    argumentsStack[stackIndex] = arguments.size();

    elementStack = ByteArrays.copyIfNecessary(elementStack, stackIndex);

    elementStack[stackIndex] = type;
  }

  private void visitChildren(Node parent) {
    Node node;
    node = parent.getFirstChild();

    while (node != null) {
      node.accept(this);

      node = node.getNext();
    }
  }

}