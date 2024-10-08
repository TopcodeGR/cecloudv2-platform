package com.ptopalidis.cecloud.platform.machine.category.repository;

import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineCategoryRepository extends JpaRepository<MachineCategory,Long> {
    Optional<MachineCategory> findMachineCategoryByName(String name);
}
