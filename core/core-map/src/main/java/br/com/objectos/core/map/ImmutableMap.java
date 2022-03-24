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
package br.com.objectos.core.map;

import java.util.Map;

/**
 * A hash-based implementation of the {@link Map} interface that does not allow
 * adding nor removing elements.
 *
 * <p>
 * After creation, instances of this class do not permit adding nor removing of
 * elements. Any mutator method will throw an
 * {@link UnsupportedOperationException} when called. This is true for mutator
 * methods present in this class and for mutator methods present in the returned
 * iterators.
 *
 * @param <K> type of the keys in this map
 * @param <V> type of the values in this map
 */
public class ImmutableMap<K, V> extends AbstractArrayBasedMap<K, V> {

  private static final ImmutableMap<Object, Object> EMPTY = new ImmutableMap<Object, Object>();

  ImmutableMap(Object[] array, int size) {
    this.array = array;

    this.size = size;

    hashMask = (array.length >> 1) - 1;
  }

  private ImmutableMap() {}

  @SuppressWarnings("unchecked")
  static <K, V> ImmutableMap<K, V> empty() {
    return (ImmutableMap<K, V>) EMPTY;
  }

  /**
   * This operation is not supported.
   *
   * <p>
   * This method performs no operation other than throw an
   * {@link UnsupportedOperationException}.
   *
   * @throws UnsupportedOperationException
   *         always
   */
  @Override
  public final void clear() {
    throw new UnsupportedOperationException();
  }

  /**
   * This operation is not supported.
   *
   * <p>
   * This method performs no operation other than throw an
   * {@link UnsupportedOperationException}.
   *
   * @param key
   *        ignored (the operation is not supported)
   * @param value
   *        ignored (the operation is not supported)
   *
   * @return this method does not return as it always throw an exception
   *
   * @throws UnsupportedOperationException
   *         always
   */
  @Override
  public final V put(K key, V value) {
    throw new UnsupportedOperationException();
  }

  /**
   * This operation is not supported.
   *
   * <p>
   * This method performs no operation other than throw an
   * {@link UnsupportedOperationException}.
   *
   * @param m
   *        ignored (the operation is not supported)
   *
   * @throws UnsupportedOperationException
   *         always
   */
  @Override
  public final void putAll(Map<? extends K, ? extends V> m) {
    throw new UnsupportedOperationException();
  }

}