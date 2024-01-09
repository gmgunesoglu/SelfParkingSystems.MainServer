package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class StripeAccountUpdateDto {
    private String publishableKey;
    private String secretKey;
}
