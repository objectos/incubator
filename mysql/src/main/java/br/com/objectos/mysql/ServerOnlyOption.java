/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.mysql;

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.ResolvedPath;
import objectos.lang.Checks;

final class ServerOnlyOption extends AbstractOption implements ServerOption {

  private static final ServerOnlyOption SKIP_LOG_BIN = new ServerOnlyOption("skip-log-bin");

  private static final ServerOption SKIP_NETWORKING = new ServerOnlyOption("skip-networking");

  private static final ServerOption SKIP_SSL = new ServerOnlyOption("skip-ssl");

  private ServerOnlyOption(String key) {
    super(key);
  }

  private ServerOnlyOption(String key, String value) {
    super(key, value);
  }

  public static ServerOption basedir(Directory directory) {
    Checks.checkNotNull(directory, "directory == null");

    return new ServerOnlyOption("basedir", directory.getPath());
  }

  public static ServerOption binlogFormat(BinlogFormat format) {
    Checks.checkNotNull(format, "format == null");

    return new ServerOnlyOption("binlog-format", format.name());
  }

  public static ServerOption characterSetServer(MysqlCharset charset) {
    Checks.checkNotNull(charset, "charset == null");

    return new ServerOnlyOption("character-set-server", charset.getValue());
  }

  public static ServerOption datadir(Directory directory) {
    Checks.checkNotNull(directory, "directory == null");

    return new ServerOnlyOption("datadir", directory.getPath());
  }

  public static ServerOption defaultTimeZone(String value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("default-time-zone", value);
  }

  public static ServerOption disabledStorageEngines(StorageEngine... engines) {
    Checks.checkNotNull(engines, "engines == null");

    String value;
    value = commaSeparated(engines);

    return new ServerOnlyOption("disabled-storage-engines", value);
  }

  public static ServerOption explicitDefaultsForTimestamp(Toggle value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("explicit-defaults-for-timestamp", value.name());
  }

  public static ServerOption generalLog(Toggle value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("general-log", value.name());
  }

  public static ServerOption generalLogFile(ResolvedPath path) {
    Checks.checkNotNull(path, "path == null");

    return new ServerOnlyOption("general-log-file", path.getPath());
  }

  public static ServerOption innodbBufferPoolChunkSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("innodb-buffer-pool-chunk-size", unit.toString(value));
  }

  public static ServerOption innodbBufferPoolSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("innodb-buffer-pool-size", unit.toString(value));
  }

  public static ServerOption innodbDoublewrite(Toggle value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("innodb-doublewrite", value.name());
  }

  public static ServerOption innodbFlushLogAtTrxCommit(int value) {
    Checks.checkArgument(value >= 0, "valid values are 0, 1, or 2");
    Checks.checkArgument(value <= 2, "valid values are 0, 1, or 2");

    return new ServerOnlyOption("innodb-flush-log-at-trx-commit", Integer.toString(value));
  }

  public static ServerOption innodbFlushMethod(FlushMethod value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("innodb-flush-method", value.name());
  }

  public static ServerOption innodbLogFileSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("innodb-log-file-size", unit.toString(value));
  }

  public static ServerOption innodbMaxUndoLogSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("innodb-max-undo-log-size", unit.toString(value));
  }

  public static ServerOption innodbRollbackSegments(int value) {
    Checks.checkArgument(value >= 1, "valid values are 1 <= valid <= 128");
    Checks.checkArgument(value <= 128, "valid values are 1 <= valid <= 128");

    return new ServerOnlyOption("innodb-rollback-segments", Integer.toString(value));
  }

  public static ServerOption keyBufferSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("key-buffer-size", unit.toString(value));
  }

  public static ServerOption logBin(Directory directory, String prefix) {
    Checks.checkNotNull(directory, "directory == null");
    Checks.checkNotNull(prefix, "prefix == null");

    return logBin(directory.resolve(prefix));
  }

  public static ServerOption logBin(ResolvedPath path) {
    Checks.checkNotNull(path, "path == null");

    return new ServerOnlyOption("log-bin", path.getPath());
  }

  public static ServerOption logBinTrustFunctionCreators(Toggle value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("log-bin-trust-function-creators", value.name());
  }

  public static ServerOption logError(ResolvedPath path) {
    Checks.checkNotNull(path, "path == null");

    return new ServerOnlyOption("log-error", path.getPath());
  }

  public static ServerOption maxBinlogSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("max-binlog-size", unit.toString(value));
  }

  public static ServerOption maxConnections(int value) {
    Checks.checkArgument(value >= 0, "value must be > 0");

    return new ServerOnlyOption("max-connections", Integer.toString(value));
  }

  public static ServerOption mysqlx(Toggle value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("mysqlx", value.name());
  }

  public static ServerOption performanceSchema(Toggle value) {
    Checks.checkNotNull(value, "value == null");

    return new ServerOnlyOption("performance-schema", value.name());
  }

  public static ServerOption pidFile(ResolvedPath path) {
    Checks.checkNotNull(path, "path == null");

    return new ServerOnlyOption("pid-file", path.getPath());
  }

  public static ServerOption readBufferSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("read-buffer-size", unit.toString(value));
  }

  public static ServerOption readRndBufferSize(int value, Unit unit) {
    Checks.checkArgument(value >= 0, "value must be >= 0");
    Checks.checkNotNull(unit, "unit == null");

    return new ServerOnlyOption("read-rnd-buffer-size", unit.toString(value));
  }

  public static ServerOption secureFilePriv(Directory directory) {
    Checks.checkNotNull(directory, "directory == null");

    return new ServerOnlyOption("secure-file-priv", directory.getPath());
  }

  public static ServerOption serverId(int id) {
    Checks.checkArgument(id >= 0, "id must be >= 0");

    return new ServerOnlyOption("server-id", Integer.toString(id));
  }

  public static ServerOption skipLogBin() {
    return SKIP_LOG_BIN;
  }

  public static ServerOption skipNetworking() {
    return SKIP_NETWORKING;
  }

  public static ServerOption skipSsl() {
    return SKIP_SSL;
  }

  public static ServerOption slowQueryLogFile(ResolvedPath path) {
    Checks.checkNotNull(path, "path == null");

    return new ServerOnlyOption("slow-query-log-file", path.getPath());
  }

  public static ServerOption sqlMode(SqlMode... modes) {
    Checks.checkNotNull(modes, "modes == null");

    String value;
    value = commaSeparated(modes);

    return new ServerOnlyOption("sql-mode", value);
  }

  public static ServerOption syncBinlog(long value) {
    Checks.checkArgument(value > 0, "value must be > 0");

    return new ServerOnlyOption("sync-binlog", Long.toString(value));
  }

  public static ServerOption tmpdir(Directory directory) {
    Checks.checkNotNull(directory, "directory == null");

    return new ServerOnlyOption("tmpdir", directory.getPath());
  }

  public static ServerOption waitTimeout(int value) {
    Checks.checkArgument(value > 0, "wait_timeout must be > 0 seconds");
    Checks.checkArgument(value <= 31536000, "wait_timeout must be <= 31536000 seconds");

    return new ServerOnlyOption("wait-timeout", Integer.toString(value));
  }

  private static String commaSeparated(Enum<?>... values) {
    StringBuilder value;
    value = new StringBuilder();

    if (values.length != 0) {
      Enum<?> first;
      first = values[0];

      value.append(first.name());
    }

    for (int i = 1; i < values.length; i++) {
      value.append(',');

      Enum<?> next;
      next = values[i];

      value.append(next.name());
    }

    return value.toString();
  }

}