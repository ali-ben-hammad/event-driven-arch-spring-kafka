package org.example.kafkaspring.web;

import org.example.kafkaspring.entities.PageEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class PageEventRestController {

    private final StreamBridge streamBridge;

    public PageEventRestController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String topic, @PathVariable String name){
        PageEvent pageEvent = new PageEvent(name, Math.random() > 0.5 ? "user1" : "user2", new Date(), (long) (Math.random() * 1000));
        streamBridge.send(topic, pageEvent);
        return pageEvent;
    }
}
