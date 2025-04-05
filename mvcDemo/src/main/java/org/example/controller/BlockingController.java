package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
public class BlockingController {

    private final Executor executor = Executors.newFixedThreadPool(20);

    @GetMapping(value = "/blocking")
    public String handleSync(@RequestParam long delay) throws InterruptedException {
        Thread.sleep(delay);
        return "somedata";
    }

    @GetMapping("/cfuture")
    public CompletableFuture<String> handleAsyncCompletableFuture(@RequestParam long delay) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "somedata";
        }, executor);
    }

    @GetMapping("/callable")
    public Callable<String> handleAsyncCallable(@RequestParam long delay) {
        return () -> {
            Thread.sleep(delay);
            return "somedata";
        };
    }

}
