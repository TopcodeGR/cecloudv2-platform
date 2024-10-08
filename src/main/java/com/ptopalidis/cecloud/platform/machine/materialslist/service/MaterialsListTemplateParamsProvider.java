package com.ptopalidis.cecloud.platform.machine.materialslist.service;

import com.ptopalidis.cecloud.platform.machine.machinefile.domain.MachineFileSubType;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.MaterialsList;
import com.ptopalidis.cecloud.platform.pdfgenerator.service.TemplateParamsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MaterialsListTemplateParamsProvider implements TemplateParamsProvider<MaterialsList> {

    @Override
    public Map<String, Object> provideParams(MaterialsList materialsList) {
        return Map.of("customerName","Τοπαλίδης Δημήτριος",
                "materials",materialsList.getMaterials());
    }

    @Override
    public String getTemplateName() {
        return MachineFileSubType.MATERIALS_LIST.toString();
    }
}
