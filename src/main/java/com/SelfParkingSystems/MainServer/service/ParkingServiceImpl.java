package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.Parking;
import com.SelfParkingSystems.MainServer.entity.Payment;
import com.SelfParkingSystems.MainServer.entity.PaymentRecipe;
import com.SelfParkingSystems.MainServer.entity.Slot;
import com.SelfParkingSystems.MainServer.exceptionhandling.GlobalRuntimeException;
import com.SelfParkingSystems.MainServer.repository.ParkRepository;
import com.SelfParkingSystems.MainServer.repository.ParkingRepository;
import com.SelfParkingSystems.MainServer.repository.PaymentRepository;
import com.SelfParkingSystems.MainServer.repository.SlotRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService{

    private final ParkingRepository parkingRepository;
    private final ParkRepository parkRepository;
    private final SlotRepository slotRepository;
    private final PaymentRepository paymentRepository;


    @Override
    public List<ParkListDto> getParks(LocationDto locationDto) {
        if(locationDto.getCity() == null || locationDto.getCity().equals("")){
            return parkRepository.getParkListDtoList();
        }
        if(locationDto.getTown() == null ||locationDto.getTown().equals("")){
            return parkRepository.getParkListDtoListByCity(locationDto.getCity());
        }
        if(locationDto.getDistrict() == null ||locationDto.getDistrict().equals("")){
            return parkRepository.getParkListDtoListByCityAndTown(locationDto.getCity(), locationDto.getTown());
        }
        return parkRepository.getParkListDtoListByCityAndTownAndDistrict(locationDto.getCity(), locationDto.getTown(), locationDto.getDistrict());
    }

    @Override
    public ParkListDetailDto getSlots(Long parkId) {
        ParkListDetailDto dto = parkRepository.getParkListDetailDtoByParkId(parkId);
        if(dto == null){
            throw new GlobalRuntimeException("Otopark bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        dto.setSlots(slotRepository.getSlotListDtoListByParkId(parkId));
        return dto;
    }

    @Override
    public SlotListDetailDto showSlotInfo(Long slotId) {
        SlotListDetailDto dto = slotRepository.getSlotListDetailDtoById(slotId);
        if(dto == null){
            throw new GlobalRuntimeException("Park alanı bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        return dto;
    }

    @Override
    public ParkingDto parkingIn(ParkingInDto parkingInDto) {
        Slot slot = slotRepository.findById(parkingInDto.getSlotId()).get();
        if(slot == null){
            throw new GlobalRuntimeException("Park alanı bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        if(slot.getState()!=SlotState.FREE){
            throw new GlobalRuntimeException("Bu park alanı kullanımda!", HttpStatus.BAD_REQUEST);
        }
        slot.setState(SlotState.BUSY);
        Parking parking = new Parking();
        parking.setVehiclePlate(parkingInDto.getVehiclePlate());
        parking.setSlotId(parkingInDto.getSlotId());
        parking.setDate(new Date(System.currentTimeMillis()));
        slotRepository.save(slot);
        parking = parkingRepository.save(parking);
        return parkingRepository.getParkingDtoById(parking.getId());
    }

    @Override
    public PaymentInfoDto showPaymentInfo(String vehiclePlate) {
        // plakanın son park olayını çek
        Parking parking = parkingRepository.findLastParkingByVehiclePlate(vehiclePlate);
        // bu araç hala park halindemi kontrol et
        Payment payment = parkingRepository.getPaymentOfParkingByParkingId(parking.getId());
        if(payment != null){
            throw new GlobalRuntimeException("Bu park için daha önce bir ödeme alınmış!", HttpStatus.BAD_REQUEST);
        }
        // ücreti hesapla
        Slot slot = slotRepository.findById(parking.getSlotId()).get();
        PaymentInfoDto dto = new PaymentInfoDto();
        dto.setAmount(calculateAmount(parking.getDate(), new Date(System.currentTimeMillis()), slot.getPaymentRecipe()));
        dto.setCurrency("TRY");
        return dto;
    }

    @Override
    public PaymentTokenDto getPaymentToken(CardDto cardDto, String vehiclePlate) {
        PaymentInfoDto paymentInfoDto = showPaymentInfo(vehiclePlate);
        Stripe.apiKey = "pk_test_51NgjmOHuSNzjvuLsqnqGaRRi0TE4uXgfj0ICIZqWVqHOnsaCGRs5fzWm37zlsJhQCZYJRk8KFMAgOhM6bdZVqjlR00BNi0CqDc";
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardDto.getCardNumber());
        cardParams.put("exp_month", cardDto.getExpMonth());
        cardParams.put("exp_year", cardDto.getExpYear());
        cardParams.put("cvc", cardDto.getCvc());
        Map<String, Object> tokenParams = new HashMap<>();
        tokenParams.put("card", cardParams);
        try{
            Token token = Token.create(tokenParams);
            PaymentTokenDto dto=new PaymentTokenDto();
            dto.setPaymentToken(token.getId());
            dto.setAmount(paymentInfoDto.getAmount());
            dto.setCurrency(paymentInfoDto.getCurrency());
            return dto;
        }catch (StripeException e){
            log.error(e.getMessage());
            throw new GlobalRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String parkingOut(ParkingOutDto parkingOutDto) {
        // plakanın son park olayını çek
        Parking parking = parkingRepository.findLastParkingByVehiclePlate(parkingOutDto.getVehiclePlate());
        // bu araç hala park halindemi kontrol et
        Payment oldPayment = parkingRepository.getPaymentOfParkingByParkingId(parking.getId());
        if(oldPayment != null){
            throw new GlobalRuntimeException("Bu park için daha önce bir ödeme alınmış!", HttpStatus.BAD_REQUEST);
        }
        // ücreti hesapla
        Slot slot = slotRepository.findById(parking.getSlotId()).get();
        double amount = calculateAmount(parking.getDate(), new Date(System.currentTimeMillis()), slot.getPaymentRecipe());
        if(parkingOutDto.getAmount() != amount){
            throw new GlobalRuntimeException("Ödenmesi gereken ücret: "
                    + amount + "TRY", HttpStatus.BAD_REQUEST);
        }
        if(!parkingOutDto.getCurrency().equals("TRY")){
            throw new GlobalRuntimeException("Para birimi 'TRY' olmalı!", HttpStatus.BAD_REQUEST);
        }

        // charge için parametreleri
        PaymentDescription paymentDescription = new PaymentDescription(
                slot.getParkId(),
                slot.getId(),
                parking.getId(),
                parking.getDate(),
                new Date(System.currentTimeMillis()),
                amount,
                parkingOutDto.getCurrency()
        );
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int)(parkingOutDto.getAmount() * 100));
        chargeParams.put("currency", "TRY");
        chargeParams.put("description", paymentDescription.toString());
        chargeParams.put("source", parkingOutDto.getPaymentToken());

        // ödemeyi yap
        try {
            Stripe.apiKey="sk_test_51NgjmOHuSNzjvuLseCeRO5y5bXmRIuR0jz5VTHw1SmKu3ZJB0BDyhKa4F8RXZo1qvuGZyGyv7IMioL9rR8bZ9g0R00KixB9LE3";
            Charge charge = Charge.create(chargeParams);
            if(charge.getId()==null || !charge.getPaid()){
                throw new GlobalRuntimeException("Stripe ödemesi ile ilgili bir sorun var!",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (StripeException e) {
            log.error("[-] Stripe Charge Error:\n" + e.getMessage());
            throw new GlobalRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // ödeme alındı veri tabanına gerekli işlemleri yap
        Payment payment = new Payment();
        payment.setParking(parking);
        payment.setStripeToken(parkingOutDto.getPaymentToken());
        payment.setAmount(amount);
        payment.setCurrency(parkingOutDto.getCurrency());
        payment.setDate(paymentDescription.getParkOutDate());
        paymentRepository.save(payment);
        return "Ödeme başarılı bir şekilde alınmıştır, park alanından ayrılabilirsiniz.";
    }

    private double calculateAmount(Date startDate, Date finishDate, PaymentRecipe paymentRecipe){
        int minutes = (int)(finishDate.getTime()-startDate.getTime())/60000;
        int hour24 = minutes/1440;
        minutes -= hour24*1440;
        if(minutes > 600){
            return paymentRecipe.getHours24()*(hour24+1);
        }
        if(minutes > 360){
            return paymentRecipe.getHours24()*hour24 + paymentRecipe.getHours10();
        }
        if(minutes > 240){
            return paymentRecipe.getHours24()*hour24 + paymentRecipe.getHours6();
        }
        if(minutes > 120){
            return paymentRecipe.getHours24()*hour24 + paymentRecipe.getHours4();
        }
        return paymentRecipe.getHours24()*hour24 + paymentRecipe.getHours2();
    }
}
