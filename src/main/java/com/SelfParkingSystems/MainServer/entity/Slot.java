package com.SelfParkingSystems.MainServer.entity;

import com.SelfParkingSystems.MainServer.dto.SlotState;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "slot", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"park_id", "name"})
})
@Data
public class Slot {

    @Id
    @SequenceGenerator(
            name = "slot_seq",
            sequenceName = "slot_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "slot_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "park_id",
            nullable = false
    )
    private Long parkId;

    @Column(
            name="name",
            nullable = false,
            length = 8
    )
    private  String name;

//    @Column(
//            name = "payment_recipe_id",
//            nullable = false
//    )
//    private Long paymentRecipeId;

    @Column(
            name="state",
            nullable = false,
            length = 8
    )
    @Enumerated(EnumType.STRING)
    private SlotState state;

    @Column(
            name = "enable",
            nullable = false
    )
    private boolean enable;

    @OneToMany(targetEntity = Parking.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="slot_id",referencedColumnName = "id")
    private List<Parking> parkings;

    @OneToMany(targetEntity = Reservation.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="slot_id",referencedColumnName = "id")
    private List<Reservation> reservations;

    @OneToOne(targetEntity = PaymentRecipe.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="payment_recipe_id",referencedColumnName = "id")
    private PaymentRecipe paymentRecipe;
}
