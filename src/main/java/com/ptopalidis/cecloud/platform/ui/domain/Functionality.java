package com.ptopalidis.cecloud.platform.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptopalidis.cecloud.platform.common.security.domain.Authority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "FUNCTIONALITY")
public class Functionality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uiTitle;

    @Column(nullable = false)
    private String uiPath;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FunctionalityCode code;


    @Column(nullable = false)
    @JsonIgnore
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "FUNCTIONALITY_AUTHORITY", joinColumns = @JoinColumn(name = "functionality"), inverseJoinColumns = @JoinColumn(name="authority"))
    private List<Authority> authorities = new ArrayList<>();

}
