package com.SelfParkingSystems.MainServer.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account/acc={acc_id}")
@RequiredArgsConstructor
public class AccountController {


    @GetMapping("/test")
    public String test(@PathVariable String acc_id){

        return "test ok\nacc_id:"+acc_id;
    }

}
