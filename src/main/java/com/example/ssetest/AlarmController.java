package com.example.ssetest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmSet alarmSet;

    @GetMapping("/api/v1/alarm")
    public ResponseEntity<SseEmitter> connect() {
        SseEmitter sseEmitter = new SseEmitter();
        alarmSet.add(UUID.randomUUID(), sseEmitter);
        try {
            sseEmitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new AlarmException("연결 실패");
        }
        return ResponseEntity.ok(sseEmitter);
    }
}
