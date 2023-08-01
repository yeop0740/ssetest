package com.example.ssetest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AlarmSet {

    private final Map<UUID, SseEmitter> store = new ConcurrentHashMap<>();

    public void add(UUID uuid, SseEmitter sseEmitter) {
        this.store.put(uuid, sseEmitter);
        sseEmitter.onCompletion(() -> {
            this.store.remove(uuid);
        });
        sseEmitter.onTimeout(sseEmitter::complete);
    }

    public void push(Set<UUID> uuids, Long postId) throws AlarmException{
        try {
            for (UUID uuid : uuids) {
                if (this.store.containsKey(uuid)) {
                    this.store.get(uuid).send(SseEmitter.event()
                            .name("post")
                            .data(postId));
                }
            }
        } catch (IOException e) {
            throw new AlarmException("알람 전송 실패");
        }
    }
}
