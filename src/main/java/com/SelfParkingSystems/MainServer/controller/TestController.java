package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @GetMapping("/integration")
    public String integrationTest(){
        return service.integrationTest();
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin test ok";
    }

    @GetMapping("/owner")
    public String owner(){
        return "owner test ok";
    }

    @GetMapping("/staff")
    public String staff(){
        return "staff test ok";
    }
    @GetMapping("/user")
    public String user(){
        return "user test ok";
    }

    @GetMapping("/visitor")
    public String visitor(){
        return "visitor test ok";
    }
}
