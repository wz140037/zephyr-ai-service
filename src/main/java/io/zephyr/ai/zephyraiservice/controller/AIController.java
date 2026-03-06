package io.zephyr.ai.zephyraiservice.controller;

import io.zephyr.ai.zephyraiservice.service.DifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private DifyService difyService;

    @GetMapping(value = "/chat-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam String message) {

        return difyService.runWorkflowStream(message);
    }
}