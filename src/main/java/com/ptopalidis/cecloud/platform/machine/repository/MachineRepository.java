package com.ptopalidis.cecloud.platform.machine.repository;


import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query(value="select m from Machine m INNER JOIN  m.machineuser u WHERE u.id=?1")
    Page<Machine> findAllByUser(Pageable p, Long userid);
}
