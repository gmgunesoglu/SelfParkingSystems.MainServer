package com.SelfParkingSystems.MainServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="person")
@Data
public class Person {

    @Id
    @SequenceGenerator(
            name = "person_seq",
            sequenceName = "person_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name="user_name",
            nullable = false,
            length = 20,
            unique = true
    )
    private  String userName;

    @Column(
            name="password",
            nullable = false,
            length = 60
    )
    private  String password;

    @Column(
            name="first_name",
            nullable = false,
            length = 30
    )
    private  String firstName;

    @Column(
            name="last_name",
            nullable = false,
            length = 20
    )
    private  String lastName;

    @Column(
            name = "authority",
            nullable = false,
            length = 5
    )
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(
            name="phone_number",
            nullable = false,
            length = 15
    )
    private String phoneNumber;

    @Column(
            name="email",
            nullable = false,
            length = 30,
            unique = true
    )
    private String email;

    @Column(
            name = "block_time"
    )
    private Date blockTime;

    @Column(
            name = "enable",
            nullable = false
    )
    private boolean enable;

    @OneToMany(targetEntity = Park.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id",referencedColumnName = "id")
    private List<Park> parks;

    @OneToMany(targetEntity = PaymentRecipe.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id",referencedColumnName = "id")
    private List<PaymentRecipe> paymentRecipes;

    @OneToMany(targetEntity = Reservation.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private List<Reservation> reservations;
}
