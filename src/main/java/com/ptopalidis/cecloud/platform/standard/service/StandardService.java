package com.ptopalidis.cecloud.platform.standard.service;


import com.ptopalidis.cecloud.platform.standard.domain.Standard;
import com.ptopalidis.cecloud.platform.standard.repository.StandardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StandardService {

    private final StandardRepository standardRepository;

    public List<Standard> findAllStandards() {
        return standardRepository.findAll();
    }
}
