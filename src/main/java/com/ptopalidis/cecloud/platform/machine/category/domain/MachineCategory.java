package com.ptopalidis.cecloud.platform.machine.category.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MACHINE_CATEGORY")
@Getter
@Setter
public class MachineCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true, nullable = false)
    private String name;


}
