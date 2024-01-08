package com.SelfParkingSystems.MainServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TestService {


    RestTemplate parkingController = new RestTemplate();
    String url = "http://localhost:8801/test/integration";

    public String integrationTest() {
        String message = "MainServer integration test ok.";
        String result = parkingController.getForObject(url, String.class);
        message = message + "\n" + result;
        return message;
    }
}
