/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
 *
 * This file is part of the ObjectosCode (testing) project.
 *
 * Confidential. Do not distribute.
 */
package br.com.objectos.code.java.io;

import br.com.objectos.code.java.declaration.ClassBodyElement;
import br.com.objectos.code.util.AbstractCodeJavaTest;
import br.com.objectos.core.collection.UnmodifiableIterator;
import br.com.objectos.core.list.ImmutableList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractFormattingTest
    extends AbstractCodeJavaTest
    implements FormattingSource {

  private Iterator<ClassBodyElement> iterator;

  @Override
  public final ClassBodyElement getElement() {
    return iterator.next();
  }

  @Override
  public final boolean hasElements() {
    return iterator.hasNext();
  }

  @BeforeMethod
  public void setUpFormattingElement() {
    iterator = new UnmodifiableIterator<ClassBodyElement>() {
      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public ClassBodyElement next() {
        throw new NoSuchElementException();
      }
    };
  }

  final FormattingAction action(Formatting action) {
    return action.newAction(TailFormattingAction.getInstance());
  }

  final void withElements(ClassBodyElement... elements) {
    iterator = ImmutableList.copyOf(elements).iterator();
  }

}