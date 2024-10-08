package com.ptopalidis.cecloud.platform.machine.materialslist.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateMaterialListDto {

    @NotNull
    private LocalDate issuanceDate;

    @NotNull
    private LocalDate productionDate;

    @NotNull
    private String productionManager;

    @Valid
    List<CreateMaterialDto> materials = new ArrayList<>();
}
