# Kafka - topic

topicとは？

- ファイルシステムのフォルダのようなもので、イベントが保存される場所

## example

topicを作成

```sh
$ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092

# kafka_1      | [2020-09-10 08:45:02,404] INFO Created log for partition quickstart-events-0 in /bitnami/kafka/data/quickstart-events-0 with properties {compression.type -> producer, min.insync.replicas -> 1, message.downconversion.enable -> true, segment.jitter.ms -> 0, cleanup.policy -> [delete], flush.ms -> 9223372036854775807, retention.ms -> 604800000, segment.bytes -> 1073741824, flush.messages -> 9223372036854775807, message.format.version -> 2.6-IV0, max.compaction.lag.ms -> 9223372036854775807, file.delete.delay.ms -> 60000, max.message.bytes -> 1048588, min.compaction.lag.ms -> 0, message.timestamp.type -> CreateTime, preallocate -> false, index.interval.bytes -> 4096, min.cleanable.dirty.ratio -> 0.5, unclean.leader.election.enable -> false, retention.bytes -> -1, delete.retention.ms -> 86400000, segment.ms -> 604800000, message.timestamp.difference.max.ms -> 9223372036854775807, segment.index.bytes -> 10485760}. (kafka.log.LogManager)
```

topicのパーティション数などの詳細

```sh
$ bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092

Topic: quickstart-events	PartitionCount: 1	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: quickstart-events	Partition: 0	Leader: 1001	Replicas: 1001	Isr: 1001
```

topicにイベントを書き込む
> `kafka-console-producer.sh`を実行すると、入力待ちになる。何か入力するとtopicに書き込まれる。

```sh
$ bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
```

> https://kafka.apache.org/quickstart#quickstart_send

topicからイベントを読み込む
> `kafka-console-consumer.sh`を実行すると、topicへの書き込みが出力される。

```sh
$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```
