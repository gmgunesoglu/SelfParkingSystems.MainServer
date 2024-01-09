package com.SelfParkingSystems.MainServer.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordDto {
    private String oldPassword;
    @Size(min=8, max=14, message="Yeni şifre en az 8 en fazla 14 karakter olmalıdır!")
    private String password;
    @Size(min=8, max=14, message="Yeni şifre tekrarı en az 8 en fazla 14 karakter olmalıdır!")
    private String rePassword;
}
