package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class CardDTO {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
}
