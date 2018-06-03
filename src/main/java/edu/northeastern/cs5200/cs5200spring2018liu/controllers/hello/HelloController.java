package edu.northeastern.cs5200.cs5200spring2018liu.controllers.hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final HelloRepository helloRepository;

    public HelloController(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @RequestMapping("/api/hello/string")
    public String sayHello() {
        return "Hello Guolin Liu!";
    }

    @RequestMapping("/api/hello/object")
    public HelloObject sayHelloObject() {
        return new HelloObject("Hello Guolin Liu!");
    }

    @RequestMapping("/api/hello/insert")
    public HelloObject insertHelloObject() {
        HelloObject obj = new HelloObject("Hello Guolin Liu!");
        helloRepository.save(obj);
        return obj;
    }

    @RequestMapping("/api/hello/insert/{msg}")
    public HelloObject insertMessage(@PathVariable("msg") String message) {
        HelloObject obj = new HelloObject(message);
        helloRepository.save(obj);
        return obj;
    }

    @RequestMapping("/api/hello/select/all")
    public List<HelloObject> selectAllHelloObjects() {
        List<HelloObject> hellos = helloRepository.findAll();
        return hellos;
    }
}
