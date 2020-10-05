package org.yamashiro0110.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerApp {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerApp.class);

    public static void main(final String[] args) {
        SpringApplication.run(KafkaConsumerApp.class, args);
    }

    @KafkaListener(topics = "topic-1")
    public void listen(final ConsumerRecord<?, ?> record) {
        this.logger.info("messageを取得しました {}", record);
    }

}
