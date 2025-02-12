package com.ptopalidis.cecloud.platform.pdfgenerator.service;

import com.ptopalidis.cecloud.platform.pdfgenerator.domain.ReportingEntity;

import java.util.Map;

public interface TemplateParamsProvider<T extends ReportingEntity> {

    Map<String, Object> provideParams(T data);

    String getTemplateName();
}
