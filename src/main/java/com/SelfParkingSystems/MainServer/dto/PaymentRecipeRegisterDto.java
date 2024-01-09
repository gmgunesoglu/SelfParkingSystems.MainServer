package com.SelfParkingSystems.MainServer.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentRecipeRegisterDto {
    @Size(min=5, max=20, message="Ödeme tarifesi adı en az 5 en fazla 20 karakter olmalıdır!")
    private String name;
    private double hours2;
    private double hours4;
    private double hours6;
    private double hours10;
    private double hours24;
}
