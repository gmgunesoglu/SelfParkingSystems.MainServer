package com.SelfParkingSystems.MainServer.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StripeAccountDto {
    @Size(min=100, max=120, message="Yayınlanabilir anahtar en az 100 en fazla 120 karakter olmalıdır!")
    private String publishableKey;
    @Size(min=100, max=120, message="Gizli anahtar en az 100 en fazla 120 karakter olmalıdır!")
    private String secretKey;
}
