package com.ptopalidis.cecloud.platform.category.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateMachineCategoryDto {

    @NotBlank(message = "Machine category name should not be blank")
    private String name;

    @Override
    public String toString() {
        return "CreateMachineCategoryDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
