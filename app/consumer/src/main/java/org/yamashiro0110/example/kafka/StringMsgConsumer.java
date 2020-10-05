package org.yamashiro0110.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class StringMsgConsumer {

    @KafkaListener(topics = "topic-1", id = "test-consumer-group")
    @Transactional
    public void listen(final ConsumerRecord<?, ?> record) {
        log.info("messageを取得しました {}", record);
    }

}
