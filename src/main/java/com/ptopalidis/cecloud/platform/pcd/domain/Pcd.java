package com.ptopalidis.cecloud.platform.pcd.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.topcode.web.domain.ResourceAuthorizedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PCD")
public class Pcd implements ResourceAuthorizedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name="production_start_date")
    private LocalDate productionStartDate;

    @NotNull
    @Column(nullable = false, name="production_manager")
    private String productionManager;

    @NotNull
    @Column(nullable = false, name="order_number")
    private String orderNumber;

    @Min(0)
    @Column(nullable = false, name="quantity")
    private Integer quantity;

    @NotNull
    @Column(nullable = false, name="cutting_production_start_date")
    private LocalDate cuttingProductionStartDate;

    @NotNull
    @Column(nullable = false, name="welding_production_start_date")
    private LocalDate weldingProductionStartDate;

    @NotNull
    @Column(nullable = false, name="modding_production_start_date")
    private LocalDate moddingProductionStartDate;

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
