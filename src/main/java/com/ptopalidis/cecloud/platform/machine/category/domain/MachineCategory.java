package com.ptopalidis.cecloud.platform.machine.category.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "MACHINE_CATEGORIES")
public class MachineCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true, nullable = false)
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
