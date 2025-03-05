package com.ptopalidis.cecloud.platform.materialslist.service;

import com.ptopalidis.cecloud.platform.materialslist.domain.MaterialsList;
import com.topcode.pdfgenerator.service.TemplateParamsProvider;
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
        return "MATERIALS_LIST";
    }
}
