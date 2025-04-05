package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class WebFluxController {

    @GetMapping("/webflux")
    public Mono<String> getDataSync(@RequestParam long delay) {
        return Mono.just("somedata").delayElement(Duration.ofMillis(delay));
    }

}
