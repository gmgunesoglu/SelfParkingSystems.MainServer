package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class SlotDetailDto {
    private Long id;
    private String parkName;
    private String slotName;
    private String paymentRecipeName;
    private SlotState state;
    private ReservationerDto reservationer;

    public SlotDetailDto() {
    }

    public SlotDetailDto(Long id, String parkName, String slotName, String paymentRecipeName, SlotState state) {
        this.id = id;
        this.parkName = parkName;
        this.slotName = slotName;
        this.paymentRecipeName = paymentRecipeName;
        this.state = state;
    }
}
