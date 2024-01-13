package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class SlotListDetailDto {
    private Long id;
    private String name;
    private SlotState state;
    private double hours2;
    private double hours4;
    private double hours6;
    private double hours10;
    private double hours24;

    public SlotListDetailDto() {
    }

    public SlotListDetailDto(Long id, String name, SlotState state, double hours2, double hours4, double hours6, double hours10, double hours24) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.hours2 = hours2;
        this.hours4 = hours4;
        this.hours6 = hours6;
        this.hours10 = hours10;
        this.hours24 = hours24;
    }
}
