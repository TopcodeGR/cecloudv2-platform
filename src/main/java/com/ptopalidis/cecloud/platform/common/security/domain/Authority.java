package com.ptopalidis.cecloud.platform.common.security.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AUTHORITY")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "authority_code")
    @Enumerated(EnumType.STRING)
    private AuthorityCode authorityCode;

    @Column(nullable = false, name = "authority_scope")
    @Enumerated(EnumType.STRING)
    private AuthorityScope authorityScope;

    @Column(nullable = false, name = "authority_domain")
    @Enumerated(EnumType.STRING)
    private AuthorityDomain authorityDomain;

}
