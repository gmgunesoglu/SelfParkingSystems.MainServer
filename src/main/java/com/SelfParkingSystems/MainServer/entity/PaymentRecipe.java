package com.SelfParkingSystems.MainServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "payment_recipe", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"owner_id", "name"})
})
@Data
public class PaymentRecipe {

    @Id
    @SequenceGenerator(
            name = "payment_recipe_seq",
            sequenceName = "payment_recipe_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_recipe_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "owner_id",
            nullable = false
    )
    private Long ownerId;

    @Column(
            name="name",
            nullable = false,
            length = 20
    )
    private  String name;

    @Column(
            name = "hours_2",
            nullable = false
    )
    private double hours2;

    @Column(
            name = "hours_4",
            nullable = false
    )
    private double hours4;

    @Column(
            name = "hours_6",
            nullable = false
    )
    private double hours6;

    @Column(
            name = "hours_10",
            nullable = false
    )
    private double hours10;

    @Column(
            name = "hours_24",
            nullable = false
    )
    private double hours24;

    @Column(
            name = "enable",
            nullable = false
    )
    private boolean enable;

    @OneToMany(targetEntity = Slot.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="payment_recipe_id",referencedColumnName = "id")
    private List<Slot> slots;
}
