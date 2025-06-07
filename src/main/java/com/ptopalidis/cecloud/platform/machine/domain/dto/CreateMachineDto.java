package com.ptopalidis.cecloud.platform.machine.domain.dto;

import com.ptopalidis.cecloud.platform.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.directive.domain.Directive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMachineDto {

    private String name;

    private String type;

    private String serialnumber;

    private String standard;

    private Directive directive;

    private List<MachineCategory> categories = new ArrayList<>();
}
