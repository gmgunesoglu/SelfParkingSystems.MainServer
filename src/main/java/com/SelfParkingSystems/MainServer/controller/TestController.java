package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.security.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
