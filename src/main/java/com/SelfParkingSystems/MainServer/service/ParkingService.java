package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;

import java.util.List;

public interface ParkingService {
    List<ParkListDto> getParks(LocationDto locationDto);

    ParkListDetailDto getSlots(Long parkId);

    SlotListDetailDto showSlotInfo(Long slotId);

    ParkingDto parkingIn(ParkingInDto parkingInDto);

    PaymentInfoDto showPaymentInfo(String vehiclePlate);

    PaymentTokenDto getPaymentToken(CardDto cardDto, String vehiclePlate);

    String parkingOut(ParkingOutDto parkingOutDto);

}
