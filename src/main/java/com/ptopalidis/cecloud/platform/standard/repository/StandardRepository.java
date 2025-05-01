package com.ptopalidis.cecloud.platform.standard.repository;

import com.ptopalidis.cecloud.platform.standard.domain.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends JpaRepository<Standard, Long> {
}
