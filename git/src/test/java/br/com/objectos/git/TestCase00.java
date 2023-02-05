/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.git;

import br.com.objectos.concurrent.Computation;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.testing.TmpDir;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Support reading and parsing loose objects. More specifically:
 *
 * - bare repository containing loose objects only (repo00)
 * - commit without parents
 *
 * 0) Materialize use-case;
 * 1) bare repo;
 * 2) loose objects only;
 * 3) first commit (no parents);
 * 4) single file at root /README.md
 *
 * @since 1
 */
final class TestCase00 {

  public static final String DESCRIPTION = "Support loose objects (iter 1)";

  private TestCase00() {}

  public static Computation<Directory> acceptGitServiceTest(GitService git) throws IOException {
    Directory target;
    target = TmpDir.create();

    MaterializeCommand command;
    command = new MaterializeCommand(getDirectory(), getCommit(), target);

    return git.submit(command);
  }

  public static byte[] getBlobContents() {
    return TestingGit.decode64(
        "IyBPYmplY3Rvc1JlcG8KClRoaXMgaXMgYSBnaXQgcmVwb3NpdG9yeSBtZWFudCB0byBiZSB1c2Vk",
        "IGluIHRlc3RzLgo="
    );
  }

  public static Entry getBlobEntry() throws InvalidObjectIdFormatException {
    return new Entry(
        EntryMode.REGULAR_FILE,

        "README.md",

        ObjectId.parse("6eaf9247b35bbc35676d1698313381be80a4bdc4")
    );
  }

  public static ObjectId getCommit() throws InvalidObjectIdFormatException {
    return ObjectId.parse("b9c4f2db7b4fd742990b518ee3c8ae59eb1d6e93");
  }

  public static byte[] getCommitContents() {
    return TestingGit.decode64(
        "dHJlZSAxY2QwNDIyOTRkMzkzMzAzMmY1ZmJiOTczNTAzNGRjYmNlNjg5ZGM5CmF1dGhvciBUaGUg",
        "QXV0aG9yIDxhdXRob3JAZXhhbXBsZS5jb20+IDE2MDAwODMxOTMgLTAzMDAKY29tbWl0dGVyIFRo",
        "ZSBDb21taXR0ZXIgPGNvbW1pdHRlckBleGFtcGxlLmNvbT4gMTYwMDA4MzE5MyAtMDMwMAoKYWRk",
        "IFJFQURNRS5tZAo="
    );
  }

  public static Directory getDirectory() throws IOException {
    return TestingGit.repo00();
  }

  public static Repository getRepository() throws IOException, ExecutionException {
    Directory directory;
    directory = getDirectory();

    return TestingGit.bareRepository(directory);
  }

  /**
   * 1) 644 blob README.md
   */
  public static ObjectId getTree() throws InvalidObjectIdFormatException {
    return ObjectId.parse("1cd042294d3933032f5fbb9735034dcbce689dc9");
  }

  public static byte[] getTreeContents() {
    return TestingGit.decode64(
        "MTAwNjQ0IFJFQURNRS5tZABur5JHs1u8NWdtFpgxM4G+gKS9xA=="
    );
  }

  /*
  
  $ git cat-file --batch-check --batch-all-objects
  09cb9b7b846756738668aae8369dffe887e2fbba tree 30
  0fc7a9f2457ab0488441ca780d590b1ec26c75ee tree 31
  1584aeadeea6a620b0d91016b7aa1eca2f62af46 blob 40
  1a4808941ac8f4c7cbe812593804f40a4102b99f blob 52
  1b738a154b5e38020d733301114f1931dbd1d48d tree 30
  1cd042294d3933032f5fbb9735034dcbce689dc9 tree 37
  1eda06f88aa783287b37817f287fbcbf318b8770 tree 61
  293db19c76f6645343bfdcaf9ae54d47951ddf6a commit 239
  2d20bb843e1c2a37537066bc6c2da77b824c1bd2 blob 50
  337b6c1f6e1824d0080dff43d75dd354efac803b tree 30
  33fce4e6ef24da6a9c38bfee54c508a2282202d1 tree 67
  4009d44c75039f653e2dbf022d79d97a15c24162 tree 31
  4409e41cfb67698910852a5d60bc69bf04a41b93 tree 97
  453088f819dcbeed8245981f47805f5c3daffeb7 tree 35
  4cf27def690a4b1cf92d49a653003cbda787ca5f blob 48
  4d9bfebdfedec184c87900717de5d4e09ff9e128 tree 31
  58d56cd9122105fc530b0c7ba7b4693db3d81a0d tree 35
  5a94ac4d34533dd1cda87ca519637dc914bcf197 tree 30
  68699720c357a5ce1d4171a65ce801741736ea31 commit 238
  6eaf9247b35bbc35676d1698313381be80a4bdc4 blob 68
  717271f0f0ee528c0bb094e8b2f84ea6cef7b39d commit 232
  792fc642dcfe46e83b5122db300eaa7cddfff72e tree 35
  84937d78a8ced6240d4820ea0a38ab231cef2d25 tree 29
  8ae78c68a5eb6abf81e7bd006d7d9bf326780589 commit 238
  8dd0f9048878b59a4188b0647bb509e0ba138f8b tree 35
  8e3578c3dd59a6739102031513732cf99d14f3c6 tree 62
  8ef9199f8253fdb0d622f0e9aee798349d28d8f7 tree 97
  98f083a5fb687304c0fbc5cf81b42f8f2b6c9d0c tree 37
  9d954d103bebfbd782835e8f632a033b1103152d commit 246
  a265fca5b5fc9d01302b05e5867b0cf62d6ee0ee commit 246
  a658c759a9970a9190d3a2d454afd344fc80adb9 tree 81
  a985785d9534c527ace20fefa5dee6ad6f7c7f86 tree 73
  a9bf588e2f88457fdf73ac7361ef1d596fb81453 blob 12
  b3b7dbc9182013995993f6358ef319823d7fad06 tree 29
  b9c4f2db7b4fd742990b518ee3c8ae59eb1d6e93 commit 182
  bc6a9161e7e8e8c965bb06247ad2ac64401672f5 tree 38
  bcaaa5a62b46019cc9a11dde75a1cbc05aea37a8 tree 97
  c5dabe365df71fa3aed52d99157a6b5c59bc13af commit 232
  cf9a728b066ba3762b7fb441ccf5fc50658b09cb tree 97
  d3906ef880ca4bdc3b3271e7efb341b51dd9898c tree 29
  d6c9538a02df64698b51a3427e455bbf0d3e2a55 tree 29
  db1f00b4e55df768ad43dd76af4d2dbac2cea73d tree 62
  dcfae91ae42930b3c7aa438404fb96b5c25068e7 tree 30
  debb4b3dca053a8585bd3d1bcbba542a0c42fc86 tree 31
  eacce2c6525173d6a0366463739611cc9014d3c7 tree 31
  f092def907ed3dd7aa3632063598518b1ef25433 blob 46
  f173feaefcfdd9440b5907d2a0cdf9e0e7dd88bb tree 93
  fa7bdaeb40a7a8b7e7f4b3a280f5393605daf83c tree 41
  fb72221c840907a404a4433b2f3222fda77db320 blob 26
  
   */

}
