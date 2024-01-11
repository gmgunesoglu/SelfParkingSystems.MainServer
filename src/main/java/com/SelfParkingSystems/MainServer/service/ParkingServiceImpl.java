package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class ParkingServiceImpl implements ParkingService{




    @Override
    public List<ParkListDto> getParks(LocationDto locationDto) {
        return null;
    }

    @Override
    public ParkListDetailDto getSlots(Long parkId) {
        return null;
    }

    @Override
    public SlotListDetailDto showSlotInfo(Long slotId) {
        return null;
    }

    @Override
    public ParkingDto parkingIn(ParkingInDto parkingInDto) {
        return null;
    }

    @Override
    public PaymentInfoDto showPaymentInfo(String vehiclePlate) {
        return null;
    }

    @Override
    public String parkingOut(ParkingOutDto parkingOutDto) {
        return null;
    }
}
