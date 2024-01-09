package com.SelfParkingSystems.MainServer.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SlotRegisterDto {
    @Size(min=8, max=40, message="Otopar adı en az 8 en fazla 40 karakter olmalıdır!")
    private String parkName;
//    @Size(min=3, max=8, message="Alan adı en az 3 en fazla 8 karakter olmalıdır!")
    private List<String> slotNames;
    @Size(min=5, max=20, message="Ödeme tarifesi adı en az 5 en fazla 20 karakter olmalıdır!")
    private String paymentRecipeName;
}
