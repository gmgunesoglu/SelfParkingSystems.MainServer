package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDto {
    private String parkName;
    private String slotName;
    private Date parkInDate;
    private Date parkOutDate;
    private double amount;
    private String currency;
    private String stripeToken;

    public ReportDto() {
    }

    public ReportDto(String parkName, String slotName, Date parkInDate, Date parkOutDate, double amount, String currency, String stripeToken) {
        this.parkName = parkName;
        this.slotName = slotName;
        this.parkInDate = parkInDate;
        this.parkOutDate = parkOutDate;
        this.amount = amount;
        this.currency = currency;
        this.stripeToken = stripeToken;
    }
}
