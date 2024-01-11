package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.entity.Person;

public interface StaffOfOwnerService {
    Person getOwner(Person staff);
}
