package com.example.ssetest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final AlarmSet alarmSet;

    @PostMapping("/api/v1/post")
    public Long write() {
        Long id = 1L;
        Set<UUID> set = new HashSet<>();
        alarmSet.push(set, 1L);
        return id;
    }
}
