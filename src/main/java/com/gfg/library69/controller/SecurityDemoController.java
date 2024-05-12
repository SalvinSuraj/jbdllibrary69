package com.gfg.library69.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityDemoController {

    @GetMapping("greet/{username}")
    public String greet(@PathVariable String username)
    {
        System.out.println("In security controller");
        return "Hello "+username;
    }

    @GetMapping("admin/greet/{username}")
    public String adminGreet(@PathVariable String username)
    {
        return "Hello Admin "+username;
    }
}
