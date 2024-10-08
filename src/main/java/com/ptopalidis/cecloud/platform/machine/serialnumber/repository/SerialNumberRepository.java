package com.ptopalidis.cecloud.platform.machine.serialnumber.repository;

import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.SerialNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerialNumberRepository extends JpaRepository<SerialNumber,Long> {
}

