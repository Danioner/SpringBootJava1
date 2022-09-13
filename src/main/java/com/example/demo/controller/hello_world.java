package com.example.demo.controller;
import com.example.demo.models.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class hello_world {

    private MessageSource messageSource;

    public hello_world(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //TODO > Tambien puede ser @RequestMapping(method = RequestMethod.GET, path = "helloWorld")
    @GetMapping(path = "helloWorld")
    public String helloWorld(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
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
