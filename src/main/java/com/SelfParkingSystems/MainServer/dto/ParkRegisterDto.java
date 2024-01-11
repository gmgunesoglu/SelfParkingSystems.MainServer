package com.SelfParkingSystems.MainServer.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParkRegisterDto {
    @Size(min=8, max=40, message="Park adı en az 8 en fazla 40 karakter olmalıdır!")
    private String name;
    @Size(min=32, max=64, message="Gizli anahtar en az 32 en fazla 64 karakter olmalıdır!")
    private String secretKey;
    @Size(min=10, max=60, message="Sunucu adresiniz en az 10 en fazla 60 karakter olmalıdır!")
    private String baseUrl;
    @Size(min=3, max=30, message="İl en az 3 en fazla 30 karakter olmalıdır!")
    private String city;
    @Size(min=3, max=30, message="İlçe en az 3 en fazla 30 karakter olmalıdır!")
    private String town;
    @Size(min=3, max=40, message="Mahalle en az 3 en fazla 40 karakter olmalıdır!")
    private String district;
    @Size(min=20, max=300, message="Adres en az 3 en fazla 300 karakter olmalıdır!")
    private String address;
    @Size(min = 30, max = 120, message = "Rezervasyon süresi en az 30, en fazla 120 dakikadır!")
    private int reservationDuration;
}
