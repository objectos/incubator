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
/**
 * Provides the Objectos Collections {@link java.util.Map} implementations and
 * utilities.
 *
 * <h2>Limitations</h2>
 *
 * <p>
 * The implementations provided by this package are not general-purpose
 * implementations:
 *
 * <ul>
 * <li>they do not permit {@code null} keys nor {@code null} values;</li>
 * <li>some operations specified by {@link java.util.Map} may not be
 * supported;</li>
 * <li>other operations specified by {@link java.util.Map} may not be
 * implemented;</li>
 * <li>the "collection view methods" do not return a view of the map. Instead,
 * any mutator method of the returned collection will throw a
 * {@link java.lang.UnsupportedOperationException};</li>
 * <li>iterators produced by the classes in this package, as returned by the
 * "collection view methods", are NOT <i>fail-fast</i> as defined by
 * {@link java.util.ConcurrentModificationException}; and</li>
 * <li>classes do not implement hash-collision attack mitigations as
 * {@link java.util.HashMap} has been implementing since Java 8.</li>
 * </ul>
 *
 * <h2>Null Pointers</h2>
 *
 * <p>
 * Unless otherwise specified, methods in this package will throw a
 * {@link NullPointerException} if given a {@code null} argument.
 */
package br.com.objectos.core.map;