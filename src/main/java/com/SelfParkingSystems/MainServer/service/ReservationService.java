package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.ReservationDetailDto;
import com.SelfParkingSystems.MainServer.dto.ReservationDto;
import com.SelfParkingSystems.MainServer.dto.ReservationRegisterDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getAll(HttpServletRequest request);

    ReservationDetailDto get(Long id, HttpServletRequest request);

    ReservationDto add(ReservationRegisterDto reservationRegisterDto, HttpServletRequest request);
}
