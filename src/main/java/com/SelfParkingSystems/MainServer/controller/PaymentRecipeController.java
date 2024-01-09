package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDetailDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeRegisterDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeUpdateDto;
import com.SelfParkingSystems.MainServer.service.PaymentRecipeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/payment-recipes")
@RequiredArgsConstructor
public class PaymentRecipeController {

    private final PaymentRecipeService paymentRecipeService;

    @GetMapping
    public List<PaymentRecipeDto> getAll(HttpServletRequest request){
        return paymentRecipeService.getAll(request);
    }

    @GetMapping("/{id}")
    public PaymentRecipeDetailDto get(@PathVariable Long id, HttpServletRequest request){
        return paymentRecipeService.get(id, request);
    }

    @PostMapping
    public PaymentRecipeDto add(@RequestBody PaymentRecipeRegisterDto paymentRecipeRegisterDto, HttpServletRequest request){
        return paymentRecipeService.add(paymentRecipeRegisterDto, request);
    }

    @PutMapping("/{id}")
    public PaymentRecipeDto update(@RequestBody PaymentRecipeUpdateDto paymentRecipeUpdateDto, @PathVariable Long id, HttpServletRequest request){
        return paymentRecipeService.update(paymentRecipeUpdateDto, id, request);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id, HttpServletRequest request){
        return paymentRecipeService.remove(id, request);
    }
}
