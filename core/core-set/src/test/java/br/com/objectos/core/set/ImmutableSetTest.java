/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.core.set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ImmutableSetTest extends AbstractObjectosSetsTest {

  private ImmutableSet<Thing> emptySet;

  private MutableSet<Thing> randomMutableSet;

  private ImmutableSet<Thing> t1AndT2Set;

  private ImmutableSet<Thing> t1Set;

  @BeforeClass
  public void _beforeClass() {
    emptySet = ImmutableSet.of();

    t1Set = ImmutableSet.of(t1);

    t1AndT2Set = ImmutableSet.of(t1, t2);

    randomMutableSet = Thing.randomMutableSet(thingSize);
  }

  @Test(description = "ImmutableSet.copyOf(E[])")
  public void copyOf0() {
    ImmutableSet<Thing> result;
    result = ImmutableSet.copyOf(new Thing[] {});

    assertSame(result, ImmutableSet.of());

    result = ImmutableSet.copyOf(thingArray);

    Set<Thing> expected;
    expected = Sets.newHashSet();

    for (Thing t : thingArray) {
      expected.add(t);
    }

    assertSet(result, expected);
  }

  @Test(description = "ImmutableSet.copyOf(Iterable)")
  public void copyOf1() {
    // ImmutableSet
    ImmutableSet<Thing> result;
    result = ImmutableSet.copyOf(ImmutableSet.<Thing> of());

    assertSame(result, ImmutableSet.of());

    ImmutableSet<Thing> randomImmutableSet;
    randomImmutableSet = Thing.randomImmutableSet(thingSize);

    result = ImmutableSet.copyOf(randomImmutableSet);

    assertSame(result, randomImmutableSet);

    // MutableSet
    result = ImmutableSet.copyOf(randomMutableSet);

    Set<Thing> expected;
    expected = Sets.newHashSet();

    expected.addAll(randomMutableSet);

    assertSet(result, expected);

    // Iterable
    result = ImmutableSet.copyOf(Collections.<Thing> emptySet());

    assertSame(result, ImmutableSet.of());

    result = ImmutableSet.copyOf(thingIterable);

    expected.clear();

    for (Thing t : thingIterable) {
      expected.add(t);
    }

    assertSet(result, expected);
  }

  @Test(description = "ImmutableSet.copyOf(Iterator)")
  public void copyOf2() {
    // empty
    Iterator<Thing> iterator;
    iterator = Collections.<Thing> emptySet().iterator();

    ImmutableSet<Thing> result;
    result = ImmutableSet.copyOf(iterator);

    assertSame(result, ImmutableSet.of());

    // singleton
    iterator = new SingletonIterator<Thing>(t1);

    result = ImmutableSet.copyOf(iterator);

    Set<Thing> expected;
    expected = Sets.newHashSet();

    expected.add(t1);

    assertSet(result, expected);

    // many
    iterator = new ArrayIterator<Thing>(thingArray, thingArray.length);

    result = ImmutableSet.copyOf(iterator);

    expected.clear();

    for (Thing thing : thingArray) {
      expected.add(thing);
    }

    assertSet(result, expected);
  }

  @Test
  public void getOnly() {
    try {
      emptySet.getOnly();

      Assert.fail();
    } catch (IllegalStateException expected) {
      assertEquals(expected.getMessage(), "Could not getOnly: empty.");
    }

    assertEquals(t1Set.getOnly(), t1);

    try {
      t1AndT2Set.getOnly();

      Assert.fail();
    } catch (IllegalStateException expected) {
      assertEquals(expected.getMessage(), "Could not getOnly: more than one element.");
    }
  }

  @Test
  public void iterator() {
    assertTrue(emptySet.isEmpty());

    Set<Thing> expected;
    expected = Sets.newHashSet();

    assertIterator(emptySet.iterator(), expected);

    expected.add(t1);

    assertIterator(t1Set.iterator(), expected);

    expected.add(t2);

    assertIterator(t1AndT2Set.iterator(), expected);
  }

  @Test
  public void remove() {
    try {
      t1Set.remove(t1);

      Assert.fail();
    } catch (UnsupportedOperationException expected) {
      assertTrue(t1Set.contains(t1));
    }
  }

  @Test
  public void removeAll() {
    ImmutableSet<Thing> set;
    set = ImmutableSet.copyOf(thingList);

    try {
      set.removeAll(thingList);

      Assert.fail();
    } catch (UnsupportedOperationException expected) {
      assertTrue(set.containsAll(thingList));
    }
  }

  @Test
  public void retainAll() {
    ImmutableSet<Thing> set;
    set = ImmutableSet.copyOf(thingList);

    List<Thing> retain;
    retain = new ArrayList<Thing>();

    retain.add(thingList.get(0));

    retain.add(thingList.get(1));

    try {
      set.retainAll(retain);

      Assert.fail();
    } catch (UnsupportedOperationException expected) {
      assertTrue(set.containsAll(thingList));
    }
  }

}