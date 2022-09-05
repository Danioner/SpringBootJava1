package com.example.demo.controller;
import com.example.demo.models.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class hello_world {

//    @RequestMapping(method = RequestMethod.GET, path = "helloWorld")
    @GetMapping(path = "helloWorld")
    public String helloWorld(){
        return "Hello World V2";
    }

    @GetMapping(path = "helloWorld-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World V2");
    }

    @GetMapping(path = "hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
