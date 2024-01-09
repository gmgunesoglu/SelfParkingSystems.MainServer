package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.ParkDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkDto;
import com.SelfParkingSystems.MainServer.dto.ParkRegisterDto;
import com.SelfParkingSystems.MainServer.dto.ParkUpdateDto;
import com.SelfParkingSystems.MainServer.entity.Person;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkServiceImpl implements ParkService{

    private final AccountService accountService;

    @Override
    public List<ParkDto> getAll(HttpServletRequest request) {
        return null;
    }

    @Override
    public ParkDetailDto get(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ParkDto add(ParkRegisterDto parkRegisterDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public ParkDto update(ParkUpdateDto parkUpdateDto, Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String remove(Long id, HttpServletRequest request) {
        return null;
    }
}
