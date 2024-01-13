package com.SelfParkingSystems.MainServer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="stripe_account")
@Data
public class StripeAccount{

    @Id
    @SequenceGenerator(
            name = "stripe_account_seq",
            sequenceName = "stripe_account_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stripe_account_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "publishable_key",
            nullable = false,
            unique = true,
            length = 120
    )
    private String publishableKey;

    @Column(
            name = "secret_key",
            nullable = false,
            unique = true,
            length = 120
    )
    private String secretKey;

    @OneToOne(targetEntity = Person.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable = false, referencedColumnName = "id")
    private Person owner;
}














