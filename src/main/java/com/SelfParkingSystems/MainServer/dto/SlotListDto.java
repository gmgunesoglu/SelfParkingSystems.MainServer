package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

@Data
public class SlotListDto {
    private Long id;
    private String name;
    private SlotState state;

    public SlotListDto() {
    }

    public SlotListDto(Long id, String name, SlotState state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
