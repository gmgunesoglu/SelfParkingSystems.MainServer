package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.dto.ReservationDetailDto;
import com.SelfParkingSystems.MainServer.dto.ReservationDto;
import com.SelfParkingSystems.MainServer.dto.ReservationRegisterDto;
import com.SelfParkingSystems.MainServer.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationDto> getAll(HttpServletRequest request){
        return reservationService.getAll(request);
    }

    @GetMapping("/{id}")
    public ReservationDetailDto get(@PathVariable Long id, HttpServletRequest request){
        return reservationService.get(id, request);
    }

    @PostMapping
    public ReservationDto add(@RequestBody ReservationRegisterDto reservationRegisterDto, HttpServletRequest request){
        return reservationService.add(reservationService, request);
    }
}
