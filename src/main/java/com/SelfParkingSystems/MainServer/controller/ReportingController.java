package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.dto.ReportCallDto;
import com.SelfParkingSystems.MainServer.dto.ReportDto;
import com.SelfParkingSystems.MainServer.service.ReportingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportingController {

    private final ReportingService reportingService;

    @PostMapping
    public List<ReportDto> get(@RequestBody ReportCallDto reportCallDto, HttpServletRequest request){
        return reportingService.get(reportCallDto, request);
    }
}
