package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.ReservationDetailDto;
import com.SelfParkingSystems.MainServer.dto.ReservationDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Override
    public List<ReservationDto> getAll(HttpServletRequest request) {
        return null;
    }

    @Override
    public ReservationDetailDto get(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ReservationDto add(ReservationService reservationService, HttpServletRequest request) {
        return null;
    }
}
