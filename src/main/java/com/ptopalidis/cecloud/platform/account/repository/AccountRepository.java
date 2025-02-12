package com.ptopalidis.cecloud.platform.account.repository;

import com.ptopalidis.cecloud.platform.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUserid(String userid);

}
