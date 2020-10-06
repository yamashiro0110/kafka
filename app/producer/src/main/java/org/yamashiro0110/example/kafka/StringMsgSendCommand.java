package org.yamashiro0110.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Slf4j
public class StringMsgSendCommand implements CommandLineRunner {
    @Autowired
    private StringMsgSendService stringMsgSendService;

    @Override
    public void run(final String... args) throws Exception {
        log.info("メッセージの送信を開始します");

        IntStream.rangeClosed(0, 10).forEach(it -> {
            final String msg = String.format("メッセージを送信します:%s", it);
            this.stringMsgSendService.send(msg);
            log.debug("send msg: {}", msg);
        });

        log.info("メッセージの送信を終了します");
    }
}
