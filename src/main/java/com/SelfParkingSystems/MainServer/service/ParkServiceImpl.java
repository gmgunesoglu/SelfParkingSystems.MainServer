package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.*;
import com.SelfParkingSystems.MainServer.exceptionhandling.GlobalRuntimeException;
import com.SelfParkingSystems.MainServer.repository.ParkRepository;
import com.SelfParkingSystems.MainServer.repository.SlotRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkServiceImpl implements ParkService{

    private final AccountService accountService;
    private final ParkRepository parkRepository;
    private final SlotRepository slotRepository;

    private final LocationService locationService;


    @Override
    public List<ParkDto> getAll(HttpServletRequest request) {
        Person owner = accountService.getOwner(request);
        return parkRepository.getParkDtoListByOwnerId(owner.getId());
    }

    @Override
    public ParkDetailDto get(Long id, HttpServletRequest request) {
        Person owner = accountService.getOwner(request);
        ParkDetailDto dto = parkRepository.getParkDetailDtoByIdAndOwnerId(id, owner.getId());
        if(dto == null){
            throw new GlobalRuntimeException("Park bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        dto.setSlots(slotRepository.getSlotDtoListByOwnerId(owner.getId()));
        return dto;
    }

    @Override
    public ParkDto add(ParkRegisterDto parkRegisterDto, HttpServletRequest request) {
        if(parkRegisterDto.getReservationDuration() % 5 != 0){
            throw new GlobalRuntimeException("Maksimum rezervasyon süresi 5 in katları şekilde olmalıdır!", HttpStatus.BAD_REQUEST);
        }
        Location location = new Location();
        location.setCity(parkRegisterDto.getCity().toUpperCase());
        location.setTown(parkRegisterDto.getTown().toUpperCase());
        location.setDistrict(parkRegisterDto.getDistrict().toUpperCase());

        Park park = new Park();
        park.setOwnerId(accountService.getPersonId(request));
        park.setName(parkRegisterDto.getName());
        park.setSecretKey(parkRegisterDto.getSecretKey());
        park.setBaseUrl(parkRegisterDto.getBaseUrl());
        park.setLocation(locationService.saveLocationIfNotExists(location));
        park.setAddress(parkRegisterDto.getAddress());
        park.setReservationDuration(parkRegisterDto.getReservationDuration());
        try{
            park = parkRepository.save(park);
        }catch (ConstraintViolationException e){
            throw new GlobalRuntimeException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return parkToParkDto(park);
    }

    @Override
    public ParkDto update(ParkUpdateDto parkUpdateDto, Long id, HttpServletRequest request) {
        Park park = parkRepository.findByOwnerIdAndEnable(id, true).get();
        if(park == null){
            throw new GlobalRuntimeException("Park bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        Person owner = accountService.getPerson(request);
        if(park.getOwnerId() != owner.getId()){
            throw new GlobalRuntimeException("Bu park için yetkiniz yok!", HttpStatus.BAD_REQUEST);
        }
        if(parkUpdateDto.getReservationDuration() % 5 != 0){
            throw new GlobalRuntimeException("Maksimum rezervasyon süresi 5 in katları şekilde olmalıdır!", HttpStatus.BAD_REQUEST);
        }
        String name = parkUpdateDto.getName();
        String secretKey = parkUpdateDto.getSecretKey();
        String baseUrl = parkUpdateDto.getBaseUrl();
        String city = parkUpdateDto.getCity();
        String town   = parkUpdateDto.getTown();
        String district = parkUpdateDto.getDistrict();
        String address = parkUpdateDto.getAddress();
        int reservationDuration = park.getReservationDuration();
        if(name != null && !name.equals("")){
            if(name.length() < 8 || name.length() > 40){
                throw new GlobalRuntimeException("Park adı en az 8 en fazla 40 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            park.setName(name);
        }
        if(secretKey != null && !secretKey.equals("")){
            if(secretKey.length() < 32 || secretKey.length() > 64){
                throw new GlobalRuntimeException("Gizli anahtar en az 32 en fazla 64 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            park.setSecretKey(secretKey);
        }
        if(baseUrl != null && !baseUrl.equals("")){
            if(baseUrl.length() < 10 || baseUrl.length() > 60){
                throw new GlobalRuntimeException("Sunucu adresiniz en az 10 en fazla 60 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            park.setBaseUrl(baseUrl);
        }
        if(city != null && !city.equals("")){
            if(city.length() < 3 || city.length() > 30){
                throw new GlobalRuntimeException("İl en az 3 en fazla 30 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            park.getLocation().setCity(city.toUpperCase());
        }
        if(town != null && !town.equals("")){
            if(town.length() < 3 || town.length() > 30){
                throw new GlobalRuntimeException("İlçe en az 3 en fazla 30 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            park.getLocation().setTown(town.toUpperCase());
        }
        if(district != null && !district.equals("")){
            if(district.length() < 3 || district.length() > 40){
                throw new GlobalRuntimeException("Mahalle en az 3 en fazla 40 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            park.getLocation().setDistrict(district.toUpperCase());
        }
        if(address != null && !address.equals("")){
            if(address.length() < 20 || address.length() > 300){
                throw new GlobalRuntimeException("Adres en az 3 en fazla 300 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            park.setAddress(address);
        }
        if(reservationDuration != 0){
            if(reservationDuration < 30 || reservationDuration > 120){
                throw new GlobalRuntimeException("Rezervasyon süresi en az 30, en fazla 120 dakikadır!", HttpStatus.BAD_REQUEST);
            }
            park.setReservationDuration(reservationDuration);
        }
        park = parkRepository.save(park);
        return parkToParkDto(park);
    }

    @Override
    public String remove(Long id, HttpServletRequest request) {
        Park park = parkRepository.findByOwnerIdAndEnable(id, true)
                .orElseThrow(() -> new GlobalRuntimeException("Park bulunamadı!", HttpStatus.BAD_REQUEST));
        Person owner = accountService.getPerson(request);
        if(!park.getOwnerId().equals(owner.getId())){
            throw new GlobalRuntimeException("Bu park için yetkniniz bulunmamaktadır!",HttpStatus.BAD_REQUEST);
        }
        for(Slot slot : park.getSlots()){
            if(slot.getState() != SlotState.FREE){
                throw new GlobalRuntimeException("Bu otoparka ait tüm park alanlarınız boş olmalıdır!", HttpStatus.BAD_REQUEST);
            }
            slot.setEnable(false);
        }
        park.setEnable(false);
        parkRepository.save(park);
        return "Otopark silindi.";
    }

    private ParkDto parkToParkDto(Park park){
        ParkDto dto = new ParkDto();
        dto.setId(park.getId());
        dto.setName(park.getName());
        dto.setSecretKey(park.getSecretKey());
        dto.setBaseUrl(park.getBaseUrl());
        dto.setCity(park.getLocation().getCity());
        dto.setTown(park.getLocation().getTown());
        dto.setDistrict(park.getLocation().getDistrict());
        dto.setAddress(park.getAddress());
        return dto;
    }
}
