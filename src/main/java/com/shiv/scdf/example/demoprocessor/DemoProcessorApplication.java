package com.shiv.scdf.example.demoprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executor;
import java.util.function.Function;

@SpringBootApplication
public class DemoProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProcessorApplication.class, args);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> process() {
        return stringFlux -> stringFlux
            .map(
                s -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return s;
                })
            .map(s -> s.toUpperCase())
            .log();
    }
}
