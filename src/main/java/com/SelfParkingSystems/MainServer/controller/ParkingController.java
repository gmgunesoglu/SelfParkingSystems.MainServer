package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkings")
@RequiredArgsConstructor
public class ParkingController {

    private ParkingService parkingService;

    @PostMapping("/parks")
    public List<ParkListDto> getParks(@RequestBody LocationDto locationDto){
        return parkingService.getParks(locationDto);
    }

    @GetMapping("/parks/{parkId}")
    public ParkListDetailDto getSlots(@PathVariable Long parkId){
        return parkingService.getSlots(parkId);
    }

    @GetMapping("/slots/{slotId}")
    public SlotListDetailDto showSlotInfo(@PathVariable Long slotId){
        return parkingService.showSlotInfo(slotId);
    }

    @PostMapping("/in")
    public ParkingDto parkingIn(@RequestBody ParkingInDto parkingInDto){
        return parkingService.parkingIn(parkingInDto);
    }

    @GetMapping("/payment/{vehiclePlate}")
    public PaymentInfoDto showPaymentInfo(@PathVariable String vehiclePlate){
        return parkingService.showPaymentInfo(vehiclePlate);
    }

    @PostMapping("/out")
    public String parkingOut(@RequestBody ParkingOutDto parkingOutDto){
        return parkingService.parkingOut(parkingOutDto);
    }
}
