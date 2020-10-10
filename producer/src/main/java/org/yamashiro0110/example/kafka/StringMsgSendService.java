package org.yamashiro0110.example.kafka;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class StringMsgSendService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaSendCallback<String, String> callback = new KafkaSendCallback<>() {
        @Override
        public void onFailure(final KafkaProducerException ex) {
            log.error("メッセージの送信に失敗しました {}", ex.getFailedProducerRecord(), ex);
        }

        @Override
        public void onSuccess(final SendResult<String, String> result) {
            log.info("メッセージを送信しました {}", result);
        }
    };

    private String msgKey(final String msg) {
        return Hashing.sha256()
                .newHasher()
                .putString(msg, StandardCharsets.UTF_8)
                .hash()
                .toString();
    }

    public void send(final String msg) {
        final String key = this.msgKey(msg);
        final ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(KafkaProducerApp.TOPIC_NAME, key, msg);
        future.addCallback(this.callback);
        log.debug("send msg: {}", msg);
    }

}
