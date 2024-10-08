package com.ptopalidis.cecloud.platform.machine.doc.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptopalidis.cecloud.platform.common.security.domain.ResourceAuthorizedEntity;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.SerialNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "DOC")
public class Doc  implements ResourceAuthorizedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="issuance_date")
    private LocalDate issuanceDate;

    @Column(nullable = false, name="production_date")
    private LocalDate productionDate;

    @Column(nullable = false, name="production_manager")
    private String productionManager;

    @OneToOne()
    @JoinColumn(name = "serial_number", referencedColumnName = "id", unique = true)
    private SerialNumber serialNumber;

    @JsonIgnore
    @Override
    public Long getUserId() {
        return serialNumber.getMachine().getUserId();
    }
}
