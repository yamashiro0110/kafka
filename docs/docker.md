# Kafka - docker

KafkaをDockerコンテナで実行する。

- [docker-compose.yml](../docker-compose.yml)

```sh
$ docker-compose up -d
```

## コンテナにログインする

```sh
# 起動しているコンテナを確認
$ docker-compose ps
      Name                     Command               State                          Ports
-----------------------------------------------------------------------------------------------------------------
kafka_kafka_1       /opt/bitnami/scripts/kafka ...   Up      0.0.0.0:9092->9092/tcp
kafka_zookeeper_1   /opt/bitnami/scripts/zooke ...   Up      0.0.0.0:2181->2181/tcp, 2888/tcp, 3888/tcp, 8080/tcp

# コンテナにログイン, `kafka_kafka_1`は上記で確認したコンテナ名
$ docker exec -it kafka_kafka_1 bash

# kafkaのインストールディレクトリに移動
$ cd /opt/bitnami/kafka
```

## Kafka Cluster

Kafka Clusterを起動する。

```sh
$ docker-compose -f docker-compose-cluster.yml up
```

[CMAK](http://localhost:9000)で、管理コンソールにアクセスできる。
