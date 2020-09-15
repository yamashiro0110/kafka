# Kafka - Connect

Kafka Connectとは？
> https://kafka.apache.org/documentation.html#connect

Kafka Connect is a tool for scalably and reliably streaming data between Apache Kafka and other systems. It makes it simple to quickly define connectors that move large collections of data into and out of Kafka. Kafka Connect can ingest entire databases or collect metrics from all your application servers into Kafka topics, making the data available for stream processing with low latency. An export job can deliver data from Kafka topics into secondary storage and query systems or into batch systems for offline analysis.
> Kafka Connectは、Apache Kafkaと他のシステムの間でスケーラブルかつ確実にデータをストリーミングするためのツールです。 大規模なデータのコレクションをKafkaの内外に移動するコネクタをすばやく簡単に定義できます。 Kafka Connectは、データベース全体を取り込むか、すべてのアプリケーションサーバーからメトリックを収集してKafkaトピックに入れ、データを低レイテンシでストリーム処理できるようにします。 エクスポートジョブは、Kafkaトピックからのデータをセカンダリストレージとクエリシステムに、またはオフライン分析用のバッチシステムに配信できます。

## example

スタンドアローンで起動する。

> NOTE: `connect-file-source.properties`と`connect-file-sink.properties`は、imageに元々含まれているサンプルのコネクタ設定ファイル

### ファイルの内容を同期する

Connectorを起動

```sh
$ ./bin/connect-standalone.sh config/connect-standalone.properties config/connect-file-source.properties config/connect-file-sink.properties
```

- connect-file-source.properties

```
name=local-file-source
connector.class=FileStreamSource
tasks.max=1
file=test.txt
```

- connect-file-sink.properties

```
name=local-file-sink
connector.class=FileStreamSink
tasks.max=1
file=test.sink.txt
```

> NOTE:
> The first parameter is the configuration for the worker. This includes settings such as the Kafka connection parameters, serialization format, and how frequently to commit offsets. The provided example should work well with a local cluster running with the default configuration provided by config/server.properties. It will require tweaking to use with a different configuration or production deployment. All workers (both standalone and distributed) require a few configs:
>
> 最初のパラメーターは、ワーカーの構成です。 これには、Kafka接続パラメーター、シリアル化形式、オフセットをコミットする頻度などの設定が含まれます。 提供されている例は、config / server.propertiesによって提供されるデフォルト構成で実行されているローカルクラスターで適切に機能します。 別の構成または本番環境で使用するには、微調整が必要です。 すべてのワーカー（スタンドアロンと分散の両方）には、いくつかの構成が必要です。

sourceのファイルを更新する。

```sh
$ date >> test.txt

# sinkのファイルに反映されている
$ cat test.sink.txt
```

consoleに出力する。
> `test.txt`の内容が出力される。追記した場合は、その内容が出力される。

```sh
$ bin/kafka-console-consumer.sh --bootstrap-server=localhost:9092 --topic connect-test --from-beginning
```
