package org.example.kafkaspring.services;

import org.example.kafkaspring.entities.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class PageEventService {
    @Bean
    public Consumer<PageEvent> pageEventConsumer(){
        return input -> {
            System.out.println("*** PageEventConsumer ***");
            System.out.println(input);
            System.out.println("**************************");
        };
    }

    @Bean
    public Supplier<PageEvent> pageEventSupplier(){
        return ()-> new PageEvent(
                Math.random()>0.5?"page1":"page2",
                Math.random()>0.5?"user1":"user2",
                new Date(),
                new Random().nextInt(90000));
    }

    @Bean
    public Function<PageEvent, PageEvent> pageEventFunction(){
        return input -> {
            input.setName(input.getName().toUpperCase() + "MODIFIED");
            input.setUser(input.getUser().toUpperCase() + "MODIFIED");
            return input;
        };
    }
}

