package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.Person;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {
    AccountDto get(HttpServletRequest request);

    AccountDto add(AccountRegisterDto accountRegisterDto);

    JwtDto login(LoginDto loginDto);

    String logout(HttpServletRequest request);

    String changePassword(ChangePasswordDto changePasswordDto, HttpServletRequest request);

    AccountDto update(AccountUpdateDto accountUpdateDto, HttpServletRequest request);

    String registerOwner(RiseUserDto riseUserDto, HttpServletRequest request);

    String registerStaff(RiseUserDto riseUserDto, HttpServletRequest request);

    String removeStaff(RiseUserDto riseUserDto, HttpServletRequest request);

    String registerStripe(StripeAccountDto stripeAccountDto, HttpServletRequest request);

    String updateStripe(StripeAccountUpdateDto stripeAccountUpdateDto, HttpServletRequest request);

    String remove(LoginDto loginDto, HttpServletRequest request);

    Person getPerson(HttpServletRequest request);

    Person getOwner(HttpServletRequest request);
}
