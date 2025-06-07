package com.ptopalidis.cecloud.platform.directive.service;


import com.ptopalidis.cecloud.platform.directive.domain.Directive;
import com.ptopalidis.cecloud.platform.directive.repository.DirectiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DirectiveService {

    private final DirectiveRepository directiveRepository;

    public List<Directive> findAllDirectives() {
        return directiveRepository.findAll();
    }
}
