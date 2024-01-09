package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.dto.SlotDetailDto;
import com.SelfParkingSystems.MainServer.dto.SlotDto;
import com.SelfParkingSystems.MainServer.dto.SlotRegisterDto;
import com.SelfParkingSystems.MainServer.dto.SlotUpdateDto;
import com.SelfParkingSystems.MainServer.service.SlotService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/slots")
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @GetMapping
    public List<SlotDto> getAll(HttpServletRequest request){
        return slotService.getAll(request);
    }

    @GetMapping("/{id}")
    public SlotDetailDto get(@PathVariable Long id, HttpServletRequest request){
        return slotService.get(id, request);
    }

    @PostMapping
    public List<SlotDto> add(@RequestBody @Valid SlotRegisterDto slotRegisterDto, HttpServletRequest request){
        return slotService.add(slotRegisterDto, request);
    }

    @PutMapping("/{id}")
    public SlotDto update(@RequestBody SlotUpdateDto slotUpdateDto, @PathVariable Long id, HttpServletRequest request){
        return slotService.update(slotUpdateDto, id, request);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id, HttpServletRequest request){
        return slotService.remove(id, request);
    }

}
