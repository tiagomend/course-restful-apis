package br.com.tiagomendonca.restmath.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.tiagomendonca.restmath.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // http://localhost:8080/greeting?name=Tiago
    @RequestMapping("/greeting")
    public Greeting greeting(
        @RequestParam(value = "name", defaultValue = "World") 
        String name) {
        
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
