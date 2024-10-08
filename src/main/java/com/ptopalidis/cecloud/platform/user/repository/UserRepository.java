package com.ptopalidis.cecloud.platform.user.repository;

import com.ptopalidis.cecloud.platform.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
}
