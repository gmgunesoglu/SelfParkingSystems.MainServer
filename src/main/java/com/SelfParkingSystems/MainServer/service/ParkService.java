package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.ParkDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkDto;
import com.SelfParkingSystems.MainServer.dto.ParkRegisterDto;
import com.SelfParkingSystems.MainServer.dto.ParkUpdateDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ParkService {
    List<ParkDto> getAll(HttpServletRequest request);

    ParkDetailDto get(Long id, HttpServletRequest request);

    ParkDto add(ParkRegisterDto parkRegisterDto, HttpServletRequest request);

    ParkDto update(ParkUpdateDto parkUpdateDto, Long id, HttpServletRequest request);

    String remove(Long id, HttpServletRequest request);
}
