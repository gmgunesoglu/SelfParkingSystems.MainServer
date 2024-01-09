package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String userName;
    private String password;
    private String rePassword;
}
