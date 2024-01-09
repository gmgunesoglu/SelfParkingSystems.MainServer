package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
