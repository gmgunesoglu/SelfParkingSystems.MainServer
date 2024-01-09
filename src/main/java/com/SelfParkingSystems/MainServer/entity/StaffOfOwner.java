package com.SelfParkingSystems.MainServer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="staff_of_owner")
@Data
public class StaffOfOwner {

    @Id
    @SequenceGenerator(
            name = "staff_of_owner_seq",
            sequenceName = "staff_of_owner_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staff_of_owner_seq"
    )
    @Column(
            name = "staff_id",
            updatable = false
    )
    private Long staffId;

    @OneToOne(targetEntity = Person.class, cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id",referencedColumnName = "id", nullable = false)
    private Person owner;
}
