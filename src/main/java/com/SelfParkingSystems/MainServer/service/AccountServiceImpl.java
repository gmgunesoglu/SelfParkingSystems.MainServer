package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.*;
import com.SelfParkingSystems.MainServer.exceptionhandling.GlobalRuntimeException;
import com.SelfParkingSystems.MainServer.repository.PaymentRecipeRepository;
import com.SelfParkingSystems.MainServer.repository.PersonRepository;
import com.SelfParkingSystems.MainServer.repository.StaffOfOwnerRepository;
import com.SelfParkingSystems.MainServer.repository.StripeAccountRepository;
import com.SelfParkingSystems.MainServer.security.SessionControl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final PersonRepository personRepository;
    private final PaymentRecipeRepository paymentRecipeRepository;
    private final StaffOfOwnerRepository staffOfOwnerRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final SessionControl sessionControl;
    private final StripeAccountRepository stripeAccountRepository;
    private final StaffOfOwnerService staffOfOwnerService;


    @Override
    public AccountDto get(HttpServletRequest request) {
        Person person = getPerson(request);
        return personToAccountDto(person);
    }

    @Override
    public AccountDto add(AccountRegisterDto accountRegisterDto) {
        if(!accountRegisterDto.getPassword().equals(accountRegisterDto.getRePassword())){
            throw new GlobalRuntimeException("Şire tekrarı hatalı!",HttpStatus.BAD_REQUEST);
        }
        String password = passwordEncoder.encode(accountRegisterDto.getPassword());
        Person person = new Person();
        person.setUserName(accountRegisterDto.getUserName());
        person.setPassword(password);
        person.setFirstName(accountRegisterDto.getFirstName());
        person.setLastName(accountRegisterDto.getLastName());
        person.setAuthority(Authority.USER);
        person.setPhoneNumber(accountRegisterDto.getPhoneNumber());
        person.setEmail(accountRegisterDto.getEmail());
        person.setBlockDate(new Date(0));
        person.setEnable(true);
        Person newPerson = personRepository.save(person);
        return personToAccountDto(newPerson);
    }

    @Override
    public JwtDto login(LoginDto loginDto) {
        Person person = personRepository.findByUserName(loginDto.getUserName());
        if(person == null || !passwordEncoder.matches(loginDto.getPassword(), person.getPassword()) || !person.isEnable()){
            throw new GlobalRuntimeException("Kullanıcı bulunamadı", HttpStatus.BAD_REQUEST);
        }
        String token = jwtService.generateJwt(person.getId()+"", person.getUserName());
        JwtDto jwt = new JwtDto();
        jwt.setJwt(token);
        sessionControl.addSession(person.getUserName(), person.getAuthority(), token);
        return jwt;
    }

    @Override
    public String logout(HttpServletRequest request) {
        sessionControl.removeSession(request);
        return "Çıkış başarılı.";
    }

    @Override
    public List<StaffInfoDto> getStaffs(HttpServletRequest request) {
        // sadece owner lar erişebilecek
        Person owner = getPerson(request);
        return personRepository.getStaffInfoDtoListByOwnerId(owner.getId());
    }

    @Override
    public String changePassword(ChangePasswordDto changePasswordDto, HttpServletRequest request) {
        if(!changePasswordDto.getRePassword().equals(changePasswordDto.getPassword())){
            throw new GlobalRuntimeException("Şire tekrarı hatalı!",HttpStatus.BAD_REQUEST);
        }
        Person person = getPerson(request);
        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(), person.getPassword())){
            throw new GlobalRuntimeException("Eski şifre hatalı!",HttpStatus.BAD_REQUEST);
        }
        person.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        personRepository.save(person);
        sessionControl.removeSession(request);
        return "Şifreniz değiştirildi, tekrar giriş yapınız.";
    }

    @Override
    public AccountDto update(AccountUpdateDto accountUpdateDto, HttpServletRequest request) {
        Person person = getPerson(request);
        if(accountUpdateDto.getEmail() != null && !accountUpdateDto.getEmail().equals("")){
            if(accountUpdateDto.getEmail().length()>50 || accountUpdateDto.getEmail().length()<8){
                throw new GlobalRuntimeException("Email en fazla 50, en az 8 karakter olabilir!", HttpStatus.BAD_REQUEST);
            }else {
                person.setEmail(accountUpdateDto.getEmail());
            }
        }
        if(accountUpdateDto.getLastName() != null && !accountUpdateDto.getLastName().equals("")){
            if(accountUpdateDto.getLastName().length()>20 || accountUpdateDto.getLastName().length()<3){
                throw new GlobalRuntimeException("Soyisim en fazla 20, en az 3 karakter olabilir!", HttpStatus.BAD_REQUEST);
            }else {
                person.setLastName(accountUpdateDto.getLastName());
            }
        }
        if(accountUpdateDto.getFirstName() != null && !accountUpdateDto.getFirstName().equals("")){
            if(accountUpdateDto.getFirstName().length()>30 || accountUpdateDto.getFirstName().length()<3){
                throw new GlobalRuntimeException("İsim en fazla 30, en az 3 karakter olabilir!", HttpStatus.BAD_REQUEST);
            }else {
                person.setFirstName(accountUpdateDto.getFirstName());
            }
        }
        if(accountUpdateDto.getPhoneNumber() != null && !accountUpdateDto.getPhoneNumber().equals("")){
            if(accountUpdateDto.getPhoneNumber().length()>30 || accountUpdateDto.getPhoneNumber().length()<3){
                throw new GlobalRuntimeException("Telefon numarası en fazla 30, en az 3 karakter olabilir!", HttpStatus.BAD_REQUEST);
            }else {
                person.setPhoneNumber(accountUpdateDto.getPhoneNumber());
            }
        }
        personRepository.save(person);
        return personToAccountDto(person);
    }

    @Override
    public String registerOwner(RiseUserDto riseUserDto, HttpServletRequest request) {
        // bu istek sadece admin yetkisinde çalışacak, istek sihibini kontrole gerek yok
        Person user = personRepository.findByUserName(riseUserDto.getUserName());
        if(user == null || !user.isEnable()){
            throw new GlobalRuntimeException("Kullanıcı bulunamadı: " + riseUserDto.getUserName(), HttpStatus.BAD_REQUEST);
        }
        if (user.getStripeAccount() == null){
            throw new GlobalRuntimeException("Kullanıcı bir stripe hesabı oluşturmalı!", HttpStatus.BAD_REQUEST);
        }
        if(user.getAuthority() != Authority.USER){
            throw new GlobalRuntimeException("Kullanıcının yetkisi: " + user.getAuthority().toString(), HttpStatus.BAD_REQUEST);
        }
        user.setAuthority(Authority.OWNER);
        personRepository.save(user);
        return riseUserDto.getUserName() + " owner yetkisine yükseltildi.";
    }

    @Override
    public String registerStaff(RiseUserDto riseUserDto, HttpServletRequest request) {
        // bu istek sadece owner yetkisinde çalışacak, istek sihibini kontrole gerek yok
        Person staff = personRepository.findByUserName(riseUserDto.getUserName());
        if(staff == null || !staff.isEnable()){
            throw new GlobalRuntimeException("Kullanıcı bulunamadı: " + riseUserDto.getUserName(), HttpStatus.BAD_REQUEST);
        }
        if(staff.getAuthority() != Authority.USER){
            throw new GlobalRuntimeException("Kullanıcının yetkisi: " + staff.getAuthority().toString(), HttpStatus.BAD_REQUEST);
        }
        Person owner = getPerson(request);
        StaffOfOwner staffOfOwner = new StaffOfOwner();
        staffOfOwner.setOwner(owner);
        staffOfOwner.setStaffId(staff.getId());
        staffOfOwnerRepository.save(staffOfOwner);
        staff.setAuthority(Authority.STAFF);
        personRepository.save(staff);
        return riseUserDto.getUserName() + " staff yetkisine yükseltildi.";
    }

    @Override
    public String removeStaff(RiseUserDto riseUserDto, HttpServletRequest request) {
        // bu istek sadece owner yetkisinde çalışacak, istek sihibini kontrole gerek yok
        Person staff = personRepository.findByUserName(riseUserDto.getUserName());
        if(staff == null){
            throw new GlobalRuntimeException("Size ait bir personel bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        Person owner = getPerson(request);
        StaffOfOwner staffOfOwner = staffOfOwnerRepository.findByStaffIdAndOwnerId(staff.getId(), owner.getId());
        if(staffOfOwner == null){
            throw new GlobalRuntimeException("Size ait bir personel bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        staffOfOwnerRepository.delete(staffOfOwner);
        staff.setAuthority(Authority.USER);
        personRepository.save(staff);
        return riseUserDto.getUserName() + " personel silindi.";
    }

    @Override
    public String registerStripe(StripeAccountDto stripeAccountDto, HttpServletRequest request) {
        Person person = getPerson(request);
        if(person.getStripeAccount()!=null){
            throw new GlobalRuntimeException("Zaten bir stripe hesabınız mevcut!", HttpStatus.BAD_REQUEST);
        }
        StripeAccount stripeAccount = new StripeAccount();
        stripeAccount.setOwner(person);
        stripeAccount.setPublishableKey(stripeAccountDto.getPublishableKey());
        stripeAccount.setSecretKey(stripeAccountDto.getSecretKey());
        stripeAccountRepository.save(stripeAccount);
        return "Stripe hesabınız oluşturuldu.";
    }

    @Override
    public String updateStripe(StripeAccountUpdateDto stripeAccountUpdateDto, HttpServletRequest request) {
        Person person = getPerson(request);
        StripeAccount stripeAccount = person.getStripeAccount();
        if(stripeAccount==null){
            throw new GlobalRuntimeException("Bir stripe hesabınız bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        if(stripeAccountUpdateDto.getPublishableKey() != null && !stripeAccountUpdateDto.getPublishableKey().equals("")){
            if(stripeAccountUpdateDto.getPublishableKey().length()<100 || stripeAccountUpdateDto.getPublishableKey().length()>120){
                throw new GlobalRuntimeException("Yayınlanabilir anahtar en az 100 en fazla 120 karakter olmalıdır!", HttpStatus.BAD_REQUEST);
            }
            stripeAccount.setPublishableKey(stripeAccountUpdateDto.getPublishableKey());
        }
        if(stripeAccountUpdateDto.getSecretKey() != null && !stripeAccountUpdateDto.getSecretKey().equals("")){
            if(stripeAccountUpdateDto.getSecretKey().length()<100 || stripeAccountUpdateDto.getSecretKey().length()>120){
                throw new GlobalRuntimeException("Gizli anahtar en az 100 en fazla 120 karakter olmalıdır!", HttpStatus.BAD_REQUEST);
            }
            stripeAccount.setSecretKey(stripeAccountUpdateDto.getSecretKey());
        }
        stripeAccountRepository.save(stripeAccount);
        return "Stripe hesap bilgileriniz güncellendi.";
    }

    @Override
    public String remove(LoginDto loginDto, HttpServletRequest request) {
        Person person = getPerson(request);
        if(person.getAuthority()==Authority.ADMIN){
            throw new GlobalRuntimeException("Admin hesabı silinemez!", HttpStatus.BAD_REQUEST);
        }
        if(person.getAuthority()==Authority.OWNER){
//             otoparklarını silmesi gerekir
            if(person.getParks() != null){
                for(Park park : person.getParks()){
                    if(park.isEnable()){
                        throw new GlobalRuntimeException("İlk önce otoparklarınızı silmelisiniz!", HttpStatus.BAD_REQUEST);
                    }
                }
            }
//            payment recipeler disable olacak
            for(PaymentRecipe recipe : person.getPaymentRecipes()){
                recipe.setEnable(false);
                paymentRecipeRepository.save(recipe);
            }
//            stripe hesabı silinecek
            StripeAccount stripeAccount = person.getStripeAccount();
            stripeAccountRepository.delete(stripeAccount);
//            staff ları user olacak
            for(StaffOfOwner staffOfOwner : person.getStaffsOfOwner()){
                Person staff = personRepository.findById(staffOfOwner.getStaffId()).get();
                staff.setAuthority(Authority.USER);
                personRepository.save(staff);
                staffOfOwnerRepository.delete(staffOfOwner);
            }
        }
        person.setEnable(false);
        personRepository.save(person);
        sessionControl.removeSession(request);
        return "Hesabınız silinmiştir.";
    }

    @Override
    public Person getPerson(HttpServletRequest request){
        String userName = jwtService.getUsername(request);
        return personRepository.findByUserName(userName);
    }

    @Override
    public Person getOwner(HttpServletRequest request){
        String userName = jwtService.getUsername(request);
        Person owner = personRepository.findByUserName(userName);
        if(owner.getAuthority() == Authority.STAFF){
            owner = staffOfOwnerService.getOwner(owner);
        }
        return owner;
    }

    @Override
    public Long getPersonId(HttpServletRequest request){
        return jwtService.getId(request);
    }

    private AccountDto personToAccountDto(Person person){
        AccountDto dto = new AccountDto();
        dto.setId(person.getId());
        dto.setUserName(person.getUserName());
        dto.setPassword(person.getPassword());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setPhoneNumber(person.getPhoneNumber());
        dto.setEmail(person.getEmail());
        return dto;
    }
}
