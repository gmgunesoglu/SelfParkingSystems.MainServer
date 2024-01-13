package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class StaffInfoDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public StaffInfoDto() {
    }

    public StaffInfoDto(Long id, String userName, String firstName, String lastName, String phoneNumber, String email) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
