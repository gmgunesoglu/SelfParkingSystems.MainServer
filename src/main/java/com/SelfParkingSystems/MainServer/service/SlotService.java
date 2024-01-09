package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.SlotDetailDto;
import com.SelfParkingSystems.MainServer.dto.SlotDto;
import com.SelfParkingSystems.MainServer.dto.SlotRegisterDto;
import com.SelfParkingSystems.MainServer.dto.SlotUpdateDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface SlotService {
    List<SlotDto> getAll(HttpServletRequest request);

    SlotDetailDto get(Long id, HttpServletRequest request);

    List<SlotDto> add(SlotRegisterDto slotRegisterDto, HttpServletRequest request);

    SlotDto update(SlotUpdateDto slotUpdateDto, Long id, HttpServletRequest request);

    String remove(Long id, HttpServletRequest request);
}
