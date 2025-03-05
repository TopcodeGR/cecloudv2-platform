package com.ptopalidis.cecloud.platform.machinefile.repository;

import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineFileRepository extends JpaRepository<MachineFile,Long> {

}
