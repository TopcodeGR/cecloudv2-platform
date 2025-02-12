package com.ptopalidis.cecloud.platform.ui.repository;

import com.ptopalidis.cecloud.platform.ui.domain.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FunctionalityRepository extends JpaRepository<Functionality, Long> {

    @Query(value="select f from Functionality f where f.enabled = true")
    List<Functionality> findAllEnabled();
}
