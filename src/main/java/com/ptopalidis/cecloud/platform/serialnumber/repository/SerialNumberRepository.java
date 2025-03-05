package com.ptopalidis.cecloud.platform.serialnumber.repository;

import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerialNumberRepository extends JpaRepository<SerialNumber,Long> {
}

