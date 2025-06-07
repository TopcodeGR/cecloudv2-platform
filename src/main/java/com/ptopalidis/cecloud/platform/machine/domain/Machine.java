package com.ptopalidis.cecloud.platform.machine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ptopalidis.cecloud.platform.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFile;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.directive.domain.Directive;
import com.topcode.web.domain.Account;
import com.topcode.web.domain.ResourceAuthorizedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MACHINE")
public class Machine implements ResourceAuthorizedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account", referencedColumnName = "id")
    private Account account;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String serialnumber;

    @Column(nullable = false)
    private String standard;


    @ManyToOne()
    @JoinColumn(name = "directive", referencedColumnName = "id", unique = true)
    private Directive directive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MACHINE_MACHINE_CATEGORY", joinColumns = @JoinColumn(name = "machine"), inverseJoinColumns = @JoinColumn(name = "category"))
    private List<MachineCategory> categories = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "machine", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MachineFile> files_ = new ArrayList<>();

    @JsonIgnoreProperties("machine")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "machine", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SerialNumber> serialNumbers = new ArrayList<>();

    @Transient
    @JsonIgnoreProperties("machine")
    private Map<String, Map<String,List<MachineFile>>> files;

    @PostLoad
    private void postLoad() {
        if (files_ != null) {
            files = files_.stream()
                    .collect(Collectors.groupingBy(machineFile -> machineFile.getSubType().getFileType().toString()))
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().collect(Collectors.groupingBy(m->m.getSubType().toString()))));
        }

    }

    @JsonIgnore
    @Override
    public Long getAccountId() {
        return account.getId();
    }
}
