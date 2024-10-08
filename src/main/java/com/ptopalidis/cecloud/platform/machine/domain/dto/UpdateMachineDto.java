package com.ptopalidis.cecloud.platform.machine.domain.dto;

import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
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
public class UpdateMachineDto {

    private String name;

    private String type;

    private String serialnumber;

    private String standard;

    private List<MachineCategory> categories = new ArrayList<>();
}
