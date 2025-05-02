package com.ptopalidis.cecloud.platform.materialslist.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMaterialDto {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String sn;

    @NotNull
    private String supplier;
}
