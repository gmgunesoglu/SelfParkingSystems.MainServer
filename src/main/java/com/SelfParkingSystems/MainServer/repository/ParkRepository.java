package com.SelfParkingSystems.MainServer.repository;

import com.SelfParkingSystems.MainServer.dto.ParkDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkDto;
import com.SelfParkingSystems.MainServer.dto.ParkListDetailDto;
import com.SelfParkingSystems.MainServer.dto.ParkListDto;
import com.SelfParkingSystems.MainServer.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkRepository extends JpaRepository<Park, Long> {
    Park findByOwnerIdAndName(Long id, String parkName);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkDto" +
            "(p.id, p.name, p.secretKey, p.baseUrl, p.location.city, p.location.town, p.location.district, p.address, p.reservationDuration) " +
            "FROM Park p " +
            "WHERE p.ownerId = :ownerId AND p.enable = true")
    List<ParkDto> getParkDtoListByOwnerId(Long ownerId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkDetailDto" +
            "(p.id, p.name, p.secretKey, p.baseUrl, p.location.city, p.location.town, p.location.district, p.address, p.reservationDuration) " +
            "FROM Park p " +
            "WHERE  p.id = :id AND p.ownerId = :ownerId AND p.enable = true")
    ParkDetailDto getParkDetailDtoByIdAndOwnerId(Long id, Long ownerId);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkListDto" +
            "(p.id, p.name, p.location.city, p.location.town, p.location.district) " +
            "FROM Park p " +
            "WHERE p.enable = true")
    List<ParkListDto> getParkListDtoList();

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkListDto" +
            "(p.id, p.name, p.location.city, p.location.town, p.location.district) " +
            "FROM Park p " +
            "WHERE p.enable = true AND p.location.city = :city")
    List<ParkListDto> getParkListDtoListByCity(String city);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkListDto" +
            "(p.id, p.name, p.location.city, p.location.town, p.location.district) " +
            "FROM Park p " +
            "WHERE p.enable = true AND p.location.city = :city AND p.location.town = :town")
    List<ParkListDto> getParkListDtoListByCityAndTown(String city, String town);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkListDto" +
            "(p.id, p.name, p.location.city, p.location.town, p.location.district) " +
            "FROM Park p " +
            "WHERE p.enable = true AND p.location.city = :city AND p.location.town = :town AND p.location.district = :district")
    List<ParkListDto> getParkListDtoListByCityAndTownAndDistrict(String city, String town, String district);

    @Query("SELECT new com.SelfParkingSystems.MainServer.dto.ParkListDetailDto" +
            "(p.id, p.name, p.location.city, p.location.town, p.location.district, p.address) " +
            "FROM Park p " +
            "WHERE p.enable = true AND p.id = :parkId")
    ParkListDetailDto getParkListDetailDtoByParkId(Long parkId);
}
