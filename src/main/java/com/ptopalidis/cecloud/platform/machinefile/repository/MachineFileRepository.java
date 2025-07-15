package com.ptopalidis.cecloud.platform.machinefile.repository;

import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFile;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFileSubType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineFileRepository extends JpaRepository<MachineFile,Long> {

    List<MachineFile> getMachineFilesByMachineAndSubType(Machine machine, @NotNull MachineFileSubType subType);

}
