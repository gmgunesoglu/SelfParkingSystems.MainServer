package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.SlotDetailDto;
import com.SelfParkingSystems.MainServer.dto.SlotDto;
import com.SelfParkingSystems.MainServer.dto.SlotRegisterDto;
import com.SelfParkingSystems.MainServer.dto.SlotUpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotServiceImpl implements SlotService{
    @Override
    public List<SlotDto> getAll(HttpServletRequest request) {
        return null;
    }

    @Override
    public SlotDetailDto get(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public List<SlotDto> add(SlotRegisterDto slotRegisterDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public SlotDto update(SlotUpdateDto slotUpdateDto, Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String remove(Long id, HttpServletRequest request) {
        return null;
    }
}
