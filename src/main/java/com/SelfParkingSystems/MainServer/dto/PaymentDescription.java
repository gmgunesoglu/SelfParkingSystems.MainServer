package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDescription {
    private Long parkId;
    private Long slotId;
    private Long parkingId;
    private Date parkIndDate;
    private Date parkOutDate;
    private double amount;
    private String currency;

    public PaymentDescription() {
    }

    public PaymentDescription(Long parkId, Long slotId, Long parkingId, Date parkIndDate, Date parkOutDate, double amount, String currency) {
        this.parkId = parkId;
        this.slotId = slotId;
        this.parkingId = parkingId;
        this.parkIndDate = parkIndDate;
        this.parkOutDate = parkOutDate;
        this.amount = amount;
        this.currency = currency;
    }
}
