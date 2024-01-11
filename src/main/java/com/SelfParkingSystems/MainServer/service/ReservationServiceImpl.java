package com.SelfParkingSystems.MainServer.service;

import com.SelfParkingSystems.MainServer.dto.*;
import com.SelfParkingSystems.MainServer.entity.Parking;
import com.SelfParkingSystems.MainServer.entity.Person;
import com.SelfParkingSystems.MainServer.entity.Reservation;
import com.SelfParkingSystems.MainServer.entity.Slot;
import com.SelfParkingSystems.MainServer.exceptionhandling.GlobalRuntimeException;
import com.SelfParkingSystems.MainServer.repository.ParkingRepository;
import com.SelfParkingSystems.MainServer.repository.PersonRepository;
import com.SelfParkingSystems.MainServer.repository.ReservationRepository;
import com.SelfParkingSystems.MainServer.repository.SlotRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;
    private final SlotRepository slotRepository;
    private final ParkingRepository parkingRepository;

    private final AccountService accountService;
    private Map<Integer, ArrayList<ReservationKeeperDto>> reservationsQueues = new HashMap<>();
    private Timer timer = new Timer();  // thread oluşturmak için...

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            checkReservations();
            System.out.println("[+] Otopark rezervasyonları kontrol edildi.");
        }
    };

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, AccountService accountService, SlotRepository slotRepository, ParkingRepository parkingRepository, PersonRepository personRepository) {
        this.accountService = accountService;
        this.reservationRepository = reservationRepository;
        this.slotRepository = slotRepository;
        this.parkingRepository = parkingRepository;
        this.personRepository = personRepository;
        timer.schedule(task, 10000, 10000);
        for(int i = 30; i <= 120; i += 5){
            reservationsQueues.put(i, new ArrayList<>());
        }
    }

    @Override
    public List<ReservationDto> getAll(HttpServletRequest request) {
        Person user = accountService.getPerson(request);
        return reservationRepository.getReservationDtoListByUserId(user.getId());
    }

    @Override
    public ReservationDetailDto get(Long id, HttpServletRequest request) {
        Person user = accountService.getPerson(request);
        ReservationDetailDto reservationDetailDto = reservationRepository.getReservationDetailDtoByUserId(user.getId(), id);
        if(reservationDetailDto == null){
            throw new GlobalRuntimeException("Rezercasyon bulunamadı!", HttpStatus.BAD_REQUEST);
        }
        int reservationDuration = reservationRepository.getReservationDurationByReservationId(id);
        reservationDetailDto.setEndDate(new Date(reservationDetailDto.getStartDate().getTime() + reservationDuration));
        return reservationDetailDto;
    }

    @Override
    public ReservationDto add(ReservationRegisterDto reservationRegisterDto, HttpServletRequest request) {
        Person user = accountService.getPerson(request);
        Reservation reservation = new Reservation();
        reservation.setUserId(user.getId());
        reservation.setSlotId(reservationRegisterDto.getSlotId());
        reservation.setDate(new Date(System.currentTimeMillis()));
        reservationRepository.save(reservation);
        String parkName = reservationRepository.getParkNameBySlotId(reservationRegisterDto.getSlotId());
        int reservationDuration = reservationRepository.getReservationDurationByReservationId(reservation.getId());
        ReservationKeeperDto reservationKeeperDto = new ReservationKeeperDto(
                reservation.getId(),
                reservation.getSlotId(),
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()+reservationDuration * 60000)
        );
        ArrayList<ReservationKeeperDto> reservationList = reservationsQueues.get(reservationDuration);
        reservationList.add(reservationKeeperDto);
        return new ReservationDto(
                reservation.getId(),
                parkName,
                reservation.getDate()
        );
    }

    private void checkReservations(){
        for (ArrayList<ReservationKeeperDto> list : reservationsQueues.values()) {
            if(list.get(0).getFinishDate().before(new Date(System.currentTimeMillis()))){
                clearList(list);
            }
        }
    }

    private void clearList(ArrayList<ReservationKeeperDto> list){
        ReservationKeeperDto dto = list.get(0);
        Slot slot = slotRepository.findById(dto.getSlotId()).get();
        if(slot.getState() == SlotState.RESERVED){
            // eğer rezervasyon süresi boyunca bir parketme olayı
            // gerçekleşmedi ise bu rezervasyon yerine getirlmemiştir
            // ve kullanıcı cezalandırılmalıdır
            List<Parking> parkings = parkingRepository.getBySlotIdAndDateAfter(dto.getSlotId(), dto.getStartDate());
            if(parkings == null || parkings.isEmpty()){
                System.out.println("[+] Bir rezervasyon iptal edildi:");
                System.out.println(dto);
                slot.setState(SlotState.FREE);
                slotRepository.save(slot);
                Reservation reservation = reservationRepository.findById(dto.getReservationId()).get();
                Person user = personRepository.findById(reservation.getUserId()).get();
                // 24 saat cezalı olacak
                user.setBlockDate(new Date(System.currentTimeMillis()+86400000));
                personRepository.save(user);
            }else{
                System.out.println("[+] Bir rezervasyon yerine getirilmiş ve araç ayrılmış:");
                System.out.println(dto);
            }
        }else{
            System.out.println("[+] Bir rezervasyon yerine getirilmiş:");
            System.out.println(dto);
        }
        list.remove(0);
        if(list.get(0).getFinishDate().before(new Date(System.currentTimeMillis()))){
            clearList(list);
        }
    }
}
