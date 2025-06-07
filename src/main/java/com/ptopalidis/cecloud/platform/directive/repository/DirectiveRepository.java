package com.ptopalidis.cecloud.platform.directive.repository;

import com.ptopalidis.cecloud.platform.directive.domain.Directive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectiveRepository extends JpaRepository<Directive, Long> {
}
