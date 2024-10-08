package com.ptopalidis.cecloud.platform.machine.machinefile.repository;

import com.ptopalidis.cecloud.platform.machine.machinefile.domain.MachineFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineFileRepository extends JpaRepository<MachineFile,Long> {

}
