package com.ptopalidis.cecloud.platform.machine.doc.repository;

import com.ptopalidis.cecloud.platform.machine.doc.domain.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocRepository extends JpaRepository<Doc, Long> {

    Optional<Doc> findBySerialNumberId(Long serialNumberId);
}

