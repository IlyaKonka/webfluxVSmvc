package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class LearnWebFluxController {
    private static final int TASK_COUNT = 10;
    private static final int DELAY = 1000;

    @GetMapping("/mono")
    public Mono<List<String>> getMonoResponse() {
        return Flux.merge(
                IntStream.range(1, TASK_COUNT+1)
                        .mapToObj(this::simulateTaskAsync)
                        .collect(Collectors.toList())
        ).collectList();
    }

    @GetMapping("/flux")
    public Flux<String> getFluxResponse() {
        return Flux.range(1, TASK_COUNT)
                .flatMap(this::simulateTaskAsync);
    }

    private Mono<String> simulateTaskAsync(int i) {
        return Mono.just("Task " + i + " completed")
                .delayElement(Duration.ofMillis((long) DELAY * i));
    }
}
