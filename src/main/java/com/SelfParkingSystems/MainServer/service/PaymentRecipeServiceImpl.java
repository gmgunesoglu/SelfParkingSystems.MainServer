package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDetailDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeRegisterDto;
import com.SelfParkingSystems.MainServer.dto.PaymentRecipeUpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentRecipeServiceImpl implements PaymentRecipeService{
    @Override
    public List<PaymentRecipeDto> getAll(HttpServletRequest request) {
        return null;
    }

    @Override
    public PaymentRecipeDetailDto get(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public PaymentRecipeDto add(PaymentRecipeRegisterDto paymentRecipeRegisterDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public PaymentRecipeDto update(PaymentRecipeUpdateDto paymentRecipeUpdateDto, Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String remove(Long id, HttpServletRequest request) {
        return null;
    }
}
