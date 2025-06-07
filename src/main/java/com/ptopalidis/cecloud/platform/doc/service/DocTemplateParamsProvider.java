package com.ptopalidis.cecloud.platform.doc.service;

import com.ptopalidis.cecloud.platform.doc.domain.Doc;
import com.topcode.pdfgenerator.service.TemplateParamsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DocTemplateParamsProvider implements TemplateParamsProvider<Doc> {

    @Override
    public Map<String, Object> provideParams(Doc doc) {
        return Map.of("doc",doc,
                "serialNumber", doc.getSerialNumber(),
                "machine", doc.getSerialNumber().getMachine(),
                "account", doc.getSerialNumber().getMachine().getAccount());
    }

    @Override
    public String getTemplateName() {
        return "DOC";
    }
}
