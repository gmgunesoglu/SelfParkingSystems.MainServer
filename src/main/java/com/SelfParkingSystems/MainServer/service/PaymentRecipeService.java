package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDetailDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeRegisterDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeUpdateDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PaymentRecipeService {
    List<PaymentRecipeDto> getAll(HttpServletRequest request);

    PaymentRecipeDetailDto get(Long id, HttpServletRequest request);

    PaymentRecipeDto add(PaymentRecipeRegisterDto paymentRecipeRegisterDto, HttpServletRequest request);

    PaymentRecipeDto update(PaymentRecipeUpdateDto paymentRecipeUpdateDto, Long id, HttpServletRequest request);

    String remove(Long id, HttpServletRequest request);
}
