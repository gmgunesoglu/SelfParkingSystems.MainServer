package com.SelfParkingSystems.MainServer.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountRegisterDto {

    @Size(min=8, max=14, message="Kullanıcı adı en az 8 en fazla 14 karakter olmalıdır!")
    private String userName;
    @Size(min=8, max=14, message="Şifre en az 8 en fazla 14 karakter olmalıdır!")
    private String password;
    @Size(min=8, max=14, message="Şifre tekrarı en az 8 en fazla 14 karakter olmalıdır!")
    private String rePassword;
    @Size(min=3, max=30, message="İsminiz en az 3 en fazla 30 karakter olmalıdır!")
    private String firstName;
    @Size(min=3, max=20, message="Soy isminiz en az 3 en fazla 20 karakter olmalıdır!")
    private String lastName;
    @Size(min=10, max=15, message="Telefon numaranız en az 10 en fazla 15 karakter olmalıdır!")
    private String phoneNumber;
    @Size(min=8, max=50, message="Mail adresiniz en az 8 en fazla 50 karakter olmalıdır!")
    private String email;
}
