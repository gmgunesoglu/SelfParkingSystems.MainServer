package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class StripeAccountDto {
    private String publishableKey;
    private String secretKey;
}
