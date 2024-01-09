package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class AccountUpdateDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
