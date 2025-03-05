package com.ptopalidis.cecloud.platform.doc.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.topcode.pdfgenerator.domain.ReportingEntity;
import com.topcode.web.domain.ResourceAuthorizedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "DOC")
public class Doc extends ReportingEntity implements ResourceAuthorizedEntity {

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
    @JsonIgnore
    private SerialNumber serialNumber;

    @JsonIgnore
    @Override
    public Long getAccountId() {
        return serialNumber.getMachine().getAccountId();
    }
}
