package com.SelfParkingSystems.MainServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "park", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"owner_id", "name"})
})
@Data
public class Park {

    @Id
    @SequenceGenerator(
            name = "park_seq",
            sequenceName = "park_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "park_seq"
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
            length = 40
    )
    private  String name;

    @Column(
            name="secret_key",
            nullable = false,
            length = 64
    )
    private  String secretKey;

    @Column(
            name="base_url",
            nullable = false,
            length = 60,
            unique = true
    )
    private  String baseUrl;

//    @Column(
//            name = "location_id",
//            nullable = false
//    )
//    private Long locationId;

    @Column(
            name="address",
            nullable = false,
            length = 300
    )
    private  String address;

    @Column(
            name = "enable",
            nullable = false
    )
    private boolean enable;

    @OneToMany(targetEntity = Slot.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="park_id",referencedColumnName = "id")
    private List<Slot> slots;

    @OneToOne(targetEntity = Location.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="location_id",referencedColumnName = "id")
    private Location location;
}
