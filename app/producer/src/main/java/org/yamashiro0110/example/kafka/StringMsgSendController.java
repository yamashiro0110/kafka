package org.yamashiro0110.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controllerで受け取ったパラメータをtopicに送信する
 */
@RestController
@Slf4j
public class StringMsgSendController {
    @Autowired
    private StringMsgSendService stringMsgSendService;

    @GetMapping(path = "/kafka/send/string")
    public ResponseEntity<?> send(@RequestParam("msg") final String msg) {
        this.stringMsgSendService.send(msg);
        return ResponseEntity.ok(Map.of("result", "ok"));
    }

}
