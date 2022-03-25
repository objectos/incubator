/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

class BrickActionBlock extends Block {

  private final SpecAction action;

  BrickActionBlock(Block block, SpecAction action) {
    super(block);
    this.action = action;
  }

  @Override
  public final Linker link(Linker linker) {
    return linker.brickActionLink(action);
  }

  public final void onSpecActionBrick(BrickMap brickMap, Link link) {
    action.onSpecActionBrick(brickMap, link);
  }

  @Override
  public final StartingBlock reverse(Block next) {
    throw new UnsupportedOperationException("Implement me");
  }

  public final Link toLink() {
    StartingBlock starting = block.reverse(this);
    return starting.link(new Linker()).toLink();
  }

}