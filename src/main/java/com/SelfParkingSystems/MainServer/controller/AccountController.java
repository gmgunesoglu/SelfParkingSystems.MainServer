package com.SelfParkingSystems.MainServer.controller;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping
    public AccountDto get(HttpServletRequest request){
        return accountService.get(request);
    }

    @PostMapping("/register")
    public AccountDto add(@RequestBody @Valid AccountRegisterDto accountRegisterDto){
        return accountService.add(accountRegisterDto);
    }

    @PostMapping("/login")
    public JwtDto login(@RequestBody LoginDto loginDto){
        return accountService.login(loginDto);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        return accountService.logout(request);
    }

    @GetMapping("/staffs")
    public List<StaffInfoDto> getStaffs(HttpServletRequest request){
        return accountService.getStaffs(request);
    }

    @PutMapping("/change-password")
    public String changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto, HttpServletRequest request) {
        return accountService.changePassword(changePasswordDto, request);
    }

    @PutMapping
    public AccountDto update(@RequestBody AccountUpdateDto accountUpdateDto, HttpServletRequest request){
        return accountService.update(accountUpdateDto, request);
    }

    @PostMapping("/rise/owner")
    public String registerOwner(@RequestBody RiseUserDto riseUserDto, HttpServletRequest request){
        return accountService.registerOwner(riseUserDto, request);
    }

    @PostMapping("/rise/staff")
    public String registerStaff(@RequestBody RiseUserDto riseUserDto, HttpServletRequest request){
        return accountService.registerStaff(riseUserDto, request);
    }

    @PostMapping("/reduce/staff")
    public String removeStaff(@RequestBody RiseUserDto riseUserDto, HttpServletRequest request){
        return accountService.removeStaff(riseUserDto, request);
    }

    @PostMapping("/stripe")
    public String registerStripe(@RequestBody @Valid StripeAccountDto stripeAccountDto, HttpServletRequest request){
        return accountService.registerStripe(stripeAccountDto, request);
    }

    @PutMapping("/stripe")
    public String updateStripe(@RequestBody StripeAccountUpdateDto stripeAccountUpdateDto, HttpServletRequest request){
        return accountService.updateStripe(stripeAccountUpdateDto, request);
    }

    @DeleteMapping
    public String remove(@RequestBody LoginDto loginDto, HttpServletRequest request){
        return accountService.remove(loginDto, request);
    }
}
