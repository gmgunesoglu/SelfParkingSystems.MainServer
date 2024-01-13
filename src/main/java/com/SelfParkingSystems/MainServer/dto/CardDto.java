package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class CardDto {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
}
