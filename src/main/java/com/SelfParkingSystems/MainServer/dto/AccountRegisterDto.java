package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class AccountRegisterDto {
    private String userName;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
