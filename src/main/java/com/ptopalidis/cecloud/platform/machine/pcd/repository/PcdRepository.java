package com.ptopalidis.cecloud.platform.machine.pcd.repository;

import com.ptopalidis.cecloud.platform.machine.pcd.domain.Pcd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PcdRepository extends JpaRepository<Pcd, Long>{

}

