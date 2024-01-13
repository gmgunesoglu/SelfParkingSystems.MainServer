package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.ReportCallDto;
import com.SelfParkingSystems.MainServer.dto.ReportDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ReportingService {
    List<ReportDto> get(ReportCallDto reportCallDto, HttpServletRequest request);
}
