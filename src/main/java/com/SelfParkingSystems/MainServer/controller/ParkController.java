package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.dto.ParkDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkDto;
import com.SelfParkingSystems.MainServer.dto.ParkRegisterDto;
import com.SelfParkingSystems.MainServer.dto.ParkUpdateDto;
import com.SelfParkingSystems.MainServer.service.ParkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/parks")
@RequiredArgsConstructor
public class ParkController {

    private final ParkService parkService;

    @GetMapping
    public List<ParkDto> getAll(HttpServletRequest request){
        return parkService.getAll(request);
    }

    @GetMapping("/{id}")
    public ParkDetailDto get(@PathVariable Long id, HttpServletRequest request){
        return parkService.get(id, request);
    }

    @PostMapping
    public ParkDto add(@RequestBody @Valid ParkRegisterDto parkRegisterDto, HttpServletRequest request){
        return parkService.add(parkRegisterDto, request);
    }

    @PutMapping("/{id}")
    public ParkDto update(@RequestBody ParkUpdateDto parkUpdateDto, @PathVariable Long id, HttpServletRequest request){
        return parkService.update(parkUpdateDto, id, request);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id, HttpServletRequest request){
        return parkService.remove(id, request);
    }
}
