package com.ptopalidis.cecloud.platform.machine.pcd.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatePcdDto {

    private LocalDate productionStartDate;

    private String productionManager;

    private String orderNumber;

    private Integer quantity;

    private LocalDate cuttingProductionStartDate;

    private LocalDate weldingProductionStartDate;

    private LocalDate moddingProductionStartDate;
}
