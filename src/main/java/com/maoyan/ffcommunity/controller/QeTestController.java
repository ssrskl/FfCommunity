package com.maoyan.ffcommunity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QeTestController {
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
