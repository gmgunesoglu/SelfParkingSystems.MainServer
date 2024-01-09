package com.SelfParkingSystems.MainServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="parking")
@Data
public class Parking {

    @Id
    @SequenceGenerator(
            name = "parking_seq",
            sequenceName = "parking_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parking_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name="vehicle_plate",
            nullable = false,
            length = 12
    )
    private  String vehiclePlate;

    @Column(
            name = "slot_id",
            nullable = false
    )
    private Long slotId;

    @Column(
            name="date",
            nullable = false
    )
    private Date date;
}
