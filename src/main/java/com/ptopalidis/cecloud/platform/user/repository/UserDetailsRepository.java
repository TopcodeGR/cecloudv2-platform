package com.ptopalidis.cecloud.platform.user.repository;

import com.ptopalidis.cecloud.platform.user.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {

    //@Query(value="SELECT * FROM user_details ud JOIN users u ON ud.userid = u.id WHERE u.username = ?1;", nativeQuery = true)
    @Query(value="select ud from UserDetails ud INNER JOIN ud.userid u WHERE u.username=?1")
    List<UserDetails> findByUserUsername(String username);

    @Query(value="select ud from UserDetails ud INNER JOIN ud.userid u WHERE u.id=?1")
    List<UserDetails> findByUserId(Long userid);

}
