package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{


    @Override
    public AccountDto get(HttpServletRequest request) {
        return null;
    }

    @Override
    public AccountDto add(AccountRegisterDto accountRegisterDto) {
        return null;
    }

    @Override
    public JwtDto login(LoginDto loginDto) {
        return null;
    }

    @Override
    public String logout(HttpServletRequest request) {
        return null;
    }

    @Override
    public String changePassword(ChangePasswordDto changePasswordDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public AccountDto update(AccountUpdateDto accountUpdateDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public String registerOwner(RiseUserDto riseUserDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public String registerStaff(RiseUserDto riseUserDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public String removeStaff(RiseUserDto riseUserDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public String registerStripe(StripeAccountDto stripeAccountDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public String updateStripe(StripeAccountUpdateDto stripeAccountUpdateDto, HttpServletRequest request) {
        return null;
    }

    @Override
    public String remove(LoginDto loginDto, HttpServletRequest request) {
        return null;
    }
}
