package com.ptopalidis.cecloud.platform.machine.materialslist.repository;

import com.ptopalidis.cecloud.platform.machine.materialslist.domain.MaterialsList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialsListRepository extends JpaRepository<MaterialsList, Long> {

    Optional<MaterialsList> findBySerialNumberId(Long serialNumberId);
}