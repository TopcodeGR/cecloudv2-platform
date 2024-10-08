package com.ptopalidis.cecloud.platform.machine.doc.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocDto {


    @NotNull
    private LocalDate issuanceDate;

    @NotNull
    private LocalDate productionDate;

    @NotNull
    private String productionManager;
}
