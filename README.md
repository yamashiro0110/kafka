# Kafka - app

kafkaのProducer/Consumerのアプリケーション。

## Requirements

- Docker(>= 19.03.13)
- docker-compose(>= 1.27.4)
- Java11

## Usage

kafka broker

> [see docs/docker.md](docs/docker.md)

kafka producer

```sh
$ ./gradlew :producer:bootRun
```

kafka consumer

```sh
$ ./gradlew :consumer:bootRun
```
