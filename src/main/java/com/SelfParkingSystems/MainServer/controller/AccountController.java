package com.SelfParkingSystems.MainServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {


    @GetMapping("/test")
    public String test(){
        return "test ok";
    }

}
