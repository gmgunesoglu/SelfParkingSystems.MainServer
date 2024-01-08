package com.SelfParkingSystems.MainServer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="payment")
@Data
public class Payment {

    @Id
    @SequenceGenerator(
            name = "payment_seq",
            sequenceName = "payment_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "stripe_token",
            nullable = false,
            length = 100
    )
    private String stripeToken;

    @Column(
            name = "amount",
            nullable = false
    )
    private double amount;

    @Column(
            name = "currency",
            nullable = false,
            length = 3
    )
    private String currency;

    @Column(
            name="date",
            nullable = false
    )
    private Date date;

    @OneToOne(targetEntity = Parking.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="parking_id",referencedColumnName = "id")
    private Parking parking;
}