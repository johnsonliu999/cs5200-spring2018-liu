package edu.northeastern.cs5200.cs5200spring2018liu.controllers.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/api/hello/string")
    public String sayHello() {
        return "Hello Guolin Liu!";
    }

    @RequestMapping("/api/hello/object")
    public HelloObject sayHelloObject() {
        return new HelloObject("Hello Guolin Liu!");
    }
}
