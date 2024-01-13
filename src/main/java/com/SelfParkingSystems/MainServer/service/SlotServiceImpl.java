package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.*;
import com.SelfParkingSystems.MainServer.exceptionhandling.GlobalRuntimeException;
import com.SelfParkingSystems.MainServer.repository.ParkRepository;
import com.SelfParkingSystems.MainServer.repository.PaymentRecipeRepository;
import com.SelfParkingSystems.MainServer.repository.SlotRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService{

    private final SlotRepository slotRepository;
    private final ParkRepository parkRepository;
    private final PaymentRecipeRepository paymentRecipeRepository;
    private final AccountService accountService;

    @Override
    public List<SlotDto> getAll(HttpServletRequest request) {
        Person owner = accountService.getOwner(request);
        return slotRepository.getSlotDtoListByOwnerId(owner.getId());
    }

    @Override
    public SlotDetailDto get(Long id, HttpServletRequest request) {
        Person owner = accountService.getOwner(request);
        SlotDetailDto dto = slotRepository.getSlotDetailDtoById(id, owner.getId());
        if(dto == null){
            throw new GlobalRuntimeException("Slot bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        if(dto.getState()== SlotState.RESERVED){
            dto.setReservationer(slotRepository.getReservationerDto(id));
        }
        return dto;
    }

    @Override
    public List<SlotDto> add(SlotRegisterDto slotRegisterDto, HttpServletRequest request) {
        List<Slot> slots = new ArrayList<>();
        Person person = accountService.getOwner(request);
        Park park = parkRepository.findByOwnerIdAndName(person.getId(), slotRegisterDto.getParkName());
        if(park == null){
            throw new GlobalRuntimeException("Size ait "+slotRegisterDto.getParkName()+" isminde bir park bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        PaymentRecipe paymentRecipe = paymentRecipeRepository.findByOwnerIdAndName(person.getId(), slotRegisterDto.getPaymentRecipeName());
        if(paymentRecipe == null){
            throw new GlobalRuntimeException("Size ait "+slotRegisterDto.getPaymentRecipeName()+" isminde bir ödeme tarifesi bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        if(slotRegisterDto.getSlotNames() == null || slotRegisterDto.getSlotNames().isEmpty()){
            throw new GlobalRuntimeException("Otopark alanları için isim listesi bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        for(String slotName : slotRegisterDto.getSlotNames()){
            if(slotName.length() < 3 ||slotName.length() > 8){
                throw new GlobalRuntimeException("Alan adı en az 3 en fazla 8 karakter olmalıdır!", HttpStatus.BAD_REQUEST);
            }
            Slot slot = new Slot();
            slot.setParkId(park.getId());
            slot.setName(slotName);
            slot.setState(SlotState.FREE);
            slot.setEnable(true);
            slot.setPaymentRecipe(paymentRecipe);
            slots.add(slot);
        }
        slotRepository.saveAll(slots);
        List<SlotDto> dtoList = new ArrayList<>();
        for(Slot slot : slots){
            dtoList.add(slotRepository.getSlotDtoByParkIdAndName(park.getId(), slot.getName()));
        }
        return dtoList;
    }

    @Override
    public SlotDto update(SlotUpdateDto slotUpdateDto, Long id, HttpServletRequest request) {
        Slot slot = slotRepository.findByIdAndEnable(id, true)
                .orElseThrow(() -> new GlobalRuntimeException("Park alanı bulunamadı!", HttpStatus.BAD_REQUEST));
        Person owner = accountService.getOwner(request);
        if(owner.getId() != slotRepository.getOwnerIdById(id)){
            throw new GlobalRuntimeException("Bu park alanı için yetkiniz bulunmamaktadır!", HttpStatus.BAD_REQUEST);
        }
        if(slot.getState()!=SlotState.FREE){
            throw new GlobalRuntimeException("Bu park alanı kullanılıyor!", HttpStatus.BAD_REQUEST);
        }
        String parkName = slotUpdateDto.getParkName();
        String slotName = slotUpdateDto.getSlotName();
        String paymentRecipeName = slotUpdateDto.getPaymentRecipeName();

        if(parkName != null && !parkName.equals("")){
            if(parkName.length()<8 || parkName.length()>40){
                throw new GlobalRuntimeException("Otopar adı en az 8 en fazla 40 karakter olmalıdır!", HttpStatus.BAD_REQUEST);
            }
            Park park = parkRepository.findByOwnerIdAndName(owner.getId(), parkName);
            if(park == null){
                throw new GlobalRuntimeException(parkName+" isminde bir otoparkınız bulunmamaktadır!", HttpStatus.BAD_REQUEST);
            }
            slot.setParkId(park.getId());
        }

        if(slotName != null && !slotName.equals("")){
            if(slotName.length() < 3 || slotName.length() > 8){
                throw new GlobalRuntimeException("Otopark alanı adı en az 3 en fazla 8 karakter olmalıdır!", HttpStatus.BAD_REQUEST);
            }
            slot.setName(slotName);
        }

        if(paymentRecipeName != null && !paymentRecipeName.equals("")){
            if(paymentRecipeName.length() < 5 || paymentRecipeName.length() > 20){
                throw new GlobalRuntimeException("Ödeme tarifesi adı en az 5 en fazla 20 karakter olmalıdır!", HttpStatus.BAD_REQUEST);
            }
            PaymentRecipe paymentRecipe = paymentRecipeRepository.findByOwnerIdAndName(owner.getId(), paymentRecipeName);
            if(paymentRecipe == null){
                throw new GlobalRuntimeException(paymentRecipeName+" isminde bir ödeme tarifeniz bulunmamaktadır!", HttpStatus.BAD_REQUEST);
            }
            slot.setPaymentRecipe(paymentRecipe);
        }
        slotRepository.save(slot);
        return slotRepository.getSlotDtoById(id);
    }

    @Override
    public String remove(Long id, HttpServletRequest request) {
        Slot slot = slotRepository.findByIdAndEnable(id, true)
                .orElseThrow(() -> new GlobalRuntimeException("Park alanı bulunamadı!", HttpStatus.BAD_REQUEST));
        Person owner = accountService.getOwner(request);
        if(owner.getId() != slotRepository.getOwnerIdById(id)){
            throw new GlobalRuntimeException("Bu park alanı için yetkiniz bulunmamaktadır!", HttpStatus.BAD_REQUEST);
        }
        if(slot.getState()!=SlotState.FREE){
            throw new GlobalRuntimeException("Bu park alanı kullanılıyor!", HttpStatus.BAD_REQUEST);
        }
        slot.setEnable(false);
        slotRepository.save(slot);
        return "Otopark alanı silindi.";
    }
}
