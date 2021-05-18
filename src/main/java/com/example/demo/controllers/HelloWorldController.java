package com.example.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("hello")
    @PreAuthorize("hasRole()")
    public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello "+name+"!!";
    }
}
