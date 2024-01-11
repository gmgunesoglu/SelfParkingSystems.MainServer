package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.entity.Person;
import com.SelfParkingSystems.MainServer.entity.StaffOfOwner;
import com.SelfParkingSystems.MainServer.repository.StaffOfOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffOfOwnerServiceImpl implements StaffOfOwnerService{

    private final StaffOfOwnerRepository staffOfOwnerRepository;

    @Override
    public Person getOwner(Person staff){
        StaffOfOwner staffOfOwner = staffOfOwnerRepository.findById(staff.getId()).get();
        return staffOfOwner.getOwner();
    }
}
