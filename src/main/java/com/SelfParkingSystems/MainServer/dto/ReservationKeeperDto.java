package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationKeeperDto {
    private Long reservationId;
    private Long slotId;
    private Date startDate;
    private Date finishDate;

    public ReservationKeeperDto() {
    }

    public ReservationKeeperDto(Long reservationId, Long slotId, Date startDate, Date finishDate) {
        this.reservationId = reservationId;
        this.slotId = slotId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}

