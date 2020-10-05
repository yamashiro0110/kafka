package org.yamashiro0110.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controllerで受け取ったパラメータをtopicに送信する
 */
@RestController
@RepositoryRestController
@Slf4j
public class StringMsgSendController {
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

    @GetMapping(path = "/kafka/send/string")
    public ResponseEntity<?> send(@RequestParam("msg") final String msg) {
        final ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(KafkaProducerApp.TOPIC_NAME, msg);
        future.addCallback(this.callback);
        return ResponseEntity.ok(Map.of("result", "ok"));
    }

}
