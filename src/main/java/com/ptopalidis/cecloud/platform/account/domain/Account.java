package com.ptopalidis.cecloud.platform.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptopalidis.cecloud.platform.common.security.domain.Authority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userid;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String businessname;

    @Column(nullable = false)
    private String country;


    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressnumber;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String afm;

    @Column(nullable = false)
    private String doi;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String signature;
    @Column()
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "ACCOUNT_AUTHORITY", joinColumns = @JoinColumn(name = "account"), inverseJoinColumns = @JoinColumn(name="authority"))
    private List<Authority> authorities = new ArrayList<>();

}
