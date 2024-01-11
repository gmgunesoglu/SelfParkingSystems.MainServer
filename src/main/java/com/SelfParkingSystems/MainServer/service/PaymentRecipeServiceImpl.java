package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.Authority;
import com.SelfParkingSystems.MainServer.entity.PaymentRecipe;
import com.SelfParkingSystems.MainServer.entity.Person;
import com.SelfParkingSystems.MainServer.entity.Slot;
import com.SelfParkingSystems.MainServer.exceptionhandling.GlobalRuntimeException;
import com.SelfParkingSystems.MainServer.repository.PaymentRecipeRepository;
import com.SelfParkingSystems.MainServer.repository.SlotRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentRecipeServiceImpl implements PaymentRecipeService{


    private final PaymentRecipeRepository paymentRecipeRepository;
    private final SlotRepository slotRepository;
    private final AccountService accountService;
    private final StaffOfOwnerService staffOfOwnerService;


    @Override
    public List<PaymentRecipeDto> getAll(HttpServletRequest request) {
        Person owner = getOwner(request);
        return paymentRecipeRepository.getPaymentRecipeDtoList(owner.getId());
    }

    @Override
    public PaymentRecipeDetailDto get(Long id, HttpServletRequest request) {
        Person owner = getOwner(request);
        PaymentRecipeDetailDto dto = paymentRecipeRepository.getPaymentRecipeDetailDto(owner.getId(), id);
        dto.setSlots(slotRepository.getSlotDtoListByOwnerIdAndPaymentRecipeId(owner.getId(), id));
        return dto;
    }

    @Override
    public PaymentRecipeDto add(PaymentRecipeRegisterDto paymentRecipeRegisterDto, HttpServletRequest request) {
        Person owner = getOwner(request);
        PaymentRecipe paymentRecipe = new PaymentRecipe();
        paymentRecipe.setOwnerId(owner.getId());
        paymentRecipe.setName(paymentRecipeRegisterDto.getName());
        paymentRecipe.setHours2(paymentRecipe.getHours2());
        paymentRecipe.setHours4(paymentRecipe.getHours4());
        paymentRecipe.setHours6(paymentRecipe.getHours6());
        paymentRecipe.setHours10(paymentRecipe.getHours10());
        paymentRecipe.setHours24(paymentRecipe.getHours24());
        paymentRecipe.setEnable(true);
        paymentRecipeRepository.save(paymentRecipe);
        return new PaymentRecipeDto(
                paymentRecipe.getId(),
                paymentRecipe.getName(),
                paymentRecipe.getHours2(),
                paymentRecipe.getHours4(),
                paymentRecipe.getHours6(),
                paymentRecipe.getHours10(),
                paymentRecipe.getHours24()
        );
    }

    @Override
    public PaymentRecipeDto update(PaymentRecipeUpdateDto paymentRecipeUpdateDto, Long id, HttpServletRequest request) {
        Person owner = getOwner(request);
        PaymentRecipe paymentRecipe = paymentRecipeRepository.getByIdAndOwnerIdAndEnable(id, owner.getId(), true);
        if(paymentRecipe == null){
            throw new GlobalRuntimeException(paymentRecipeUpdateDto.getName() + " isminde ödeme tarifesi bulunamadı", HttpStatus.BAD_REQUEST);
        }
        for(Slot slot : paymentRecipe.getSlots()){
            if(slot.getState()!= SlotState.FREE){
                throw new GlobalRuntimeException("Ödeme tarifesini kullanan tüm park alanları boş olmalıdır!",HttpStatus.BAD_REQUEST);
            }
        }
        String paymentRecipeName = paymentRecipeUpdateDto.getName();
        if(paymentRecipeName != null && !paymentRecipeName.equals("")){
            if(paymentRecipeName.length()<5 || paymentRecipeName.length()>20){
                throw new GlobalRuntimeException("Ödeme tarifesi adı en az 5 en fazla 20 karakter olmalıdır!",HttpStatus.BAD_REQUEST);
            }
        }
        paymentRecipe.setName(paymentRecipeName);
        paymentRecipe.setHours2(paymentRecipeUpdateDto.getHours2());
        paymentRecipe.setHours4(paymentRecipeUpdateDto.getHours4());
        paymentRecipe.setHours6(paymentRecipeUpdateDto.getHours6());
        paymentRecipe.setHours10(paymentRecipeUpdateDto.getHours10());
        paymentRecipe.setHours24(paymentRecipeUpdateDto.getHours24());
        paymentRecipeRepository.save(paymentRecipe);
        return new PaymentRecipeDto(
                paymentRecipe.getId(),
                paymentRecipe.getName(),
                paymentRecipe.getHours2(),
                paymentRecipe.getHours4(),
                paymentRecipe.getHours6(),
                paymentRecipe.getHours10(),
                paymentRecipe.getHours24()
        );
    }

    @Override
    public String remove(Long id, HttpServletRequest request) {
        Person owner = getOwner(request);
        PaymentRecipe paymentRecipe = paymentRecipeRepository.getByIdAndOwnerIdAndEnable(id, owner.getId(), true);
        if(paymentRecipe == null){
            throw new GlobalRuntimeException("Ödeme tarifesi bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        for(Slot slot : paymentRecipe.getSlots()){
            if(slot.getState()!= SlotState.FREE){
                throw new GlobalRuntimeException("Ödeme tarifesini kullanan tüm park alanları boş olmalıdır!",HttpStatus.BAD_REQUEST);
            }
            slot.setEnable(false);
        }
        paymentRecipe.setEnable(false);
        paymentRecipeRepository.save(paymentRecipe);
        return "Ödeme tarifesi silindi.";
    }

    private Person getOwner(HttpServletRequest request){
        Person person = accountService.getPerson(request);
        if(person.getAuthority() == Authority.STAFF){
            person = staffOfOwnerService.getOwner(person);
        }
        return person;
    }
}
