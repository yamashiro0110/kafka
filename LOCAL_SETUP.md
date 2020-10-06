# kafka - setup:local

localでKafkaを起動する。
> NOTE: macでの手順

## Requirements

- Homebrew(>= 2.5.2)

## Install

```sh
$ brew install kafka
```

## Start Kafka Broker

```sh
$ zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties
```
