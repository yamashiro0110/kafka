package org.yamashiro0110.example.kafka;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * ファイルで追記された行をKafka Topicに送信する
 */
@Component
public class CamelMsgSender extends EndpointRouteBuilder {
    private static final Logger KAFKA_LOG = LoggerFactory.getLogger("kafkaEventLog");
    @Autowired
    private StringMsgSendService stringMsgSendService;

    @Override
    public void configure() throws Exception {
        // @formatter:off
        this.from(this.stream("file")
                .fileName("tmp/kafka.log")
                .scanStream(true)
                .fileWatcher(true)
                .retry(true)
            )
            .process(exchange -> {
                final String msg = exchange.getIn().getBody(String.class);
                this.stringMsgSendService.send(msg);
            })
            .end();
        // @formatter:on
    }

    /**
     * @return kafkaEventLogにログを出力するcommandを実行する
     */
    @Bean
    CommandLineRunner kafkaEventLogRunner() {
        return args -> IntStream.rangeClosed(0, 10).forEach(it -> KAFKA_LOG.info(String.format("kafka.logにログを出力します %s", it)));
    }

}
