package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDetailDto {
    private Long id;
    private String parkName;
    private String slotName;
    private String city;
    private String town;
    private String district;
    private Date startDate;
    private Date endDate;
}
