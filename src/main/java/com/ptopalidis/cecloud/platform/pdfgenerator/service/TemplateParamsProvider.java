package com.ptopalidis.cecloud.platform.pdfgenerator.service;

import java.util.Map;

public interface TemplateParamsProvider<T> {

    Map<String, Object> provideParams(T data);

    String getTemplateName();
}
