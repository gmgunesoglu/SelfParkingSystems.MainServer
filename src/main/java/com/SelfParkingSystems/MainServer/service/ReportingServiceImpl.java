package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.ReportCallDto;
import com.SelfParkingSystems.MainServer.dto.ReportDto;
import com.SelfParkingSystems.MainServer.entity.Person;
import com.SelfParkingSystems.MainServer.repository.ParkingRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportingServiceImpl implements ReportingService{

    private final ParkingRepository parkingRepository;
    private final AccountService accountService;

    @Override
    public List<ReportDto> get(ReportCallDto reportCallDto, HttpServletRequest request) {
        Person owner = accountService.getOwner(request);
        List<ReportDto> dtoList = new ArrayList<>();
        for(Long parkId : reportCallDto.getParksId()){
            dtoList.addAll(parkingRepository.getReportDtoListByParkIdAndOwnerIdAndStartDateAndFinishDate(parkId, owner.getId(), reportCallDto.getStartDate(), reportCallDto.getFinishDate()));
        }
        return dtoList;
    }
}
