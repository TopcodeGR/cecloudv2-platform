package com.ptopalidis.cecloud.platform.machine.doc.service;

import com.ptopalidis.cecloud.platform.machine.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.pdfgenerator.service.TemplateParamsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DocTemplateParamsProvider implements TemplateParamsProvider<Doc> {

    @Override
    public Map<String, Object> provideParams(Doc doc) {
        return Map.of("productionManager",doc.getProductionManager());
    }

    @Override
    public String getTemplateName() {
        return "DOC";
    }
}
