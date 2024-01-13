package com.SelfParkingSystems.MainServer.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReportCallDto {

    private Date startDate;
    private Date finishDate;
    private List<Long> parksId;
}
